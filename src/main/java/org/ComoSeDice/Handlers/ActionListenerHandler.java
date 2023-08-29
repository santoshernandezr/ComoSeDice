package org.ComoSeDice.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.ComoSeDice.Constants.ComoSeDiceConstants;
import org.ComoSeDice.GameModes.SinglePlayer;
import org.springframework.stereotype.Component;

/**
 * This class is to handle all the buttons (Action Listeners) that will be used in {@link
 * org.ComoSeDice.ComoSeDiceGUI}.
 */
@Component
public class ActionListenerHandler implements ActionListener {

  /**
   * This action listener is for the "Submit" button. This button will check the guess of the user
   * to see if it is the correct spanish word.
   *
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param correctLabel JLabel that stores the "Word is correct" message.
   * @param scoreLabel JLabel that stores the "Score" of the player.
   * @param winnerLabel JLabel that stores the "Ganaste" message.
   * @param ranOutOfLivesLabel JLabel that stores the "Ran out of lives" message.
   * @param guess JLabel that has the users guess.
   * @param player Instance of the current player.
   * @return ActionListener to be used for the submit button.
   */
  public ActionListener submitButton(
      JLabel incorrectLabel,
      JLabel correctLabel,
      JLabel scoreLabel,
      JLabel winnerLabel,
      JLabel ranOutOfLivesLabel,
      JTextField guess,
      Player player) {

    ActionListener submit =
        e -> {
          incorrectLabel.setVisible(false);
          ranOutOfLivesLabel.setVisible(false);

          /*
          If the players guess was correct we give a point to the player and checks whether they have
          reached the winning score.
          */
          if (guess.getText().equalsIgnoreCase(SinglePlayer.wordToGuess.getSpanish())) {

            // Give the player a point.
            player.addPoint();

            /*
            If the player got the word correct and has reached the winning score, then we will reset the players points and lives
            to 0, 0 respectively. We will also show the winner label and get rid of the correct label since we have restarted.
             */
            if (player.score == SinglePlayer.winnerScore) {

              // Reset player stats.
              player.reset();

              winnerLabel.setVisible(true);
              correctLabel.setVisible(false);

            }
            /*
            If the player got the word correct but did not reach the winning score, then we will not
            show the winner label, but we will show the correct label, indicating that the word guessed was correct.
             */
            else {

              winnerLabel.setVisible(false);
              correctLabel.setVisible(true);
            }
          }
          /*
          If the players guess was NOT correct we deduct a life from the player and checks whether they have
          run out of lives.
          */
          else {
            // Removing a life from the player.
            player.removeLife();

            // Since the guess was incorrect, we do not show the correct label or the winner label.
            correctLabel.setVisible(false);
            winnerLabel.setVisible(false);

            /*
            If the players guess was NOT correct and have no lives left, then we will reset the players points and lives
            to 0,0 respectively. We will not show the incorrectLabel since they ran out of lives and instead
            show the ran out of lives label indicating they ran out of lives.
             */
            if (player.lives <= 0) {
              player.reset();

              incorrectLabel.setVisible(false);
              ranOutOfLivesLabel.setVisible(true);
            }
            /*
            If the players guess was NOT correct, but they have not yet ran out of lives, then we will show
            the incorrect label indicating their guess is wrong, but not show the ran out of lives label.
             */
            else {
              incorrectLabel.setVisible(true);
              ranOutOfLivesLabel.setVisible(false);
            }
          }

          scoreLabel.setText(
              String.format(
                  ComoSeDiceConstants.SCORE_OF_PLAYER_ONE,
                  player.name,
                  player.score,
                  player.lives));
        };
    return submit;
  }

  /**
   * This action listener is for the "New word" button. This button will get a new english word from
   * {@link org.ComoSeDice.ComoSeDiceEnum} and set it as the new word to guess. After getting the
   * new english word we will update the following labels:
   * <li>COMO_SE_DICE_LABEL: updated to include the new englishWord.
   * <li>WORD_IS_CORRECT_LABEL: updated to include the new CORRECT spanish word.
   * <li>WORD_IS_INCORRECT_LABEL: updated setVisible to false, so the label won't show.
   * <li>WINNER_LABEL: updated setVisible to false, so label won't show.
   * <li>RAN_OUT_OF_LIVES_LABEL: updated setVisible to false, so label won't show.
   *
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param correctLabel JLabel that stores the "Word is correct" message.
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param winnerLabel JLabel that stores the "Ganaste" message.
   * @param ranOutOfLivesLabel JLabel that stores the "Ran out of lives" message.
   * @param guess JLabel that has the users guess.
   * @return Action Listener to be used for the "New word" button.
   */
  public ActionListener newWordButton(
      JLabel comoSeDiceLabel,
      JLabel correctLabel,
      JLabel incorrectLabel,
      JLabel winnerLabel,
      JLabel ranOutOfLivesLabel,
      JTextField guess) {

    ActionListener newWord =
        e -> {
          // Gets a new random word to guess.
          SinglePlayer.setWordToGuess();

          /*
          Updates the como se dice LABELs text which contains the como se dice MESSAGE to include the new word to guess.
          */
          comoSeDiceLabel.setText(
              String.format(ComoSeDiceConstants.COMO_SE_DICE_MESSAGE, SinglePlayer.wordToGuess));

          // Sets the guess text field to an empty string so the user can guess again.
          guess.setText("");

          /*
          Sets the correct LABEL that contains the word is correct MESSAGE to include the new correct spanish word that
          is being guessed.
           */
          correctLabel.setText(
              String.format(
                  ComoSeDiceConstants.WORD_IS_CORRECT_MESSAGE,
                  SinglePlayer.wordToGuess.getSpanish()));

          // Setting all the labels setVisible to false since we don't want to show these.
          incorrectLabel.setVisible(false);
          correctLabel.setVisible(false);
          winnerLabel.setVisible(false);
          ranOutOfLivesLabel.setVisible(false);
        };
    return newWord;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of making the class happy.
  }
}
