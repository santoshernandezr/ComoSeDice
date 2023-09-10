package org.game.Common;

import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Class for panels that can be reused in the all the GUIs. */
public class CommonPanels {

  /**
   * Creating the picture panel which will contain the logo.png that's in the resources' directory.
   * This panel will contain the Image Icon that is made in the next few lines, IMAGE_ICON.
   *
   * @param xCoordinate x coordinate of where we want to place the Picture panel.
   * @param yCoordinate y coordinate of where we want to place the Picture panel.
   * @return JPanel that will be used for the Picture panel.
   */
  public static JPanel picturePanel(int xCoordinate, int yCoordinate) {
    JPanel PICTURE_PANEL = new JPanel();
    PICTURE_PANEL.setBounds(xCoordinate, yCoordinate, 200, 100);

    ImageIcon IMAGE_ICON =
        new ImageIcon(Objects.requireNonNull(CommonPanels.class.getResource("/images/logo.png")));
    JLabel IMAGE_LABEL = new JLabel(IMAGE_ICON);

    PICTURE_PANEL.add(IMAGE_LABEL);

    return PICTURE_PANEL;
  }
}
