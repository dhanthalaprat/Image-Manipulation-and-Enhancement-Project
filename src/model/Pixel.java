package model;

/**
 * The Pixel interface represents a single pixel in an image.
 * It provides methods for retrieving color, value, intensity, and luma components of the pixel.
 */
public interface Pixel {

  /**
   * Returns the color component of the pixel.
   *
   * @return the color component of the pixel as a Color object
   */
  Color getColorComponent();

  /**
   * Returns the value component of the pixel.
   *
   * @return the value component of the pixel as an integer.
   */
  int getValueComponent();

  /**
   * Returns the intensity component of the pixel.
   *
   * @return the intensity component of the pixel as an integer.
   */

  int getIntensityComponent();

  /**
   * Returns the luma component of the pixel.
   *
   * @return the luma component of the pixel as an integer.
   */
  int getLumaComponent();
}
