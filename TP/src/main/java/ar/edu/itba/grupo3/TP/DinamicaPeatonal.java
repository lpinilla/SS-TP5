package ar.edu.itba.grupo3.TP;

import java.util.Set;

public class DinamicaPeatonal {

    private FileHandler fileHandler;
    private SimInfo simInfo;
    private double deltaT;
    private int saveFactor;
    private double width;
    private CIM cim;

    public DinamicaPeatonal(int saveFactor, double width){
        fileHandler = new FileHandler("resources");
        simInfo = fileHandler.loadData();
        this.saveFactor = saveFactor;
        deltaT = simInfo.getRmin() /  (2 * Math.max(simInfo.getDmax(), simInfo.getVe()));
        this.width = width;
        cim = new CIM(simInfo, 10, width, true, true);
        cim.recalculateHeads();
        fileHandler.saveDynamic("testing", simInfo, 0, true);
    }

    public void evolveSystem(int i){
        cim.recalculateHeads();
        cim.calculateNeighbors();
        for(Particle p : simInfo.getAllParticles()){
            p.findTarget();
            p.setNextTarget();
            p.updateRadius(simInfo.getRmax(), simInfo.getTau(), deltaT,simInfo.getRmin());
            p.updateSpeed(simInfo.getDmax(), simInfo.getVe(), simInfo.getRmin(), simInfo.getRmax(), simInfo.getBeta());
            p.updatePosition(deltaT, simInfo.getRmin(), simInfo.getL());
            Set<Particle> neighbors = p.getNeighbours();
            for(Particle n : neighbors){
                boolean collision = p.isColliding(n);
                p.setCollision(collision);
                n.setCollision(collision);
                //si choco calculo ve
                if(collision){
                    p.setRadius(simInfo.getRmin());
                }
            }

        }
        fileHandler.saveDynamic("testing", simInfo, i, true);
    }
}
