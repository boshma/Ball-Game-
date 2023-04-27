import java.awt.Color;
import java.util.ArrayList;

// SplitBall is a subclass of BasicBall that splits into two smaller balls after each hit
public class SplitBall extends BasicBall {

    // Constructor
    public SplitBall(double r, Color c) {
        super(r, c, "split");
    }

    // Override reset method to create two new smaller balls
    @Override
    public ArrayList<BasicBall> reset() {
        ArrayList<BasicBall> newBalls = new ArrayList<>();

        // Create two new balls
        SplitBall newBall1 = new SplitBall(radius, color);
        SplitBall newBall2 = new SplitBall(radius, color);

        // Set the initial position and velocity of the new balls
        newBall1.rx = this.rx;
        newBall1.ry = this.ry;
        newBall2.rx = this.rx;
        newBall2.ry = this.ry;

        newBall1.vx = StdRandom.uniform(-0.01, 0.01);
        newBall1.vy = StdRandom.uniform(-0.01, 0.01);
        newBall2.vx = StdRandom.uniform(-0.01, 0.01);
        newBall2.vy = StdRandom.uniform(-0.01, 0.01);
        // Add the new balls to the ArrayList
        newBalls.add(newBall1);
        newBalls.add(newBall2);

        // Mark the original ball as out
        this.isOut = true;

        // Return the ArrayList containing the new balls
        return newBalls;
    }

    // Override getScore method to return a different score for SplitBall
    @Override
    public int getScore() {
        return 10;
    }
}
