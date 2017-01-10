package service;

/**
 * Created by iulian on 06.01.2017.
 */
public class ServiceInfo {
    //uid, name, version, input parameters, return parameters.
    private String uid;
    private String name;
    private String version;
    private String description;
    private Object[] inputParameters;
    private Object returnParameter;

    public ServiceInfo(Object[] inputParameters) {
        version = Long.toString(System.currentTimeMillis());
        this.inputParameters = inputParameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object[] getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(Object[] inputParameters) {
        this.inputParameters = inputParameters;
    }

    public Object getReturnParameter() {
        return returnParameter;
    }

    public void setReturnParameter(Object returnParameter) {
        this.returnParameter = returnParameter;
    }

    @Override
    public String toString() {
        return name + ", " + description;
    }
}
