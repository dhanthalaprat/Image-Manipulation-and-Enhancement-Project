package model;

import java.util.List;

/**
 * This interface specifies the operations on image.
 *
 * <p>An image is characterized by its width,height,
 * its pixels representing its respective RGB-color.
 *
 * <p>It can be asked to produce an image using on of the commands below.
 * Another command can be used to save this image locally in ppm file format.
 */
public interface ImageProcessingModel {

  /**
   * Method to initialize the values passed from controller.
   *
   * @param image     the image to be loaded.
   * @param imageName the string which contains destination image name.
   */
  Image loadImage(Image image, String imageName);

  /**
   * This method saves the image with the given name to the specified path which should
   * include the name of the file.
   *
   * @param imageName the string which contains source image name.
   * @return destination Image.
   */

  Image saveImage(String imageName);

  /**
   * This method creates a greyscale image with the component of the image with the given name,
   * and refer to it henceforth in the program by the given destination name.
   * Red, green, blue, value, luma and intensity are the components that are supported.
   *
   * @param component       the component value on which the image needs to grey-scaled.
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   * @return destination Image.
   */
  Image greyscale(String component, String sourceImageName, String destImageName);

  /**
   * This method flips image horizontally to create a new image,
   * referred to henceforth by the given destination name.
   *
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   * @return destination Image.
   */
  Image horizontalFlip(String sourceImageName, String destImageName);

  /**
   * This method flips image vertically to create a new image,
   * referred to henceforth by the given destination name.
   *
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   * @return destination Image.
   */
  Image verticalFlip(String sourceImageName, String destImageName);

  /**
   * This method brightens the image by the given increment.
   *
   * @param increment       takes in integer value with which the image is brightened or darkened
   *                        based on its sign.
   * @param sourceImageName the string which contains source image name.
   * @param destImageName   the string which contains destination image name.
   * @return destination Image.
   */
  Image brighten(int increment, String sourceImageName, String destImageName);

  /**
   * This method splits the given image into three greyscale images containing its red,
   * green and blue components respectively.
   *
   * @param sourceImageName the string which contains source image name.
   * @param redImageName    the string which contains destination red image name.
   * @param greenImageName  the string which contains destination green image name.
   * @param blueImageName   the string which contains destination blue image name.
   * @return destination Image.
   */
  List<Image> rgbSplit(String sourceImageName, String redImageName, String greenImageName,
                       String blueImageName);

  /**
   * This command class combines the three greyscale images into a single image that gets its red,
   * green and blue components from the three images respectively.
   *
   * @param destImageName  the string which contains destination image name.
   * @param redImageName   the string which contains source red image name.
   * @param greenImageName the string which contains source green image name.
   * @param blueImageName  the string which contains source blue image name.
   * @return destination Image.
   */
  Image rgbCombine(String destImageName, String redImageName, String greenImageName,
                   String blueImageName);
}
