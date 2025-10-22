package module8;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    private double rx, ry;
    private double vx, vy;
    private final double radius;

    public Ball(double rx, double ry, double vx, double vy, double radius) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
    }

    public void move(double dt) {
        final double newRX = rx + vx * dt;
        final double newRY = ry + vy * dt;

        if (newRX < radius || newRX > 1.0 - radius) vx *= -1;
        if (newRY < radius || newRY > 1.0 - radius) vy *= -1;

        rx = newRX;
        ry = newRY;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}