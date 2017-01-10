package client;

import utils.AppConstants;
import utils.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by iulian on 07.01.LABEL_HEIGHT17.
 */
public class ClientWorker extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1659847655864947450L;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 400;
    private final int TEXT_FIELD_HEIGHT = 40;
    private final int TEXT_FIELD_WIDTH = 400;
    private final int LABEL_HEIGHT = 40;
    private final int LABEL_WIDTH = 200;
    private JButton buttonExecuteService;
    private JButton buttonGetClusterInfo;
    private JComboBox<SimpleNode> comboNodes;
    private JComboBox<SimpleService> comboServices;
    private JLabel labelNodePort;
    private JLabel labelNodes;
    private JLabel labelServices;
    private JLabel labelServiceParams;
    private JLabel labelServiceResult;
    private JTextField fieldNodePortInput;
    private JTextField fieldServiceParamsInput;
    private JTextField fieldServiceResult;
    private JTextField fieldServiceDescription;

    public ClientWorker() {
        initComponents();
        setButtons();
        setRequestComponentsStatus(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setRequestComponentsStatus(Boolean status) {
        buttonExecuteService.setEnabled(status);
        comboNodes.setEnabled(status);
        comboServices.setEnabled(status);
        labelNodes.setEnabled(status);
        labelServices.setEnabled(status);
        labelServiceParams.setEnabled(status);
        labelServiceResult.setEnabled(status);
        fieldServiceParamsInput.setEnabled(status);
        fieldServiceResult.setEnabled(status);
        fieldServiceDescription.setEnabled(status);
    }

    private void initComponents() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        this.setFont(font);

        buttonExecuteService = new JButton();
        buttonGetClusterInfo = new JButton();

        labelNodePort = createLabel(AppConstants.LABEL_NODE_PORT, font);
        labelNodes = createLabel(AppConstants.LABEL_NODE_INPUT, font);
        labelServices = createLabel(AppConstants.LABEL_SERVICE_INPUT, font);
        labelServiceParams = createLabel(AppConstants.LABEL_SERVICE_PARAMS_INPUT, font);
        labelServiceResult = createLabel(AppConstants.LABEL_SERVICE_RESULT, font);

        fieldNodePortInput = createTextField(font);
        fieldServiceParamsInput = createTextField(font);
        fieldServiceResult = createReadOnlyTextField(font);
        fieldServiceResult.setText(AppConstants.LABEL_NO_RESULT);
        fieldServiceDescription = createReadOnlyTextField(font);
        fieldServiceDescription.setText(AppConstants.LABEL_NO_DESCRIPTION);

        comboNodes = new JComboBox<SimpleNode>();
        comboServices = new JComboBox<SimpleService>();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 3, 20, 20));
        mainPanel.add(labelNodePort);
        mainPanel.add(fieldNodePortInput);
        mainPanel.add(buttonGetClusterInfo);

        mainPanel.add(labelNodes);
        mainPanel.add(comboNodes);
        mainPanel.add(new JLabel());

        mainPanel.add(labelServices);
        mainPanel.add(comboServices);
        mainPanel.add(fieldServiceDescription);

        mainPanel.add(labelServiceParams);
        mainPanel.add(fieldServiceParamsInput);
        mainPanel.add(new JLabel());

        mainPanel.add(labelServiceResult);
        mainPanel.add(fieldServiceResult);
        mainPanel.add(buttonExecuteService);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.white);

        this.add(mainPanel);
        this.setVisible(true);
    }

    private void setButtons() {
        buttonGetClusterInfo.setText("GET CLUSTER");
        buttonGetClusterInfo.setBackground(Color.GRAY);
        buttonGetClusterInfo.setForeground(Color.white);
        buttonExecuteService.setText("EXECUTE SERVICE");
        buttonExecuteService.setBackground(Color.GRAY);
        buttonExecuteService.setForeground(Color.white);

        buttonGetClusterInfo.addActionListener(e -> sendRequestToNode());
    }

    private void sendRequestToNode() {
        String response = "Request received.";
        Integer port = null;
        try {
            port = Integer.parseInt(fieldNodePortInput.getText());
            Socket socket = new Socket("127.0.0.1", port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(AppConstants.MESSAGE_GET_CLUSTER_INFO);
            String serverResponse = in.readLine();
            System.out.println(serverResponse);
            ClusterInfo clusterInfo = (ClusterInfo) JsonParser.fromJsonString(serverResponse,
                    AppConstants.CLUSTER_INFO_TYPE);
            populateFields(clusterInfo);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            response = e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            response = e.getMessage();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        showMessageDialog(response);
    }

    private void populateFields(ClusterInfo clusterInfo) {
        setRequestComponentsStatus(true);
        for (SimpleNode node : clusterInfo.getNodes()) {
            comboNodes.addItem(node);
        }
        for (SimpleService service : clusterInfo.getServices()) {
            comboServices.addItem(service);
        }
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField();
        textField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textField.setFont(font);
        return textField;
    }

    private JTextField createReadOnlyTextField(Font font) {
        JTextField textField = new JTextField();
        textField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textField.setFont(font);
        textField.setEditable(false);
        return textField;
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text, JLabel.LEFT);
        label.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        label.setFont(font);
        return label;
    }

    public static void main(String[] args) {
        new ClientWorker();
    }
}
