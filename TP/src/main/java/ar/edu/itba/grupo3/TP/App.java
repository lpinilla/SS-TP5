package ar.edu.itba.grupo3.TP;

public class App
{
    public static void main( String[] args )
    {
        double width = 3;
        //generamos input random
        //GenerateInput generateInput = new GenerateInput();
        //generateInput.generateInputs(100, 10, width, 0.15, 0.32, 1.55, 1.55, 0.5, 1);
        int saveFactor = 1;
        DinamicaPeatonal dinamicaPeatonal = new DinamicaPeatonal(saveFactor, width);
        //arranca en 1 ya que el 0 son las iniciales
        for(int i = 1; i < 100; i++){
            dinamicaPeatonal.evolveSystem(i, "prueba");
        }
    }
}
