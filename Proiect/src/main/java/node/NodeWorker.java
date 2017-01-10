package node;

import client.ClusterInfo;
import client.SimpleNode;
import client.SimpleService;
import service.Service;
import service.ServiceSumDouble;
import service.ServiceSumInt;
import utils.AppConstants;
import utils.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NodeWorker extends Thread {
    private ConcurrentHashMap<String, NodeInfo> clusterNodes;
    private HashMap<String, Service> clusterServices;
    private InetAddress group;
    private int port;
    private NodeInfo nodeInfo;
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));

    public NodeWorker(String name, String clientPort) {
        nodeInfo = new NodeInfo(name, Integer.valueOf(clientPort));
        clusterNodes = new ConcurrentHashMap<String, NodeInfo>();
        clusterServices = new HashMap<String, Service>();
        clusterServices.put(AppConstants.SERVICE_NAME_SUM_DOUBLE, new ServiceSumDouble());
        clusterServices.put(AppConstants.SERVICE_NAME_SUM_INT, new ServiceSumInt());
        port = 4446;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public void run() {
        sendHeartBeat();
        listenClientRequests();
        receiveHeartBeat();
    }

    private void sendHeartBeat() {
        new SendHeartBeat().start();
    }

    private void receiveHeartBeat() {
        MulticastSocket socket = null;
        try {
            group = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Starting node " + nodeInfo.getName());
            socket = new MulticastSocket(port);
            socket.joinGroup(group);
            byte[] buff = null;
            buff = new byte[256];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            while (true) {
                try {
                    socket.receive(packet);
                    String s = new String(packet.getData(), packet.getOffset(),
                            packet.getLength());
                    NodeInfo node = (NodeInfo) JsonParser.fromJsonString(s, AppConstants.NODE_INFO_TYPE);
                    if (clusterNodes.containsKey(node.getName())) {
                        NodeInfo existentNode = clusterNodes.get(node.getName());
                        existentNode.setTime(node.getTime());
                        existentNode.setIdleCounter(0);
                    } else {
                        clusterNodes.put(node.getName(), node);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.leaveGroup(group);
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void listenClientRequests() {
        new ClientRequestListener().start();
    }

    private class SendHeartBeat extends Thread {
        public void run() {
            byte[] buf = null;
            DatagramPacket packet = null;

            DatagramSocket socket = null;
            try {
                socket = new DatagramSocket();
                while (true) {
                    nodeInfo.setTime(System.currentTimeMillis());
                    buf = JsonParser.toJsonString(nodeInfo).getBytes();
                    try {
                        packet = new DatagramPacket(buf, buf.length, group,
                                port);
                        socket.send(packet);
                        Thread.sleep(nodeInfo.getHeartBeatDuration());
                        clearFaultyNodes();
                        showClusterTopology();
                        showClusterServices();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SocketException e1) {
                e1.printStackTrace();
            } finally {
                socket.close();
            }
        }
    }

    private class ClientRequestListener extends Thread {
        public void run() {
            ServerSocket socket = null;
            while (true) {
                try {
                    socket = new ServerSocket(nodeInfo.getClientPort());
                    Socket clientSocket = socket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String inputLine;
                    System.out.println("Request from client");
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                        String response = processClientRequest(inputLine);
                        out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * computes the json string with requested information
     *
     * @param inputLine
     * @return
     */
    private String processClientRequest(String inputLine) {
        if (inputLine.startsWith(AppConstants.MESSAGE_GET_CLUSTER_INFO)) {
            ClusterInfo clusterInfo = new ClusterInfo();
            List<SimpleNode> nodes = new ArrayList<SimpleNode>();
            List<SimpleService> services = new ArrayList<SimpleService>();
            clusterNodes.forEach((k, v) -> nodes.add(new SimpleNode(k, v.getClientPort())));
            clusterServices.forEach((k, v) -> services.add(new SimpleService(k, v.getServiceInfo().getUid(),
                    v.getServiceInfo().getDescription())));
            clusterInfo.setNodes(nodes);
            clusterInfo.setServices(services);
            return JsonParser.toJsonString(clusterInfo);
        }
        return null;
    }

    public void clearFaultyNodes() {
        clusterNodes.forEach((k, v) -> {
            if (v.faultyCheck()) clusterNodes.remove(k);
        });
    }

    public void showClusterTopology() {
        clusterNodes.forEach((k, v) -> System.out.println(v.toString()));
        System.out.println("*********");
    }

    public void showClusterServices() {
        System.out.println("Services:");
        clusterServices.forEach((k, v) -> System.out.println(v.toString()));
        System.out.println("********************");
    }

    public static void main(String[] args) {
        new NodeWorker(args[0], args[1]).start();
    }
}
