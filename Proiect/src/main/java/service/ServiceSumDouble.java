package service;

import utils.AppConstants;

/**
 * Created by iulian on 05.01.2017.
 */
public class ServiceSumDouble extends Service {

    public ServiceSumDouble(Object... inputParams) {
        serviceInfo = new ServiceInfo(inputParams);
        serviceInfo.setName(AppConstants.SERVICE_NAME_SUM_DOUBLE);
        serviceInfo.setDescription(AppConstants.SERVICE_DESCRIPTION_SUM_DOUBLE);
        StringBuilder sb = new StringBuilder();
        sb.append(serviceInfo.getName().hashCode());
        sb.append(serviceInfo.getDescription().hashCode());
        serviceInfo.setUid(sb.toString());
    }

    @Override
    public void run() {
        Double result = 0.0;
        for (Object d : serviceInfo.getInputParameters()) {
            result += (Double) d;
        }
        serviceInfo.setReturnParameter(result);
    }

    @Override
    public String toString() {
        return "ServiceSumDouble{" + serviceInfo.toString() + "}";
    }
}
