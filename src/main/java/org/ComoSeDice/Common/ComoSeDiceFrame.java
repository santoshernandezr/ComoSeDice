package org.ComoSeDice.Common;

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

  public ComoSeDiceFrame(String title) {
    super(title);
    setLayout(null);

    setGUIinMiddleOfScreen(this);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Sets the JFrame right in the middle of the screen.
   *
   * @param frame the frame we wish to put in the middle of the screen.
   */
  public static void setGUIinMiddleOfScreen(JFrame frame) {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int x = ((int) size.getWidth() / 2) - 200;
    int y = ((int) size.getHeight()) / 2 - 200;
    frame.setBounds(x, y, 400, 400);
  }
}
