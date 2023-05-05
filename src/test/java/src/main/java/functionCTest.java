package src.main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class functionCTest {

    @Test
    public void normalInputs() throws Exception {

    }

    @Test
    void optimize() {
    }

    @Test
    void getOptimizedRose() {
    }

    @Test
    void getOptimizedNoir() {
    }

    @Test
    void getOptimizedTotal() {
    }

    @Test
    void getOptimizedRevenue() {
    }

    @Test
    void getBackorderFulfilled() {
    }

    public void normalInputBackorderFulfilled() throws Exception {

        int weekOfYear = 2301;
        int capGrape = 12000;
        int capLabor = 5000;
        double prcNoir = 12.5;
        double prcRose = 23.5;
        int BkoNoir = 380;
        int BkoRose = 180;

        functionC test = new functionC();
        test.optimize(BkoRose,BkoNoir, capGrape, capLabor, weekOfYear,prcNoir,prcRose);

        assertAll(
                () -> assertEquals(test.getOptimizedRose(), 380),
                () -> assertEquals(test.getOptimizedNoir(), 680),
                () -> assertEquals(test.getOptimizedTotal(), 10060),
                () -> assertEquals(test.getOptimizedRevenue(), 5000),
                () -> assertEquals(test.getBackorderFulfilled(), true)
        );
    }
}