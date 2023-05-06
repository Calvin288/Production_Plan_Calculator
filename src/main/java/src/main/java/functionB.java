package src.main.java;

public class functionB {

    // Fixed given value
    int roseLabor = 5;  // Minutes
    int noirLabor = 12; // Minutes
    int roseGrape = 6;  // Kg
    int noirGrape = 4;  // Kg
    int productionCap = 5000;   // Litres

    int optimalRevenue = 0;
    int optimalRose = 0;
    int optimalNoir = 0;
    double prcRose;
    double prcNoir;
    int capLabour;
    int capGrape;
    int maxRFromLabor;
    int maxRFromGrape;
    int maxR;
    int maxPFromLabor;
    int maxPFromGrape;
    int maxP;
    int surGrape;
    int surLabor;



    public void optimize(int Num_Week, int Cap_Labor, int Cap_Grape, double Prc_Rose, double Prc_Noir) {
        optimalRevenue = 0;
        optimalRose = 0;
        optimalNoir = 0;
        prcRose = Math.round(Prc_Rose * 100.0) / 100.0;
        prcNoir = Math.round(Prc_Noir * 100.0) / 100.0;
        capLabour = Cap_Labor;
        capGrape = Cap_Grape;
        maxRFromLabor = capLabour / 5;
        maxRFromGrape = capGrape / 6;
        maxR = maxRFromLabor > maxRFromGrape ? maxRFromGrape : maxRFromLabor;
        maxPFromLabor = capLabour / 12;
        maxPFromGrape = capGrape / 4;
        maxP = maxPFromLabor > maxPFromGrape ? maxPFromGrape : maxPFromLabor;

        for (int optimalR = 0; optimalR <= maxR; optimalR++) {
            for (int optimalP = 0; optimalP <= maxP; optimalP++) {
                if (optimalR * roseLabor + optimalP * noirLabor > capLabour || roseGrape * optimalR + noirGrape * optimalP > capGrape)
                    break;
                if (optimalR * prcRose + optimalP * prcNoir > optimalRevenue) {
                    optimalRevenue = (int) (optimalR * prcRose + optimalP * prcNoir);
                    optimalRose = optimalR;
                    optimalNoir = optimalP;
                }
            }
        }

        surLabor = capLabour - (optimalRose * roseLabor + optimalNoir * noirLabor);
        if (surLabor > 0 && surLabor < roseLabor)
            surLabor = 0;
        surGrape = capGrape - (optimalRose * roseGrape + optimalNoir * noirGrape);
        if (surGrape > 0 && surGrape < noirGrape)
            surGrape = 0;

    }

    public int getOptimizedRose() {
        return optimalRose;
    }

    public int getOptimizedNoir () {
        return optimalNoir;
    }

    public int getOptimizedTotal() {
        return optimalRose + optimalNoir;
    }

    public int getOptimizedRevenue () {
        return optimalRevenue;
    }

    public int getGrapeSurplus() {
        return surGrape;
    }

    public int getLaborSurplus() {
        return surLabor;
    }
}
