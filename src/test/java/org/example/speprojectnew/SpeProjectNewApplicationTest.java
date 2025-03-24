package org.example.speprojectnew;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpeProjectNewApplicationTest {
    private static SpeProjectNewApplication app;
    @BeforeAll
    static void setUp() {
         app = new SpeProjectNewApplication();

    }
    @Test
    void sqrt() {
        double expected=app.sqrt(25.0);
        double actual=5.0;
        assertEquals(expected,actual);
    }
    @Test
    void fact() {
        long expected=app.fact(4);
        long actual=24;
        assertEquals(expected,actual);
    }
    @Test
    void ln() {
        double expected=app.ln(10.0);
        double actual=2.302585092994046;
        assertEquals(expected,actual);
    }
    @Test
    void power() {
        double expected=app.power(10.0,2.0);
        double actual=100.0;
        assertEquals(expected,actual);
    }
}