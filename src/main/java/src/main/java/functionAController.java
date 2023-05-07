package src.main.java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;


/**
 *
 * Function A is used to help the user in computing the optimal mix of wines
 * to be produced in the year for maximizing the gross profit for the winery<br><br>
 *
 * The user have 6 inputs in the unit interface:<br>
 * 1. Num_Week (integer) = number of weeks to be estimated for the harvest season<br>
 * 2. Cap_Labor (integer) = labor resource planned for the production cycle<br>
 * 3. Cap_Grape (integer) = grape resource planned for the production cycle<br>
 * 4. Prc_Rose (double) = price of rose wine<br>
 * 5. Prc_Noir (double) = price of pinot-noir wine<br>
 * 6. Fixed_Costs (integer) = total fixed costs of operating and manufacturing overheads<br><br>
 *
 * The function will output the following:<br>
 * 1. Opt_Rose = the optimal amount of rose wine production in litres<br>
 * 2. Opt_Noir = the optimal amount of pinot-noir wine production in litres<br>
 * 3. Opt_Profit = the optimized total gross profit before tax could be generated for the year<br>
 * 4. Opt_Margin = the optimized profit margin in x.x% format<br>
 *
 * @author  Albertus Alexander
 * @version 1.0
 * @since   2023-04-30
 */
public class functionAController {

    @FXML
    private TextField Cap_Grape;

    @FXML
    private TextField Cap_Labor;

    @FXML
    private TextField Fixed_Costs;

    @FXML
    private Line HLine2;

    @FXML
    private Line HLine3;

    @FXML
    private Line HLine4;

    @FXML
    private Line HLine_1;

    @FXML
    private TextField Num_Week;

    @FXML
    private TextField Prc_Noir;

    @FXML
    private TextField Prc_Rose;

    @FXML
    private Line VLine1;

    @FXML
    private Line VLine2;

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
    private Label data_input_caption4;

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
    private Label opt_result_heading1;

    @FXML
    private TextField or_Gross_Profit;

    @FXML
    private TextField or_Prod_Vol_Noir;

    @FXML
    private TextField or_Prod_Vol_Rose;

    @FXML
    private TextField or_Prod_Vol_Total;

    @FXML
    private TextField or_Profit_Margin;

    @FXML
    private Spinner<String> or_scroll_text1;

    @FXML
    private Line vLine3;

    @FXML
    void buttonpressed(ActionEvent event) {}

    int roseLabor = 5;  // Minutes
    int noirLabor = 12; // Minutes
    int roseGrape = 6;  // Kg
    int noirGrape = 4;  // Kg
    int productionCap = 5000;   // Litres

    public void initialize() {
        Num_Week.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (1 > Integer.parseInt(newValue) || 16 < Integer.parseInt(newValue)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than 0 and less than 16");
                        alert.showAndWait();
                        Num_Week.setText("");
                    } else {

                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter the number greater than 0 and less than 16");
                    alert.showAndWait();
                    Num_Week.setText("");
                }
            }
        });

        Cap_Labor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!(1 <= Integer.parseInt(newValue))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than or equal 1");
                        alert.showAndWait();
                        Cap_Labor.setText("");
                    } else {

                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter an integer greater than or equal 1");
                    alert.showAndWait();
                    Cap_Labor.setText("");
                }
            }
        });

        Cap_Grape.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!(1 <= Integer.parseInt(newValue))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than or equal 1");
                        alert.showAndWait();
                        Cap_Grape.setText("");
                    } else {

                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter an integer greater than or equal 1");
                    alert.showAndWait();
                    Cap_Grape.setText("");
                }
            }
        });

        Prc_Rose.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!(1 <= Double.parseDouble(newValue))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an double greater than or equal 1");
                        alert.showAndWait();
                        Prc_Rose.setText("");
                    } else {
                        if (newValue.contains(".")) {
                            String[] parts = newValue.split("\\.");
                            if (parts[1].length() > 2) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("error");
                                alert.setHeaderText("input error");
                                alert.setContentText("Please enter only double with 2 decimal points");
                                alert.showAndWait();
                                Prc_Rose.setText("");
                            }
                            else {

                            }
                        }

                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter an double greater than or equal 1");
                    alert.showAndWait();
                    Prc_Rose.setText("");
                }
            }
        });

        Prc_Noir.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!(1 <= Double.parseDouble(newValue))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an double greater than or equal 1");
                        alert.showAndWait();
                        Prc_Noir.setText("");
                    } else {
                        if (newValue.contains(".")) {
                            String[] parts = newValue.split("\\.");
                            if (parts[1].length() > 2) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("error");
                                alert.setHeaderText("input error");
                                alert.setContentText("Please enter only double with 2 decimal points");
                                alert.showAndWait();
                                Prc_Noir.setText("");
                            }
                            else {

                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter an double greater than or equal 1");
                    alert.showAndWait();
                    Prc_Noir.setText("");
                }
            }
        });

        Fixed_Costs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (!(1 <= Integer.parseInt(newValue))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than or equal 1");
                        alert.showAndWait();
                        Fixed_Costs.setText("");
                    } else {

                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter an integer greater than or equal 1");
                    alert.showAndWait();
                    Fixed_Costs.setText("");
                }
            }
        });
    }
    @FXML
    private void getDefaultValue(InputMethodEvent event) {


    }

    public void toexit(ActionEvent actionEvent) {Main.stage.setScene(Main.scene);
    }

    public void toclick(ActionEvent actionEvent) {
        // check if any text field is empty
        if( tf_empty(Num_Week)  ||
                tf_empty(Cap_Labor) ||
                tf_empty(Cap_Grape) ||
                tf_empty(Prc_Rose)  ||
                tf_empty(Prc_Noir)  ||
                tf_empty(Fixed_Costs))
        {
            or_scroll_text1.setPromptText("W4: Please fill all the required parameters");
        }

        // check if all the text field is the correct format
        else if (   tf_notInt(Num_Week)     ||
                tf_notInt(Cap_Labor)    ||
                tf_notInt(Cap_Grape)    ||
                tf_notDou(Prc_Rose)     ||
                tf_notDou(Prc_Noir)     ||
                tf_notInt(Fixed_Costs))
        {
            or_scroll_text1.setPromptText("W5: Please fill the text field with the proper format");
        }

        // check for any negative value
        else if (   tf_negativeInt(Num_Week)    ||
                tf_negativeInt(Cap_Labor)   ||
                tf_negativeInt(Cap_Grape)   ||
                tf_negativeDou(Prc_Rose)    ||
                tf_negativeDou(Prc_Noir)    ||
                tf_negativeInt(Fixed_Costs))
        {
            or_scroll_text1.setPromptText("W6: Please fill positive values");
        }

        else
        {
            // list of inputs
            int input_numWeek = Integer.parseInt(Num_Week.getText());
            int input_capLabor = Integer.parseInt(Cap_Labor.getText());
            int input_capGrape = Integer.parseInt(Cap_Grape.getText());
            double input_prcRose = Double.parseDouble(Prc_Rose.getText());
            double input_prcNoir = Double.parseDouble(Prc_Noir.getText());
            int input_fixedCosts = Integer.parseInt(Fixed_Costs.getText());
            // find the max revenue
            functionA opt = new functionA();
            opt.optimize(input_numWeek, input_capLabor, input_capGrape, input_prcRose, input_prcNoir, input_fixedCosts);

            // list of outputs
            or_Prod_Vol_Rose.setText(String.valueOf(opt.getOptimizedRose()));
            or_Prod_Vol_Noir.setText(String.valueOf(opt.getOptimizedNoir()));
            or_Prod_Vol_Total.setText(String.valueOf(opt.getOptimizedTotal()));
            or_Gross_Profit.setText(String.valueOf(opt.getOptimizedProfit()));
            or_Profit_Margin.setText(String.valueOf(opt.getOptimizedMargin()));

            // give warning
            int remainingGrape = input_capGrape*input_numWeek - (roseGrape*opt.getOptimizedRose()) - (noirGrape*opt.getOptimizedNoir());
            int remainingLabour = input_capLabor*input_numWeek - (roseLabor*opt.getOptimizedRose()) - (noirLabor*opt.getOptimizedNoir());
            String warnings = "";
            if (opt.getOptimizedTotal()>(productionCap*input_numWeek)) {
                warnings += "W1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!\r";
            }
            if (input_capGrape > 0 && input_capLabor > 0) {
                if (((input_capGrape - remainingGrape) * 100 / input_capGrape) < 90) {
                    warnings += "W2: Insufficient labor supplied to utilize the grape resource (less than 90%).\r";
                }
                else if (((input_capLabor - remainingLabour) * 100 / input_capLabor) < 90) {
                    warnings += "W3: Insufficient grape resource to utilize the labor supplied (less than 90%).\r";
                }
            }
            or_scroll_text1.setPromptText(warnings);
        }
    }

    public boolean tf_empty (TextField x) {
        if (x.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean tf_notInt (TextField x) {
        try {
            Integer.parseInt(x.getText());
            return false;
        }
        catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean tf_notDou (TextField x) {
        try {
            Double.parseDouble(x.getText());
            return false;
        }
        catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean tf_negativeInt (TextField x) {
        if(Integer.parseInt(x.getText()) < 0) {
            return true;
        }
        return false;
    }

    public boolean tf_negativeDou (TextField x) {
        if(Double.parseDouble(x.getText()) < 0) {
            return true;
        }
        return false;
    }
}
