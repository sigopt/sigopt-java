package com.sigopt;

import com.sigopt.exception.APIException;
import com.sigopt.model.Experiment;
import com.sigopt.model.Observation;

public class Main {
    public static void main(String args[]) throws APIException {
        Sigopt.userToken = "sample_user_token";
        Sigopt.clientToken = "sample_client_token";

        String experimentId = "1";
        Experiment experiment = Experiment.retrieve(experimentId).call();
        System.out.println(experiment);
    }
}
