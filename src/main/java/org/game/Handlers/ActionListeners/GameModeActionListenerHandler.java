package org.game.Handlers.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.game.GUI.GameModeGUI;
import org.game.GUI.SinglePlayerGUI;
import org.game.GUI.TimedModeGUI;
import org.game.GameModes.SinglePlayer;
import org.game.Handlers.Player;
import org.springframework.stereotype.Component;

/**
 * This class is to handle all the buttons (Action Listeners) that will be used in the {@link
 * org.game.GUI.GameModeGUI}.
 */
@Component
public class GameModeActionListenerHandler implements ActionListener {

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
          SinglePlayerGUI singlePlayerGUI =
              new SinglePlayerGUI(gameModeGUI, player, "Normal Mode!");
          singlePlayerGUI.setVisible(true);
          gameModeGUI.setVisible(false);
        };

    return normalModeButton;
  }

  /**
   * Action listener that will be used for the Hard Mode. The button will hide the {@link
   * GameModeGUI} and show the {@link SinglePlayerGUI}. It will also set {@link
   * SinglePlayer#hardMode} to true, enabling hard mode.
   *
   * @param gameModeGUI game mode gui.
   * @param player the player object.
   * @return ActionListener that the play button will use for Hard Mode.
   */
  public ActionListener hardModeButtonActionListener(GameModeGUI gameModeGUI, Player player) {
    ActionListener hardModeButton =
        e -> {
          SinglePlayer.hardMode = true;
          SinglePlayerGUI singlePlayerGUI = new SinglePlayerGUI(gameModeGUI, player, "Hard Mode!");
          singlePlayerGUI.setVisible(true);
          gameModeGUI.setVisible(false);
        };
    return hardModeButton;
  }

  /**
   * Action listener that will be used for the Timed Mode. The button will hide the {@link
   * GameModeGUI} and show the {@link TimedModeGUI}.
   *
   * @param gameModeGUI game mode gui.
   * @param player the player object.
   * @return ActionListener that the Play button will use for Timed Mode.
   */
  public ActionListener timedModeButtonActionListener(GameModeGUI gameModeGUI, Player player) {
    ActionListener timedModeButton =
        e -> {
          TimedModeGUI timedModeGUI = new TimedModeGUI(gameModeGUI, player);
          timedModeGUI.setVisible(true);
          gameModeGUI.setVisible(false);
        };

    return timedModeButton;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of making the class happy.
  }
}
