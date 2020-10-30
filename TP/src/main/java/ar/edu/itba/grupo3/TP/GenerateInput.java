package ar.edu.itba.grupo3.TP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateInput {

    private List<Particle> generated;

    public GenerateInput(){
        generated = new ArrayList<>();
    }

    /**
     * GenerateInputs: dada un valor de cantidad de partículas y el lado del cuadrado, genera 2 archivos
     * Para este trabajo partícular, se cumple que se inserta primero el objeto de mayor radio y luego
     * se colocan los objetos de menor radios sin que estos colisionen con alguno de los anteriores
     * RandomStatic informa sobre las propiedades estáticas de las partículas, el radio y la masa
     * RandomDynamic informa sobre las propiedades dinámicas de las partículas, posición y velocidad
     * @param N cantidad de partículas
     * @param rmin radio mínimo de las partículas
     * @param rmax radio máximo de las partículas
     * @param dmax dmax rapidez máxima
     * @param tau tau tiempo hasta alcanzar la velocidad máxima
     * @param betha factor que define la relación entre la rapidez y el radio actual
     */
    public void generateInputs(int N, double rmin, double rmax, double dmax, double tau, double betha) {
        //if(!checkParams(N, L)) return;
        double l = 10d * (rmin + rmax) / 2;
        generateStaticFile(N, l, rmin, rmax, dmax, tau, betha);
        generateDynamic(N, l, rmin);
    }

    private void generateStaticFile(int N, double L, double rmin, double rmax, double dmax, double tau, double betha){
        try {
            OutputStream outputStream = new FileOutputStream(new File("resources/RandomStaticInput.txt"));
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(N);
            writer.println(L);
            writer.println(rmin);
            writer.println(rmax);
            writer.println(dmax);
            writer.println(tau);
            writer.println(betha);
            writer.flush();
            writer.close();
            outputStream.close();
        }catch (IOException e) {
            System.out.println("Error creating Static Input");
            e.printStackTrace();
        }
    }

    private void generateDynamic(int N, double L, double rmin){
        try {
            OutputStream outputStream = new FileOutputStream(new File("resources/RandomDynamicInput.txt"));
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println("0"); //time of dynamic particles.
            //creamos una lista para ir guardando sus posiciones
            double xpos, ypos;
            for (int i = 0; i < N; i++) {
                //generar posiciones x e y hasta que se encuentre una que no colisione
                do {
                    xpos = ThreadLocalRandom.current().nextDouble(rmin, L - rmin);
                    ypos = ThreadLocalRandom.current().nextDouble(rmin, L - rmin);
                }while(checkIfCollision(xpos, ypos, rmin));
                writer.println(
                        String.format(Locale.US, "%6.7e", xpos) + "\t" +
                        String.format(Locale.US, "%6.7e", ypos)
                );
                generated.add(new Particle(xpos, ypos, 0d, 0d, rmin, 1d, 0d));
            }
            writer.flush();
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Error creating Dynamic Input");
        }
    }

    private boolean checkIfCollision(double x, double y, double rmin){
        double aux;
        for(Particle p : generated){
            aux = Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2) - Math.pow(rmin + p.getRadius(), 2);
            if(aux <= 0) return true;
        }
        return false;
    }


    private boolean checkParams(int N, int L){
        if(N <= 1 || N >= 250){
            System.out.println("Invalid number of particles");
            return false;
        }
        if(L != 6){
            System.out.print("Invalid Size for this simulation");
            return false;
        }
        return true;
    }
}
