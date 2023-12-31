package org.game.Common;

/** Class that contains constants that will be used throughout the Como Se Dice game. */
public class ComoSeDiceConstants {
  public static final String COMO_SE_DICE_MESSAGE = "Como se dice %s?";
  public static final String WORD_IS_INCORRECT_MESSAGE = "Sorry, asi no se dice. Try again!";
  public static final String SCORE_OF_PLAYER_ONE = "%s : %s  Lives : %s  Retries : %s";
  public static final String TIMED_MODE_SCORE = "%s's points : %s      Best Score: %s";
  public static final String NEW_HIGH_SCORE_TIMED_MODE = "New High Score: %s";
  public static String RAN_OUT_OF_LIVES_MESSAGE = "Oh no, you have ran out of lives :(";
  public static String WINNER_MESSAGE = "Yay, ganaste!";

  public static String RULES_MESSAGE =
      """
     1. You will given a word in English and you have to spell
         it out in Spanish.
     2. You will be given x amount of lives and y amount of
         retries depending on the game mode. You will lose a life
         everytime you guess incorrectly. Retries mean you can
         generate a new word to guess.
     3. Have fun :)!!

        Note: Accent marks will be ignoring until we implement
        hard mode.""";

  public static String NORMAL_GAME_RULES =
      """
     You will be given 3 lives, 3 opportunities to generate a new
     word (retries), and you have to get 5 points to win :).""";

  public static String HARD_MODE_GAME_RULES =
      """
     Same rules as in Normal Mode, but accent marks are
     REQUIRED in this mode :).""";

  public static String TIMED_MODE_RULES =
      """
     You will have 60 seconds to get as many points as possible.
     Every word you get right is a point.""";
}
