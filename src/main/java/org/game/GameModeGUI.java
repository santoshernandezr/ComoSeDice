package org.game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.game.Common.CommonPanels;
import org.game.Common.ComoSeDiceConstants;
import org.game.Common.ComoSeDiceFrame;
import org.game.Common.RoundedPanel;
import org.game.Handlers.Player;

/**
 * GUI for the Game Mode screen for the Como Se Dice game. The player will see AT THE MOMENT one
 * game mode, Normal Mode, and it will have a description and how to play.
 */
public class GameModeGUI extends ComoSeDiceFrame implements ActionListener {

  public GameModeGUI(Player player) {
    super("Game Modes!");

    /*
     Creating the Picture Panel which will contain the logo.png that's in the resources' directory.
     This panel will contain the Image Icon that is made in the next few lines, IMAGE_ICON.
    */
    JPanel PICTURE_PANEL = CommonPanels.picturePanel(100, 0);

    add(PICTURE_PANEL);

    /*
    Creating the Normal Mode Panel which will contain a panel for the Normal Game. It will contain
    the description of Normal Mode.
     */
    JPanel NORMAL_MODE_PANEL = new RoundedPanel(20, Color.CYAN);
    NORMAL_MODE_PANEL.setBounds(5, 115, 390, 95);
    NORMAL_MODE_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

    JLabel NORMAL_MODE_LABEL = new JLabel("Normal Mode");
    NORMAL_MODE_LABEL.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));

    /*
     Text area was the best bet for the Normal Games Rules string. We can set the setEditable
     to false in order for it to look like a JLabel and all text wrap.
    */
    JTextArea NORMAL_GAME_RULES_TEXT_AREA = new JTextArea(ComoSeDiceConstants.NORMAL_GAME_RULES);
    NORMAL_GAME_RULES_TEXT_AREA.setWrapStyleWord(true);
    NORMAL_GAME_RULES_TEXT_AREA.setBackground(Color.CYAN);
    NORMAL_GAME_RULES_TEXT_AREA.setEditable(false);

    JButton NORMAL_MODE_BUTTON = playButton();
    NORMAL_MODE_BUTTON.addActionListener(normalModeButtonActionListener(this, player));

    /*
     Adding all elements, Normal Mode Label, Text Area which contains the rules, and the play
     button.
    */
    NORMAL_MODE_PANEL.add(NORMAL_MODE_LABEL);
    NORMAL_MODE_PANEL.add(NORMAL_GAME_RULES_TEXT_AREA);
    NORMAL_MODE_PANEL.add(NORMAL_MODE_BUTTON);

    add(NORMAL_MODE_PANEL);

    setVisible(true);
  }

  //  public static void main(String[] args) {
  //    Player player = new Player();
  //    player.setName("Roberto");
  //    new GameModeGUI(player);
  //  }

  /**
   * Action listener that will be used for the Normal Mode. The button will hide the {@link
   * GameModeGUI} and show the {@link SinglePlayerGUI}.
   *
   * @param gameModeGUI game mode gui.
   * @param player the player object.
   * @return ActionListener that the Play button will use for Normal Mode.
   */
  public ActionListener normalModeButtonActionListener(GameModeGUI gameModeGUI, Player player) {
    ActionListener normalModeButton =
        e -> {
          SinglePlayerGUI singlePlayerGUI = new SinglePlayerGUI(gameModeGUI, player);
          singlePlayerGUI.setVisible(true);
          gameModeGUI.setVisible(false);
        };

    return normalModeButton;
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

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of pleasing Action Listener.
  }
}
