package service;

/**
 * Created by iulian on 07.01.2017.
 */
public abstract class Service implements Runnable {

    protected ServiceInfo serviceInfo;

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }
}
