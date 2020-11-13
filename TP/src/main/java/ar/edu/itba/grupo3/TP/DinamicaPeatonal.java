package ar.edu.itba.grupo3.TP;

import java.util.Set;

public class DinamicaPeatonal {

    private final FileHandler fileHandler;
    private final SimInfo simInfo;
    private final double deltaT;
    private int saveFactor;
    private final double width;
    private final CIM cim;

    public DinamicaPeatonal(int saveFactor, double width, String outputFile){
        fileHandler = new FileHandler("resources");
        simInfo = fileHandler.loadData();
        this.saveFactor = saveFactor;
        this.deltaT = simInfo.getRmin() /  (2 * Math.max(simInfo.getDmax(), simInfo.getVe()));
        this.width = width;
        cim = new CIM(simInfo, 10, width, true, true);
        cim.recalculateHeads();
        fileHandler.saveDynamic(outputFile, simInfo, 0, true);
    }

    public void evolveSystem(int i, String outputFile){
        cim.recalculateHeads();
        cim.calculateNeighbors();
        simInfo.getAllParticles().forEach(p -> p.setWillCollide(false));
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
                    n.setWillCollide(true);
                    n.collideWithParticle(p, simInfo.getRmin(), simInfo.getVe());
                }
            }
            p.updatePosition(deltaT, simInfo.getL());
        }
        fileHandler.saveDynamic(outputFile, simInfo, i, true);
        fileHandler.saveSpeedAVG(simInfo.getAllParticles());
    }
}
