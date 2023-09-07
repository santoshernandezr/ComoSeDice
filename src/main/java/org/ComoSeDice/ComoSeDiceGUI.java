package org.ComoSeDice;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
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
  @Autowired private ActionListenerHandler actionListenerHandler;

  public ComoSeDiceGUI(ActionListenerHandler actionListenerHandler) {

    super("Como se dice!");
    this.actionListenerHandler = actionListenerHandler;

    Player playerOne = new Player();

    createDialog(this, playerOne);

    SinglePlayer.setWordToGuess();

    JLabel COMO_SE_DICE_LABEL =
        new JLabel(
            String.format(ComoSeDiceConstants.COMO_SE_DICE_MESSAGE, SinglePlayer.wordToGuess));

    JLabel SCORE_LABEL =
        new JLabel(
            String.format(
                ComoSeDiceConstants.SCORE_OF_PLAYER_ONE,
                playerOne.name,
                playerOne.score,
                playerOne.lives,
                playerOne.retries));
    SCORE_LABEL.setVisible(true);

    JLabel RAN_OUT_OF_LIVES_LABEL = new JLabel(ComoSeDiceConstants.RAN_OUT_OF_LIVES_MESSAGE);
    RAN_OUT_OF_LIVES_LABEL.setVisible(false);

    JLabel WORD_IS_INCORRECT_LABEL = new JLabel(ComoSeDiceConstants.WORD_IS_INCORRECT_MESSAGE);
    WORD_IS_INCORRECT_LABEL.setVisible(false);

    JLabel WINNER_LABEL = new JLabel(ComoSeDiceConstants.WINNER_MESSAGE);
    WINNER_LABEL.setVisible(false);

    JTextField GUESS = new JTextField(10);

    JButton SUBMIT = new JButton("Se dice");

    JButton NEW_WORD = new JButton("New word");

    JButton PLAY_AGAIN = new JButton("Play again");
    PLAY_AGAIN.setVisible(false);

    setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));

    add(COMO_SE_DICE_LABEL);
    add(SCORE_LABEL);
    add(WORD_IS_INCORRECT_LABEL);
    add(RAN_OUT_OF_LIVES_LABEL);
    add(WINNER_LABEL);

    add(GUESS);

    add(SUBMIT);
    add(NEW_WORD);
    add(PLAY_AGAIN);

    // Adding action listeners
    SUBMIT.addActionListener(
        this.actionListenerHandler.submitButton(
            SUBMIT,
            NEW_WORD,
            PLAY_AGAIN,
            COMO_SE_DICE_LABEL,
            WORD_IS_INCORRECT_LABEL,
            SCORE_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            GUESS,
            playerOne));

    NEW_WORD.addActionListener(
        this.actionListenerHandler.newWordButton(
            NEW_WORD,
            COMO_SE_DICE_LABEL,
            WORD_IS_INCORRECT_LABEL,
            SCORE_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            GUESS,
            playerOne));

    PLAY_AGAIN.addActionListener(
        this.actionListenerHandler.playAgainButton(
            SUBMIT,
            NEW_WORD,
            PLAY_AGAIN,
            COMO_SE_DICE_LABEL,
            WORD_IS_INCORRECT_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            SCORE_LABEL,
            GUESS,
            playerOne));

    // Key Listener for the "enter" button.
    GUESS.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            // Only call the check word with the enter button call when the player has lives.
            if (e.getKeyCode() == 10 && playerOne.lives > 0) {
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
                  playerOne);
            }
          }
        });

    setSize(400, 400);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * This will bring up a modal to tell the user to play and for them to enter their username.
   *
   * @param frame the MAIN GUI {@link ComoSeDiceGUI}
   * @param player the player object that will be used.
   */
  private void createDialog(final JFrame frame, Player player) {

    JDialog modelDialog = new JDialog(frame, "Como Se Dice!", Dialog.ModalityType.DOCUMENT_MODAL);
    modelDialog.setLayout(null);

    JPanel WELCOME_PANEL = new JPanel();
    //    WELCOME_PANEL.setBackground(Color.GREEN);
    WELCOME_PANEL.setBounds(0, 0, 400, 120);
    WELCOME_PANEL.setLayout(new BorderLayout());

    JPanel PICTURE_PANEL = new JPanel();
    PICTURE_PANEL.setBounds(100, 0, 200, 100);

    ImageIcon img =
        new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
    JLabel IMAGE_LABEL = new JLabel(img);
    System.out.println("Width: " + img.getIconHeight() + " Height: " + img.getIconHeight());
    System.out.println(img.getImageLoadStatus());

    PICTURE_PANEL.add(IMAGE_LABEL);

    JPanel USERNAME_PANEL = new JPanel();
    //    USERNAME_PANEL.setBackground(Color.BLUE);
    USERNAME_PANEL.setBounds(0, 120, 400, 30);
    USERNAME_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER));

    JPanel RULES_PANEL = new JPanel();
    //    RULES_PANEL.setBackground(Color.RED);
    RULES_PANEL.setBounds(0, 160, 400, 220);
    RULES_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER));

    TitledBorder centerBorder = BorderFactory.createTitledBorder("Rules");
    centerBorder.setTitleJustification(TitledBorder.CENTER);
    RULES_PANEL.setBorder(centerBorder);

    JLabel LETS_PLAY_LABEL = new JLabel("Hola, let's play Como Se Dice!");
    LETS_PLAY_LABEL.setVerticalAlignment(JLabel.BOTTOM);
    LETS_PLAY_LABEL.setHorizontalAlignment(JLabel.CENTER);

    JLabel USERNAME_LABEL = new JLabel("Enter username:");
    JLabel RULES_LABEL = new JLabel("Rules:");

    JTextField USERNAME_TEXT = new JTextField(10);

    JButton PLAY_BUTTON = new JButton("Play");

    PLAY_BUTTON.addActionListener(
        e -> {
          if (!Objects.equals(USERNAME_TEXT.getText(), "")) {
            modelDialog.setVisible(false);
            player.setName(USERNAME_TEXT.getText());
          }
        });

    // Key Listener for the "enter" button.
    USERNAME_TEXT.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            // Hide the model and set the username to the player object when the button pressed is
            // entered and the text is not empty.
            if (e.getKeyCode() == 10 && (!Objects.equals(USERNAME_TEXT.getText(), ""))) {
              modelDialog.setVisible(false);
              player.setName(USERNAME_TEXT.getText());
            }
          }
        });

    // Text area was the best bet for the RULES string.
    JTextArea RULES_TEXT_AREA = new JTextArea(ComoSeDiceConstants.RULES_MESSAGE);
    RULES_TEXT_AREA.setWrapStyleWord(true);
    RULES_TEXT_AREA.setBackground(frame.getBackground());

    // Adding stuff to each panel.

    WELCOME_PANEL.add(LETS_PLAY_LABEL);

    USERNAME_PANEL.add(USERNAME_LABEL);
    USERNAME_PANEL.add(USERNAME_TEXT);

    //    RULES_PANEL.add(RULES_LABEL);
    RULES_PANEL.add(RULES_TEXT_AREA);

    modelDialog.add(PICTURE_PANEL);
    modelDialog.add(WELCOME_PANEL);
    modelDialog.add(USERNAME_PANEL);
    modelDialog.add(RULES_PANEL);

    modelDialog.pack();
    modelDialog.setSize(400, 400);
    modelDialog.setVisible(true);
    modelDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
