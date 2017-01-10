package client;

import java.util.List;

/**
 * Created by iulian on 07.01.2017.
 */
public class ClusterInfo {
    private List<SimpleNode> nodes;
    private List<SimpleService> services;

    public List<SimpleNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<SimpleNode> nodes) {
        this.nodes = nodes;
    }

    public List<SimpleService> getServices() {
        return services;
    }

    public void setServices(List<SimpleService> services) {
        this.services = services;
    }
}
