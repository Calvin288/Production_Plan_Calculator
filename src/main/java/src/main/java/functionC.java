package src.main.java;

public class functionC {

    //Fixed given values
    int LABOUR_PER_ROSE = 5;
    int LABOR_PER_NOIR = 12;
    //6kgs of grapes needed for rose and 4kgs for noir
    int GRAPE_PER_ROSE = 6;
    int GRAPE_PER_NOIR = 4;
    //max production capacity is 5000 litres per week
    int MAX_PRODUCTION_CAPACITY = 5000;


    //Private Variables - list of outputs
    private double maxRevenue = 0;
    private int roseProduced = 0;
    private int noirProduced = 0;
    private int remainingLabour;
    private int remainingGrape;
    private boolean backorderFulfilled = false;


    //Private functions
    public void optimize(int Bcko_Rose, int Bcko_Noir, int Cap_Grapes, int Cap_Labors, int Num_Weeks, double Prc_Noirs, double Prc_Roses) {
        //use 2-layer nested for loop to compute optimal mix of wines Opt_Rose and Opt_Noir to maximize Opt_Revenue
        //Reserve all resource of both labor and grape to backorders first, then optimize remaining resource of both labor and grape capacity
        //Sales Revenue = (Opt_Rose * Prc_Rose) + (Opt_Noir * Prc_Noir)

        //reserve for backorder
        remainingLabour = Cap_Labors - (Bcko_Noir * LABOR_PER_NOIR + Bcko_Rose * LABOUR_PER_ROSE);
        remainingGrape = Cap_Grapes - (Bcko_Noir * GRAPE_PER_NOIR + Bcko_Rose * GRAPE_PER_ROSE);
        //A: If the backorders cannot be met.
        if (remainingLabour < 0 || remainingGrape < 0) {
            //four cases: 1. only capacity for backorder of rose, 2. only capacity for backorder of noir, 3. no capacity for backorder of rose nor noir 4. capacity for either backorder of rose or noir

            //optimize the backorders
            remainingGrape = Cap_Grapes;
            remainingLabour = Cap_Labors;
            for (int i = 0; i <= Bcko_Rose; i++) {
                for (int j = 0; j <= Bcko_Noir; j++) {
                    if (i * LABOUR_PER_ROSE + j * LABOR_PER_NOIR <= Cap_Labors && i * GRAPE_PER_ROSE + j * GRAPE_PER_NOIR <= Cap_Grapes) {
                        if (i * Prc_Roses + j * Prc_Noirs > maxRevenue) {
                            maxRevenue = (i * Prc_Roses + j * Prc_Noirs);
                            roseProduced = i;
                            noirProduced = j;
                        }
                    }
                }
            }
        }
        //B: If backorders can be met
        else {
            backorderFulfilled = true;
            //there is still capacity after backorders
            //optimize with remaining capacity of labor and grape
            for (int r = 0; r <= remainingLabour / LABOUR_PER_ROSE; r++) {
                for (int n = 0; n <= remainingLabour / LABOR_PER_NOIR; n++) {
                    if (r * LABOUR_PER_ROSE + n * LABOR_PER_NOIR <= remainingLabour && r * GRAPE_PER_ROSE + n * GRAPE_PER_NOIR <= remainingGrape) {
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
    }




    //Public functions
    public int getOptimizedRose() {return roseProduced;}
    public int getOptimizedNoir() {return noirProduced;}
    public int getOptimizedTotal() {return roseProduced+noirProduced;}
    public double getOptimizedRevenue() {return maxRevenue;}
    public boolean getBackorderFulfilled() {return backorderFulfilled;}
}
