
/******************************************************************************
 *  Compilation:  javac ColoredBall.java
 *  Execution:    java ColoredBall
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d ball moving in square with coordinates
 *  between -1 and +1. Bounces off the walls upon collision.
 *  
 *
 ******************************************************************************/

import java.awt.Color;
import java.util.ArrayList;

public class BasicBall {
    protected double rx, ry; // ball's x and y position
    protected double vx, vy; // ball's x and y velocity
    protected double radius; // ball's radius
    protected final Color color; // ball's color
    public boolean isOut; // flag to check if the ball is out of bounds
    protected String ballType; // ball's type

    // constructor
    public BasicBall(double r, Color c, String type) {
        rx = 0.0; // initial x position
        ry = 0.0; // initial y position
        vx = StdRandom.uniform(-0.01, 0.01); // random x velocity
        vy = StdRandom.uniform(-0.01, 0.01); // random y velocity
        radius = r; // set ball's radius
        color = c; // set ball's color
        isOut = false; // set initial 'isOut' status to false
        ballType = type; // set ball's type
    }

    // move the ball one step
    public void move() {
        rx = rx + vx; // update x position
        ry = ry + vy; // update y position

        // check if the ball is out of bounds
        if ((Math.abs(rx) > 1.0) || (Math.abs(ry) > 1.0))
            isOut = true;
    }

    // draw the ball
    public void draw() {
        // check if the ball is within bounds
        if ((Math.abs(rx) <= 1.0) && (Math.abs(ry) <= 1.0)) {
            StdDraw.setPenColor(color); // set pen color
            StdDraw.filledCircle(rx, ry, radius); // draw filled circle
        } else {
            // set isOut flag to true if the ball is out of bounds
            isOut = true;
        }
    }

    // reset the ball position and velocity
    public ArrayList<BasicBall> reset() {
        ArrayList<BasicBall> newBalls = new ArrayList<>();
        rx = 0.0; // reset x position
        ry = 0.0; // reset y position
        vx = StdRandom.uniform(-0.01, 0.01); // random x velocity
        vy = StdRandom.uniform(-0.01, 0.01); // random y velocity
        return newBalls;
    }

    // check if the ball is hit by the player
    public boolean isHit(double x, double y) {
        // calculate distance between ball and hit point
        double distance = Math.sqrt(Math.pow(rx - x, 2) + Math.pow(ry - y, 2));
        double hitRadius = radius * 2.5; // hit detection radius

        // return true if hit, otherwise false
        if (distance <= hitRadius) {
            return true;
        } else {
            return false;
        }
    }

    // return the score for hitting the ball
    public int getScore() {
        return 25;
    }

    // return the ball's radius
    public double getRadius() {
        return radius;
    }

}
