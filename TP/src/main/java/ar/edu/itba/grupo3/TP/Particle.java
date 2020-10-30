package ar.edu.itba.grupo3.TP;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Particle implements Comparable<Particle> {
    private Integer id; //id of particle
    private Double x; //x position of particle
    private Double prevX;
    private Double y; //y position of particle
    private Double prevY;
    private Double vx; //x velocity of particle
    private Double vy; //y velocity of particle
    private Double radius; //radius of particle
    private Double mass; //mass of particle
    private Double angle; //value of angle
    private Double targetX;
    private Double targetY;
    private Set<Particle> neighbours; //list of neighbours
    private Double speed;
    private boolean collision;

    //Vamos a tener una lista de particulas general, la primer particula de la lista hace referencia a la particula "padre" ubicada en el casillero cero
    //la segunda particula del array hace referencia a la particula "padre" ubicada en el segundo casillero del tablero....
    //"padre" llamamos a la primer particula que esta ubicada en ese casillero. el next de esa particula, hace refernecia
    //a otra particula ubicada en el mismo casillero
    private List<Particle> particlesSameCellList;

    public Particle(int id, Double x, Double y, Double vx, Double vy, Double radius, Double mass, Double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
        this.radius = radius;
        this.angle = angle;
        this.prevX = 0.0;
        this.prevY = 0.0;
        this.speed = 0d;
        this.collision = false;
        this.neighbours = new TreeSet<>();
        this.particlesSameCellList = new LinkedList<>();
        this.particlesSameCellList.add(this);
    }

    public Particle(int id, Double x, Double y, Double radius) {
        new Particle(id, x, y,  0d, 0d, radius, 1d, 0d);
    }

    public Particle(Double x, Double y, Double vx, Double vy, Double radius, Double mass, Double angle) {
        new Particle(0, x, y, vx, vy, radius, mass, angle);
    }

    public Particle(Double radius, Integer id){
        new Particle(radius, 1.0d, id);
    }

    public Particle(Double radius, Double mass, Integer id){
        new Particle(id, 0d, 0d, 0d, 0d, radius, mass, 0d);
    }

    public Particle(Double radius, Double angle, Double mass, Integer id){
        new Particle(id, 0d, 0d, 0d, 0d, radius, mass, angle);
    }

    public Particle(Double x, Double y, Double r, Double angle){
        new Particle(0, x, y, 0d, 0d,  r, 1d, angle);
    }

    public String toString(){
        return this.getId().toString();
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        Particle p = (Particle) o;
        return this.id.equals(p.id);
    }

    @Override
    public int compareTo(Particle particle) {
        return this.getId().compareTo(particle.getId());
    }

    public List<Particle> getParticlesFromCell(){
        return particlesSameCellList;
    }

    public void moveAgent(float l){
        double xPos = (Math.cos(this.getAngle()) * speed) + this.getX();
        double yPos = (Math.sin(this.getAngle()) * speed) + this.getY();
        if(xPos < 0) xPos += l;
        if(yPos < 0) yPos += l;
        if(yPos > l) yPos %= l;
        if(xPos > l) xPos %= l;
        this.setX(xPos);
        this.setY(yPos);
    }

    public void calculateNewAngle(double randomVal){
        double sinAux = Math.sin(this.getAngle());
        double cosAux = Math.cos(this.getAngle());
        Set<Particle> neighbors = this.getNeighbours();
        for(Particle p : neighbors){
            sinAux += Math.sin(p.getAngle());
            cosAux += Math.cos(p.getAngle());
        }
        if(neighbors.size() > 0) {
            sinAux /= neighbors.size() + 1;
            cosAux /= neighbors.size() + 1;
        }
        double newProperty = Math.atan2(sinAux, cosAux);
        if(newProperty < 0) newProperty += Math.PI * 2;
        double finalProperty = newProperty + randomVal;
        if(finalProperty > Math.PI * 2) finalProperty -= 2 * Math.PI;
        if(finalProperty < 0) finalProperty += Math.PI * 2;
        this.setAngle(finalProperty);
    }

    public double angleBetweenParticle(Particle p){
        double deltaX = p.getX() - this.x;
        double deltaY = p.getY() - this.y;
        return Math.atan2(deltaY, deltaX);
    }

    public double distanceToPoint(double x, double y){
        double deltaX = x - this.x;
        double deltaY = y - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public double distanceToParticle(Particle p){
        return distanceToPoint(p.getX(), p.getY());
    }

    public double realSpeed(){
        return Math.sqrt(this.vx*this.vx+this.vy*this.vy);
    }

    public double[] velocityVersor(){
        double speed = realSpeed();
        return new double[] { (vx / speed), (vy / speed)};
    }

    public void setX(double x){
        this.prevX = this.getX();
        this.x = x;
    }

    public void setY(double y){
        this.prevY = this.getY();
        this.y = y;
    }

    public void updateRadius(double rmax, double tau, double deltaT){
        setRadius(this.radius + rmax * deltaT / tau);
    }

    public void updateSpeed(double dmax, double rmin, double rmax, double beta){
        if(this.radius <= rmax) {
            setSpeed(dmax * Math.pow(((this.radius - rmin) / (rmax - rmin)), beta));
        }else{
            setSpeed(dmax);
        }
    }

    public void updatePosition(double deltaT, double rmin, double ve){
        if(collision){
            setRadius(rmin);
            setSpeed(ve);
        }else {
            setX(this.x + vx * deltaT);
            setY(this.y + vy * deltaT);
        }
    }


}
