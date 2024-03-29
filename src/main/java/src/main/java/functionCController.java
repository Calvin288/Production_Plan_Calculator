package src.main.java;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 * Function C implements an
 * application that finds the optimal combination of Rose and Pinot Noir.
 * to maxmize revenue and meet backorder given labor and grape capacity
 * constraints.<br><br>
 *
 * The user has 7 inputs in the interface:<br>
 * 1. Num_Week (integer) = current week of the year<br>
 * 2. Cap_Labor (integer) = labor capacity in minutes<br>
 * 3. Cap_Grape (integer) = grape capacity in kg<br>
 * 4. Prc_Rose (float 2dp) = price of rose per litre<br>
 * 5. Prc_Noir (float 2dp) = price of pinot noir per litre<br>
 * 6. Bko_Rose (integer) = rose backorder in litres<br>
 * 7. Bko_Noir (integer) = pinot noir backorder in litres<br><br>
 *
 * The function  will output the following:<br>
 * 1. Opt_Rose (integer) = optimal liters of rose to produce<br>
 * 2. Opt_Noir (integer) = optimal liters of pinot noir to produce<br>
 * 3. Opt_Total (integer) = optimal total liters to produce<br>
 * 4. Opt_Revenue (integer) = rounded optimal revenue resulted from the amount produced.<br>
 * 5. Bko_Fulfill ("Yes" or "No") = backorder fulfilled<br>
 * Along with these, it will output warnings if:<br>
 * 1. Insufficient production capacity to produce the optimal mix<br>
 * 2. Insufficient labor supplied to utilize the grape resource<br>
 * 3. Ratio of backorder volume is less than 70% of the optimal production volume<br>
 * @author  Nidhi Shah
 * @version 1.0
 * @since   2023-04-19
 */
public class functionCController {

    @FXML
    private TextField Bko_Fulfill;

    @FXML
    private TextField Bko_Noir;

    @FXML
    private TextField Bko_Rose;

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
    private TextField Num_Week;

    @FXML
    private TextField Opt_Noir;

    @FXML
    private TextField Opt_Revenue;

    @FXML
    private TextField Opt_Rose;

    @FXML
    private TextField Opt_Total;

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
    private Label data_input_caption3a1;

    @FXML
    private Label data_input_caption3b;

    @FXML
    private Label data_input_caption3b1;

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
    private Spinner<?> or_scroll_text1;

    @FXML
    private Line vLine3;

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
        //run
        //list of warnings
        //ObservableList<String> warnings = FXCollections.observableArrayList("No warnings.","W1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!","W2: Insufficient labor supplied to utilize the grape resource (less than 90%)!", "W3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!", "W4: Please fill all the required parameters", "W5: Please fill the text field with the proper format", "W6: Please fill the text field with the proper range");
        //or_scroll_text1.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(warnings));

        //first clear list of warnings from previous run:
        or_scroll_text1.setPromptText(" ");

        //validate field inputs
        //check if any field is empty
        if (Bko_Noir.getText().isEmpty() || Bko_Rose.getText().isEmpty() || Cap_Grape.getText().isEmpty() || Cap_Labor.getText().isEmpty() || Num_Week.getText().isEmpty() || Prc_Noir.getText().isEmpty() || Prc_Rose.getText().isEmpty()) {
            or_scroll_text1.setPromptText("W4: Please fill all the required parameters");
        }

        //check if fields are in correct data type
        else if ( !isPositiveInteger(Cap_Grape.getText()) || !isPositiveInteger(Cap_Labor.getText()) || !isPositiveInteger(Num_Week.getText()) || !isPositiveFloat(Prc_Noir.getText()) || !isPositiveFloat(Prc_Rose.getText())) {
            or_scroll_text1.setPromptText("W5: Please fill the text field with the proper format");
        }

        //check if fields are within valid range
        else if (!isNumWeekCorrect(Num_Week.getText())) {
            or_scroll_text1.setPromptText("W6: Please fill the text field with the proper range");
        }

        //if no errors, calculate
        //Revenue = (Opt_Rose * Prc_Rose) + (Opt_Noir * Prc_Noir)
        else {
            //list of inputs
            int Bcko_Rose = Integer.parseInt(Bko_Rose.getText());
            int Bcko_Noir = Integer.parseInt(Bko_Noir.getText());
            int Cap_Grapes = Integer.parseInt(Cap_Grape.getText());
            int Cap_Labors = Integer.parseInt(Cap_Labor.getText());
            int Num_Weeks = Integer.parseInt(Num_Week.getText());
            double Prc_Noirs = Double.parseDouble(Prc_Noir.getText());
            double Prc_Roses = Double.parseDouble(Prc_Rose.getText());

            //find max revenue and meet backorders
            functionC opt = new functionC();
            opt.optimize(Bcko_Rose, Bcko_Noir, Cap_Grapes, Cap_Labors, Num_Weeks, Prc_Noirs, Prc_Roses);

            Opt_Rose.setText(String.valueOf(opt.getOptimizedRose()));
            Opt_Noir.setText(String.valueOf(opt.getOptimizedNoir()));
            Opt_Total.setText(String.valueOf(opt.getOptimizedTotal()));
            Opt_Revenue.setText(String.valueOf(opt.getOptimizedRevenue()));
            //format Bko_Fulfill as Yes / No
            if (opt.getBackorderFulfilled()) {
                Bko_Fulfill.setText("Yes");
            }
            else {
                Bko_Fulfill.setText("No");
            }

            //abnormal situations:
            String warnings ="";
            int MAX_PRODUCTION_CAPACITY = 5000;
            int GRAPE_PER_ROSE = 6;
            int GRAPE_PER_NOIR = 4;
            // If Actual Production Capacity < Opt_Rose + Opt_Noir, show: "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!"
            if (opt.getOptimizedTotal() > MAX_PRODUCTION_CAPACITY) {
                warnings+="w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!\r";
            }

            //After optimizing product mix, if consumption of grapes is <90% of given capacity due to insufficient labor supplied, show warning "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!"
            if ((opt.getOptimizedRose() * GRAPE_PER_ROSE + opt.getOptimizedNoir() * GRAPE_PER_NOIR) < (0.9* Cap_Grapes)) {
                warnings+="w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!\r";
            }

            //If (Bko_Rose + Bko_Noir) < 70% (Opt_Rose + Opt_Noir), show warning: "w3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!".
            if ((Bcko_Rose + Bcko_Noir) < (0.7 * (opt.getOptimizedTotal()))) {
                warnings+="w3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!\r";
            }
            or_scroll_text1.setPromptText(warnings);
        }
    }

    public void initialize() {
        Num_Week.textProperty().addListener(new ChangeListener<String>() {
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
                        alert.setContentText("Please enter an integer between 2301 and 2315");
                        alert.showAndWait();
                        Num_Week.setText("");
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter the number between 2301 and 2315");
                    alert.showAndWait();
                    Num_Week.setText("");
                }
            }
        });

        //Capacities

        //Cap_Grape
        Cap_Grape.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                try {
                    //check if it is a positive integer
                    if (Integer.parseInt(t1) <= 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a positive integer for the grape capacity.");
                        alert.showAndWait();
                        Cap_Grape.setText("");
                    } else {
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a positive integer for the grape capacity.");
                    alert.showAndWait();
                    Cap_Grape.setText("");
                }
            }
        });

        //Cap_Labor
        Cap_Labor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                try {
                    //check if it is a positive integer
                    if (Integer.parseInt(t1) <= 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a positive integer for the labor capacity.");
                        alert.showAndWait();
                        Cap_Labor.setText("");
                    } else {
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a positive integer for the labor capacity.");
                    alert.showAndWait();
                    Cap_Labor.setText("");
                }
            }
        });

        //Backorders
        //Bko_Rose
        Bko_Rose.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                try {
                    //check if it is a positive integer
                    if (Integer.parseInt(t1) < 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a positive integer for the Rose backorder.");
                        alert.showAndWait();
                        Bko_Rose.setText("");
                    } else {
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a positive integer for the Rose backorder.");
                    alert.showAndWait();
                    Bko_Rose.setText("");
                }
            }
        });

        //Bko_Noir
        Bko_Noir.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    //check it's a non-negative integer
                    if (Integer.parseInt(t1) < 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a positive integer for the Noir backorder.");
                        alert.showAndWait();
                        Bko_Noir.setText("");
                    } else {
                    }
                }
                catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a positive integer for the Noir backorder.");
                    alert.showAndWait();
                    Bko_Noir.setText("");
                }
            }
        });


        //Prices
        Prc_Rose.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    //check if it is a positive float
                    if (Float.parseFloat(newValue) < 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a positive float greater than 1");
                        alert.showAndWait();
                        Prc_Rose.setText("");
                    } else {
                        //If it is a decimal, it should have no more than 2 decimal places
                        if (newValue.contains(".")) {
                            if (newValue.substring(newValue.indexOf(".") + 1).length() > 2) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("error");
                                alert.setHeaderText("input error");
                                alert.setContentText("Please enter a number with no more than 2 decimal places");
                                alert.showAndWait();
                                Prc_Rose.setText("");
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a positive number for the price");
                    alert.showAndWait();
                    Prc_Rose.setText("");
                }
            }
        });

        Prc_Noir.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    //check if it is a positive float
                    if (Float.parseFloat(newValue) < 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a positive float greater than 1.");
                        alert.showAndWait();
                        Prc_Noir.setText("");
                    } else {
                        //If it is a decimal, it should have no more than 2 decimal places
                        if (newValue.contains(".")) {
                            if (newValue.substring(newValue.indexOf(".") + 1).length() > 2) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("error");
                                alert.setHeaderText("input error");
                                alert.setContentText("Please enter a number with no more than 2 decimal places");
                                alert.showAndWait();
                                Prc_Noir.setText("");
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter a number for the price.");
                    alert.showAndWait();
                    Prc_Noir.setText("");
                }
            }
        });
        //end of input validation
    }

    @FXML
    void toexit(ActionEvent event) {
        Main.stage.setScene(Main.scene);
    }



}