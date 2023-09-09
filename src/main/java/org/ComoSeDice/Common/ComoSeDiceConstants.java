package org.ComoSeDice.Common;

/** Class that contains constants that will be used throughout the Como Se Dice game. */
public class ComoSeDiceConstants {
  public static final String COMO_SE_DICE_MESSAGE = "Como se dice %s?";
  public static final String WORD_IS_INCORRECT_MESSAGE = "Sorry, asi no se dice. Try again!";
  public static final String SCORE_OF_PLAYER_ONE = "%s : %o  Lives : %o  Retries : %o";
  public static String RAN_OUT_OF_LIVES_MESSAGE = "Oh no, you have ran out of lives :(";
  public static String WINNER_MESSAGE = "Yay, ganaste!";

  public static String RULES_MESSAGE =
      """
        1. You will given a word in English and you have to spell
        it out in Spanish.
        2. You will be given 3 lives and 3 retries. You will lose a
        life everytime you guess incorrectly. Retries mean you can
        generate a new word to guess.
        3. You need 5 points to win.
        4. Have fun :)!!

        Note: Some of the words that will be used have accent
        marks in their spanish form, however, for now we will
        be ignoring accent marks.""";

  public static String NORMAL_GAME_RULES =
      """
     You will be given 3 lives, 3 opportunities to generate a new
     word (retries), and you have to get 5 points to win :).""";
}
