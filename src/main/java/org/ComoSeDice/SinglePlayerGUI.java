package org.ComoSeDice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.ComoSeDice.Common.CommonPanels;
import org.ComoSeDice.Common.ComoSeDiceConstants;
import org.ComoSeDice.Common.ComoSeDiceEnum;
import org.ComoSeDice.GameModes.SinglePlayer;
import org.ComoSeDice.Handlers.ActionListenerHandler;
import org.ComoSeDice.Handlers.Player;

/**
 * GUI for the Single Player mode in the Como Se Dice game. It'll get a random word from {@link
 * ComoSeDiceEnum} and ask the user to guess the word in spanish.
 */
public class SinglePlayerGUI extends JFrame implements ActionListener {

  ActionListenerHandler actionListenerHandler = new ActionListenerHandler();

  public SinglePlayerGUI(Player player) {

    super("Normal Mode!");
    setLayout(null);

    SinglePlayer.setWordToGuess();

    /*
     Creating the Picture Panel which will contain the logo.png that's in the resources' directory.
     This panel will contain the Image Icon that is made in the next few lines, IMAGE_ICON.
    */
    JPanel PICTURE_PANEL = CommonPanels.picturePanel(100, 15);

    /*
     Creating the User Panel which will contain the possible labels that can be shown during the
     game, i.e., Ran out of lives label, Word is incorrect label etc. It will also contain the
     Guess Text Field in which the user will input his guess of the word.
    */
    JPanel USER_PANEL = new JPanel();
    USER_PANEL.setBounds(0, 115, 400, 160);
    USER_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));

    JLabel COMO_SE_DICE_LABEL =
        new JLabel(
            String.format(ComoSeDiceConstants.COMO_SE_DICE_MESSAGE, SinglePlayer.wordToGuess));

    JLabel SCORE_LABEL =
        new JLabel(
            String.format(
                ComoSeDiceConstants.SCORE_OF_PLAYER_ONE,
                player.name,
                player.score,
                player.lives,
                player.retries));
    SCORE_LABEL.setVisible(true);

    JLabel RAN_OUT_OF_LIVES_LABEL = new JLabel(ComoSeDiceConstants.RAN_OUT_OF_LIVES_MESSAGE);
    RAN_OUT_OF_LIVES_LABEL.setVisible(false);

    JLabel WORD_IS_INCORRECT_LABEL = new JLabel(ComoSeDiceConstants.WORD_IS_INCORRECT_MESSAGE);
    WORD_IS_INCORRECT_LABEL.setVisible(false);

    JLabel WINNER_LABEL = new JLabel(ComoSeDiceConstants.WINNER_MESSAGE);
    WINNER_LABEL.setVisible(false);

    JTextField GUESS = new JTextField(10);

    USER_PANEL.add(COMO_SE_DICE_LABEL);
    USER_PANEL.add(SCORE_LABEL);
    USER_PANEL.add(WORD_IS_INCORRECT_LABEL);
    USER_PANEL.add(RAN_OUT_OF_LIVES_LABEL);
    USER_PANEL.add(WINNER_LABEL);
    USER_PANEL.add(GUESS);

    /*
     Creating the Button Panel which will contain the Submit, New word, and Play again buttons. The
     Submit and New Word button will be visible, while the Play again will not.
    */
    JPanel BUTTON_PANEL = new JPanel();
    BUTTON_PANEL.setBounds(0, 275, 400, 125);
    BUTTON_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20));

    JButton SUBMIT = new JButton("Se dice");

    JButton NEW_WORD = new JButton("New word");

    JButton PLAY_AGAIN = new JButton("Play again");
    PLAY_AGAIN.setVisible(false);

    BUTTON_PANEL.add(NEW_WORD);
    BUTTON_PANEL.add(SUBMIT);
    BUTTON_PANEL.add(PLAY_AGAIN);

    // Adding action listeners
    SUBMIT.addActionListener(
        actionListenerHandler.submitButton(
            SUBMIT,
            NEW_WORD,
            PLAY_AGAIN,
            COMO_SE_DICE_LABEL,
            WORD_IS_INCORRECT_LABEL,
            SCORE_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            GUESS,
            player));

    NEW_WORD.addActionListener(
        actionListenerHandler.newWordButton(
            NEW_WORD,
            COMO_SE_DICE_LABEL,
            WORD_IS_INCORRECT_LABEL,
            SCORE_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            GUESS,
            player));

    PLAY_AGAIN.addActionListener(
        actionListenerHandler.playAgainButton(
            SUBMIT,
            NEW_WORD,
            PLAY_AGAIN,
            COMO_SE_DICE_LABEL,
            WORD_IS_INCORRECT_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            SCORE_LABEL,
            GUESS,
            player));

    // Key Listener for the "enter" button.
    GUESS.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            // Only call the check word with the enter button call when the player has lives.
            if (e.getKeyCode() == 10 && player.lives > 0) {
              actionListenerHandler.checkWord(
                  SUBMIT,
                  NEW_WORD,
                  PLAY_AGAIN,
                  COMO_SE_DICE_LABEL,
                  WORD_IS_INCORRECT_LABEL,
                  SCORE_LABEL,
                  WINNER_LABEL,
                  RAN_OUT_OF_LIVES_LABEL,
                  GUESS,
                  player);
            }
          }
        });

    // Adding panels to the JFrame.
    add(PICTURE_PANEL);
    add(USER_PANEL);
    add(BUTTON_PANEL);

    setSize(400, 400);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of pleasing Action Listener.
  }
}
