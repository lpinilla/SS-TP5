package ar.edu.itba.grupo3.TP;

public class App
{
    public static void main( String[] args )
    {
        double width = 3;
        //generamos input random
        //GenerateInput generateInput = new GenerateInput();
        //generateInput.generateInputs(100, 10, width, 0.15, 0.32, 1.55, 1.55, 0.5, 1);
        //cargamos los datos
        FileHandler fileHandler = new FileHandler("resources");
        SimInfo info = fileHandler.loadData();
        //testear reconocimiento de vecinos
        CIM cim = new CIM(info, 10, width, true, true);
        cim.recalculateHeads();
        cim.calculateNeighbors();

    }
}
