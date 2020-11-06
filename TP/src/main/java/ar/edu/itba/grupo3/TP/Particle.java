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
        this.targetX = this.targetY = 0d;
        this.neighbours = new TreeSet<>();
        this.particlesSameCellList = new LinkedList<>();
        this.particlesSameCellList.add(this);
    }

    public Particle(Double radius, Double mass, Integer id) {
        this(id, 0d, 0d, 0d, 0d, radius, mass, 0d);
    }

    public Particle(Double x, Double y, Double r, Double angle) {
        this(0, x, y, 0d, 0d, r, 1d, angle);
    }

    @Override
    public String toString() {
        return this.getId().toString();
    }

    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) return false;
        if (o == this) return true;
        Particle p = (Particle) o;
        return this.id.equals(p.id);
    }

    @Override
    public int compareTo(Particle particle) {
        return this.getId().compareTo(particle.getId());
    }

    public List<Particle> getParticlesFromCell() {
        return particlesSameCellList;
    }

    public double angleBetweenParticle(Particle p) {
        double deltaX = p.getX() - this.x;
        double deltaY = p.getY() - this.y;
        return Math.atan2(deltaY, deltaX);
    }

    public double distanceToPoint(double x, double y) {
        double deltaX = x - this.x;
        double deltaY = y - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public double distanceToParticle(Particle p) {
        return distanceToPoint(p.getX(), p.getY());
    }

    public double realSpeed() {
        return Math.sqrt(this.vx * this.vx + this.vy * this.vy);
    }

    public double[] velocityVersor() {
        double speed = realSpeed();
        return new double[]{(vx / speed), (vy / speed)};
    }

    public void setX(double x) {
        this.prevX = this.getX();
        this.x = x;
    }

    public void setY(double y) {
        this.prevY = this.getY();
        this.y = y;
    }

    public void findTarget(){
        setTargetX(x);
        setTargetY(y + 2d);
    }

    public void setNextTarget(){
        double deltaX = targetX - x;
        double deltaY = targetY - y;
        setVx(Math.signum(deltaX));
        setVy(Math.signum(deltaY));
    }

    public void updateRadius(double rmax, double tau, double deltaT) {
        if(radius >= rmax){
            radius = rmax;
        }else{
            setRadius(radius + rmax * deltaT / tau);
        }
    }

    public void updateSpeed(double dmax, double rmin, double rmax, double beta) {
        setSpeed(dmax * Math.pow(((radius - rmin) / (rmax - rmin)), beta));
    }

    public void updatePosition(double deltaT, double L) {
        //movimiento en x
        setX(this.x + speed * vx * deltaT);
        //movimiento en Y
        setY((this.y + speed * vy * deltaT) % L);
    }

    public boolean isColliding(Particle p2){
        return distanceToParticle(p2) < (radius + p2.radius);
    }

    public int collisionWithWalls(double w){
        //colisión pared izquierda x = 0
        if(x < radius) return 1;
        //colisión pared derecha x = w
        if(w - x < radius) return 2;
        return 0;
    }

    public void collide(double rmin, double ve, double vx, double vy){
        //cambiar el radio
        setRadius(rmin);
        //cambiar la rapidez
        setSpeed(ve);
        //cambiar los versores de la velocidad
        setVx(vx);
        setVy(vy);
    }

    public void collideWithParticle(Particle p, double rmin, double ve){
        //calcular versor hacia la partícula
        double deltaX = p.getX() - x;
        double deltaY = p.getY() - y;
        double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        //utilizar la dirección contraria
        collide(rmin, ve, -deltaX / length, -deltaY / length);
    }

    public void collideWithWall(int wcol, double rmin, double ve){
        //cambiar el versor x correspondiente depende de que pared era
        collide(rmin, ve, (wcol == 1)? 1d : -1d, vy);
    }


}
