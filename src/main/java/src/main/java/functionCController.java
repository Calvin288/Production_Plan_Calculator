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

public class functionCController {

    @FXML
    private TextField Bko_Noir; //integer

    @FXML
    private TextField Bko_Rose; //integer

    @FXML
    private TextField Cap_Grape; //integer

    @FXML
    private TextField Cap_Labor; //integer

    @FXML
    private Line HLine2;

    @FXML
    private Line HLine3;

    @FXML
    private Line HLine4;

    @FXML
    private Line HLine_1;

    @FXML
    private TextField Num_Week; //integer

    @FXML
    private TextField Prc_Noir; //9999.99

    @FXML
    private TextField Prc_Rose; //9999.99

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

    @FXML
    void toclick(ActionEvent event) {
        //run
        //list of warnings


        //check if any field is empty


        //check if fields are in correct data type


        //check for negative values


        //check if fields are within valid range



        //if no errors, calculate
        //use 2-layer nested for loop to compute optimal mix of wines Opt_Rose and Opt_Noir to maximize Opt_Revenue

        //Reserve all resource of both labor and grape to backorders first, then optimize remaining resource of both labor and grape capacity
        //Revenue = (Opt_Rose * Prc_Rose) + (Opt_Noir * Prc_Noir)

    }

    //validate field inputs
    //abnormal situations:
    // If Actual Production Capacity < Rose + Opt_Noir, show: "w1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!"
    //After optimizing product ix, if consumption of grapes is <90% of given capacity due to insufficient labor supplied, show warning "w2: Insufficient labor supplied to utilize the grape resource (less than 90%)!"
    //If (Bko_Rose + Bko_Noir) < 70% (Opt_Rose + Opt_Noir), show warning: "w3: According to company policy, ratio of backorder volume should not lower than 70% of the optimal production volume!".
  public void initialize() {
        Num_Week.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    //is it between 2301 and 2315?

                } catch (NumberFormatException e) {
                    //if it's not an integer

                }
            }
        });

    }
    @FXML
    void toexit(ActionEvent event) {

    }

}
