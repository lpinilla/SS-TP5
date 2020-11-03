package ar.edu.itba.grupo3.TP;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SimInfo {

    private int N; //numero total de particulas
    private double L; //ancho de pasillo
    private double H; //altura del pasillo
    private double rmin;
    private double rmax;
    private double dmax;
    private double ve;
    private double tau;
    private double beta;
    private List<Particle> allParticles;

    public SimInfo(){
        this.N = 0;
        this.L = 0;
        this.rmin = 0d;
        this.rmax = 0d;
        this.dmax = 0d;
        this.ve = 0d;
        this.tau  = 0d;
        this.beta = 0d;
        this.H = 0d;
        this.allParticles = new ArrayList<>();
    }


}