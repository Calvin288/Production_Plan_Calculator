package src.main.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class functionATest {
    @Test
    public void normalInputs() throws Exception {
        int num_Week = 1;
        int capLabor = 28500;
        int capGrape = 28500;
        double prcRose = 12.5;
        double prcNoir = 23.5;
        int fixedCosts = 50000;

        functionA test = new functionA();
        test.optimize(num_Week, capLabor, capGrape, prcRose, prcNoir, fixedCosts);

        assertEquals(test.getOptimizedRose(), 4384);
        assertEquals(test.getOptimizedNoir(), 548);
        assertEquals(test.getOptimizedTotal(), 4932);
        assertEquals(test.getOptimizedProfit(), 5836);
        assertEquals(test.getOptimizedMargin(), 8.6);
    }

    @Test
    public void abnormalLabor() throws Exception {
        int num_Week = 12;
        int capLabor = 2850000;
        int capGrape = 50000;
        double prcRose = 12.5;
        double prcNoir = 23.5;
        int fixedCosts = 5000000;

        functionA test = new functionA();
        test.optimize(num_Week, capLabor, capGrape, prcRose, prcNoir, fixedCosts);

        assertEquals(test.getOptimizedRose(), 0);
        assertEquals(test.getOptimizedNoir(), 150000);
        assertEquals(test.getOptimizedTotal(), 150000);
        assertEquals(test.getOptimizedProfit(), -2223000);
        assertEquals(test.getOptimizedMargin(), -63.0);
    }

    @Test
    public void abnormalPrice() throws Exception {
        int num_Week = 3;
        int capLabor = 1200;
        int capGrape = 1400;
        double prcRose = 1200;
        double prcNoir = 5;
        int fixedCosts = 10000;

        functionA test = new functionA();
        test.optimize(num_Week, capLabor, capGrape, prcRose, prcNoir, fixedCosts);

        assertEquals(test.getOptimizedRose(), 699);
        assertEquals(test.getOptimizedNoir(), 0);
        assertEquals(test.getOptimizedTotal(), 699);
        assertEquals(test.getOptimizedProfit(), 827347);
        assertEquals(test.getOptimizedMargin(), 98.6);
    }
}