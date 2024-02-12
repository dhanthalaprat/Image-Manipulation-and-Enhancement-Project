package model;

/**
 * This class represents RGB color factor for each pixel.
 */
public class Color extends java.awt.Color {
  private int redComponent;
  private int greenComponent;
  private int blueComponent;

  /**
   * Constructor to initialize values of components of color object.
   *
   * @param redComponent   is the red component value of color.
   * @param greenComponent is the green component value of color
   * @param blueComponent  is the blue component value of color
   */
  public Color(int redComponent, int greenComponent, int blueComponent) {
    super(redComponent, greenComponent, blueComponent);
    this.redComponent = redComponent;
    this.greenComponent = greenComponent;
    this.blueComponent = blueComponent;
  }

  /**
   * Constructor to initialize values of components of color object using rgb value.
   *
   * @param rgbValue the rgb value of color component.
   */
  public Color(int rgbValue) {
    super(rgbValue);
    this.redComponent = this.getRed();
    this.greenComponent = this.getGreen();
    this.blueComponent = this.getBlue();
  }

  /**
   * Returns the red component of Color.
   *
   * @return integer value of red component
   */
  public int getRedComponent() {
    return redComponent;
  }

  /**
   * Sets the red component of Color.
   *
   * @param redComponent integer value of red component
   */
  public void setRedComponent(int redComponent) {
    this.redComponent = redComponent;
  }

  /**
   * Returns the green component of Color.
   *
   * @return integer value of green component
   */
  public int getGreenComponent() {
    return greenComponent;
  }

  /**
   * Sets the red component of Color.
   *
   * @param greenComponent integer value of red component
   */
  public void setGreenComponent(int greenComponent) {
    this.greenComponent = greenComponent;
  }

  /**
   * Returns the blue component of Color.
   *
   * @return integer value of blue component
   */
  public int getBlueComponent() {
    return blueComponent;
  }

  /**
   * Sets the blue component of Color.
   *
   * @param blueComponent integer value of blue component
   */
  public void setBlueComponent(int blueComponent) {
    this.blueComponent = blueComponent;
  }
}
