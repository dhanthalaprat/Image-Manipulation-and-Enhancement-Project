package model;

import java.util.Objects;

/**
 * This class implements advanced operations on image.
 */
public class ImprovedImageProcessingImpl extends ImageProcessingModelImpl
        implements ImprovedImageProcessing {

  @Override
  public Image filtering(String operation, String sourceImageName, String destImageName) {
    Image sourceImage = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (sourceImage == null) {
      return null;
    }
    Image resultImage;
    switch (operation) {
      case "blur":
        resultImage = imageBlurring(sourceImage);
        break;
      case "sharpen":
        resultImage = imageSharpening(sourceImage);
        break;
      default:
        return null;
    }
    LIST_OF_IMAGES.put(destImageName, resultImage);
    return resultImage;
  }

  @Override
  public Image colorTransformation(String transformation, String sourceImageName,
                                   String destImageName) {
    Image sourceImage = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (sourceImage == null) {
      return null;
    }
    Image resultImage;
    resultImage = helperColorTransformation(sourceImage, getMatrix(transformation));
    LIST_OF_IMAGES.put(destImageName, resultImage);
    return resultImage;
  }

  private double[][] getMatrix(String operation) {
    if (Objects.equals(operation, "sepia")) {
      return new double[][]{
              {0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    } else if (operation.equals("greyscale")) {
      return new double[][]{
              {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};
    }
    return null;
  }

  private Image helperColorTransformation(Image image, double[][] values) {
    Pixel[][] listOfPixelsDestImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        listOfPixelsDestImage[i][j] = new RGBPixel(i, j, 0, 0,
                0);
        int r = image.getPixels()[i][j].getColorComponent().getRedComponent();
        int g = image.getPixels()[i][j].getColorComponent().getGreenComponent();
        int b = image.getPixels()[i][j].getColorComponent().getBlueComponent();
        listOfPixelsDestImage[i][j].getColorComponent().setRedComponent(Math.max(0, Math.min(255,
                (int) ((values[0][0] * r) + (values[0][1] * g) + (values[0][2] * b)))));
        listOfPixelsDestImage[i][j].getColorComponent().setGreenComponent(Math.max(0, Math.min(255,
                (int) ((values[1][0] * r) + (values[1][1] * g) + (values[1][2] * b)))));
        listOfPixelsDestImage[i][j].getColorComponent().setBlueComponent(Math.max(0, Math.min(255,
                (int) ((values[2][0] * r) + (values[2][1] * g) + (values[2][2] * b)))));
      }
    }
    return new RGBImage(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  @Override
  public Image dither(String sourceImageName, String destImageName) {
    Image image = LIST_OF_IMAGES.getOrDefault(sourceImageName, null);
    if (image == null) {
      return null;
    }
    Image greyscaleImage = super.greyscale("luma-component", sourceImageName,
            "greyscaleImage");
    Pixel[][] listOfPixelsDestImage = new RGBPixel[image.getHeight()][image.getWidth()];
    int r = image.getHeight();
    int c = image.getWidth();
    int old_color;
    int new_color;
    int error;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        listOfPixelsDestImage[i][j] = new RGBPixel(i, j, 0, 0,
                0);
      }
    }
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        old_color = greyscaleImage.getPixels()[i][j].getColorComponent().getRedComponent();
        new_color = (Math.abs(old_color - 255) < old_color) ? 255 : 0;
        error = old_color - new_color;
        listOfPixelsDestImage[i][j].getColorComponent().setRedComponent(new_color);
        listOfPixelsDestImage[i][j].getColorComponent().setGreenComponent(new_color);
        listOfPixelsDestImage[i][j].getColorComponent().setBlueComponent(new_color);
        if (j + 1 < c) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i, j + 1, error,
                  7.0 / 16.0);
        }
        if (j > 0 && i + 1 < r) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j - 1, error,
                  3.0 / 16.0);
        }
        if (i + 1 < r) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j, error,
                  5.0 / 16.0);
        }
        if (j + 1 < c && i + 1 < r) {
          listOfPixelsDestImage = addError(listOfPixelsDestImage, i + 1, j + 1, error,
                  1.0 / 16.0);
        }
      }
    }
    Image destImage = new RGBImage(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
    LIST_OF_IMAGES.put(destImageName, destImage);
    return destImage;

  }

  private Pixel[][] addError(Pixel[][] pixels, int r, int c, int error, double fraction) {
    pixels[r][c].getColorComponent().setRedComponent(pixels[r][c].getColorComponent()
            .getRedComponent() + (int) fraction * error);
    pixels[r][c].getColorComponent().setGreenComponent(pixels[r][c].getColorComponent()
            .getGreenComponent() + (int) fraction * error);
    pixels[r][c].getColorComponent().setBlueComponent(pixels[r][c].getColorComponent()
            .getBlueComponent() + (int) fraction * error);
    return pixels;
  }

  private Pixel[][] createPaddedArray(Pixel[][] pixels, int r, int c, int padding) {
    Pixel[][] paddedArray = new RGBPixel[r + 2 * padding][c + 2 * padding];
    for (int i = 0; i < r + 2 * padding; i++) {
      for (int j = 0; j < c + 2 * padding; j++) {
        if (i >= padding && i < r + padding && j >= padding && j < c + padding) {
          paddedArray[i][j] = new RGBPixel(i, j,
                  pixels[i - padding][j - padding].getColorComponent().getRedComponent(),
                  pixels[i - padding][j - padding].getColorComponent().getGreenComponent(),
                  pixels[i - padding][j - padding].getColorComponent().getBlueComponent());
        } else {
          paddedArray[i][j] = new RGBPixel(i, j, 0, 0, 0);
        }
      }
    }
    return paddedArray;
  }

  private Image imageBlurring(Image image) {
    Pixel[][] paddedArray = createPaddedArray(image.getPixels(), image.getHeight(),
            image.getWidth(), 1);
    int rSum;
    int gSum;
    int bSum;
    int r = image.getHeight();
    int c = image.getWidth();
    Pixel[][] listOfPixelsDestImage = new RGBPixel[r][c];
    for (int i = 1; i < r + 1; i++) {
      for (int j = 1; j < c + 1; j++) {
        rSum = (int) ((0.25) * paddedArray[i][j].getColorComponent().getRedComponent());
        gSum = (int) ((0.25) * paddedArray[i][j].getColorComponent().getGreenComponent());
        bSum = (int) ((0.25) * paddedArray[i][j].getColorComponent().getBlueComponent());
        for (int x = i - 1; x < i + 2; x++) {
          for (int y = j - 1; y < j + 2; y++) {
            if ((x == i - 1 && (y == j - 1 || y == j + 1)) || (x == i + 1 && (y == j - 1
                    || y == j + 1))) {
              rSum += (0.0625) * (paddedArray[x][y].getColorComponent().getRedComponent());
              gSum += (0.0625) * (paddedArray[x][y].getColorComponent().getGreenComponent());
              bSum += (0.0625) * (paddedArray[x][y].getColorComponent().getBlueComponent());
            } else if ((x == i && (y == j - 1 || y == j + 1)) || (y == j && (x == i - 1
                    || x == i + 1))) {
              rSum += (0.125) * (paddedArray[x][y].getColorComponent().getRedComponent());
              gSum += (0.125) * (paddedArray[x][y].getColorComponent().getGreenComponent());
              bSum += (0.125) * (paddedArray[x][y].getColorComponent().getBlueComponent());
            }
          }
        }
        listOfPixelsDestImage[i - 1][j - 1] = new RGBPixel(i - 1, j - 1,
                Math.max(0, Math.min(rSum, 255)), Math.max(0, Math.min(gSum, 255)),
                Math.max(0, Math.min(bSum, 255)));
      }
    }
    return new RGBImage(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }

  private Image imageSharpening(Image image) {
    Pixel[][] paddedArray = createPaddedArray(image.getPixels(), image.getHeight(),
            image.getWidth(), 2);
    int rSum;
    int gSum;
    int bSum;
    int r = image.getHeight();
    int c = image.getWidth();
    Pixel[][] listOfPixelsDestImage = new RGBPixel[r][c];
    for (int i = 2; i < r + 2; i++) {
      for (int j = 2; j < c + 2; j++) {
        rSum = paddedArray[i][j].getColorComponent().getRedComponent();
        gSum = paddedArray[i][j].getColorComponent().getGreenComponent();
        bSum = paddedArray[i][j].getColorComponent().getBlueComponent();
        for (int x = i - 2; x < i + 3; x++) {
          for (int y = j - 2; y < j + 3; y++) {
            if (x == i - 2 || x == i + 2 || y == j - 2 || y == j + 2) {
              rSum += (-0.125) * (paddedArray[x][y].getColorComponent().getRedComponent());
              gSum += (-0.125) * (paddedArray[x][y].getColorComponent().getGreenComponent());
              bSum += (-0.125) * (paddedArray[x][y].getColorComponent().getBlueComponent());
            } else if (x == i - 1 || x == i + 1 || y == j - 1 || y == j + 1) {
              rSum += (0.25) * (paddedArray[x][y].getColorComponent().getRedComponent());
              gSum += (0.25) * (paddedArray[x][y].getColorComponent().getGreenComponent());
              bSum += (0.25) * (paddedArray[x][y].getColorComponent().getBlueComponent());
            }
          }
        }
        listOfPixelsDestImage[i - 2][j - 2] = new RGBPixel(i - 2, j - 2,
                Math.max(0, Math.min(rSum, 255)), Math.max(0, Math.min(gSum, 255)),
                Math.max(0, Math.min(bSum, 255)));
      }
    }
    return new RGBImage(image.getWidth(), image.getHeight(), image.getMaxValueOfColor(),
            listOfPixelsDestImage);
  }
}
