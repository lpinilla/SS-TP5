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
    private List<Particle> allParticles;

    public SimInfo(){
        this.N = 0;
        this.L = 0;
        this.allParticles = new ArrayList<>();
    }


}