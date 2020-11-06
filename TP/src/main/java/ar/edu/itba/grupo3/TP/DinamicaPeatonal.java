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
    }

    public void evolveSystem(int i, String outputFile){
        cim.recalculateHeads();
        cim.calculateNeighbors();
        for(Particle p : simInfo.getAllParticles()){
            p.findTarget();
            p.setNextTarget();
            p.updateRadius(simInfo.getRmax(), simInfo.getTau(), deltaT);
            p.updateSpeed(simInfo.getDmax(), simInfo.getRmin(), simInfo.getRmax(), simInfo.getBeta());
            Set<Particle> neighbors = p.getNeighbours();
            int wcol = p.collisionWithWalls(width);
            if(wcol != 0) p.collideWithWall(wcol, simInfo.getRmin(), simInfo.getVe());
            for(Particle n : neighbors){
                if(p.isColliding(n)){
                    p.collideWithParticle(n, simInfo.getRmin(), simInfo.getVe());
                    n.collideWithParticle(p, simInfo.getRmin(), simInfo.getVe());
                }
            }
            p.updatePosition(deltaT, simInfo.getL());
        }
        fileHandler.saveDynamic(outputFile, simInfo, i, false);
    }
}
