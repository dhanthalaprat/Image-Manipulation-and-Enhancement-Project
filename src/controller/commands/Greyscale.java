package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;

/**
 * This command class creates a greyscale image with the component of the image with the given name,
 * and refer to it henceforth in the program by the given destination name.
 * Red, green, blue, value, luma and intensity are the components that are supported.
 */
public class Greyscale implements ImageCommandController {
  String component;
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param component       the component value on which the image needs to grey-scaled.
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public Greyscale(String component, String sourceImageName, String destImageName) {
    this.component = component;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }


  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    Image greyscaleImage = m.greyscale(component, sourceImageName, destImageName);
    if (greyscaleImage == null) {
      return null;
    } else {
      return List.of(new Image[]{greyscaleImage});
    }
  }
}

