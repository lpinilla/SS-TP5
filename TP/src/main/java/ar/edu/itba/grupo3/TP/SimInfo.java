package ar.edu.itba.grupo3.TP;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SimInfo {

    private int N; //numero total de particulas
    private int L; //ancho de pasillo
    private double rmin;
    private double rmax;
    private double dmax;
    private double tau;
    private double betha;
    private List<Particle> allParticles;

    public SimInfo(){
        this.N = 0;
        this.L = 0;
        this.rmin = 0;
        this.rmax = 0;
        this.dmax = 0;
        this.tau  = 0;
        this.betha = 0;
        this.allParticles = new ArrayList<>();
    }


}