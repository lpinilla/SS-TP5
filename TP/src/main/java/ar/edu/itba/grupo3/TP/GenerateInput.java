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
     * @param L lado del cuadrado
     */
    public void generateInputs(int N,int L) {
        if(!checkParams(N, L)) return;
        //particles radius between 0 and 1;
        generateStaticFile(N, L);
        generateDynamic(N, L);
    }

    private void generateStaticFile(int N, int L){
        try {
            OutputStream outputStream = new FileOutputStream(new File("resources/RandomStaticInput.txt"));
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(N);
            writer.println(L);
            //big object
            String radius = String.format(Locale.US, "%.4f", 0.7) + "\t";
            String mass = String.format(Locale.US, "%.4f", 2.0);
            writer.println(radius + mass);
            //setting the radius and mass for all the small objects
            for (int i = 1; i < N; i++) {
                radius = String.format(Locale.US, "%.4f", 0.2) + "\t";
                mass = String.format(Locale.US, "%.4f", 0.9);
                writer.println(radius + mass);
            }
            writer.flush();
            writer.close();
            outputStream.close();
        }catch (IOException e) {
            System.out.println("Error creating Static Input");
            e.printStackTrace();
        }
    }

    private void generateDynamic(int N, int L){
        try {
            OutputStream outputStream = new FileOutputStream(new File("resources/RandomDynamicInput.txt"));
            OutputStream velocityT_0 = new FileOutputStream(new File("resources/velocityT_0.txt"));

            PrintWriter writer = new PrintWriter(outputStream);
            PrintWriter writerVelocity = new PrintWriter(velocityT_0);


            writer.println("0"); //time of dynamic particles.
            //creamos una lista para ir guardando sus posiciones
            //primero seteamos el objecto más grande
            String x = String.format(Locale.US, "%.4f", L / 2.0) + "\t";
            String y = String.format(Locale.US, "%.4f", L / 2.0) + "\t";
            String vx = String.format(Locale.US, "%.4f", 0.0) + "\t";
            String vy = String.format(Locale.US, "%.4f", 0.0);
            generated.add(new Particle(L/2.0,L/2.0, 0.0, 0.0, 0.7, 0.0, 0.0));
            //ahora seteamos los objetos más pequeños
            writer.println(x + y + vx + vy);
            double xpos, ypos;
            double xvel, yvel;
            double speed;
            for (int i = 1; i < N; i++) {
                //generar posiciones x e y hasta que se encuentre una que no colisione
                do {
                    xpos = ThreadLocalRandom.current().nextDouble(0.2, L - 0.2);
                    ypos = ThreadLocalRandom.current().nextDouble(0.2, L - 0.2);
                }while(checkIfCollision(xpos, ypos));
                //generar velocidades aleatorias, módulo de rapidez < 2
                xvel = ThreadLocalRandom.current().nextDouble(-1, 1);
                yvel = ThreadLocalRandom.current().nextDouble(-1, 1);
                speed = ThreadLocalRandom.current().nextDouble(0, 2);
                xvel *= speed;
                yvel *= speed;
                writer.println(
                        String.format(Locale.US, "%6.7e", xpos) + "\t" +
                                String.format(Locale.US, "%6.7e", ypos) + "\t" +
                                String.format(Locale.US, "%6.7e", xvel) + "\t" +
                                String.format(Locale.US, "%6.7e", yvel)
                );
                writerVelocity.println(
                        String.format(Locale.US, "%6.7e", Math.sqrt(xvel*xvel+yvel*yvel))+" ");

                generated.add(new Particle(xpos, ypos, xvel, yvel, 0.2, 0.0, 0.0));
            }
            writer.flush();
            writer.close();
            outputStream.close();
            writerVelocity.flush();
            writerVelocity.close();
            velocityT_0.close();
        } catch (IOException e) {
            System.out.println("Error creating Dynamic Input");
        }
    }

    private boolean checkIfCollision(double x, double y){
        double aux;
        for(Particle p : generated){
            aux = Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2) - Math.pow(0.2 + p.getRadius(), 2);
            if(aux <= 0) return true;
        }
        return false;
    }


    private boolean checkParams(int N, int L){
        if(N <= 1 || N >= 150){
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
