package src.main.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class functionBTest {

    @Test
    void normalInputs() {
        int weekOfYear = 2301;
        int capLabor = 1000;
        int capGrape = 1000;
        double prcRose = 23.5;
        double prcNoir = 13.5;

        functionB test = new functionB();
        test.optimize(weekOfYear, capLabor, capGrape, prcRose, prcNoir);

        assertEquals(test.getOptimizedRose(), 166);
        assertEquals(test.getOptimizedNoir(), 1);
        assertEquals(test.getOptimizedTotal(), 167);
        assertEquals(test.getOptimizedRevenue(), 3914);
        assertEquals(test.getLaborSurplus(), 158);
        assertEquals(test.getGrapeSurplus(), 0);
    }

    @Test
    void exceedCap() {
        int weekOfYear = 2301;
        int capLabor = 100000;
        int capGrape = 3000000;
        double prcRose = 30;
        double prcNoir = 21;

        functionB test = new functionB();
        test.optimize(weekOfYear, capLabor, capGrape, prcRose, prcNoir);

        assertEquals(test.getOptimizedRose(), 20000);
        assertEquals(test.getOptimizedNoir(), 0);
        assertEquals(test.getOptimizedTotal(), 20000);
        assertEquals(test.getOptimizedRevenue(), 600000);
        assertEquals(test.getLaborSurplus(), 0);
        assertEquals(test.getGrapeSurplus(), 2880000);
    }

}