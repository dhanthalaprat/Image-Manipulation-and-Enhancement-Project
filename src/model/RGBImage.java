package model;

/**
 * The RGBImage class represents an RGB image and its properties such as width, height,
 * maximum value of color,and an array of pixels that make up the image.
 */
public class RGBImage implements Image {

  private final int width;
  private final int height;
  private final int maxValueOfColor;
  private final Pixel[][] listOfPixels;

  /**
   * Constructor to initialize the attributes of RGBImage class.
   *
   * @param width           is width of image.
   * @param height          is height of image.
   * @param maxValueOfColor max color value in the all the pixels of class.
   * @param listOfPixels    2-D array of pixels.
   */
  public RGBImage(int width, int height, int maxValueOfColor, Pixel[][] listOfPixels) {
    this.width = width;
    this.height = height;
    this.maxValueOfColor = maxValueOfColor;
    this.listOfPixels = listOfPixels;
  }

  /**
   * Returns the pixel values of Image.
   *
   * @return Array of pixels of Image
   */
  @Override
  public Pixel[][] getPixels() {
    return this.listOfPixels;
  }

  /**
   * Returns the width of Image.
   *
   * @return the integer value of width of Image
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of Image.
   *
   * @return the integer value of height of Image
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Returns the maximum value of color of Image.
   *
   * @return the integer value of maximum value of color of Image
   */
  @Override
  public int getMaxValueOfColor() {
    return maxValueOfColor;
  }
}
