package org.ComoSeDice;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.ComoSeDice.Common.CommonPanels;
import org.ComoSeDice.Handlers.Player;

public class GameModeGUI extends JFrame implements ActionListener {

  public GameModeGUI(Player player) {
    super("Game Modes!");
    setLayout(null);

    /*
     Creating the Picture Panel which will contain the logo.png that's in the resources' directory.
     This panel will contain the Image Icon that is made in the next few lines, IMAGE_ICON.
    */
    JPanel PICTURE_PANEL = CommonPanels.picturePanel(100, 0);

    add(PICTURE_PANEL);

    /*
    Creating the Button Panel which will contain all the game modes and their respective descriptions.
    This will be like the controller for all game modes.
     */
    JPanel BUTTON_PANEL = new JPanel();
    BUTTON_PANEL.setBounds(0, 115, 400, 160);
    BUTTON_PANEL.setBackground(Color.BLUE);
    BUTTON_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 20));

    JButton NORMAL_MODE_BUTTON = new JButton("Normal Mode");
    NORMAL_MODE_BUTTON.addActionListener(normalModeButton(this, player));

    BUTTON_PANEL.add(NORMAL_MODE_BUTTON);

    add(BUTTON_PANEL);

    setSize(400, 400);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  //  public static void main(String[] args) {
  //    new GameModeGUI();
  //  }

  public ActionListener normalModeButton(GameModeGUI gameModeGUI, Player player) {
    ActionListener normalMode =
        e -> {
          SinglePlayerGUI singlePlayerGUI = new SinglePlayerGUI(player);
          singlePlayerGUI.setVisible(true);
          gameModeGUI.setVisible(false);
        };

    return normalMode;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of pleasing Action Listener.
  }
}
