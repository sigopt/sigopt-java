package com.sigopt;

import com.sigopt.exception.SigoptException;
import com.sigopt.model.Experiment;
import com.sigopt.model.Observation;

public class Main {
    public static void main(String args[]) throws SigoptException {
        Sigopt.clientToken = "sample_client_token";

        String experimentId = "1";
        Experiment experiment = Experiment.fetch(experimentId).call();
        System.out.println(experiment);
    }
}
