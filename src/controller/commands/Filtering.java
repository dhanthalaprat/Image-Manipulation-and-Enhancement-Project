package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;

/**
 * This command class performs filtering operations such as blur and sharpen on an image.
 */
public class Filtering implements ImageCommandController {
  String operation;
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param operation       the filtering operation to be performed on the image.
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public Filtering(String operation, String sourceImageName, String destImageName) {
    this.operation = operation;
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    Image filteringImage = m.filtering(operation, sourceImageName, destImageName);
    if (filteringImage == null) {
      return null;
    } else {
      return List.of(new Image[]{filteringImage});
    }
  }
}
