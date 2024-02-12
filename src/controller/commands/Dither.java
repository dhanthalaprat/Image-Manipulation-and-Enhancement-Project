package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;

/**
 * This command class performs dithering operation on an image.
 */
public class Dither implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public Dither(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    Image ditherImage = m.dither(sourceImageName, destImageName);
    if (ditherImage == null) {
      return null;
    } else {
      return List.of(new Image[]{ditherImage});
    }
  }
}
