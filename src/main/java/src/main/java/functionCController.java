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
 * <h1>Weekly Review with Revenue & Backorder</h1>
 * <p>The Weekly Review with Revenue & Backorder  program implements an
 * application that finds the optimal combination of Rose and Pinot Noir.
 * to maxmise revenue and meet backorder given labor and grape capacity
 * constraints.</p>
 *
 * @author  Nidhi Shah
 * @version 1.0
 * @since   2023-04-19
 */
public class functionCController {

    @FXML
    private TextField Bko_Noir; //integer

    @FXML
    private TextField Bko_Rose; //integer

    @FXML
    private TextField Cap_Grape; //integer, kg

    @FXML
    private TextField Cap_Labor; //integer, minutes

    @FXML
    private Line HLine2;

    @FXML
    private Line HLine3;

    @FXML
    private Line HLine4;

    @FXML
    private Line HLine_1;

    @FXML
    private TextField Num_Week; //integer, week

    @FXML
    private TextField Prc_Noir; //float 2dp (9999.99), $/litre

    @FXML
    private TextField Prc_Rose; //float 2dp (9999.99), $/litre

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
    private TextField Bko_Fulfill; //boolean

    @FXML
    private TextField Opt_Noir; //integer

    @FXML
    private TextField Opt_Rose; //integer

    @FXML
    private TextField Opt_Total; //integer

    @FXML
    private TextField Opt_Revenue; //integer

    @FXML
    private Spinner<String> or_scroll_text1;

    @FXML
    private Line vLine3;

    @FXML
    void getDefaultValue(InputMethodEvent event) {

    }

    boolean isNonNegativeInteger(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i >= 0) {
                return true; //valid
            } else {
                return false; //negative
            }
        } catch (NumberFormatException e) {
            return false; //not an integer
        }
    }

    boolean isNonNegativeFloat(String s) {
        try {
            float i = Float.parseFloat(s);
            if (i >= 0) {
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
        ObservableList<String> warnings = FXCollections.observableArrayList("No warnings.","W1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!","W2: Insufficient labor supplied to utilize the grape resource (less than 90%)!", "W3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!", "W4: Please fill all the required parameters", "W5: Please fill the text field with the proper format", "W6: Please fill the text field with the proper range");
        or_scroll_text1.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(warnings));

        //validate field inputs
        //check if any field is empty
        if (Bko_Noir.getText().isEmpty() || Bko_Rose.getText().isEmpty() || Cap_Grape.getText().isEmpty() || Cap_Labor.getText().isEmpty() || Num_Week.getText().isEmpty() || Prc_Noir.getText().isEmpty() || Prc_Rose.getText().isEmpty()) {
            or_scroll_text1.getValueFactory().setValue("W4: Please fill all the required parameters");
        }

        //check if fields are in correct data type
        else if (!isNonNegativeInteger(Bko_Noir.getText()) || !isNonNegativeInteger(Bko_Rose.getText()) || !isNonNegativeInteger(Cap_Grape.getText()) || !isNonNegativeInteger(Cap_Labor.getText()) || !isNonNegativeInteger(Num_Week.getText()) || !isNonNegativeFloat(Prc_Noir.getText()) || !isNonNegativeFloat(Prc_Rose.getText())) {
            or_scroll_text1.getValueFactory().setValue("W5: Please fill the text field with the proper format");
        }

        //check for negative values

        //check if fields are within valid range
        else if (!isNumWeekCorrect(Num_Week.getText())) {
            or_scroll_text1.getValueFactory().setValue("W6: Please fill the text field with the proper range");
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
            float Prc_Noirs = Float.parseFloat(Prc_Noir.getText());
            float Prc_Roses = Float.parseFloat(Prc_Rose.getText());

            //find max revenue and meet backorders
            //list of outputs
            double maxRevenue = 0;
            int roseProduced = 0;
            int noirProduced = 0;
            int remainingLabour;
            int remainingGrape;
            boolean backorderFulfilled = false;

            //constants
            //5 minutes needed to produce rose and 12 minutes needed to produce p_noir
            int LABOUR_PER_ROSE = 5;
            int LABOUR_PER_NOIR = 12;
            //6kgs of grapes needed for rose and 4kgs for noir
            int GRAPE_PER_ROSE = 6;
            int GRAPE_PER_NOIR = 4;
            //max production capacity is 5000 litres per week
            int MAX_PRODUCTION_CAPACITY = 5000;
            //standard man power per head is 37.5 Man hours per week
            double STANDARD_MANPOWER_PER_HEAD = 37.5;
            //labor cost per week is $935; irrelevant
            //int LABOUR_COST_PER_WEEK = 935;


            //use 2-layer nested for loop to compute optimal mix of wines Opt_Rose and Opt_Noir to maximize Opt_Revenue
            //Reserve all resource of both labor and grape to backorders first, then optimize remaining resource of both labor and grape capacity
            //Sales Revenue = (Opt_Rose * Prc_Rose) + (Opt_Noir * Prc_Noir)

            //reserve for backorder
            remainingLabour = Cap_Labors - (Bcko_Noir * LABOUR_PER_NOIR + Bcko_Rose * LABOUR_PER_ROSE);
            remainingGrape = Cap_Grapes - (Bcko_Noir * GRAPE_PER_NOIR + Bcko_Rose * GRAPE_PER_ROSE);
            if (remainingLabour < 0 || remainingGrape < 0) {
                //four cases: 1. only capacity for backorder of rose, 2. only capacity for backorder of noir, 3. no capacity for backorder of rose nor noir 4. capacity for either backorder of rose or noir

                //optimize the backorders
                remainingGrape = Cap_Grapes;
                remainingLabour = Cap_Labors;
                for (int i = 0; i <= Bcko_Rose; i++) {
                    for (int j = 0; j <= Bcko_Noir; j++) {
                        if (i * LABOUR_PER_ROSE + j * LABOUR_PER_NOIR <= Cap_Labors && i * GRAPE_PER_ROSE + j * GRAPE_PER_NOIR <= Cap_Grapes) {
                            if (i * Prc_Roses + j * Prc_Noirs > maxRevenue) {
                                maxRevenue = (i * Prc_Roses + j * Prc_Noirs);
                                roseProduced = i;
                                noirProduced = j;
                            }
                        }
                    }
                }
            }
            else {
                backorderFulfilled = true;

                //there is still capacity after backorders
                //optimize with remaining capacity of labor and grape
                for (int r = 0; r <= remainingLabour / LABOUR_PER_ROSE; r++) {
                    for (int n = 0; n <= remainingLabour / LABOUR_PER_NOIR; n++) {
                        if (r * LABOUR_PER_ROSE + n * LABOUR_PER_NOIR <= remainingLabour && r * GRAPE_PER_ROSE + n * GRAPE_PER_NOIR <= remainingGrape) {
                            if (r * Prc_Roses + n * Prc_Noirs > maxRevenue) {
                                maxRevenue = (r * Prc_Roses + n * Prc_Noirs);
                                roseProduced = r;
                                noirProduced = n;
                            }
                        }
                    }
                }
                roseProduced += Bcko_Rose;
                noirProduced += Bcko_Noir;
            }

            //display the result
            //set the variables Opt_Rose, Opt_Noir, Opt_Total, Opt_Revenue, Bko_Fulfill
            Opt_Rose.setText(Integer.toString(roseProduced));
            Opt_Noir.setText(Integer.toString(noirProduced));
            Opt_Total.setText(Integer.toString(roseProduced + noirProduced));
            Opt_Revenue.setText(Integer.toString((int) (roseProduced * Prc_Roses + noirProduced * Prc_Noirs)));
            //format Bko_Fulfill as Yes / No
            if (backorderFulfilled) {
                Bko_Fulfill.setText("Yes");
            }
            else {
                Bko_Fulfill.setText("No");
            }


            //abnormal situations:
            // If Actual Production Capacity < Rose + Opt_Noir, show: "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!"
            if (roseProduced + noirProduced > MAX_PRODUCTION_CAPACITY) {
                or_scroll_text1.getValueFactory().setValue("w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!");
            }

            //After optimizing product mix, if consumption of grapes is <90% of given capacity due to insufficient labor supplied, show warning "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!"
            if ((roseProduced * GRAPE_PER_ROSE + noirProduced * GRAPE_PER_NOIR) < (0.9* Cap_Grapes)) {
                or_scroll_text1.getValueFactory().setValue("w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!");
            }

            //If (Bko_Rose + Bko_Noir) < 70% (Opt_Rose + Opt_Noir), show warning: "w3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!".
            if (Bcko_Rose + Bcko_Noir < 0.7 * (roseProduced + noirProduced)) {
                or_scroll_text1.getValueFactory().setValue("w3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!");
            }

        }
    }

    public void initialize() {
        Num_Week.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (2301 > Integer.parseInt(newValue) || 2315 < Integer.parseInt(newValue)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter an integer greater than 0 and less than 15");
                        alert.showAndWait();
                        Num_Week.setText("");
                    } else {

                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter the number greater than 0 and less than 15");
                    alert.showAndWait();
                    Num_Week.setText("");
                }
            }
        });

        Prc_Rose.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    //check if it is a positive float
                    if (Float.parseFloat(newValue) < 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("error");
                        alert.setHeaderText("input error");
                        alert.setContentText("Please enter a positive float");
                        alert.showAndWait();
                        Prc_Rose.setText("");
                    } else {
                        //If it is a decimal, it should have no more than 2 decimal places
                        if (newValue.contains(".")) {
                            if (newValue.substring(newValue.indexOf(".") + 1).length() > 2) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("error");
                                alert.setHeaderText("input error");
                                alert.setContentText("Please enter a float with no more than 2 decimal places");
                                alert.showAndWait();
                                Prc_Rose.setText("");
                            }
                        }
                    }

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("error");
                    alert.setHeaderText("input error");
                    alert.setContentText("Please enter the number greater than 0 and less than 15");
                    alert.showAndWait();
                    Num_Week.setText("");
                }
            }
        });
    }

        @FXML
    void toexit(ActionEvent event) {
        Main.stage.setScene(Main.scene);
    }



}
