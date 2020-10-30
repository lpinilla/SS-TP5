package ar.edu.itba.grupo3.TP;


public class DinamicaPeatonal {

    private FileHandler fileHandler;
    private SimInfo simInfo;
    private double deltaT;
    private int saveFactor;

    public DinamicaPeatonal(int saveFactor){
        fileHandler = new FileHandler("resources");
        simInfo = fileHandler.loadData();
        this.saveFactor = saveFactor;
        deltaT = simInfo.getRmin() /  (2 * Math.max(simInfo.getDmax(), simInfo.getVe()));
    }

    public void evolveSystem(){

    }
}
