//ShrinkBall.java
import java.awt.Color;
import java.util.ArrayList;

// ShrinkBall is a subclass of BasicBall that shrinks after each hit
public class ShrinkBall extends BasicBall {
    private double initialRadius; // store the initial radius

    // Constructor
    public ShrinkBall(double r, Color c) {
        super(r, c, "shrink");
        initialRadius = r; // store the initial radius
    }

    // Override reset method to make the ball shrink
    @Override
    public ArrayList<BasicBall> reset() {
        super.reset();
        double newRadius = radius * 2.0 / 3.0; // calculate the new radius
        
        // Set the radius to the initial radius if it's too small
        if (newRadius <= (initialRadius * 0.25)) {
            radius = initialRadius;
        } else {
            radius = newRadius;
        }
        return new ArrayList<>(); // Return an empty ArrayList<BasicBall>
    }

    // Override getScore method to return a different score for ShrinkBall
    @Override
    public int getScore() {
        return 20;
    }
}
