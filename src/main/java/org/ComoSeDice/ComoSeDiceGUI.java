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

  // JLabels
  JLabel COMO_SE_DICE_LABEL;
  JLabel WORD_IS_CORRECT_LABEL; // SetVisibility default to false
  JLabel WORD_IS_INCORRECT_LABEL; // SetVisibility default to false

  // Text Fields
  JTextField GUESS;

  // Buttons
  JButton SUBMIT;
  JButton NEW_WORD;

  ComoSeDiceEnum englishWord;

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

    this.setUpListeners();
    SUBMIT.addActionListener(this);

    setSize(300, 300);
    setVisible(true);
  }

  // Will set up the action each button will do.
  public void setUpListeners() {
    /*
    This action listener is for the "Submit" button. This button will check the guess of the user to see if it is
    the correct spanish word.
     */
    ActionListener submit =
        e -> {
          WORD_IS_INCORRECT_LABEL.setVisible(false);

          if (GUESS.getText().equalsIgnoreCase(englishWord.getSpanish())) {
            WORD_IS_CORRECT_LABEL.setVisible(true);
          } else {
            WORD_IS_INCORRECT_LABEL.setVisible(true);
          }
        };

    /*
    This action listener is for the "New word" button.
    This button will get a new english word from {@link ComoSeDiceEnum} and set it as the new word.
    After getting the new english word we will update the following labels:
    COMO_SE_DICE_LABEL: updated to include the new englishWord.
    WORD_IS_CORRECT_LABEL: updated to include the new CORRECT spanish word.
    WORD_IS_INCORRECT_LABEL: updated setVisible to false, so the label won't show
    WORD_IS_CORRECT_LABEL: updated setVisible to false, so the label won't show
    */
    ActionListener newWord =
        e -> {
          englishWord = ComoSeDiceEnum.getRandom();

          COMO_SE_DICE_LABEL.setText(String.format(COMO_SE_DICE_MESSAGE, englishWord));
          GUESS.setText("");
          WORD_IS_CORRECT_LABEL.setText(
              String.format(WORD_IS_CORRECT_MESSAGE, englishWord.getSpanish()));

          WORD_IS_INCORRECT_LABEL.setVisible(false);
          WORD_IS_CORRECT_LABEL.setVisible(false);
        };

    SUBMIT.addActionListener(submit);
    NEW_WORD.addActionListener(newWord);
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
