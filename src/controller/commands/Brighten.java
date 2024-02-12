package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;

/**
 * This command class brightens the image by the given increment.
 */
public class Brighten implements ImageCommandController {
  int increment;
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param increment       takes in integer value with which the image is brightened or darkened
   *                        based on its sign.
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public Brighten(int increment, String sourceImageName, String destImageName) {
    this.increment = increment;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    Image brightenImage = m.brighten(increment, sourceImageName, destImageName);
    if (brightenImage == null) {
      return null;
    } else {
      return List.of(new Image[]{brightenImage});
    }
  }
}
