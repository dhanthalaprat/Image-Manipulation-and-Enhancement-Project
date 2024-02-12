package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;


/**
 * This command class splits the given image into three greyscale images containing its red,
 * green and blue components respectively.
 */
public class RGBSplit implements ImageCommandController {
  String sourceImageName;
  String redImage;
  String greenImage;
  String blueImage;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param sourceImageName the string which contains source image name.
   * @param redImage        the string which contains destination red image name.
   * @param greenImage      the string which contains destination green image name.
   * @param blueImage       the string which contains destination blue image name.
   */
  public RGBSplit(String sourceImageName, String redImage, String greenImage, String blueImage) {
    this.sourceImageName = sourceImageName;
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
  }


  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    return m.rgbSplit(sourceImageName, redImage, greenImage, blueImage);
  }
}
