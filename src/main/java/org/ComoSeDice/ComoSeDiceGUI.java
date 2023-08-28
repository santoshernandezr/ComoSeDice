package org.ComoSeDice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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

  private final String COMO_SE_DICE_MESSAGE = "Como se dice %s?";
  private final String WORD_IS_INCORRECT_MESSAGE = "Sorry, asi no se dice. Try again!";
  private final String WORD_IS_CORRECT_MESSAGE = "Si, se dice %s";
  ComoSeDiceEnum englishWord;
  ActionListenerHandler actionListenerHandler = new ActionListenerHandler();

  // JLabels
  JLabel COMO_SE_DICE_LABEL;
  JLabel WORD_IS_CORRECT_LABEL; // SetVisibility default to false
  JLabel WORD_IS_INCORRECT_LABEL; // SetVisibility default to false

  // Text Fields
  JTextField GUESS;

  // Buttons
  JButton SUBMIT;
  JButton NEW_WORD;

  public ComoSeDiceGUI() {

    super("Como se dice!");

    englishWord = ComoSeDiceEnum.getRandom();

    COMO_SE_DICE_LABEL = new JLabel(String.format(COMO_SE_DICE_MESSAGE, englishWord));

    WORD_IS_INCORRECT_LABEL = new JLabel(WORD_IS_INCORRECT_MESSAGE);
    WORD_IS_INCORRECT_LABEL.setVisible(false);

    WORD_IS_CORRECT_LABEL =
        new JLabel(String.format(WORD_IS_CORRECT_MESSAGE, englishWord.getSpanish()));
    WORD_IS_CORRECT_LABEL.setVisible(false);

    GUESS = new JTextField(10);

    SUBMIT = new JButton("Se dice");

    NEW_WORD = new JButton("New word");

    setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));

    add(COMO_SE_DICE_LABEL);
    add(WORD_IS_INCORRECT_LABEL);

    add(GUESS);

    add(SUBMIT);
    add(NEW_WORD);
    add(WORD_IS_CORRECT_LABEL);

    // Adding action handlers
    SUBMIT.addActionListener(
        actionListenerHandler.submitButton(
            WORD_IS_INCORRECT_LABEL, WORD_IS_CORRECT_LABEL, GUESS, englishWord));
    NEW_WORD.addActionListener(
        actionListenerHandler.newWordButton(
            COMO_SE_DICE_LABEL,
            WORD_IS_CORRECT_LABEL,
            WORD_IS_INCORRECT_LABEL,
            GUESS,
            COMO_SE_DICE_MESSAGE,
            WORD_IS_INCORRECT_MESSAGE));

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
