package service;

import utils.AppConstants;

/**
 * Created by iulian on 05.01.2017.
 */
public class ServiceSumInt extends Service {

    public ServiceSumInt(Object... inputParams) {
        serviceInfo = new ServiceInfo(inputParams);
        serviceInfo.setName(AppConstants.SERVICE_NAME_SUM_INT);
        serviceInfo.setDescription(AppConstants.SERVICE_DESCRIPTION_SUM_INT);
        StringBuilder sb = new StringBuilder();
        sb.append(serviceInfo.getName().hashCode());
        sb.append(serviceInfo.getDescription().hashCode());
        serviceInfo.setUid(sb.toString());
    }

    @Override
    public void run() {
        Integer result = 0;
        for (Object d : serviceInfo.getInputParameters()) {
            result += (Integer) d;
        }
        serviceInfo.setReturnParameter(result);
        System.out.println(serviceInfo.toString());
    }

    @Override
    public String toString() {
        return "ServiceSumInt{" + serviceInfo.toString() + "}";
    }
}
