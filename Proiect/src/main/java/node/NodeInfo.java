package node;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by iulian on 05.01.2017.
 */
public class NodeInfo {
    private String name;
    private Long time;
    private Long startTime;
    private Integer idleCounter;
    private Integer heartBeatDuration;
    private Integer idleLimit;
    private Integer clientPort;

    public NodeInfo(String name, Integer clientPort) {
        this.name = name;
        heartBeatDuration = 2000;
        time = 0l;
        idleCounter = 0;
        idleLimit = 3;
        startTime = System.currentTimeMillis();
        this.clientPort = clientPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getIdleCounter() {
        return idleCounter;
    }

    public void setIdleCounter(Integer idleCounter) {
        this.idleCounter = idleCounter;
    }

    public Integer getHeartBeatDuration() {
        return heartBeatDuration;
    }

    public void setHeartBeatDuration(Integer heartBeatDuration) {
        this.heartBeatDuration = heartBeatDuration;
    }

    public Integer getIdleLimit() {
        return idleLimit;
    }

    public void setIdleLimit(Integer idleLimit) {
        this.idleLimit = idleLimit;
    }

    public Integer getClientPort() {
        return clientPort;
    }

    public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }

    public Boolean faultyCheck() {
        if (idleCounter == idleLimit) {
            System.out.println("\tNode _" + name + "_ is being removed...");
            return true;
        } else {
            idleCounter++;
            return false;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((NodeInfo) obj).getName());
    }

    @Override
    public String toString() {
        return "[" + name + "-" + clientPort + "]" + ": idle = " + idleCounter + ", time = " +
                new SimpleDateFormat("mm:ss").format(new Date(time - startTime));
    }
}
