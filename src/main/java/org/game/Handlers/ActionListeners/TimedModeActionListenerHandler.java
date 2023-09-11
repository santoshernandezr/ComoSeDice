package org.game.Handlers.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.commons.lang3.StringUtils;
import org.game.Common.ComoSeDiceConstants;
import org.game.GameModes.SinglePlayer;
import org.game.Handlers.Player;

/**
 * This class is to handle all the buttons (Action Listeners) that will be used in the {@link
 * org.game.GUI.TimedMode}.
 */
public class TimedModeActionListenerHandler implements ActionListener {

  /**
   * This action listener is for the "Submit" button. This button will call the method {@link
   * TimedModeActionListenerHandler#checkWord(JLabel, JLabel, JTextField, Player)} to check the
   * guess of the user to see if it is the correct spanish word and then determine how the GUI will
   * be updated according to the scenario/case of the game.
   *
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   * @return ActionListener to be used for the submit button.
   */
  public ActionListener submitButton(
      JLabel comoSeDiceLabel, JLabel scoreLabel, JTextField guess, Player player) {

    ActionListener submit =
        e -> {
          checkWord(comoSeDiceLabel, scoreLabel, guess, player);
        };
    return submit;
  }

  /**
   * This method is used by the "Submit" button and when the player presses the enter button. This
   * button will check the guess of the user to see if it is the correct spanish word. In the timed
   * game mode we will add a point if the word is correct and generate a new word everytime,
   * regardless if they got it right or wrong.
   *
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   */
  public void checkWord(
      JLabel comoSeDiceLabel, JLabel scoreLabel, JTextField guess, Player player) {
    if (guess
        .getText()
        .equalsIgnoreCase(StringUtils.stripAccents(SinglePlayer.wordToGuess.getSpanish()))) {
      // Give the player a point.
      player.addPoint();
      // Update score
      updateScoreLabel(scoreLabel, player);
    }

    // Get a new word regardless if they got it right or wrong.
    getNewWord(comoSeDiceLabel, guess);
  }

  /**
   * Method to be called when the user presses the Start Over or Play Again button. The words that
   * have been used will be cleaned up. The score of the player will be reset, a new word will be
   * generated and the score will be updated to reflect the reset of the players score.
   *
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   */
  public void startOver(
      JLabel comoSeDiceLabel, JLabel scoreLabel, JTextField guess, Player player) {
    SinglePlayer.resetWords();
    player.reset();
    getNewWord(comoSeDiceLabel, guess);
    updateScoreLabel(scoreLabel, player);
  }

  /**
   * Updates the score label with the players score.
   *
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param player Instance of the current player.
   */
  public void updateScoreLabel(JLabel scoreLabel, Player player) {
    scoreLabel.setText(
        String.format(
            ComoSeDiceConstants.TIMED_MODE_SCORE, player.name, String.valueOf(player.score)));
  }

  /**
   * Gets a new word and sets it. Updates the Como se dice label to reflect the newly chosen word.
   *
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param guess JLabel that has the users guess.
   */
  public void getNewWord(JLabel comoSeDiceLabel, JTextField guess) {
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
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of making the class happy.
  }
}
