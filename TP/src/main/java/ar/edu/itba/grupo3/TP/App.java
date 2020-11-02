package ar.edu.itba.grupo3.TP;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        //GenerateInput generateInput = new GenerateInput();
        //generateInput.generateInputs(40, 0.15, 0.32, 1.55, 1.55, 0.5, 1);
        FileHandler fileHandler = new FileHandler("resources");
        SimInfo info = fileHandler.loadData();
        List<Particle> particles = info.getAllParticles();
    }
}
