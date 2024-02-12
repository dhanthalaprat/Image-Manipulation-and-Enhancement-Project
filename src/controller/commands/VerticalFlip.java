package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;


/**
 * This command class flips an image vertically to create a new image,
 * referred to henceforth by the given destination name.
 */
public class VerticalFlip implements ImageCommandController {
  String sourceImageName;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   */
  public VerticalFlip(String sourceImageName, String destImageName) {
    this.sourceImageName = sourceImageName;
    this.destImageName = destImageName;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    Image verticalFlipImage = m.verticalFlip(sourceImageName, destImageName);
    if (verticalFlipImage == null) {
      return null;
    } else {
      return List.of(new Image[]{verticalFlipImage});
    }
  }
}