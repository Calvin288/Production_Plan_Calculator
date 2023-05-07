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
 *
 * Function B is used to compute the optimal mix of wines (rose and pinot-noir wine)
 * produced during a certain week of year to maximize revenue<br><br>
 *
 * The user have 5 inputs in the unit interface:<br>
 * 1. WeekOfYear (integer) = Harvest Week<br>
 * 2. Cap_Labor (integer) = labor resource planned for the production cycle<br>
 * 3. Cap_Grape (integer) = grape resource planned for the production cycle<br>
 * 4. Prc_Rose (double, x.xx format) = price of rose wine<br>
 * 5. Prc_Noir (double, x.xx format) = price of pinot-noir wine<br><br>
 *
 * The function will output the following:<br>
 * 1. optRose = the optimal amount of rose wine production in litres<br>
 * 2. optNoir = the optimal amount of pinot-noir wine production in litres<br>
 * 3. totalGrapes = the sum of optimal Rose and optimal P-Noir<br>
 * 4. optRevenue = the optimal revenue<br>
 * 5. surGrape = the grape surplus<br>
 * 6. surLabor = the labor surplus<br>
 *
 * @author  Calvin Wiogo
 * @version 1.0
 * @since   2023-04-30
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
                    if(Integer.parseInt(newValue) <= 0)
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than 0");
                        alert.showAndWait();
                        Cap_Grape.setText("");
                    }
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
                    if(Integer.parseInt(newValue) <= 0)
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than 0");
                        alert.showAndWait();
                        Cap_Labor.setText("");
                    }
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
                    if(Double.parseDouble(newValue) <= 0)
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a float greater than 0");
                        alert.showAndWait();
                        Prc_Rose.setText("");
                    }
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
                    if(Double.parseDouble(newValue) <= 0)
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a float greater than 0");
                        alert.showAndWait();
                        Prc_Noir.setText("");
                    }
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

    boolean isPositiveInteger(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i >= 1) {
                return true; //valid
            } else {
                return false; //negative
            }
        } catch (NumberFormatException e) {
            return false; //not an integer
        }
    }

    boolean isPositiveFloat(String s) {
        try {
            float i = Float.parseFloat(s);
            if (i >= 1) {
                //has more than two decimal places?
                if (s.contains(".")) {
                    if (s.substring(s.indexOf(".")).length() > 3) {
                        return false;
                    }
                }
                return true; //otherwise valid
            } else {
                return false; //negative
            }
        } catch (NumberFormatException e) {
            return false; //not a float
        }
    }

    boolean isNumWeekCorrect(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i >= 2301 && i <= 2315) {
                return true; //valid
            } else {
                return false; //out of range
            }
        } catch (NumberFormatException e) {
            return false; //not an integer
        }
    }

    @FXML
    void toclick(ActionEvent event) {
        or_scroll_text1.setPromptText(" ");

        if (Cap_Grape.getText().isEmpty() || Cap_Labor.getText().isEmpty()|| Prc_Noir.getText().isEmpty() || Prc_Rose.getText().isEmpty()) {
            or_scroll_text1.setPromptText("W4: Please fill all the required parameters");
        }

        //check if fields are in correct data type
        else if ( !isPositiveInteger(Cap_Grape.getText()) || !isPositiveInteger(Cap_Labor.getText()) || !isPositiveFloat(Prc_Noir.getText()) || !isPositiveFloat(Prc_Rose.getText())) {
            or_scroll_text1.setPromptText("W5: Please fill the text field with the proper format");
        }

        //check if fields are within valid range
        else if (!isNumWeekCorrect(WeekOfYear.getText())) {
            or_scroll_text1.setPromptText("W6: Please fill the text field with the proper range");
        }
        else {
            int num_week = Integer.parseInt(WeekOfYear.getText());
            int input_capLabor = Integer.parseInt(Cap_Labor.getText());
            int input_capGrape = Integer.parseInt(Cap_Grape.getText());
            double input_prcRose = Double.parseDouble(Prc_Rose.getText());
            double input_prcNoir = Double.parseDouble(Prc_Noir.getText());

            functionB opt = new functionB();
            opt.optimize(num_week, input_capLabor, input_capGrape, input_prcRose, input_prcNoir);

            int optimalRose = opt.getOptimizedRose();
            int optimalNoir = opt.getOptimizedNoir();
            int optimalRevenue = opt.getOptimizedRevenue();
            int surGrape = opt.getGrapeSurplus();
            int surLabor = opt.getLaborSurplus();
            int totalGrapes = opt.getOptimizedTotal();

            String outputPrompt = "";
            if ((optimalRose + optimalNoir) > 5000)
                outputPrompt += "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!\r";
            if ((float) surGrape / input_capGrape >= 0.1)
                outputPrompt += "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!\r";
            if (surLabor < 0)
                outputPrompt += "Er1a: Program bug is occurred, Labor Consumption cannot greater than its capacity!\r";
            if (surGrape < 0)
                outputPrompt += "Er1b: Program bug is occurred, Grape Consumption cannot greater than its capacity!\r";

            or_scroll_text1.setPromptText(outputPrompt);
            or_Prod_Vol_Rose.setText(String.valueOf(optimalRose));
            or_Prod_Vol_Noir.setText(String.valueOf(optimalNoir));
            or_Prod_Vol_Total.setText(String.valueOf(totalGrapes));
            or_Total_Revenue.setText(String.valueOf(optimalRevenue));
            or_Labor_Surplus.setText(String.valueOf(surLabor));
            or_Grape_Surplus.setText(String.valueOf(surGrape));
        }
    }


    @FXML
    void toexit(ActionEvent event) {
        Main.stage.setScene(Main.scene);
    }


}
