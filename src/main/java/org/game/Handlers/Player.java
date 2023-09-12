package org.game.Handlers;

/**
 * This class is to instantiate the players that will be playing "Como se dice" jueguito. This will
 * store their name, score, lives. This will make it easier to access this information throughout
 * other classes.
 */
public class Player {
  // The name of the player.
  public String name;

  /*
  This will keep track of how many words the players have gotten right. By default, everyone
  starts with 0 points.
  */
  public int score;

  // This will keep track of the players lives. By default, everyone starts with 3 lives.
  public int lives;

  // This will keep track of the players retries. By default, everyone starts with 3 retries.
  public int retries;

  // This will keep track of the players best score in the timed mode.
  public int bestTimedScore = 0;

  public Player() {
    this(null, 0, 3, 3);
  }

  public Player(String name, int score, int lives, int retries) {
    this.name = name;
    this.score = score;
    this.lives = lives;
    this.retries = retries;
  }

  /** Will add a point to the players score. */
  public void addPoint() {
    score += 1;
  }

  /** Will remove a life from the players lives. */
  public void removeLife() {
    lives -= 1;
  }

  /** Deduct a retry from the player. */
  public void deductRetry() {
    retries -= 1;
  }

  /** Resets score, lives, and retries to 0, 3, 3. */
  public void reset() {
    score = 0;
    lives = 3;
    retries = 3;
  }

  /**
   * Set the player name.
   *
   * @param name players name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the players best score.
   *
   * @return players best score.
   */
  public int getBestTimedScore() {
    return this.bestTimedScore;
  }

  /**
   * Set new best score for timed mode.
   *
   * @param bestScore best score.
   */
  public void setBestTimedScore(int bestScore) {
    this.bestTimedScore = bestScore;
  }
}
