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
import javax.swing.Timer;
import org.game.Common.ComoSeDiceConstants;
import org.game.Common.ComoSeDiceFrame;
import org.game.Common.Panels.CommonPanels;
import org.game.GameModes.SinglePlayer;
import org.game.Handlers.ActionListeners.TimedModeActionListenerHandler;
import org.game.Handlers.Player;

/**
 * GUI for the Timed mode in the Como Se Dice game. In this mode, we will no longer take into
 * account lives or retries. The player will try to guess as many words as they can. The player will
 * earn a point for every word they get correct. Everytime they enter a guess, a new word will be
 * generated regardless if they got it right or wrong.
 */
public class TimedModeGUI extends ComoSeDiceFrame implements ActionListener {

  JLabel COUNTER_LABEL = new JLabel();
  JLabel COMO_SE_DICE_LABEL = new JLabel();

  JLabel SCORE_LABEL = new JLabel();

  JButton SUBMIT = new JButton();

  JButton START_OVER = new JButton();

  JButton PLAY_AGAIN = new JButton();

  Timer TIMER;

  int second = 61;

  TimedModeActionListenerHandler timedModeActionListenerHandler =
      new TimedModeActionListenerHandler();

  /**
   * Constructor for {@link TimedModeGUI}. This GUI will be used for the Timed mode.
   *
   * @param gameModeGUI The game mode gui so we can go back to it if the player decides to.
   * @param player The player instance that comes in from the gameModeGUI.
   */
  public TimedModeGUI(GameModeGUI gameModeGUI, Player player) {

    super("Timed Mode", 400, 400);

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
     game, i.e., Como se dice and Timer label. It will also contain the Guess Text Field in which the
     user will input his guess of the word.
    */
    JPanel USER_PANEL = new JPanel();
    //    USER_PANEL.setBackground(Color.BLUE);
    USER_PANEL.setBounds(0, 115, 400, 160);
    USER_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));

    COMO_SE_DICE_LABEL.setText(
        String.format(ComoSeDiceConstants.COMO_SE_DICE_MESSAGE, SinglePlayer.wordToGuess));

    SCORE_LABEL.setText(
        String.format(ComoSeDiceConstants.TIMED_MODE_SCORE, player.name, player.score));
    SCORE_LABEL.setVisible(true);

    JTextField GUESS = new JTextField(10);

    USER_PANEL.add(COUNTER_LABEL);
    USER_PANEL.add(COMO_SE_DICE_LABEL);
    USER_PANEL.add(SCORE_LABEL);
    USER_PANEL.add(GUESS);

    /*
     Creating the Button Panel which will contain the Submit, Start Over, and Play again buttons. The
     Submit and Start Over button will be visible, while the Play again will not.
    */
    JPanel BUTTON_PANEL = new JPanel();
    //    BUTTON_PANEL.setBackground(Color.RED);
    BUTTON_PANEL.setBounds(0, 275, 400, 125);
    BUTTON_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20));

    SUBMIT.setText("Se dice");

    START_OVER.setText("Start over");

    PLAY_AGAIN.setText("Play again");
    PLAY_AGAIN.setVisible(false);

    BUTTON_PANEL.add(START_OVER);
    BUTTON_PANEL.add(SUBMIT);
    BUTTON_PANEL.add(PLAY_AGAIN);

    // Adding action listeners
    SUBMIT.addActionListener(
        timedModeActionListenerHandler.submitButton(
            COMO_SE_DICE_LABEL, SCORE_LABEL, GUESS, player));

    /*
    The Start Over button will allow the user to start over, meaning the time and score will reset.
    We will also call startOver() to help us clean up the GUI, generate a new word, and reset the
    score that is visible to the user.
    */
    START_OVER.addActionListener(
        e -> {
          second = 60;
          COUNTER_LABEL.setText("" + second);
          player.reset();
          timedModeActionListenerHandler.startOver(COMO_SE_DICE_LABEL, SCORE_LABEL, GUESS, player);
        });

    /*
    The Play Again button will appear once the timer has hit 0. This ActionListener will hide the
    Play Again and show the Start Over and Submit button. It will also show the Como se dice and
    Counter Label. It also calls startOver() to help us clean up the GUI, generate a new word, and
    reset the score that is visible to the user.
    */
    PLAY_AGAIN.addActionListener(
        e -> {
          second = 61;
          TIMER.start();
          PLAY_AGAIN.setVisible(false);
          SUBMIT.setVisible(true);
          START_OVER.setVisible(true);
          COMO_SE_DICE_LABEL.setVisible(true);
          COUNTER_LABEL.setVisible(true);
          timedModeActionListenerHandler.startOver(COMO_SE_DICE_LABEL, SCORE_LABEL, GUESS, player);
        });

    // Key Listener for the "enter" button.
    GUESS.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            // Only call the check word with the enter button call when the timer is still running.
            if (e.getKeyCode() == 10 && second > 0) {
              timedModeActionListenerHandler.checkWord(
                  COMO_SE_DICE_LABEL, SCORE_LABEL, GUESS, player);
            }
          }
        });

    // Adding panels to the JFrame.
    add(BACK_BUTTON);
    add(PICTURE_PANEL);
    add(USER_PANEL);
    add(BUTTON_PANEL);

    countDownTimer();
    TIMER.start();

    setVisible(true);
  }

  /**
   * Action Listener that will be used for the back button in the {@link SinglePlayerGUI}, this
   * Action Listener will allow to go back to the {@link GameModeGUI}.
   *
   * @param gameModeGUI {@link GameModeGUI} instance that came in.
   * @param timedMode {@link TimedModeGUI} instance that is being used.
   * @param player {@link Player} instance that will be used throughout the game.
   * @return ActionListener that will be used for the back button.
   */
  public ActionListener backButtonActionListener(
      GameModeGUI gameModeGUI, TimedModeGUI timedMode, Player player) {
    ActionListener backButton =
        e -> {
          player.reset();
          gameModeGUI.setVisible(true);
          /*
           Disposing the Timed Mode gui since we are starting a new instance everytime we start a
           new Timed mode game.
          */
          timedMode.dispose();
        };

    return backButton;
  }

  /** Countdown timer that will be used in the Timer Mode. */
  public void countDownTimer() {
    TIMER =
        new Timer(
            1000,
            e -> {
              second--;
              COUNTER_LABEL.setText("" + second);

              /*
              When the timer has hit 0. This will stop the timer, hide the Start Over and Submit
              button since the mode has ended. The Como se dice and Counter label are also hidden
              when the timer hits 0. This will leave the GUI with only the players score and the
              Play Again button.
               */
              if (second == 0) {
                TIMER.stop();
                PLAY_AGAIN.setVisible(true);
                SUBMIT.setVisible(false);
                START_OVER.setVisible(false);
                COMO_SE_DICE_LABEL.setVisible(false);
                COUNTER_LABEL.setVisible(false);
              }
            });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of pleasing Action Listener.
  }
}
