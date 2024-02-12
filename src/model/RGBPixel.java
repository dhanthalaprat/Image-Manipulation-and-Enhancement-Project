package model;

/**
 * The RGBPixel class represents a single RGB pixel in an image, storing its row and column numbers,
 * as well as the color component of the pixel.
 */
public class RGBPixel implements Pixel {
  private final Color colorComponent;

  /**
   * Constructor to initialize the RGBPixel object with the provided properties.
   *
   * @param row            the row number of the pixel.
   * @param column         the column number of the pixel.
   * @param redComponent   the red component value of the pixel's color component.
   * @param greenComponent the green component value of the pixel's color component.
   * @param blueComponent  the blue component value of the pixel's color component.
   */
  public RGBPixel(int row, int column, int redComponent, int greenComponent, int blueComponent) {
    int pixelRow = row;
    int pixelColumn = column;
    this.colorComponent = new Color(redComponent, greenComponent, blueComponent);
  }

  @Override
  public Color getColorComponent() {
    return colorComponent;
  }

  @Override
  public int getValueComponent() {
    return Math.max(this.colorComponent.getRedComponent(), Math.max(
            this.colorComponent.getGreenComponent(),
            this.colorComponent.getBlueComponent()));
  }

  @Override
  public int getIntensityComponent() {
    return (this.colorComponent.getRedComponent() + this.colorComponent.getGreenComponent()
            + this.colorComponent.getBlueComponent()) / 3;
  }

  @Override
  public int getLumaComponent() {
    return (int) Math.round((0.2126 * this.colorComponent.getRedComponent())
            + (0.7152 * this.colorComponent.getGreenComponent())
            + (0.0722 * this.colorComponent.getBlueComponent()));
  }
}
