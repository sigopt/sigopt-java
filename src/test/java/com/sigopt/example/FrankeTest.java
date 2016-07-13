package com.sigopt.example;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FrankeTest {
  @Test
  public void evaluate() throws Exception {
    assertTrue(Franke.evaluate(0.0, 0.0) > 0);
    assertTrue(Franke.evaluate(1.0, 0.0) > 0);
    assertTrue(Franke.evaluate(0.0, 1.0) > 0);
    assertTrue(Franke.evaluate(1.0, 1.0) > 0);
  }
}
