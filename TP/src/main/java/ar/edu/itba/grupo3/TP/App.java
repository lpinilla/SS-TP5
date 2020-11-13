package ar.edu.itba.grupo3.TP;

public class App
{
    public static void main( String[] args )
    {
        double width = 3;
        int[] n ={20,37,52,65,82,144};
//        int n=20;
        for(int j=0;j<6;j++){
            //generamos input random
            GenerateInput generateInput = new GenerateInput();
            generateInput.generateInputs(n[j], 10, width, 0.13, 0.32, 1.55, 1.55, 0.5, 1);
            int saveFactor = 1;
            String fileName="prueba"+ n[j];
            DinamicaPeatonal dinamicaPeatonal = new DinamicaPeatonal(saveFactor, width,fileName);
            //arranca en 1 ya que el 0 son las iniciales
            for(int i = 1; i <= 400; i++){
                System.out.println(i);
                dinamicaPeatonal.evolveSystem(i, fileName);
            }
        }


    }
}
