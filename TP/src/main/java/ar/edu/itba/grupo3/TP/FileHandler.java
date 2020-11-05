package ar.edu.itba.grupo3.TP;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class FileHandler{

    private String basePath;
    private String staticInputFile = "RandomStaticInput";
    private String dynamicInputfile = "RandomDynamicInput";
    private final String dynamicFile = "dynamicOutput";
    private final String velocity = "velocity";
    private final String position = "position";

    public FileHandler(String basePath){
        this.basePath = basePath;
        staticInputFile = basePath + "/" + staticInputFile + ".txt";
        dynamicInputfile = basePath + "/" + dynamicInputfile + ".txt";
    }

    public SimInfo loadData(){
        SimInfo info = loadStaticFile();
        return loadDynamicFile(info);
    }

    public SimInfo loadStaticFile(){
        SimInfo ret = new SimInfo();
        List<Particle> allParticles = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(staticInputFile)));
            ret.setN(Integer.parseInt(br.readLine()));          //N
            ret.setL(Double.parseDouble(br.readLine()));        //L
            ret.setH(Double.parseDouble(br.readLine()));        //H
            ret.setRmin(Double.parseDouble(br.readLine()));     //rmin
            ret.setRmax(Double.parseDouble(br.readLine()));     //rmax
            ret.setDmax(Double.parseDouble(br.readLine()));     //dmax
            ret.setVe(Double.parseDouble(br.readLine()));       //ve
            ret.setTau(Double.parseDouble(br.readLine()));      //tau
            ret.setBeta(Double.parseDouble(br.readLine()));     //beta
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //cargar las partículas
        for (int i = 0; i < ret.getN(); i++) allParticles.add(new Particle(ret.getRmin(), 1d, i));
        ret.setAllParticles(allParticles);
        return ret;
    }

    public SimInfo loadDynamicFile(SimInfo info){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(dynamicInputfile)));
            String s;
            br.readLine(); //ignore first line
            //particles
            int index = 0;
            Particle aux;
            while ((s = br.readLine()) != null) {
                String[] position = s.split("\t");
                aux = info.getAllParticles().get(index);
                aux.setX(Double.parseDouble(position[0]));
                aux.setY(Double.parseDouble(position[1]));
                index++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return info;
    }

    public void saveDynamic(String filename, SimInfo info, int i, boolean ovitoGraph){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(basePath + "/" + filename + ".txt"), true));
            if(ovitoGraph) {
                writer.write(Integer.toString(info.getN() + 4)); //cant particle mas las 4 para fijar los bordes
                writer.newLine();
            }
            writer.write(String.valueOf(i)); //time
            writer.newLine();
            //necesito 4 particulas de radio 0 para "fijar" los bordes en ovito
            if(ovitoGraph) {
                writer.write(0 + "    " + 0 + "    " + 0.01 + "    " + 0);
                writer.newLine();
                writer.write(0 + "    " + info.getL() + "    " + 0.01 + "    " + 0);
                writer.newLine();
                writer.write(info.getH() + "    " + 0 + "    " + 0.01 + "    " + 0);
                writer.newLine();
                writer.write(info.getH() + "    " + info.getL() + "    " + 0.01 + "    " + 0);
                writer.newLine();
            }
            for (Particle p : info.getAllParticles()) {
                Double aux = Math.sqrt(Math.pow(p.getVx(), 2) + Math.pow(p.getVy(), 2));
                String builder =
                        String.format(Locale.US, "%6.7e", p.getX()) + "    " +
                                String.format(Locale.US, "%6.7e", p.getY()) + "    " +
                                String.format(Locale.US, "%6.7e", p.getRadius()) + "    " +
                                String.format(Locale.US, "%6.7e", p.getSpeed());
                writer.write(builder);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void savePosition(List<Particle> particles, String filename){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(basePath + "/" + filename + ".tsv"), true));
            for (Particle p : particles) {
                String builder =
                        String.format(Locale.US, "%6.7e", p.getX()) + "    " +
                                String.format(Locale.US, "%6.7e", p.getY()) + "    ";
                writer.write(builder);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveVelocity(List<Particle> particles, String filename){
        saveVelocityIndexed(particles, filename, 0);
    }

    public void saveVelocityIndexed(List<Particle> particles, String filename, long i){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(basePath + "/" + filename + ".tsv"), true));
            writer.write(String.valueOf(i));
            writer.newLine();
            for (Particle p : particles) {
                String builder = String.format(Locale.US, "%6.7e", p.getVx()) + "    " +
                        String.format(Locale.US, "%6.7e", p.getVy());
                writer.write(builder);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveSpeed(List<Particle> l){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(basePath + "/" + velocity + ".tsv"), true));
            for (Particle p : l) {
                writer.write(String.format(Locale.US, "%6.7e", (p.realSpeed())));
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveSpeedAVG(List<Particle> l){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(basePath + "/" + velocity +"_"+Integer.toString(l.size())+ ".tsv"), true));
            Double speedSUM=0.0;
            for (Particle p : l) {
                speedSUM+=p.getSpeed();
            }
            writer.write(String.format(Locale.US, "%6.7e", speedSUM/(double)l.size()));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void saveData(String file, double idx, double data){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(basePath + "/" + file), true));
            String str = idx + "    " + String.format(Locale.US, "%6.7e", data);
            writer.write(str);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}