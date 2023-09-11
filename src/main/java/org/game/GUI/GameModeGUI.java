package org.game.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.game.Common.ComoSeDiceConstants;
import org.game.Common.ComoSeDiceFrame;
import org.game.Common.Panels.CommonPanels;
import org.game.Common.Panels.RoundedPanel;
import org.game.Handlers.ActionListeners.GameModeActionListenerHandler;
import org.game.Handlers.Player;

/**
 * GUI for the Game Mode screen for the Como Se Dice game. The player will see Normal, Hard and
 * timed Mode, and they will each have a description of how to play each mode.
 */
public class GameModeGUI extends ComoSeDiceFrame implements ActionListener {

  GameModeActionListenerHandler gameModeActionListenerHandler = new GameModeActionListenerHandler();

  /**
   * Constructor for the Game Mode GUI. We have Player as a parameter that will be brought in from
   * the {@link MenuGUI} and it will be instantiated with the username the user entered.
   *
   * @param player instance of Player.
   */
  public GameModeGUI(Player player) {
    super("Game Modes!", 400, 450);

    /*
     Creating the Picture Panel which will contain the logo.png that's in the resources' directory.
     This panel will contain the Image Icon that is made in the next few lines, IMAGE_ICON.
    */
    JPanel PICTURE_PANEL = CommonPanels.picturePanel(100, 0);

    add(PICTURE_PANEL);

    /*
    Creating the Normal Mode Panel which will contain a panel for the Normal Game mode. It will contain
    the description of Normal Mode.
     */
    JPanel NORMAL_MODE_PANEL =
        setUpModePanel(5, 115, "Normal Mode", ComoSeDiceConstants.NORMAL_GAME_RULES, Color.CYAN);

    JButton NORMAL_MODE_BUTTON = playButton();
    NORMAL_MODE_BUTTON.addActionListener(
        gameModeActionListenerHandler.normalModeButtonActionListener(this, player));

    NORMAL_MODE_PANEL.add(NORMAL_MODE_BUTTON);

    /*
     Creating the Hard Mode Panel which will contain a panel for the Hard Mode. It will contain the
     description of Hard Mode.
    */
    JPanel HARD_MODE_PANEL =
        setUpModePanel(5, 215, "Hard Mode", ComoSeDiceConstants.HARD_MODE_GAME_RULES, Color.RED);

    JButton HARD_MODE_BUTTON = playButton();
    HARD_MODE_BUTTON.addActionListener(
        gameModeActionListenerHandler.hardModeButtonActionListener(this, player));

    HARD_MODE_PANEL.add(HARD_MODE_BUTTON);

    /*
    Creating the Timed Mode Panel which will contain a panel for the Timed Mode Game Mode. It will
    contain the description of Timed Mode.
    */
    JPanel TIMED_MODE_PANEL =
        setUpModePanel(5, 315, "Timed Mode", ComoSeDiceConstants.TIMED_MODE_RULES, Color.MAGENTA);

    JButton TIMED_MODE_BUTTON = playButton();
    TIMED_MODE_BUTTON.addActionListener(
        gameModeActionListenerHandler.timedModeButtonActionListener(this, player));

    TIMED_MODE_PANEL.add(TIMED_MODE_BUTTON);

    // Adding Panels to the GameModeGUI JPanel.
    add(NORMAL_MODE_PANEL);
    add(HARD_MODE_PANEL);
    add(TIMED_MODE_PANEL);

    setVisible(true);
  }

  /**
   * General Play button that all game modes will use.
   *
   * @return JButton that all GUIs will use.
   */
  public JButton playButton() {
    JButton PLAY_BUTTON = new JButton("Play");
    PLAY_BUTTON.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));

    return PLAY_BUTTON;
  }

  /**
   * Will set up a similar panel for each game mode.
   *
   * @param x x-coordinate of the JPanel.
   * @param y y-coordinate of the JPanel.
   * @param title Title of game mode.
   * @param rules Rules of the game mode.
   * @param color Color of the JPanel.
   * @return JPanel for the specified game mode.
   */
  public JPanel setUpModePanel(int x, int y, String title, String rules, Color color) {
    JPanel PANEL = new RoundedPanel(20, color);
    PANEL.setBounds(x, y, 390, 95);
    PANEL.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

    JLabel MODE_LABEL = new JLabel(title);
    MODE_LABEL.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));

    /*
     Text area was the best bet for the Normal Games Rules string. We can set the setEditable
     to false in order for it to look like a JLabel and all text wrap.
    */
    JTextArea GAME_RULES_TEXT_AREA = new JTextArea(rules);
    GAME_RULES_TEXT_AREA.setWrapStyleWord(true);
    GAME_RULES_TEXT_AREA.setBackground(color);
    GAME_RULES_TEXT_AREA.setEditable(false);

    PANEL.add(MODE_LABEL);
    PANEL.add(GAME_RULES_TEXT_AREA);

    return PANEL;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of pleasing Action Listener.
  }
}
