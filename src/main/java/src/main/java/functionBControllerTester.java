package src.main.java;


import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Platform;

class functionBControllerTester {
    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    functionBController fBController = new functionBController();

    @Test
    public void normalInputs() throws Exception {

        TextField weekOfYear = new TextField();
        weekOfYear.setText("2301");
        TextField capGrape = new TextField();
        capGrape.setText("1000");
        TextField capLabor = new TextField();
        capLabor.setText("1000");
        TextField prcNoir = new TextField();
        prcNoir.setText("13.5");
        TextField prcRose = new TextField();
        prcRose.setText("23.5");

        fBController.setUp(weekOfYear, capGrape, capLabor, prcNoir, prcRose);
        fBController.toclick(new ActionEvent());

        assertAll(
                () -> assertEquals(fBController.getOptimalRoseProduction(), "166"),
                () -> assertEquals(fBController.getOptimalNoirProduction(), "1"),
                () -> assertEquals(fBController.getOptimalTotalProduction(), "167"),
                () -> assertEquals(fBController.getLaborSurplus(), "158"),
                () -> assertEquals(fBController.getGrapeSurplus(), "0"),
                () -> assertEquals(fBController.getTotalRevenue(), "3914")
        );
    }
    @Test
    public void exceedCap() throws Exception {

        TextField weekOfYear = new TextField();
        weekOfYear.setText("2315");
        TextField capGrape = new TextField();
        capGrape.setText("100000");
        TextField capLabor = new TextField();
        capLabor.setText("3000000");
        TextField prcNoir = new TextField();
        prcNoir.setText("21");
        TextField prcRose = new TextField();
        prcRose.setText("30");

        fBController.setUp(weekOfYear, capGrape, capLabor, prcNoir, prcRose);
        fBController.toclick(new ActionEvent());

        assertAll(
                () -> assertEquals(fBController.getOptimalRoseProduction(), "0"),
                () -> assertEquals(fBController.getOptimalNoirProduction(), "25000"),
                () -> assertEquals(fBController.getOptimalTotalProduction(), "25000"),
                () -> assertEquals(fBController.getLaborSurplus(), "2700000"),
                () -> assertEquals(fBController.getGrapeSurplus(), "0"),
                () -> assertEquals(fBController.getTotalRevenue(), "525000")
        );
    }
}