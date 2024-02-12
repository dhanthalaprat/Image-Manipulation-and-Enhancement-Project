package controller.commands;

import java.util.List;

import controller.ImageUtil;
import model.Image;
import model.ImprovedImageProcessing;


/**
 * This command class saves the image with the given name to the specified path
 * which should include the name of the file.
 */
public class Save implements ImageCommandController {
  String imagePath;
  String imageName;

  /**
   * Constructor to initialize the values passed from controller.
   *
   * @param imagePath the string which contains destination ppm image path.
   * @param imageName the string which contains source image name.
   */
  public Save(String imagePath, String imageName) {
    this.imagePath = imagePath;
    this.imageName = imageName;
  }

  @Override
  public List<Image> execute(ImprovedImageProcessing m) {
    Image image = m.saveImage(imageName);
    if (image == null) {
      return null;
    }
    String extension = null;
    int index = imagePath.lastIndexOf('.');
    if (index > 0) {
      extension = imagePath.substring(index + 1);
    }
    if (extension.equalsIgnoreCase("PPM")) {
      ImageUtil.createPPM(imagePath, image);
    } else {
      ImageUtil.createImage(extension, imagePath, image);
    }
    if (image == null) {
      return null;
    } else {
      return List.of(new Image[]{image});
    }
  }
}
