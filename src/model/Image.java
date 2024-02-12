package model;

/**
 * The Image interface represents an image, which consists of an array of pixels and its
 * properties/attributes.
 */
public interface Image {

  /**
   * Returns the pixel values of Image.
   *
   * @return Array of pixels of Image
   */
  Pixel[][] getPixels();

  /**
   * Returns the width of Image.
   *
   * @return the integer value of width of Image
   */
  int getWidth();

  /**
   * Returns the height of Image.
   *
   * @return the integer value of height of Image
   */
  int getHeight();

  /**
   * Returns the maximum value of color of Image.
   *
   * @return the integer value of maximum value of color of Image
   */
  int getMaxValueOfColor();
}
