package ar.edu.itba.grupo3.TP;

public class App
{
    public static void main( String[] args )
    {
        //GenerateInput generateInput = new GenerateInput();
        //generateInput.generateInputs(100, 10, 0.15, 0.32, 1.55, 1.55, 0.5, 1);
        FileHandler fileHandler = new FileHandler("resources");
        SimInfo info = fileHandler.loadData();
        CIM cim = new CIM(info, 3, 6, true, true);
    }
}
