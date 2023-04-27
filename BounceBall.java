
//BounceBall.java
import java.awt.Color;

// BounceBall is a subclass of BasicBall that bounces off the wall
public class BounceBall extends BasicBall {
    private int bounceCount; // count of the bounces off the wall

    // Constructor
    public BounceBall(double r, Color c) {
        super(r, c, "bounce");
        bounceCount = 0; // initialize bounce count to 0
    }

    // Override move method to make the ball bounce
    @Override
    public void move() {
        // Reverse the x velocity if the ball hits the left or right wall
        if (Math.abs(rx + vx) > 1.0) {
            vx = -vx;
            bounceCount++; // increase bounce count
        }
        // Reverse the y velocity if the ball hits the top or bottom wall
        if (Math.abs(ry + vy) > 1.0) {
            vy = -vy;
            bounceCount++; // increase bounce count
        }
        // Set the ball as out if it has bounced three times or more
        if (bounceCount >= 3) {
            isOut = true;
            return;
        }
        // Update the ball's position
        rx += vx;
        ry += vy;
    }

    // Override getScore method to return a different score for BounceBall
    @Override
    public int getScore() {
        return 15;
    }
}
