package model;

/**
 * This interface specifies advanced operations on image.
 */
public interface ImprovedImageProcessing extends ImageProcessingModel {

  /**
   * This method applies an appropriate kernel on the image and filters it to perform certain
   * operations such as blur, sharpen and so on.
   *
   * @param operation       the operation to be performed for filtering
   * @param sourceImageName the string which contains source image name
   * @param destImageName   the string which contains destination image name
   * @return destination Image
   */
  Image filtering(String operation, String sourceImageName, String destImageName);

  /**
   * This method applies transformation on the color of a pixel based on its own color. These
   * transformations can include operations such as sepia.
   *
   * @param transformation  the transformation to be performed
   * @param sourceImageName the string which contains source image name
   * @param destImageName   the string which contains destination image name
   * @return destination Image
   */
  Image colorTransformation(String transformation, String sourceImageName, String destImageName);

  /**
   * This method performs dithering on the image.
   *
   * @param sourceImageName the string which contains source image name
   * @param destImageName   the string which contains destination image name
   * @return destination Image
   */
  Image dither(String sourceImageName, String destImageName);

}
