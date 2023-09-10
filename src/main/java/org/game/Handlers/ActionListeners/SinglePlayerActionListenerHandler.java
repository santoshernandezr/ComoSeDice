package org.game.Handlers.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.commons.lang3.StringUtils;
import org.game.Common.ComoSeDiceConstants;
import org.game.Common.ComoSeDiceEnum;
import org.game.GameModes.SinglePlayer;
import org.game.Handlers.Player;
import org.springframework.stereotype.Component;

/**
 * This class is to handle all the buttons (Action Listeners) that will be used in the {@link
 * org.game.GUI.SinglePlayerGUI}.
 */
@Component
public class SinglePlayerActionListenerHandler implements ActionListener {

  /**
   * This action listener is for the "Submit" button. This button will call the method {@link
   * SinglePlayerActionListenerHandler#checkWord(JButton, JButton, JButton, JLabel, JLabel, JLabel,
   * JLabel, JLabel, JTextField, Player)} to check the guess of the user to see if it is the correct
   * spanish word and then determine how the GUI will be updated according to the scenario/case of
   * the game.
   *
   * @param submitButton JButton that stores the "submit" button.
   * @param newWordButton JButton that stores the "new word" button.
   * @param playAgainButton JButton that stores the "play again" button.
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param winnerLabel JLabel that stores the "Ganaste" message.
   * @param ranOutOfLivesLabel JLabel that stores the "Ran out of lives" message.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   * @return ActionListener to be used for the submit button.
   */
  public ActionListener submitButton(
      JButton submitButton,
      JButton newWordButton,
      JButton playAgainButton,
      JLabel comoSeDiceLabel,
      JLabel incorrectLabel,
      JLabel scoreLabel,
      JLabel winnerLabel,
      JLabel ranOutOfLivesLabel,
      JTextField guess,
      Player player) {

    ActionListener submit =
        e ->
            checkWord(
                submitButton,
                newWordButton,
                playAgainButton,
                comoSeDiceLabel,
                incorrectLabel,
                scoreLabel,
                winnerLabel,
                ranOutOfLivesLabel,
                guess,
                player);
    return submit;
  }

  /**
   * This action listener is for the "New word" button. It will first deduct a retry from the
   * player, and then check if the amount of retries the player has. If the player has 0 retries, it
   * will hide the "New word" button, so they can no longer generate a new word while playing. If
   * they still have retries, it will generate a new word by calling {@link
   * SinglePlayerActionListenerHandler#getNewWord(JLabel, JLabel, JLabel, JLabel, JTextField)} and
   * update the GUI accordingly.
   *
   * @param newWordButton JButton that stores the "new word" button.
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param winnerLabel JLabel that stores the "Ganaste" message.
   * @param ranOutOfLivesLabel JLabel that stores the "Ran out of lives" message.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   * @return Action Listener to be used for the "New word" button.
   */
  public ActionListener newWordButton(
      JButton newWordButton,
      JLabel comoSeDiceLabel,
      JLabel incorrectLabel,
      JLabel scoreLabel,
      JLabel winnerLabel,
      JLabel ranOutOfLivesLabel,
      JTextField guess,
      Player player) {

    ActionListener newWord =
        e -> {
          // Deduct a retry from the player
          player.deductRetry();

          updateScoreLabel(scoreLabel, player);
          // If the player has no retries left, hide the "New word" button.
          if (player.retries <= 0) {
            getNewWord(comoSeDiceLabel, incorrectLabel, winnerLabel, ranOutOfLivesLabel, guess);
            newWordButton.setVisible(false);
          } else { // If the player has retries left, generate a new word.
            getNewWord(comoSeDiceLabel, incorrectLabel, winnerLabel, ranOutOfLivesLabel, guess);
          }
        };
    return newWord;
  }

  /**
   * This action listener is for the "Play again" button. This button will get a new word by calling
   * {@link SinglePlayerActionListenerHandler#getNewWord(JLabel, JLabel, JLabel, JLabel,
   * JTextField)}, reset the players score and lives back to 0 and 3 respectively by calling {@link
   * Player#reset()}. This will also empty out the {@link SinglePlayer#wordsAlreadyUsed} list which
   * contains all the words used during the single player session. It will also update the GUI
   * accordingly, meaning that it will hide the "Play again" button and show the "Submit" and "New
   * word" button.
   *
   * @param submitButton JButton that stores the "submit" button.
   * @param newWordButton JButton that stores the "new word" button.
   * @param playAgainButton JButton that stores the "play again" button.
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param winnerLabel JLabel that stores the "Ganaste" message.
   * @param ranOutOfLivesLabel JLabel that stores the "Ran out of lives" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   * @return Action listener to be used for the "Play again" button.
   */
  public ActionListener playAgainButton(
      JButton submitButton,
      JButton newWordButton,
      JButton playAgainButton,
      JLabel comoSeDiceLabel,
      JLabel incorrectLabel,
      JLabel winnerLabel,
      JLabel ranOutOfLivesLabel,
      JLabel scoreLabel,
      JTextField guess,
      Player player) {

    ActionListener newWord =
        e -> {
          getNewWord(comoSeDiceLabel, incorrectLabel, winnerLabel, ranOutOfLivesLabel, guess);
          player.reset();
          submitButton.setVisible(true);
          newWordButton.setVisible(true);
          playAgainButton.setVisible(false);

          updateScoreLabel(scoreLabel, player);
          SinglePlayer.resetWords();
        };
    return newWord;
  }

  /**
   * This method will get a new english word from {@link ComoSeDiceEnum} and set it as the new word
   * to guess. After getting the new english word we will update the following labels:
   * <li>COMO_SE_DICE_LABEL: updated to include the new englishWord.
   * <li>WORD_IS_INCORRECT_LABEL: updated setVisible to false, so the label won't show.
   * <li>WINNER_LABEL: updated setVisible to false, so label won't show.
   * <li>RAN_OUT_OF_LIVES_LABEL: updated setVisible to false, so label won't show.
   *
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param winnerLabel JLabel that stores the "Ganaste" message.
   * @param ranOutOfLivesLabel JLabel that stores the "Ran out of lives" message.
   * @param guess JLabel that has the users guess.
   */
  public void getNewWord(
      JLabel comoSeDiceLabel,
      JLabel incorrectLabel,
      JLabel winnerLabel,
      JLabel ranOutOfLivesLabel,
      JTextField guess) {
    // Gets a new random word to guess.
    SinglePlayer.setWordToGuess();

    /*
     Updates the como se dice LABELs text which contains the como se dice MESSAGE to include the
     new word to guess.
    */
    comoSeDiceLabel.setText(
        String.format(ComoSeDiceConstants.COMO_SE_DICE_MESSAGE, SinglePlayer.wordToGuess));
    comoSeDiceLabel.setVisible(true);

    // Sets the guess text field to an empty string so the user can guess again.
    guess.setText("");

    // Setting all the labels setVisible to false since we don't want to show these.
    incorrectLabel.setVisible(false);
    winnerLabel.setVisible(false);
    ranOutOfLivesLabel.setVisible(false);
  }

  /**
   * This method will update the GUI to show the "Play again" button and hide the "Submit" and the
   * "New word" button. This will only happen in two scenarios.
   * <li>When the player wins the game.
   * <li>When the player has run out of lives.
   *
   * @param submitButton JButton that stores the "submit" button.
   * @param newWordButton JButton that stores the "new word" button.
   * @param playAgainButton JButton that stores the "play again" button.
   */
  public void setButtonsWhenPlayerWinsOrRunsOutOfLives(
      JButton submitButton, JButton newWordButton, JButton playAgainButton) {
    playAgainButton.setVisible(true);
    submitButton.setVisible(false);
    newWordButton.setVisible(false);
  }

  /**
   * This method is used by the "Submit" button and when the player presses the enter button. This
   * button will check the guess of the user to see if it is the correct spanish word and then
   * determine how the GUI will be updated according to the scenario/case of the game.
   *
   * @param submitButton JButton that stores the "submit" button.
   * @param newWordButton JButton that stores the "new word" button.
   * @param playAgainButton JButton that stores the "play again" button.
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param winnerLabel JLabel that stores the "Ganaste" message.
   * @param ranOutOfLivesLabel JLabel that stores the "Ran out of lives" message.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   */
  public void checkWord(
      JButton submitButton,
      JButton newWordButton,
      JButton playAgainButton,
      JLabel comoSeDiceLabel,
      JLabel incorrectLabel,
      JLabel scoreLabel,
      JLabel winnerLabel,
      JLabel ranOutOfLivesLabel,
      JTextField guess,
      Player player) {
    incorrectLabel.setVisible(false);
    ranOutOfLivesLabel.setVisible(false);

    boolean wordIsCorrect = checkNormalOrHardMode(SinglePlayer.hardMode, guess.getText());

    /*
    If the players guess was correct we give a point to the player and checks whether they have
    reached the winning score.
    */
    if (wordIsCorrect) {

      // Give the player a point.
      player.addPoint();

      /*
      If the player got the word correct but did not reach the winning score we will get a new word and
      call getNewWord() to update the GUI accordingly.
       */
      getNewWord(comoSeDiceLabel, incorrectLabel, winnerLabel, ranOutOfLivesLabel, guess);

      /*
      If the player got the word correct and has reached the winning score, then we will show the
      winning label to indicate that they have won and call setButtonsWhenPlayerWinsOrRunsOutOfLives()
      to update GUI accordingly.
       */
      if (player.score == SinglePlayer.winnerScore) {
        comoSeDiceLabel.setVisible(false);
        winnerLabel.setVisible(true);
        setButtonsWhenPlayerWinsOrRunsOutOfLives(submitButton, newWordButton, playAgainButton);
      }
    }
    /*
    If the players guess was NOT correct we deduct a life from the player and checks whether they have
    run out of lives.
    */
    else {
      // Removing a life from the player.
      player.removeLife();

      // Since the guess was incorrect, we do not show the winner label.
      winnerLabel.setVisible(false);

      /*
      If the players guess was NOT correct, but they have not yet ran out of lives, then we will show
      the incorrect label indicating their guess is wrong, but not show the ran out of lives label.
       */
      incorrectLabel.setVisible(true);
      ranOutOfLivesLabel.setVisible(false);

      /*
      If the players guess was NOT correct and have no lives left. We will not show the incorrectLabel
      and instead show the ran out of lives label indicating they ran out of lives. We will also call
      setButtonsWhenPlayerWinsOrRunsOutOfLives() to update the GUI accordingly.
       */
      if (player.lives <= 0) {
        incorrectLabel.setVisible(false);
        ranOutOfLivesLabel.setVisible(true);

        setButtonsWhenPlayerWinsOrRunsOutOfLives(submitButton, newWordButton, playAgainButton);
      }
    }

    updateScoreLabel(scoreLabel, player);
  }

  /**
   * This method will update the score label, so it will update {@link
   * ComoSeDiceConstants#SCORE_OF_PLAYER_ONE}
   *
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param player Instance of the current player.
   */
  public void updateScoreLabel(JLabel scoreLabel, Player player) {
    scoreLabel.setText(
        String.format(
            ComoSeDiceConstants.SCORE_OF_PLAYER_ONE,
            player.name,
            player.score,
            player.lives,
            player.retries));
  }

  /**
   * Checking whether we are playing Normal or Hard mode. If we are playing Normal mode, we ignore
   * the accent marks. If we are playing Hard mode we validate the word with the accent marks.
   *
   * @param hardMode boolean indicating whether hard mode is true or false.
   * @param word word that we are validating.
   * @return boolean whether the word was correct depending on the validation.
   */
  public boolean checkNormalOrHardMode(Boolean hardMode, String word) {
    if (hardMode) {
      return word.equalsIgnoreCase(SinglePlayer.wordToGuess.getSpanish());
    }
    return word.equalsIgnoreCase(StringUtils.stripAccents(SinglePlayer.wordToGuess.getSpanish()));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of making the class happy.
  }
}
