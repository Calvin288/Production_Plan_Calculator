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
        int capLabor = 12000;
        int capGrape = 5000;
        double prcRose = 23.5;
        double prcNoir = 12.5;
        int BkoRose = 180;
        int BkoNoir = 380;

        functionC test = new functionC();
        test.optimize(BkoRose,BkoNoir, capGrape, capLabor, weekOfYear,prcNoir,prcRose);

        assertAll(
                () -> assertEquals(580, test.getOptimizedRose()),
                () -> assertEquals(380, test.getOptimizedNoir()),
                () -> assertEquals(960, test.getOptimizedTotal()),
                () -> assertEquals(18380, test.getOptimizedRevenue()),
                () -> assertEquals(true, test.getBackorderFulfilled())
        );
    }


    @Test
    public void abnormalPrice() throws Exception {
        int weekOfYear = 2301;
        int capLabor = 12000;
        int capGrape = 5000;
        double prcRose = 20;
        double prcNoir = 10;
        int BkoRose = 180;
        int BkoNoir = 380;

        functionC test = new functionC();
        test.optimize(BkoRose,BkoNoir, capGrape, capLabor, weekOfYear,prcNoir,prcRose);

        assertAll(
                () -> assertEquals(580, test.getOptimizedRose()),
                () -> assertEquals(380, test.getOptimizedNoir()),
                () -> assertEquals(960, test.getOptimizedTotal()),
                () -> assertEquals(15400, test.getOptimizedRevenue()),
                () -> assertEquals(true, test.getBackorderFulfilled())
        );
    }

    @Test
    public void tooMuchCapacity() throws Exception {
        int weekOfYear = 2301;
        int capLabor = 72000;
        int capGrape = 45000;
        double prcRose = 12.5;
        double prcNoir = 23.5;
        int BkoRose = 3500;
        int BkoNoir = 2800;

        functionC test = new functionC();
        test.optimize(BkoRose,BkoNoir, capGrape, capLabor, weekOfYear,prcNoir,prcRose);

        assertAll(
                () -> assertEquals(4845, test.getOptimizedRose()),
                () -> assertEquals(3981, test.getOptimizedNoir()),
                () -> assertEquals(8826, test.getOptimizedTotal()),
                () -> assertEquals(154116, test.getOptimizedRevenue()),
                () -> assertEquals(true, test.getBackorderFulfilled())
        );
    }

    @Test
    public void backOrderNotFulfilled() throws Exception {
        int weekOfYear = 2301;
        int capLabor = 12000;
        int capGrape = 5000;
        double prcRose = 12.5;
        double prcNoir = 23.5;
        int BkoRose = 3500;
        int BkoNoir = 2800;

        functionC test = new functionC();
        test.optimize(BkoRose,BkoNoir, capGrape, capLabor, weekOfYear,prcNoir,prcRose);

        assertAll(
                () -> assertEquals(230, test.getOptimizedRose()),
                () -> assertEquals(904, test.getOptimizedNoir()),
                () -> assertEquals(1134, test.getOptimizedTotal()),
                () -> assertEquals(24119, test.getOptimizedRevenue()),
                () -> assertEquals(false, test.getBackorderFulfilled())
        );
    }

    @Test
    public void doubleDPPrice() throws Exception {
        int weekOfYear = 2301;
        int capLabor = 45000;
        int capGrape = 450000;
        double prcRose = 12.25;
        double prcNoir = 23.19;
        int BkoRose = 700;
        int BkoNoir = 200;

        functionC test = new functionC();
        test.optimize(BkoRose,BkoNoir, capGrape, capLabor, weekOfYear,prcNoir,prcRose);

        assertAll(
                () -> assertEquals(8520, test.getOptimizedRose()),
                () -> assertEquals(200, test.getOptimizedNoir()),
                () -> assertEquals(8720, test.getOptimizedTotal()),
                () -> assertEquals(109008, test.getOptimizedRevenue()),
                () -> assertEquals(true, test.getBackorderFulfilled())
        );
    }
}