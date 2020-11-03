package ar.edu.itba.grupo3.TP;

public class App
{
    public static void main( String[] args )
    {
        double height = 3;
        //GenerateInput generateInput = new GenerateInput();
        //generateInput.generateInputs(100, 10, height, 0.15, 0.32, 1.55, 1.55, 0.5, 1);
        FileHandler fileHandler = new FileHandler("resources");
        SimInfo info = fileHandler.loadData();
        CIM cim = new CIM(info, 10, height, true, true);
        Particle target = info.getAllParticles().get(1);
        Particle neighbor = info.getAllParticles().get(0);
        info.getAllParticles().clear();
        target.setX(target.getX() - 5);
        target.setY(target.getY() + 2.15);
        info.getAllParticles().add(target);
        neighbor.setX(target.getX());
        neighbor.setY(info.getL() - info.getH() + 0.15);
        info.getAllParticles().add(neighbor);
        fileHandler.saveDynamicForAnimation("testing", info, 0, false);
        cim.calculateNeighbors();
        System.out.println(target.getNeighbours());


        //double deltaT = 0.1;
        //following.setVy(1d);
        //for(int i = 0; i < 20; i++){
        //    following.updatePosition(deltaT, info.getRmin(), info.getVe(), info.getH(), info.getL(), cim.getCellSize());
        //    fileHandler.saveDynamicForAnimation("testing", info, i, false);
        //}
    }
}
