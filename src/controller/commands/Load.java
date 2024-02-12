package controller.commands;

import java.util.List;

import controller.ImageUtil;
import model.Image;
import model.ImprovedImageProcessing;

/**
 * This command class load an image from the specified path and refers it  in the program
 * by the given image name.
 */
public class Load implements ImageCommandController {
  String imagePath;
  String destImageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param imagePath     the string which contains source ppm image path.
   * @param destImageName the string which contains destination image name.
   */
  public Load(String imagePath, String destImageName) {
    this.imagePath = imagePath;
    this.destImageName = destImageName;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    String extension;
    Image image = null;
    int index = imagePath.lastIndexOf('.');
    if (index > 0) {
      extension = imagePath.substring(index + 1);
      if (extension.equalsIgnoreCase("PPM")) {
        image = ImageUtil.readPPM(imagePath);
      } else {
        image = ImageUtil.readImage(imagePath);
      }
    }
    if (image == null) {
      return null;
    }
    return List.of(new Image[]{m.loadImage(image, destImageName)});
  }
}
