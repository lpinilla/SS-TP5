package ar.edu.itba.grupo3.TP;

public class App
{
    public static void main( String[] args )
    {
        double height = 2.5;
        //GenerateInput generateInput = new GenerateInput();
        //generateInput.generateInputs(100, 10, height, 0.15, 0.32, 1.55, 1.55, 0.5, 1);
        FileHandler fileHandler = new FileHandler("resources");
        SimInfo info = fileHandler.loadData();
        CIM cim = new CIM(info, 5, height, true, true);
        Particle following = info.getAllParticles().get(1);
        info.getAllParticles().clear();
        info.getAllParticles().add(following);
        double deltaT = 0.1;
        for(int i = 0; i < 20; i++){
            following.updatePosition(deltaT, info.getRmin(), info.getVe(), info.getH(), info.getL(), cim.getCellSize());
            fileHandler.saveDynamicForAnimation("testing", info, i, false);
        }
    }
}
