package com.sigopt.example;

public class Franke {
    public Franke() {
    }

    public static Double evaluate(double x, double y) {
        return (
            0.75 * Math.exp(-Math.pow((9 * x - 2), 2.0) / 4.0 - Math.pow((9 * y - 2), 2.0) / 4.0) +
            0.75 * Math.exp(-Math.pow((9 * x + 1), 2.0) / 49.0 - (9 * y + 1) / 10.0) +
            0.5 * Math.exp(-Math.pow((9 * x - 7), 2.0) / 4.0 - Math.pow((9 * y - 3), 2.0) / 4.0) -
            0.2 * Math.exp(-Math.pow((9 * x - 4), 2.0) - Math.pow((9 * y - 7), 2.0))
        );
    }
}
