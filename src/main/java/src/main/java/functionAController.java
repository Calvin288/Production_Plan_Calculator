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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

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
    void buttonpressed(ActionEvent event) {

    }
    public void initialize() {
        Num_Week.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    if (0 > Integer.parseInt(newValue) || 15 < Integer.parseInt(newValue)) {
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
    }
    @FXML
    private void getDefaultValue(InputMethodEvent event) {


    }

    public void toexit(ActionEvent actionEvent) {Main.stage.setScene(Main.scene);
    }

    int roseLabor = 5;  // Minutes
    int noirLabor = 12; // Minutes
    int roseGrape = 6;  // Kg
    int noirGrape = 4;  // Kg
    int productionCap = 5000;   // Litres

    public void toclick(ActionEvent actionEvent) {
        ObservableList<String> items = FXCollections.observableArrayList("No warnings.",
                "W1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!",
                "W2: Insufficient labor supplied to utilize the grape resource (less than 90%).",
                "W3: Insufficient grape resource to utilize the labor supplied (less than 90%).",
                "W4: Please fill all the required parameters",
                "W5: Please fill the text field with the proper format",
                "W6: Please fill positive values");
        or_scroll_text1.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<String>(items));
        // check if any text field is empty
        if( tf_empty(Num_Week)  ||
            tf_empty(Cap_Labor) ||
            tf_empty(Cap_Grape) ||
            tf_empty(Prc_Rose)  ||
            tf_empty(Prc_Noir)  ||
            tf_empty(Fixed_Costs))
        {
            or_scroll_text1.getValueFactory().setValue("W4: Please fill all the required parameters");
        }

        // check if all the text field is the correct format
        else if (   tf_notInt(Num_Week)     ||
                    tf_notInt(Cap_Labor)    ||
                    tf_notInt(Cap_Grape)    ||
                    tf_notDou(Prc_Rose)     ||
                    tf_notDou(Prc_Noir)     ||
                    tf_notInt(Fixed_Costs))
        {
            or_scroll_text1.getValueFactory().setValue("W5: Please fill the text field with the proper format");
        }

        // check for any negative value
        else if (   tf_negativeInt(Num_Week)    ||
                    tf_negativeInt(Cap_Labor)   ||
                    tf_negativeInt(Cap_Grape)   ||
                    tf_negativeDou(Prc_Rose)    ||
                    tf_negativeDou(Prc_Noir)    ||
                    tf_negativeInt(Fixed_Costs))
        {
            or_scroll_text1.getValueFactory().setValue("W6: Please fill positive values");
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
            int maxRevenue = 0;
            int roseProduced;
            int noirProduced ;
            int remainingLabour;
            int remainingGrape ;

            // case 1: maximize rose (mr)
            // calculate the max amount of rose that can be produced
            int mr_RoseLitre = (input_capLabor/roseLabor > input_capGrape/roseGrape) ? (input_capGrape/roseGrape) : (input_capLabor/roseLabor);
            int mr_NoirLitre = 0;
            int mr_revenue;
            /*// if the cap larger than productionCap
            if(mr_RoseLitre > productionCap) {
                mr_RoseLitre = productionCap;
            }
            else {*/
                // check if there is remaining resource to make noir

                remainingLabour = input_capLabor - (mr_RoseLitre*roseLabor);
                remainingGrape = input_capGrape - (mr_RoseLitre*roseGrape);
                if (remainingLabour >= noirLabor && remainingGrape >= noirGrape) {
                    mr_NoirLitre = (remainingLabour/noirLabor > remainingGrape/noirGrape) ? (remainingGrape/noirGrape) : (remainingLabour/noirLabor);
                    // check if it passes productionCap
                    if (mr_RoseLitre + mr_NoirLitre > productionCap) {
                        mr_NoirLitre = productionCap - mr_RoseLitre;
                    }
                }
            //}
            mr_revenue = (int) (mr_RoseLitre*input_prcRose + mr_NoirLitre*input_prcNoir);
            maxRevenue = mr_revenue;
            roseProduced = mr_RoseLitre;
            noirProduced = mr_NoirLitre;

            // case 2: maximize noir (mn)
            int mn_RoseLitre = 0;
            int mn_NoirLitre = (input_capLabor/noirLabor > input_capGrape/noirGrape) ? (input_capGrape/noirGrape) : (input_capLabor/noirLabor);
            int mn_revenue;
            /*// if the cap larger than productionCap
            if(mn_NoirLitre > productionCap) {
                mn_NoirLitre = productionCap;
            }*/
            //else {
                // check if there is remaining resource to make rose
                remainingLabour = input_capLabor - (mn_NoirLitre*noirLabor);
                remainingGrape = input_capGrape - (mn_NoirLitre*noirGrape);
                if (remainingLabour >= noirLabor && remainingGrape >= noirGrape) {
                    mn_RoseLitre = (remainingLabour/roseLabor > remainingGrape/roseGrape) ? (remainingGrape/roseGrape) : (remainingLabour/roseLabor);
                    // check if it passes productionCap
                    if (mn_RoseLitre + mn_NoirLitre > productionCap) {
                        mn_RoseLitre = productionCap - mn_NoirLitre;
                    }
                }
            //}
            mn_revenue = (int) (mn_RoseLitre*input_prcRose + mn_NoirLitre*input_prcNoir);
            if (mn_revenue > maxRevenue) {
                maxRevenue = mn_revenue;
                roseProduced = mn_RoseLitre;
                noirProduced = mn_NoirLitre;
            }

            // case 3: maximize both rose and noir
            boolean case3 = false;
            boolean case3Negative = false;
            int grape_noir = input_capGrape/noirGrape;
            int minutes_noir = input_capLabor/noirLabor;
            int grape_rose = input_capGrape/roseGrape;
            int minutes_rose = input_capLabor/roseLabor;

            long[] a = {grape_noir,grape_rose,(-1*grape_noir*grape_rose)};
            long[] b = {minutes_noir,minutes_rose,(-1*minutes_noir*minutes_rose)};
            long[] cross_section = intersection(a, b);

            int c_rose = (int) cross_section[0];
            int c_noir = (int) cross_section[1];

            if(c_rose<0 || c_noir<0) {
                case3Negative = true;
            }
            if ((input_capGrape - (roseGrape*c_rose) - (noirGrape*c_noir))<0) {
                case3Negative = true;
            }
            if ((input_capLabor - (roseLabor*c_rose) - (noirLabor*c_noir))<0) {
                case3Negative = true;
            }

            int c_revenue = (int) (c_rose*input_prcRose + c_noir*input_prcNoir);
            if (c_revenue > maxRevenue && !case3Negative) {
                System.out.println("case 3");
                maxRevenue = c_revenue;
                roseProduced = c_rose;
                noirProduced = c_noir;
                case3 = true;
            }

            // check if it is feasible to add 1 more litre of rose or noir
            remainingGrape = input_capGrape - (roseGrape*roseProduced) - (noirGrape*noirProduced);
            remainingLabour = input_capLabor - (roseLabor*roseProduced) - (noirLabor*noirProduced);

            //if (c_rose + c_noir < productionCap && case3) {
            if (case3) {
                if (input_prcRose > input_prcNoir) {
                    if ((remainingGrape >= roseGrape) && (remainingLabour >= roseLabor)) {
                        maxRevenue += input_prcRose;
                        roseProduced += 1;
                        remainingGrape -= roseGrape;
                        remainingLabour -= roseLabor;
                    }
                    else if ((remainingGrape >= noirGrape) && (remainingLabour >= noirLabor)) {
                        maxRevenue += input_prcNoir;
                        noirProduced += 1;
                        remainingGrape -= noirGrape;
                        remainingLabour -= noirLabor;
                    }
                }
                else {
                    if ((remainingGrape >= noirGrape) && (remainingLabour >= noirLabor)) {
                        maxRevenue += input_prcNoir;
                        noirProduced += 1;
                        remainingGrape -= noirGrape;
                        remainingLabour -= noirLabor;
                    }
                    else if ((remainingGrape >= roseGrape) && (remainingLabour >= roseLabor)) {
                        maxRevenue += input_prcRose;
                        roseProduced += 1;
                        remainingGrape -= roseGrape;
                        remainingLabour -= roseLabor;
                    }
                }
            }

            // multiply by weeks
            roseProduced *= input_numWeek;
            noirProduced *= input_numWeek;
            maxRevenue *= input_numWeek;

            double vcl = (roseProduced*5*60*37.5/935) + (noirProduced*5*60*37.5/935);
            double grossProfit = maxRevenue - vcl - input_fixedCosts;
            int i_grossProfit = (int) grossProfit;
            double profitMargin = (grossProfit/maxRevenue)*1000;
            int convertingPM = (int) profitMargin;
            double updatedProfitMargin = convertingPM;
            updatedProfitMargin = updatedProfitMargin/10;

            // console check
            System.out.println("Remaining grapes");
            System.out.println(remainingGrape);
            System.out.println("Remaining labour");
            System.out.println(remainingLabour);
            System.out.println();
            System.out.println("Consumed grapes");
            System.out.println("Rose");
            System.out.println(roseProduced*roseGrape);
            System.out.println("Noir");
            System.out.println(noirProduced*noirGrape);
            System.out.println("Total");
            System.out.println((roseProduced*roseGrape)+(noirProduced*noirGrape));
            System.out.println();
            System.out.println("Consumed labour");
            System.out.println("Rose");
            System.out.println(roseProduced*roseLabor);
            System.out.println("Noir");
            System.out.println(noirProduced*noirLabor);
            System.out.println("Total");
            System.out.println((roseProduced*roseLabor)+(noirProduced*noirLabor));
            System.out.println();

            // list of outputs
            or_Prod_Vol_Rose.setText(String.valueOf(roseProduced));
            or_Prod_Vol_Noir.setText(String.valueOf(noirProduced));
            or_Prod_Vol_Total.setText(String.valueOf(roseProduced+noirProduced));
            or_Gross_Profit.setText(String.valueOf(i_grossProfit));
            or_Profit_Margin.setText(String.valueOf(updatedProfitMargin));

            // give warning
            if ((roseProduced+noirProduced)>(productionCap*input_numWeek)) {
                or_scroll_text1.getValueFactory().setValue("W1: Insufficient production capacity to produce the optimal mix, please reduce or adjust the capacity of labor & grape volume!");
            }
            if (input_capGrape > 0 && input_capLabor > 0) {
                if (((input_capGrape - remainingGrape) * 100 / input_capGrape) < 90) {
                    or_scroll_text1.getValueFactory().setValue("W2: Insufficient labor supplied to utilize the grape resource (less than 90%).");
                }
                else if (((input_capLabor - remainingLabour) * 100 / input_capLabor) < 90) {
                    or_scroll_text1.getValueFactory().setValue("W3: Insufficient grape resource to utilize the labor supplied (less than 90%).");
                }
            }
        }
    }

    public long[] intersection(long[] a, long[] b) {
        long[] result = {0, 0};
        if ((a[0]*b[1])-(b[0]*a[1]) != 0) {
            result[0] = ((a[1]*b[2])-b[1]*a[2])/((a[0]*b[1])-(b[0]*a[1]));
            result[1] = ((b[0]*a[2])-(a[0]*b[2]))/((a[0]*b[1])-(b[0]*a[1]));
        }
        return result;
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
