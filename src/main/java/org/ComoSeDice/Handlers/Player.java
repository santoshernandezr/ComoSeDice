package org.ComoSeDice.Handlers;

/**
 * This class is to instantiate the players that will be playing "Como se dice" jueguito. This will
 * store their name, score, lives. This will make it easier to access this information throughout
 * other classes.
 */
public class Player {
  // The name of the player.
  public final String name;

  /*
  This will keep track of how many words the players have gotten right. By default, everyone
  starts with 0 points.
  */
  public int score;

  /*
  This will keep track of the players lives. By default, everyone starts with 3 lives.
  */
  public int lives;

  public Player(String name) {
    this(name, 0, 3);
  }

  public Player(String name, int score, int lives) {
    this.name = name;
    this.score = score;
    this.lives = lives;
  }

  /** Will add a point to the players score. */
  public void addPoint() {
    score += 1;
  }

  /** Will remove a life from the players lives. */
  public void removeLife() {
    lives -= 1;
  }

  /** Resets score and lives to 0, 0. */
  public void reset() {
    score = 0;
    lives = 3;
  }
}
