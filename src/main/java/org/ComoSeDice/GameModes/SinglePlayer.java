package org.ComoSeDice.GameModes;

import java.util.ArrayList;
import java.util.List;
import org.ComoSeDice.Common.ComoSeDiceEnum;

/**
 * This class is to set the rules for single player mode. This contains the amount of correct words
 * needed to win the single player game. This also stores the word that is being currently guessed
 * in the game, {@link SinglePlayer#wordToGuess}, so it can be used throughout the game and accessed
 * across multiple classes.
 */
public class SinglePlayer {
  // This is the word to guess during the single player session.
  public static ComoSeDiceEnum wordToGuess;

  // Stores the amount a words a player has to get right in order for them to win.
  public static final int winnerScore = 5;

  // List that stores the words that have already been guessed.
  public static List<ComoSeDiceEnum> wordsAlreadyUsed = new ArrayList<>();

  /**
   * Will get a random word from {@link ComoSeDiceEnum} and checks to see that it has
   * not already been used. If it has been used then we call the function again to regenerate a
   * word, if it hasn't been used then we add it to {@link SinglePlayer#wordsAlreadyUsed} and set
   * the random word to {@link SinglePlayer#wordToGuess}.
   */
  public static void setWordToGuess() {
    // Get a random word.
    wordToGuess = ComoSeDiceEnum.getRandom();

    // If the word has already been used then we will get another one by calling same method.
    if (wordsAlreadyUsed.contains(wordToGuess)) {
      setWordToGuess();
    } else {
      // If the word has not been used then we add it to the wordsAlreadyUsed list.
      wordsAlreadyUsed.add(wordToGuess);
    }
  }

  /**
   * Will clear {@link SinglePlayer#wordsAlreadyUsed} list which contains all the words already used
   * to ensure that no word is used twice.
   */
  public static void resetWords() {
    wordsAlreadyUsed.clear();
  }
}
