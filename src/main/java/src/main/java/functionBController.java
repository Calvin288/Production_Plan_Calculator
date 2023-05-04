package src.main.java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import javax.swing.*;

/**
 * <p>The Weekly Review that concerns Revenue program implements an
 * application that finds the optimal combination of Rose and Pinot Noir.
 * to maximise revenue
 * constraints.</p>
 *
 * @author  Calvin Wiogo
 * @version 1.0
 * @since   2023-04-19
 */

public class functionBController {

    @FXML
    private TextField Cap_Grape;

    @FXML
    private TextField Cap_Labor;

    @FXML
    private Line HLine2;

    @FXML
    private Line HLine3;

    @FXML
    private Line HLine4;

    @FXML
    private Line HLine_1;

    @FXML
    private TextField Prc_Noir;

    @FXML
    private TextField Prc_Rose;

    @FXML
    private Line VLine1;

    @FXML
    private Line VLine2;

    @FXML
    private TextField WeekOfYear;

    @FXML
    private Button button_exit1;

    @FXML
    private Button button_run1;

    @FXML
    private Label data_input_caption1;

    @FXML
    private Label data_input_caption2;

    @FXML
    private Label data_input_caption2a;

    @FXML
    private Label data_input_caption2b;

    @FXML
    private Label data_input_caption3;

    @FXML
    private Label data_input_caption3a;

    @FXML
    private Label data_input_caption3b;

    @FXML
    private Label data_input_heading1;

    @FXML
    private Label form_heading1;

    @FXML
    private AnchorPane label_col_heading2;

    @FXML
    private Label label_sysmsg1;

    @FXML
    private Label opt_r_caption1;

    @FXML
    private Label opt_r_caption1a;

    @FXML
    private Label opt_r_caption1b;

    @FXML
    private Label opt_r_caption1c;

    @FXML
    private Label opt_r_caption2;

    @FXML
    private Label opt_r_caption3;

    @FXML
    private Label opt_r_caption4;

    @FXML
    private Label opt_result_heading1;

    @FXML
    private TextField or_Grape_Surplus;

    @FXML
    private TextField or_Labor_Surplus;

    @FXML
    private TextField or_Prod_Vol_Noir;

    @FXML
    private TextField or_Prod_Vol_Rose;

    @FXML
    private TextField or_Prod_Vol_Total;

    @FXML
    private TextField or_Total_Revenue;

    @FXML
    private Spinner<String> or_scroll_text1;

    @FXML
    private Line vLine3;

    public void setUp(TextField weekOfYear, TextField capGrape, TextField capLabour, TextField prcNoir, TextField prcRose) {
        WeekOfYear = weekOfYear;
        Cap_Grape = capGrape;
        Cap_Labor = capLabour;
        Prc_Noir = prcNoir;
        Prc_Rose = prcRose;
        or_scroll_text1 = new Spinner<String>();
        or_Prod_Vol_Total = new TextField();
        or_Prod_Vol_Noir = new TextField();
        or_Prod_Vol_Rose = new TextField();
        or_Total_Revenue = new TextField();
        or_Grape_Surplus = new TextField();
        or_Labor_Surplus = new TextField();
    }

    public String getGrapeSurplus()
    {
        return or_Grape_Surplus.getText();
    }

    public String getLaborSurplus()
    {
        return or_Labor_Surplus.getText();
    }

    public String getOptimalNoirProduction()
    {
        return or_Prod_Vol_Noir.getText();
    }

    public String getOptimalRoseProduction()
    {
        return or_Prod_Vol_Rose.getText();
    }

    public String getOptimalTotalProduction()
    {
        return or_Prod_Vol_Total.getText();
    }

    public String getTotalRevenue()
    {
        return or_Total_Revenue.getText();
    }

    public void initialize() {
        WeekOfYear.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (    (Integer.parseInt(newValue) < 10 && Integer.parseInt(newValue)!=2) ||
                            ((Integer.parseInt(newValue) >=10 && Integer.parseInt(newValue) < 100) && Integer.parseInt(newValue)!=23) ||
                            ((Integer.parseInt(newValue) >=100 && Integer.parseInt(newValue) < 1000) && (Integer.parseInt(newValue)!=230 && Integer.parseInt(newValue)!=231)) ||
                            (Integer.parseInt(newValue) >=1000) && (Integer.parseInt(newValue)<2301 || Integer.parseInt(newValue)>2315)
                    ) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than 2300 and less than 2316");
                        alert.showAndWait();
                        WeekOfYear.setText("");
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter the number greater than 2300 and less than 2316");
                    alert.showAndWait();
                    WeekOfYear.setText("");
                }
            }
        });

        Cap_Grape.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    Integer.parseInt(newValue);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a valid number");
                    alert.showAndWait();
                    Cap_Grape.setText("");
                }
            }
        });

        Cap_Labor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    Integer.parseInt(newValue);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a valid number");
                    alert.showAndWait();
                    Cap_Labor.setText("");
                }
            }
        });

        Prc_Rose.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    Float.parseFloat(newValue);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a valid number");
                    alert.showAndWait();
                    Prc_Rose.setText("");
                }
            }
        });

        Prc_Noir.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    Float.parseFloat(newValue);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a valid number");
                    alert.showAndWait();
                    Prc_Noir.setText("");
                }
            }
        });
    }

    @FXML
    void getDefaultValue(InputMethodEvent event) {

    }

    @FXML
    void toclick(ActionEvent event) {
        int optimalRevenue = 0;
        int optimalRose = 0;
        int optimalNoir = 0;
        double prcRose = Math.round(Float.parseFloat(Prc_Rose.textProperty().getValue())*100.0) /100.0;
        double prcNoir = Math.round(Float.parseFloat(Prc_Noir.textProperty().getValue())*100.0) /100.0;
        int capLabour =  Integer.parseInt(Cap_Labor.textProperty().getValue());
        int capGrape = Integer.parseInt(Cap_Grape.textProperty().getValue());
        int maxRFromLabor = capLabour/5;
        int maxRFromGrape = capGrape/6;
        int maxR = maxRFromLabor > maxRFromGrape ? maxRFromGrape : maxRFromLabor;
        int maxPFromLabor = capLabour/12;
        int maxPFromGrape = capGrape/4;
        int maxP = maxPFromLabor > maxPFromGrape ? maxPFromGrape : maxPFromLabor;

        for(int optimalR = 0; optimalR <= maxR; optimalR++)
        {
            for(int optimalP = 0; optimalP <= maxP; optimalP++)
            {
                if(optimalR * 5 + optimalP * 12 > capLabour || 6 * optimalR + 4 * optimalP > capGrape)
                    break;
                if(optimalR * prcRose + optimalP * prcNoir > optimalRevenue){
                    optimalRevenue = (int) (optimalR * prcRose + optimalP * prcNoir);
                    optimalRose = optimalR;
                    optimalNoir = optimalP;
                }
            }
        }

        int surLabor = capLabour - (optimalRose * 5 + optimalNoir * 12);
        if(surLabor>0 && surLabor <5)
            surLabor = 0;
        int surGrape = capGrape - (optimalRose * 6 + optimalNoir * 4);
        if(surGrape >0 && surGrape <4)
            surGrape = 0;


        String outputPrompt = "";
        if((optimalRose + optimalNoir) > 5000)
            outputPrompt += "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!\r";
        if((float) surGrape/capGrape >= 0.1)
            outputPrompt += "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!\r";
        if(surLabor < 0)
            outputPrompt += "Er1a: Program bug is occurred, Labor Consumption cannot greater than its capacity!\r";
        if(surGrape < 0)
            outputPrompt += "Er1b: Program bug is occurred, Grape Consumption cannot greater than its capacity!\r";

        or_scroll_text1.setPromptText(outputPrompt);
        or_Prod_Vol_Rose.setText(String.valueOf(optimalRose));
        or_Prod_Vol_Noir.setText(String.valueOf(optimalNoir));
        or_Prod_Vol_Total.setText(String.valueOf(optimalRose + optimalNoir));
        or_Total_Revenue.setText(String.valueOf(optimalRevenue));
        or_Labor_Surplus.setText(String.valueOf(surLabor));
        or_Grape_Surplus.setText(String.valueOf(surGrape));
    }


    @FXML
    void toexit(ActionEvent event) {
        Main.stage.setScene(Main.scene);
    }


}
