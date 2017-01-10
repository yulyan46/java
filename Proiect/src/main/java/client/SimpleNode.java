package client;

/**
 * Created by iulian on 07.01.2017.
 */

public class SimpleNode {
    private String name;
    private Integer port;

    public SimpleNode(String name, Integer port) {
        this.name = name;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return name;
    }
}
