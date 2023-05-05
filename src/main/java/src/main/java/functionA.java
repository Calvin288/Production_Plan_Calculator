package src.main.java;

public class functionA {

    // Fixed given value
    int roseLabor = 5;  // Minutes
    int noirLabor = 12; // Minutes
    int roseGrape = 6;  // Kg
    int noirGrape = 4;  // Kg
    int productionCap = 5000;   // Litres

    // Private variables
    private int maxRevenue = 0;
    private int roseProduced;
    private int noirProduced ;
    private int remainingLabour;
    private int remainingGrape;
    private int optGrossProfit = 0;
    private double optProfitMargin = 0;

    // Private functions
    public long[] intersection(long[] a, long[] b) {
        long[] result = {0, 0};
        if ((a[0]*b[1])-(b[0]*a[1]) != 0) {
            result[0] = ((a[1]*b[2])-b[1]*a[2])/((a[0]*b[1])-(b[0]*a[1]));
            result[1] = ((b[0]*a[2])-(a[0]*b[2]))/((a[0]*b[1])-(b[0]*a[1]));
        }
        return result;
    }

    public void optimize(int Num_Week, int Cap_Labor, int Cap_Grape, double Prc_Rose, double Prc_Noir, int Fixed_Costs) {
        /*
         *   There are 3 different possibilities in 2 linear function to find the maximum value
         *   1. Maximum X
         *   2. Maximum Y
         *   3. Intersection
         */

        // case 1: maximize rose (mr)
        // calculate the max amount of rose that can be produced
        int mr_RoseLitre = (Cap_Labor/roseLabor > Cap_Grape/roseGrape) ? (Cap_Grape/roseGrape) : (Cap_Labor/roseLabor);
        int mr_NoirLitre = 0;
        int mr_revenue;

        remainingLabour = Cap_Labor - (mr_RoseLitre*roseLabor);
        remainingGrape = Cap_Grape - (mr_RoseLitre*roseGrape);
        if (remainingLabour >= noirLabor && remainingGrape >= noirGrape) {
            mr_NoirLitre = (remainingLabour/noirLabor > remainingGrape/noirGrape) ? (remainingGrape/noirGrape) : (remainingLabour/noirLabor);
            // check if it passes productionCap
            if (mr_RoseLitre + mr_NoirLitre > productionCap) {
                mr_NoirLitre = productionCap - mr_RoseLitre;
            }
        }
        mr_revenue = (int) (mr_RoseLitre*Prc_Rose + mr_NoirLitre*Prc_Noir);
        maxRevenue = mr_revenue;
        roseProduced = mr_RoseLitre;
        noirProduced = mr_NoirLitre;

        // case 2: maximize noir (mn)
        int mn_RoseLitre = 0;
        int mn_NoirLitre = (Cap_Labor/noirLabor > Cap_Grape/noirGrape) ? (Cap_Grape/noirGrape) : (Cap_Labor/noirLabor);
        int mn_revenue;
        // check if there is remaining resource to make rose
        remainingLabour = Cap_Labor - (mn_NoirLitre*noirLabor);
        remainingGrape = Cap_Grape - (mn_NoirLitre*noirGrape);
        if (remainingLabour >= noirLabor && remainingGrape >= noirGrape) {
            mn_RoseLitre = (remainingLabour/roseLabor > remainingGrape/roseGrape) ? (remainingGrape/roseGrape) : (remainingLabour/roseLabor);
            // check if it passes productionCap
            if (mn_RoseLitre + mn_NoirLitre > productionCap) {
                mn_RoseLitre = productionCap - mn_NoirLitre;
            }
        }
        mn_revenue = (int) (mn_RoseLitre*Prc_Rose + mn_NoirLitre*Prc_Noir);
        if (mn_revenue > maxRevenue) {
            maxRevenue = mn_revenue;
            roseProduced = mn_RoseLitre;
            noirProduced = mn_NoirLitre;
        }

        // case 3: maximize both rose and noir
        boolean case3 = false;
        boolean case3Negative = false;
        int grape_noir = Cap_Grape/noirGrape;
        int minutes_noir = Cap_Labor/noirLabor;
        int grape_rose = Cap_Grape/roseGrape;
        int minutes_rose = Cap_Labor/roseLabor;

        long[] a = {grape_noir,grape_rose,(-1*grape_noir*grape_rose)};
        long[] b = {minutes_noir,minutes_rose,(-1*minutes_noir*minutes_rose)};
        long[] cross_section = intersection(a, b);

        int c_rose = (int) cross_section[0];
        int c_noir = (int) cross_section[1];

        if(c_rose<0 || c_noir<0) {
            case3Negative = true;
        }
        if ((Cap_Grape - (roseGrape*c_rose) - (noirGrape*c_noir))<0) {
            case3Negative = true;
        }
        if ((Cap_Labor - (roseLabor*c_rose) - (noirLabor*c_noir))<0) {
            case3Negative = true;
        }

        int c_revenue = (int) (c_rose*Prc_Rose + c_noir*Prc_Noir);
        if (c_revenue > maxRevenue && !case3Negative) {
            maxRevenue = c_revenue;
            roseProduced = c_rose;
            noirProduced = c_noir;
            case3 = true;
        }

        // check if it is feasible to add 1 more litre of rose or noir
        remainingGrape = Cap_Grape - (roseGrape*roseProduced) - (noirGrape*noirProduced);
        remainingLabour = Cap_Labor - (roseLabor*roseProduced) - (noirLabor*noirProduced);

        //if (c_rose + c_noir < productionCap && case3) {
        if (case3) {
            if (Prc_Rose > Prc_Noir) {
                if ((remainingGrape >= roseGrape) && (remainingLabour >= roseLabor)) {
                    maxRevenue += Prc_Rose;
                    roseProduced += 1;
                    remainingGrape -= roseGrape;
                    remainingLabour -= roseLabor;
                }
                else if ((remainingGrape >= noirGrape) && (remainingLabour >= noirLabor)) {
                    maxRevenue += Prc_Noir;
                    noirProduced += 1;
                    remainingGrape -= noirGrape;
                    remainingLabour -= noirLabor;
                }
            }
            else {
                if ((remainingGrape >= noirGrape) && (remainingLabour >= noirLabor)) {
                    maxRevenue += Prc_Noir;
                    noirProduced += 1;
                    remainingGrape -= noirGrape;
                    remainingLabour -= noirLabor;
                }
                else if ((remainingGrape >= roseGrape) && (remainingLabour >= roseLabor)) {
                    maxRevenue += Prc_Rose;
                    roseProduced += 1;
                    remainingGrape -= roseGrape;
                    remainingLabour -= roseLabor;
                }
            }
        }

        // multiply by weeks
        roseProduced *= Num_Week;
        noirProduced *= Num_Week;
        maxRevenue *= Num_Week;

        double vcl = ((roseProduced*roseLabor)+(noirProduced*noirLabor)) * (935/(37.5*60));
        double grossProfit = maxRevenue - vcl - Fixed_Costs;
        optGrossProfit = (int) grossProfit;
        double profitMargin = (grossProfit/maxRevenue)*1000;
        int convertingPM = (int) profitMargin;
        optProfitMargin = convertingPM;
        optProfitMargin = optProfitMargin/10;
    }

    public int getOptimizedRose () {
        return roseProduced;
    }

    public int getOptimizedNoir () {
        return noirProduced;
    }

    public int getOptimizedTotal () {
        return roseProduced+noirProduced;
    }

    public int getOptimizedProfit () {
        return optGrossProfit;
    }

    public double getOptimizedMargin () {
        return optProfitMargin;
    }
}
