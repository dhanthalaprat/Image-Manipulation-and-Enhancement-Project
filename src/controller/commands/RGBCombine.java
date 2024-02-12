package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;


/**
 * This command class combines the three greyscale images into a single image that gets its red,
 * green and blue components from the three images respectively.
 */
public class RGBCombine implements ImageCommandController {
  String destImageName;
  String redImage;
  String greenImage;
  String blueImage;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param destImageName the string which contains destination image name.
   * @param redImage      the string which contains source red image name.
   * @param greenImage    the string which contains source green image name.
   * @param blueImage     the string which contains source blue image name.
   */
  public RGBCombine(String destImageName, String redImage, String greenImage, String blueImage) {
    this.blueImage = blueImage;
    this.destImageName = destImageName;
    this.greenImage = greenImage;
    this.redImage = redImage;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    Image rgbCombineImage = m.rgbCombine(destImageName, redImage, greenImage, blueImage);
    if (rgbCombineImage == null) {
      return null;
    } else {
      return List.of(new Image[]{rgbCombineImage});
    }
  }
}
