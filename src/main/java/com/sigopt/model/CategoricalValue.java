package com.sigopt.model;

import com.sigopt.net.APIObject;

public class CategoricalValue extends APIObject {
    String name;

    public CategoricalValue(String name) {
        this.name = name;
    }
}
