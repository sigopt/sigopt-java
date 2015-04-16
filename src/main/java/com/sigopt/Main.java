package com.sigopt;

import com.sigopt.exception.APIException;
import com.sigopt.model.Experiment;
import com.sigopt.model.Observation;

public class Main {

    public static void main(String args[]) throws APIException {
        Sigopt.userToken = "HTKWBSJQLLTAYHVMIXNLUMVGYEVNRELCGBJKJWDVJETVYNZX";
        Sigopt.clientToken = "KWQOLYOGALCIHCCZGDHBMIJAINBEPOICGBZVPWEHZBIHONIQ";

        String experimentId = "438";
        Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
        Observation observation = experiment.bestObservation().call();
        System.out.println(observation);
    }
}
