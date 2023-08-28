package org.example.Handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.example.ComoSeDiceEnum;

/**
 * This class is to handle all the buttons (Action Listeners) that will be used in {@link
 * org.example.ComoSeDiceGUI}.
 */
public class ActionListenerHandler implements ActionListener {

  /**
   * This action listener is for the "Submit" button. This button will check the guess of the user
   * to see if it is the correct spanish word.
   *
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param correctLabel JLabel that stores the "Word is correct" message.
   * @param guess JLabel that has the users guess.
   * @param englishWord The english word that is trying to be guessed.
   * @return ActionListener to be used for the submit button.
   */
  public ActionListener submitButton(
      JLabel incorrectLabel, JLabel correctLabel, JTextField guess, ComoSeDiceEnum englishWord) {
    ActionListener submit =
        e -> {
          incorrectLabel.setVisible(false);

          if (guess.getText().equalsIgnoreCase(englishWord.getSpanish())) {
            correctLabel.setVisible(true);
          } else {
            incorrectLabel.setVisible(true);
          }
        };
    return submit;
  }

  /**
   * This action listener is for the "New word" button. This button will get a new english word from
   * {@link org.example.ComoSeDiceEnum} and set it as the new word to guess. After getting the new
   * english word we will update the following labels:
   * <li>COMO_SE_DICE_LABEL: updated to include the new englishWord.
   * <li>WORD_IS_CORRECT_LABEL: updated to include the new CORRECT spanish word.
   * <li>WORD_IS_CORRECT_LABEL: updated to include the new CORRECT spanish word.
   * <li>WORD_IS_INCORRECT_LABEL: updated setVisible to false, so the label won't show
   * <li>WORD_IS_CORRECT_LABEL: updated setVisible to false, so the label won't show
   *
   * @param comoSeDiceLabel JLabel that stores the "Como se dice" message.
   * @param correctLabel JLabel that stores the "Word is correct" message.
   * @param incorrectLabel JLabel that stores the "Word is incorrect" message.
   * @param guess JLabel that has the users guess.
   * @param comoSeDiceMessage String that contains the "Como se dice" message.
   * @param incorrectMessage String that contains the "Word is incorrect" message.
   * @return Action Listener to be used for the "New word" button.
   */
  public ActionListener newWordButton(
      JLabel comoSeDiceLabel,
      JLabel correctLabel,
      JLabel incorrectLabel,
      JTextField guess,
      String comoSeDiceMessage,
      String incorrectMessage) {

    ActionListener newWord =
        e -> {
          ComoSeDiceEnum englishWord = ComoSeDiceEnum.getRandom();
          comoSeDiceLabel.setText(String.format(comoSeDiceMessage, englishWord));
          guess.setText("");
          correctLabel.setText(String.format(incorrectMessage, englishWord.getSpanish()));

          incorrectLabel.setVisible(false);
          correctLabel.setVisible(false);
        };
    return newWord;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of making the class happy.
  }
}
