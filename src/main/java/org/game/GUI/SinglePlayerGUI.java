package org.game.GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.game.Common.ComoSeDiceConstants;
import org.game.Common.ComoSeDiceEnum;
import org.game.Common.ComoSeDiceFrame;
import org.game.Common.Panels.CommonPanels;
import org.game.GameModes.SinglePlayer;
import org.game.Handlers.ActionListeners.SinglePlayerActionListenerHandler;
import org.game.Handlers.Player;

/**
 * GUI for the Single Player mode in the Como Se Dice game. It'll get a random word from {@link
 * ComoSeDiceEnum} and ask the user to guess the word in spanish.
 */
public class SinglePlayerGUI extends ComoSeDiceFrame implements ActionListener {

  SinglePlayerActionListenerHandler singlePlayerActionListenerHandler =
      new SinglePlayerActionListenerHandler();

  /**
   * Constructor for {@link SinglePlayerGUI}. This GUI will be used for the Normal and Hard mode.
   * That is we have the title parameter, so we can distinguish and set the title to the correct
   * game mode that is being played.
   *
   * @param gameModeGUI The game mode gui so we can go back to it if the player decides to.
   * @param player The player instance that comes in from the gameModeGUI.
   * @param title game mode that is being played.
   */
  public SinglePlayerGUI(GameModeGUI gameModeGUI, Player player, String title) {

    super(title);

    SinglePlayer.setWordToGuess();

    // Creating the back button. This button will allow us to go back to the Game Mode Gui.
    JButton BACK_BUTTON = new JButton("<");
    BACK_BUTTON.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
    BACK_BUTTON.setBounds(10, 10, 30, 20);

    BACK_BUTTON.addActionListener(backButtonActionListener(gameModeGUI, this, player));

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
        singlePlayerActionListenerHandler.submitButton(
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
        singlePlayerActionListenerHandler.newWordButton(
            NEW_WORD,
            COMO_SE_DICE_LABEL,
            WORD_IS_INCORRECT_LABEL,
            SCORE_LABEL,
            WINNER_LABEL,
            RAN_OUT_OF_LIVES_LABEL,
            GUESS,
            player));

    PLAY_AGAIN.addActionListener(
        singlePlayerActionListenerHandler.playAgainButton(
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
              singlePlayerActionListenerHandler.checkWord(
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
    add(BACK_BUTTON);
    add(PICTURE_PANEL);
    add(USER_PANEL);
    add(BUTTON_PANEL);

    setVisible(true);
  }

  /**
   * Action Listener that will be used for the back button in the {@link SinglePlayerGUI}, this
   * Action Listener will allow to go back to the {@link GameModeGUI}.
   *
   * @param gameModeGUI {@link GameModeGUI} instance that came in.
   * @param singlePlayerGUI {@link SinglePlayerGUI} instance that is being used.
   * @param player {@link Player} instance that will be used throughout the game.
   * @return ActionListener that will be used for the back button.
   */
  public ActionListener backButtonActionListener(
      GameModeGUI gameModeGUI, SinglePlayerGUI singlePlayerGUI, Player player) {
    ActionListener backButton =
        e -> {
          player.reset();
          /*
           If we're in hard mode, i.e. hardMode is true, if we go back to the Game Mode GUI, we
           have to reset the hard mode to false since there is a possibility that the player goes
           back to the Game Mode GUI and decides to play Normal Mode.
          */
          if (SinglePlayer.hardMode) {
            SinglePlayer.hardMode = false;
          }
          gameModeGUI.setVisible(true);
          /*
           Disposing the single player gui since we are starting a new instance everytime we start a
           new Single Player game.
          */
          singlePlayerGUI.dispose();
        };

    return backButton;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of pleasing Action Listener.
  }
}
