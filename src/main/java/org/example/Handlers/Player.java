package org.example.Handlers;

/** This class is to instantiate the players that will be playing "Como se dice" jueguito. */
public class Player {
  // The name of the player.
  public final String name;

  /* This will keep track of how many words the players have gotten right. By default, everyone
  starts with 0 points. */
  public int score = 0;

  /* This will keep track of how many words the players have gotten wrong. By default, everyone
  starts with 0 points. */
  public int strikes = 0;

  public Player(String name) {
    this(name, 0, 0);
  }

  public Player(String name, int score, int strikes) {
    this.name = name;
    this.score = score;
    this.strikes = strikes;
  }

  /** Will add a point to the players score. */
  public void addPoint() {
    score += 1;
  }

  /** Will add a point to the players number of strikes. */
  public void addStrike() {
    strikes += 1;
  }

  /** Resets score and strikes */
  public void reset() {
    score = 0;
    strikes = 0;
  }
}
