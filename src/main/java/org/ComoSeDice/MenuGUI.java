package org.ComoSeDice;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import org.ComoSeDice.Common.CommonPanels;
import org.ComoSeDice.Common.ComoSeDiceConstants;
import org.ComoSeDice.Common.ComoSeDiceFrame;
import org.ComoSeDice.Handlers.Player;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Swing Spring Boot Application. This is the start of the application and will have the Menu GUI
 * and then will take the player to the Game Mode GUI.
 */
@ComponentScan("org.ComoSeDice")
@SpringBootApplication
public class MenuGUI extends ComoSeDiceFrame implements ActionListener {
  Player playerOne = new Player();

  public MenuGUI() {
    super("Como Se Dice!");

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
    JPanel PICTURE_PANEL = CommonPanels.picturePanel(100, 0);

    /*
     Creating the Username Panel. This panel will contain the Username label and Username text in
     order for the user to enter their username.
    */
    JPanel USERNAME_PANEL = setUsernamePanelAndKeyListeners(playerOne, this);

    /*
    Creating the Rules Panel. This panel will contain the Rules Text Area, and it will have a title
    border.
    */
    JPanel RULES_PANEL = new JPanel();
    RULES_PANEL.setBounds(5, 160, 390, 205);
    RULES_PANEL.setLayout(new FlowLayout(FlowLayout.CENTER));

    // Setting up the title border to be used for the Rules Panel.
    TitledBorder CENTER_BORDER = BorderFactory.createTitledBorder("General rules");
    CENTER_BORDER.setTitleJustification(TitledBorder.CENTER);
    RULES_PANEL.setBorder(CENTER_BORDER);

    /*
     Text area was the best bet for the Rules string. We can set the setEditable to false in order
     for it to look like a JLabel and all text wrap.
    */
    JTextArea RULES_TEXT_AREA = new JTextArea(ComoSeDiceConstants.RULES_MESSAGE);
    RULES_TEXT_AREA.setWrapStyleWord(true);
    RULES_TEXT_AREA.setBackground(this.getBackground());
    RULES_TEXT_AREA.setEditable(false);

    RULES_PANEL.add(RULES_TEXT_AREA);

    /*
    Adding all the panels to the Model Dialog. To see how the panels are organized see
    GUILayouts.asciidoc
     */
    add(PICTURE_PANEL);
    add(WELCOME_PANEL);
    add(USERNAME_PANEL);
    add(RULES_PANEL);

    setVisible(true);
  }

  /**
   * Creates and sets up Username Panel. Adds Key Listener to the Username Text Field.
   *
   * @param player Player object that will be created the user presses enter.
   * @param menuGUI the JFrame MenuGUI
   * @return returns USERNAME_PANEL JPanel.
   */
  private static JPanel setUsernamePanelAndKeyListeners(Player player, JFrame menuGUI) {
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
              player.setName(USERNAME_TEXT.getText());
              GameModeGUI gameModeGUI = new GameModeGUI(player);
              gameModeGUI.setVisible(true);
              menuGUI.setVisible(false);
            }
          }
        });

    USERNAME_PANEL.add(USERNAME_LABEL);
    USERNAME_PANEL.add(USERNAME_TEXT);
    return USERNAME_PANEL;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx =
        new SpringApplicationBuilder(MenuGUI.class).headless(false).run(args);

    EventQueue.invokeLater(
        () -> {
          MenuGUI ex = ctx.getBean(MenuGUI.class);
          ex.setVisible(true);
        });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Here for the sole purpose of pleasing Action Listener.
  }
}
