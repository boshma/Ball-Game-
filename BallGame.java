
/******************************************************************************
 *  Compilation:  javac BallGame.java
 *  Execution:    java BallGame n
 *  Dependencies: BasicBall.java StdDraw.java
 *
 *  Creates a BasicBall and animates it
 *
 *  Part of the animation code is adapted from Computer Science: An Interdisciplinary Approach Book
 *  
 *  Run the skeleton code with arguments : 1  basic  0.08
 *******************************************************************************/
// Import necessary classes
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

// Class for BallGame
public class BallGame {

    public static void main(String[] args) {

        // Initialize variables
        long lastClickTime = 0;
        int numBalls = Integer.parseInt(args[0]);
        String ballTypes[] = new String[numBalls];
        double ballSizes[] = new double[numBalls];

        // Process command-line arguments
        int index = 1;
        for (int i = 0; i < numBalls; i++) {
            ballTypes[i] = args[index];
            index = index + 2;
        }

        index = 2;
        for (int i = 0; i < numBalls; i++) {
            ballSizes[i] = Double.parseDouble(args[index]);
            index = index + 2;
        }

        // Initialize player and number of balls in the game
        Player player = new Player();
        int numBallsinGame = 0;
        StdDraw.enableDoubleBuffering();

        // Set up canvas
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // Create ball objects and add them to the list
        ArrayList<BasicBall> balls = new ArrayList<>();
        for (int i = 0; i < numBalls; i++) {
            BasicBall ball;
            switch (ballTypes[i]) {
                case "shrink":
                    ball = new ShrinkBall(ballSizes[i], Color.RED);
                    break;
                case "split":
                    ball = new SplitBall(ballSizes[i], Color.YELLOW);
                    break;
                case "bounce":
                    ball = new BounceBall(ballSizes[i], Color.BLUE);
                    break;
                default:
                    ball = new BasicBall(ballSizes[i], Color.BLACK, ballTypes[i]);
                    break;
            }
            balls.add(ball);
            numBallsinGame++;
        }

        // Enable double buffering for smoother animations
        StdDraw.enableDoubleBuffering();

        // Main game loop
        while (numBallsinGame > 0) {

            // Move balls
            for (BasicBall ball : balls) {
                ball.move();
            }

            // Detect mouse clicks and update game state accordingly
            if (StdDraw.isMousePressed() && System.currentTimeMillis() - lastClickTime > 200) {
                lastClickTime = System.currentTimeMillis();
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();

                // Create a list of new balls that will replace the balls that are hit
                ArrayList<BasicBall> newBalls = new ArrayList<>();

                // Check if any balls are hit and update player score and stats
                for (BasicBall ball : balls) {
                    if (ball.isHit(x, y)) {
                        ArrayList<BasicBall> resetBalls = ball.reset();
                        newBalls.addAll(resetBalls);
                        player.addScore(ball.getScore());
                        switch (ball.ballType) {
                            case "shrink":
                                player.addShrinkHit();
                                break;
                            case "split":
                                player.addSplitHit();
                                break;
                            case "bounce":
                                player.addBounceHit();
                                break;
                            default:
                                player.addBasicHit();
                                break;
                        }
                    }
                }

                // Add new balls to the game
                balls.addAll(newBalls);
            }

            // Update number of balls in the game
            numBallsinGame = 0;
            StdDraw.clear(StdDraw.GRAY);
            // Update ball positions and draw them
            StdDraw.setPenColor(StdDraw.BLACK);
            for (BasicBall ball : balls) {
                if (!ball.isOut) {
                    ball.draw();
                    numBallsinGame++;
                }
            }

            // Display game information
            StdDraw.setPenColor(StdDraw.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.65, 0.80, "Number of balls in game: " + String.valueOf(numBallsinGame));
            int totalHits = player.getBasicHits() + player.getShrinkHits() + player.getBounceHits()
                    + player.getSplitHits();
            StdDraw.text(-0.65, 0.70, "Total Hits: " + totalHits);
            StdDraw.text(-0.65, 0.60, "Score: " + player.getScore());

            // Refresh display
            StdDraw.show();
            StdDraw.pause(20);
        }

        // Game over loop
        while (true) {
            // Display game over message and final statistics
            StdDraw.setPenColor(StdDraw.BLUE);
            Font font = new Font("Arial", Font.BOLD, 60);
            StdDraw.setFont(font);
            StdDraw.text(0, 0, "GAME OVER");

            StdDraw.setPenColor(StdDraw.YELLOW);
            font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(0, -0.1, "Score: " + player.getScore());
            StdDraw.text(0, -0.2, "Basic Hits: " + player.getBasicHits());
            StdDraw.text(0, -0.3, "Shrink Hits: " + player.getShrinkHits());
            StdDraw.text(0, -0.4, "Bounce Hits: " + player.getBounceHits());
            StdDraw.text(0, -0.5, "Split Hits: " + player.getSplitHits());

            // Refresh display
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
