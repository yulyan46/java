package utils;

import client.ClusterInfo;
import com.google.gson.reflect.TypeToken;
import node.NodeInfo;
import service.ServiceInfo;

import java.lang.reflect.Type;

/**
 * Created by iulian on 06.01.2017.
 */
public class AppConstants {
    public static final Type NODE_INFO_TYPE = new TypeToken<NodeInfo>() {
    }.getType();
    public static final Type SERVICE_INFO_TYPE = new TypeToken<ServiceInfo>() {
    }.getType();
    public static final Type CLUSTER_INFO_TYPE = new TypeToken<ClusterInfo>() {
    }.getType();
    public static final String SERVICE_NAME_SUM_DOUBLE = "Sum double";
    public static final String SERVICE_NAME_SUM_INT = "Sum integers";
    public static final String SERVICE_DESCRIPTION_SUM_DOUBLE = "input: double[], output: double";
    public static final String SERVICE_DESCRIPTION_SUM_INT = "input: int[], output: int";

    public static final String LABEL_NODE_INPUT = "Node name: ";
    public static final String LABEL_SERVICE_INPUT = "Service name: ";
    public static final String LABEL_SERVICE_PARAMS_INPUT = "Service input parameters: ";
    public static final String LABEL_SERVICE_RESULT = "Service result: ";
    public static final String LABEL_NODE_PORT = "Node port: ";

    public static final String LABEL_NO_RESULT = "No result...";
    public static final String LABEL_NO_DESCRIPTION = "No description...";

    public static final String MESSAGE_GET_CLUSTER_INFO = "GET_CLUSTER_INFO:";
    public static final String MESSAGE_EXECUTE_SERVICE = "EXECUTE_SERVICE:";
}
