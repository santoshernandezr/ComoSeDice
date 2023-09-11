package org.game.Common;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Parent class of all the GUIs for the Como Se Dice game. This class will set up the basic template
 * of all the GUIs that will be used. All GUIs will have the Layout as null since that is what is
 * recommended when working with JPanels and easier to manipulate. The GUIs will be set to be
 * positioned in the middle of the screen.
 */
public class ComoSeDiceFrame extends JFrame {

  /**
   * Constructor for the template frame that all GUIs will use. We've decided to have title, width,
   * and height as a parameter to make it easier to adjust size depending on the content of each
   * Frame.
   *
   * @param title title of the GUI.
   * @param width width of the GUI.
   * @param height height of the GUI.
   */
  public ComoSeDiceFrame(String title, int width, int height) {
    super(title);
    setLayout(null);

    setGUIinMiddleOfScreen(this, width, height);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Sets the JFrame right in the middle of the screen.
   *
   * @param frame the frame we wish to put in the middle of the screen.
   */
  public static void setGUIinMiddleOfScreen(JFrame frame, int width, int height) {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int x = ((int) size.getWidth() / 2) - (width / 2);
    int y = ((int) size.getHeight()) / 2 - (height / 2);
    frame.setBounds(x, y, width, height);
  }
}
