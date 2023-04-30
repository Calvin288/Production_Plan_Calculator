package src.main.java;


import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Platform;

class functionAControllerTester {
    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    functionAController fAController = new functionAController();

    @Test
    public void normalInputs() throws Exception {

        TextField num_Week = new TextField();
        num_Week.setText("1");
        TextField capLabor = new TextField();
        capLabor.setText("28500");
        TextField capGrape = new TextField();
        capGrape.setText("28500");
        TextField prcRose = new TextField();
        prcRose.setText("12.5");
        TextField prcNoir = new TextField();
        prcNoir.setText("23.5");
        TextField fixedCosts = new TextField();
        fixedCosts.setText("50000");

        fAController.setUp(num_Week, capGrape, capLabor, prcNoir, prcRose, fixedCosts);
        fAController.toclick(new ActionEvent());
        assertEquals(fAController.getProdVolRose(), "4384");
        assertEquals(fAController.getProdVolNoir(), "548");
        assertEquals(fAController.getProdVolTotal(), "4932");
        assertEquals(fAController.getGrossProfit(), "8302");
        assertEquals(fAController.getProfitMargin(), "11.8");
    }
    @Test
    public void exceedCap() throws Exception {

        TextField num_Week = new TextField();
        num_Week.setText("12");
        TextField capLabor = new TextField();
        capLabor.setText("2850000");
        TextField capGrape = new TextField();
        capGrape.setText("50000");
        TextField prcRose = new TextField();
        prcRose.setText("12.5");
        TextField prcNoir = new TextField();
        prcNoir.setText("23.5");
        TextField fixedCosts = new TextField();
        fixedCosts.setText("5000000");

        fAController.setUp(num_Week, capGrape, capLabor, prcNoir, prcRose, fixedCosts);
        fAController.toclick(new ActionEvent());
        assertEquals(fAController.getProdVolRose(), "0");
        assertEquals(fAController.getProdVolNoir(), "150000");
        assertEquals(fAController.getProdVolTotal(), "150000");
        assertEquals(fAController.getGrossProfit(), "-2148000");
        assertEquals(fAController.getProfitMargin(), "-59.6");
    }
    /*@Test
    public void alert1() throws Exception {
        TextField num_Week = new TextField();
        num_Week.setText("12");
        verifyThat("OK", NodeMatchers.isVisible());
    }*/
}