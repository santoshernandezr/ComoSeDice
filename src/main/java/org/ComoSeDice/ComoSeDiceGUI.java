package org.ComoSeDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.ComoSeDice.Constants.ComoSeDiceConstants;
import org.ComoSeDice.GameModes.SinglePlayer;
import org.ComoSeDice.Handlers.ActionListenerHandler;
import org.ComoSeDice.Handlers.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * GUI for the Como Se Dice game. It'll get a random word from {@link ComoSeDiceEnum} and ask the
 * user to guess the word in spanish.
 */
@ComponentScan("org.ComoSeDice")
@SpringBootApplication
public class ComoSeDiceGUI extends JFrame implements ActionListener {
  // JLabels
  JLabel COMO_SE_DICE_LABEL;
  // Stores the "Word is correct message". SetVisibility default to false.
  JLabel WORD_IS_CORRECT_LABEL;
  // Stores the "Word is incorrect message". SetVisibility default to false.
  JLabel WORD_IS_INCORRECT_LABEL;
  // Stores the "Ran out of lives" label. SetVisibility default to false.
  JLabel RAN_OUT_OF_LIVES_LABEL;
  // Stores the "Score" of the player. SetVisibility default to false.
  JLabel SCORE_LABEL;
  // Stores the "Ganaste" message. SetVisibility default to false.
  JLabel WINNER_LABEL;

  // Text Fields
  JTextField GUESS;

  // Buttons
  JButton SUBMIT;
  JButton NEW_WORD;

  @Autowired private ActionListenerHandler actionListenerHandler;

  public ComoSeDiceGUI(ActionListenerHandler actionListenerHandler) {

    super("Como se dice!");
    this.actionListenerHandler = actionListenerHandler;

    SinglePlayer.setWordToGuess();

    Player playerOne = new Player("Roberto");

    COMO_SE_DICE_LABEL =
        new JLabel(
            String.format(ComoSeDiceConstants.COMO_SE_DICE_MESSAGE, SinglePlayer.wordToGuess));

    SCORE_LABEL =
        new JLabel(
            String.format(
                ComoSeDiceConstants.SCORE_OF_PLAYER_ONE,
                playerOne.name,
                playerOne.score,
                playerOne.lives));
    SCORE_LABEL.setVisible(true);

    RAN_OUT_OF_LIVES_LABEL = new JLabel(ComoSeDiceConstants.RAN_OUT_OF_LIVES_MESSAGE);
    RAN_OUT_OF_LIVES_LABEL.setVisible(false);

    WORD_IS_INCORRECT_LABEL = new JLabel(ComoSeDiceConstants.WORD_IS_INCORRECT_MESSAGE);
    WORD_IS_INCORRECT_LABEL.setVisible(false);

    WINNER_LABEL = new JLabel(ComoSeDiceConstants.WINNER_MESSAGE);
    WINNER_LABEL.setVisible(false);

    WORD_IS_CORRECT_LABEL =
        new JLabel(
            String.format(ComoSeDiceConstants.WORD_IS_CORRECT_MESSAGE, SinglePlayer.wordToGuess));
    WORD_IS_CORRECT_LABEL.setVisible(false);

    GUESS = new JTextField(10);

    SUBMIT = new JButton("Se dice");

    NEW_WORD = new JButton("New word");

    setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));

    add(COMO_SE_DICE_LABEL);
    add(SCORE_LABEL);
    add(WORD_IS_INCORRECT_LABEL);
    add(RAN_OUT_OF_LIVES_LABEL);
    add(WINNER_LABEL);

    add(GUESS);

    add(SUBMIT);
    add(NEW_WORD);
    add(WORD_IS_CORRECT_LABEL);

    // Adding action handlers
    SUBMIT.addActionListener(
        this.actionListenerHandler.submitButton(
            WORD_IS_INCORRECT_LABEL,
            WORD_IS_CORRECT_LABEL,
            SCORE_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            GUESS,
            playerOne));

    NEW_WORD.addActionListener(
        this.actionListenerHandler.newWordButton(
            COMO_SE_DICE_LABEL,
            WORD_IS_CORRECT_LABEL,
            WORD_IS_INCORRECT_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            GUESS));

    setSize(300, 300);
    setVisible(true);
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx =
        new SpringApplicationBuilder(ComoSeDiceGUI.class).headless(false).run(args);

    EventQueue.invokeLater(
        () -> {
          ComoSeDiceGUI ex = ctx.getBean(ComoSeDiceGUI.class);
          ex.setVisible(true);
        });
  }

  // Here for the sole purpose of pleasing Action Listener.
  @Override
  public void actionPerformed(ActionEvent e) {}
}
