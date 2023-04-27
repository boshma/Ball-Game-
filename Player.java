//Player.java
public class Player {
    private int score;
    private int basicHits;
    private int shrinkHits;
    private int bounceHits;
    private int splitHits;
    // Constructor
    public Player() {
        score = 0;
        basicHits = 0;
        shrinkHits = 0;
        bounceHits = 0;
        splitHits = 0;
    }
    // Add points to the player's score
    public void addScore(int points) {
        score += points;
    }
    // Add basic hits to the player's hits
    public void addBasicHit() {
        basicHits++;
    }
    // Add shrink hits to the player's hits
    public void addShrinkHit() {
        shrinkHits++;
    }
    // Add bounce hits to the player's hits
    public void addBounceHit() {
        bounceHits++;
    }
    // Add split hits to the player's hits
    public void addSplitHit() {
        splitHits++;
    }
    // Get the score
    public int getScore() {
        return score;
    }
    // Get the number of basic hits
    public int getBasicHits() {
        return basicHits;
    }
    // Get the number of shrink hits
    public int getShrinkHits() {
        return shrinkHits;
    }
    // Get the number of bounce hits
    public int getBounceHits() {
        return bounceHits;
    }
    // Get the number of split hits
    public int getSplitHits() {
        return splitHits;
    }
}
