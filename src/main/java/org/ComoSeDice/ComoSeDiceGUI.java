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
    setLayout(null);

    this.actionListenerHandler = actionListenerHandler;

    Player playerOne = new Player();

    // Brings up the modal which welcomes the user and asks them for their username.
    createDialog(this, playerOne);

    SinglePlayer.setWordToGuess();

    /*
     Creating the Picture Panel which will contain the logo.png that's in the resources' directory.
     This panel will contain the Image Icon that is made in the next few lines, IMAGE_ICON.
    */
    JPanel PICTURE_PANEL = new JPanel();
    PICTURE_PANEL.setBounds(100, 15, 200, 100);

    ImageIcon IMAGE_ICON =
        new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
    JLabel IMAGE_LABEL = new JLabel(IMAGE_ICON);

    PICTURE_PANEL.add(IMAGE_LABEL);

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

    USER_PANEL.add(COMO_SE_DICE_LABEL);
    USER_PANEL.add(SCORE_LABEL);
    USER_PANEL.add(WORD_IS_INCORRECT_LABEL);
    USER_PANEL.add(RAN_OUT_OF_LIVES_LABEL);
    USER_PANEL.add(GUESS);

    /*
     Creating the Button Panel which will contain the Submit, New word, and Play again buttons. The
     Submit and New Word button will be visible, while the Play again will not.
    */
    JPanel BUTTON_PANEL = new JPanel();
    BUTTON_PANEL.setBounds(0, 275, 400, 125);
    //    BUTTON_PANEL.setBackground(Color.RED);
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

    // Adding panels to the JFrame.
    add(PICTURE_PANEL);
    add(USER_PANEL);
    add(BUTTON_PANEL);

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

    /*
     Creating a new instance of a JDialog where all the panels will go. We have to ensure that the
     layout is set to null to allow the panels to come in properly.
    */
    JDialog modelDialog = new JDialog(frame, "Como Se Dice!", Dialog.ModalityType.DOCUMENT_MODAL);
    modelDialog.setLayout(null);

    // Creating the welcome panel. This Welcome panel will contain the Let's play Label.
    JPanel WELCOME_PANEL = new JPanel();
    WELCOME_PANEL.setBounds(0, 0, 400, 120);
    WELCOME_PANEL.setLayout(new BorderLayout());

    JLabel LETS_PLAY_LABEL = new JLabel("Hola, let's play Como Se Dice!");
    LETS_PLAY_LABEL.setVerticalAlignment(JLabel.BOTTOM);
    LETS_PLAY_LABEL.setHorizontalAlignment(JLabel.CENTER);

    WELCOME_PANEL.add(LETS_PLAY_LABEL);

    /*
     Creating the picture panel which will contain the logo.png that's in the resources' directory.
     This panel will contain the Image Icon that is made in the next few lines, IMAGE_ICON.
    */
    JPanel PICTURE_PANEL = new JPanel();
    PICTURE_PANEL.setBounds(100, 0, 200, 100);

    ImageIcon IMAGE_ICON =
        new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
    JLabel IMAGE_LABEL = new JLabel(IMAGE_ICON);

    PICTURE_PANEL.add(IMAGE_LABEL);

    /*
     Creating the Username Panel. This panel will contain the Username label and Username text in
     order for the user to enter their username.
    */
    JPanel USERNAME_PANEL = setUsernamePanel(player, modelDialog);

    /*
    Creating the Rules Panel. This panel will contain the Rules Text Area, and it will have a title
    border.
    */
    JPanel RULES_PANEL = new JPanel();
    RULES_PANEL.setBounds(5, 160, 390, 205);
    RULES_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER));

    // Setting up the title border to be used for the Rules Panel.
    TitledBorder CENTER_BORDER = BorderFactory.createTitledBorder("Rules");
    CENTER_BORDER.setTitleJustification(TitledBorder.CENTER);
    RULES_PANEL.setBorder(CENTER_BORDER);

    // Text area was the best bet for the RULES string.
    JTextArea RULES_TEXT_AREA = new JTextArea(ComoSeDiceConstants.RULES_MESSAGE);
    RULES_TEXT_AREA.setWrapStyleWord(true);
    RULES_TEXT_AREA.setBackground(frame.getBackground());

    RULES_PANEL.add(RULES_TEXT_AREA);

    /*
    Adding all the panels to the Model Dialog. To see how the panels are organized see
    GUILayouts.asciidoc
     */
    modelDialog.add(PICTURE_PANEL);
    modelDialog.add(WELCOME_PANEL);
    modelDialog.add(USERNAME_PANEL);
    modelDialog.add(RULES_PANEL);

    modelDialog.pack();
    modelDialog.setSize(400, 400);
    modelDialog.setVisible(true);
    modelDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Creates and sets up Username Panel. Adds Key Listener to the Username Text Field.
   *
   * @param player Player object that will be created the user presses enter.
   * @param modelDialog the JDialog in which we will add this to.
   * @return returns USERNAME_PANEL JPanel.
   */
  private static JPanel setUsernamePanel(Player player, JDialog modelDialog) {
    JPanel USERNAME_PANEL = new JPanel();
    USERNAME_PANEL.setBounds(0, 120, 400, 30);
    USERNAME_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER));

    JLabel USERNAME_LABEL = new JLabel("Enter username:");
    JTextField USERNAME_TEXT = new JTextField(10);

    // Key Listener for the "enter" button.
    USERNAME_TEXT.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            /*
             Hide the model and set the username to the player object when the button pressed is
             entered and the text is not empty.
            */
            if (e.getKeyCode() == 10 && (!Objects.equals(USERNAME_TEXT.getText(), ""))) {
              modelDialog.setVisible(false);
              player.setName(USERNAME_TEXT.getText());
            }
          }
        });

    USERNAME_PANEL.add(USERNAME_LABEL);
    USERNAME_PANEL.add(USERNAME_TEXT);
    return USERNAME_PANEL;
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
