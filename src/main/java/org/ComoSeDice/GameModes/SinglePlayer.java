package org.ComoSeDice.GameModes;

import java.util.ArrayList;
import java.util.List;
import org.ComoSeDice.ComoSeDiceEnum;

/**
 * This class is to set the rules for single player mode. This contains the amount of correct words
 * needed to win the single player game. This also stores the word that is being currently guessed
 * in the game, {@link SinglePlayer#wordToGuess} so it can be used throughout the game and accessed
 * across multiple classes.
 */
public class SinglePlayer {
  // This is the word to guess during the single player session.
  public static ComoSeDiceEnum wordToGuess;

  // Stores the amount a words a player has to get right in order for them to win.
  public static final int winnerScore = 5;

  public static List<ComoSeDiceEnum> wordsAlreadyUsed = new ArrayList<>();

  /**
   * Will get a random word from {@link org.ComoSeDice.ComoSeDiceEnum} and set it to the wordToGuess
   * variable.
   */
  public static void setWordToGuess() {
    wordToGuess = ComoSeDiceEnum.getRandom();
  }

  public static void resetWords() {
    wordsAlreadyUsed.clear();
  }
}
