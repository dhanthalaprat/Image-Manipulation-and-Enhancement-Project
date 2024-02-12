package model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import controller.commands.ImageCommandController;
import controller.commands.Load;
import controller.commands.Save;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This class contains tests for ImageProcessing model class.
 */
public class ImageProcessingImplTest {
  private ImprovedImageProcessing model;

  @Before
  public void setup() {
    model = new ImprovedImageProcessingImpl();
  }

  @Test
  public void testLoadSamePPMFileInImagesCreateDifferentObjects() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    controller = new Load("res/dog.ppm", "another");
    List<Image> another = controller.execute(model);
    assertNotEquals(original.get(0), another.get(0));
  }

  @Test
  public void testLoadImageFromExistingPPMFile() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    assertEquals(original.get(0).getWidth(), 200);
    assertEquals(original.get(0).getHeight(), 201);
    assertEquals(original.get(0).getMaxValueOfColor(), 255);
  }

  @Test
  public void testLoadImageFromNonExistingPPMFile() {
    ImageCommandController controller = new Load("images/k.ppm", "original");
    List<Image> original = controller.execute(model);
    assertNull(original);
  }

  @Test
  public void testLoadImageFromNonExistingImage() {
    ImageCommandController controller = new Load("images/k.ppm", "original");
    List<Image> original = controller.execute(model);
    assertNull(original);
  }

  @Test
  public void testSaveImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    File f = new File("res/Original.ppm");
    assertTrue(f.exists());
    controller = new Save("res/Original.ppm", "original");
    original = controller.execute(model);
    assertTrue(f.exists());
    f.delete();
  }

  @Test
  public void testCheckPPMFormat() throws FileNotFoundException {
    ImageCommandController controller = new Load("res/testFile.ppm",
            "original");
    List<Image> original = controller.execute(model);
    File f = new File("res/Original.ppm");
    f.delete();
    assertFalse(f.exists());
    controller = new Save("res/Original.ppm", "original");
    original = controller.execute(model);
    assertTrue(f.exists());
    String filename = "res/Original.ppm";
    Scanner sc = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    assertEquals("P3", sc.nextLine());
    assertEquals("# Created by GIMP version 2.10.20 PNM plug-in", sc.nextLine());
    assertEquals(sc.nextInt(), original.get(0).getWidth());
    assertEquals(sc.nextInt(), original.get(0).getHeight());
    assertEquals(sc.nextInt(), original.get(0).getMaxValueOfColor());
    assertEquals(sc.nextInt(), original.get(0).getPixels()[0][0].getColorComponent()
            .getRedComponent());
    assertEquals(sc.nextInt(), original.get(0).getPixels()[0][0].getColorComponent()
            .getGreenComponent());
    assertEquals(sc.nextInt(), original.get(0).getPixels()[0][0].getColorComponent()
            .getBlueComponent());
    assertEquals(sc.nextInt(), original.get(0).getPixels()[0][1].getColorComponent()
            .getRedComponent());
    assertEquals(sc.nextInt(), original.get(0).getPixels()[0][1].getColorComponent()
            .getGreenComponent());
    assertEquals(sc.nextInt(), original.get(0).getPixels()[0][1].getColorComponent()
            .getBlueComponent());
  }

  @Test
  public void testBrightenImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    int increment = 50;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertTrue(brightenedImage.getPixels()[i][j].getColorComponent().getRedComponent()
                - original.get(0).getPixels()[i][j].getColorComponent()
                .getRedComponent() <= increment);
        assertTrue(brightenedImage.getPixels()[i][j].getColorComponent()
                .getGreenComponent()
                - original.get(0).getPixels()[i][j].getColorComponent().getGreenComponent()
                <= increment);
        assertTrue(brightenedImage.getPixels()[i][j].getColorComponent()
                .getBlueComponent()
                - original.get(0).getPixels()[i][j].getColorComponent().getBlueComponent()
                <= increment);
      }
    }
  }

  @Test
  public void testBrightenNullImage() {
    int increment = 50;
    Image brightenedImage = model.brighten(increment, null,
            "brightenedImage");
    assertNull(brightenedImage);
  }

  @Test
  public void testBrightenImageDoesNotExceedMaxValue() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    int increment = 50;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertTrue(brightenedImage.getPixels()[i][j].getColorComponent().getRedComponent()
                <= brightenedImage.getMaxValueOfColor());
        assertTrue(brightenedImage.getPixels()[i][j].getColorComponent()
                .getGreenComponent() <= brightenedImage.getMaxValueOfColor());
        assertTrue(brightenedImage.getPixels()[i][j].getColorComponent().getBlueComponent()
                <= brightenedImage.getMaxValueOfColor());
      }
    }
  }

  @Test
  public void testBrightenImageIncrementMaxValue() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    int increment = 1000;
    Image maxBrightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(255,
                maxBrightenedImage.getPixels()[i][j].getColorComponent().getRedComponent());
        assertEquals(255,
                maxBrightenedImage.getPixels()[i][j].getColorComponent().getGreenComponent());
        assertEquals(255,
                maxBrightenedImage.getPixels()[i][j].getColorComponent().getBlueComponent());
      }
    }
  }

  @Test
  public void testBrightenImageIncrementByMaxNegativeValue() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    int increment = -1000;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(0, brightenedImage.getPixels()[i][j].getColorComponent()
                .getRedComponent());
        assertEquals(0, brightenedImage.getPixels()[i][j].getColorComponent()
                .getGreenComponent());
        assertEquals(0, brightenedImage.getPixels()[i][j].getColorComponent()
                .getBlueComponent());
      }
    }
  }

  @Test
  public void testBrightenImageIncrementByNegativeValue() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    int increment = -25;
    Image brightenedImage = model.brighten(increment, "original",
            "brightenedImage");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertTrue(Math.abs(brightenedImage.getPixels()[i][j].getColorComponent()
                .getRedComponent() - original.get(0).getPixels()[i][j].getColorComponent()
                .getRedComponent()) <= Math.abs(increment));
        assertTrue(Math.abs(brightenedImage.getPixels()[i][j].getColorComponent()
                .getGreenComponent() - original.get(0).getPixels()[i][j].getColorComponent()
                .getGreenComponent()) <= Math.abs(increment));
        assertTrue(Math.abs(brightenedImage.getPixels()[i][j].getColorComponent()
                .getBlueComponent() - original.get(0).getPixels()[i][j].getColorComponent()
                .getBlueComponent()) <= Math.abs(increment));
      }
    }
  }

  @Test
  public void testVerticalFlipNullImage() {
    Image verticalFlipImage = model.verticalFlip(null,
            "verticalFlipImage");
    assertNull(verticalFlipImage);
  }

  @Test
  public void testVerticalFlip() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image verticalFlipFirstTime = model.verticalFlip("original",
            "verticalFlipFirstTime");
    Image verticalFlipSecondTime = model.verticalFlip("verticalFlipFirstTime",
            "verticalFlipSecondTime");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(verticalFlipFirstTime.getPixels()[i][j].getColorComponent()
                .getRedComponent(), original.get(0).getPixels()[original.get(0)
                .getHeight() - i - 1][j]
                .getColorComponent().getRedComponent());
        assertEquals(verticalFlipFirstTime.getPixels()[i][j].getColorComponent()
                .getGreenComponent(), original.get(0).getPixels()[original.get(0)
                .getHeight() - i - 1][j]
                .getColorComponent().getGreenComponent());
        assertEquals(verticalFlipFirstTime.getPixels()[i][j].getColorComponent().getBlueComponent(),
                original.get(0).getPixels()[original.get(0).getHeight() - i - 1][j]
                        .getColorComponent()
                        .getBlueComponent());
      }
    }
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(verticalFlipSecondTime.getPixels()[i][j].getColorComponent().getRedComponent(),
                original.get(0).getPixels()[i][j].getColorComponent().getRedComponent());
        assertEquals(verticalFlipSecondTime.getPixels()[i][j].getColorComponent()
                        .getGreenComponent(),
                original.get(0).getPixels()[i][j].getColorComponent().getGreenComponent());
        assertEquals(verticalFlipSecondTime.getPixels()[i][j].getColorComponent()
                        .getBlueComponent(),
                original.get(0).getPixels()[i][j].getColorComponent().getBlueComponent());
      }
    }
  }

  @Test
  public void testHorizontalFlipNullImage() {
    Image horizontalFlipImage = model.horizontalFlip(null,
            "horizontalFlipImage");
    assertNull(horizontalFlipImage);
  }

  @Test
  public void testHorizontalFlip() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image horizontalFlipFirstTime = model.horizontalFlip("original",
            "horizontalFlipFirstTime");
    Image horizontalFlipSecondTime = model.horizontalFlip("horizontalFlipFirstTime",
            "horizontalFlipSecondTime");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].getColorComponent()
                .getRedComponent(), original.get(0).getPixels()[i][original.get(0)
                .getWidth() - 1 - j]
                .getColorComponent().getRedComponent());
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].getColorComponent()
                .getGreenComponent(), original.get(0).getPixels()[i][original.get(0)
                .getWidth() - 1 - j]
                .getColorComponent().getGreenComponent());
        assertEquals(horizontalFlipFirstTime.getPixels()[i][j].getColorComponent()
                .getBlueComponent(), original.get(0).getPixels()[i][original.get(0)
                .getWidth() - 1 - j]
                .getColorComponent().getBlueComponent());
      }
    }
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].getColorComponent()
                .getRedComponent(), original.get(0).getPixels()[i][j].getColorComponent()
                .getRedComponent());
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].getColorComponent()
                .getGreenComponent(), original.get(0).getPixels()[i][j].getColorComponent()
                .getGreenComponent());
        assertEquals(horizontalFlipSecondTime.getPixels()[i][j].getColorComponent()
                .getBlueComponent(), original.get(0).getPixels()[i][j].getColorComponent()
                .getBlueComponent());
      }
    }
  }

  @Test
  public void testGreyScaleOnRedComponentNullImage() {
    Image greyscaleOnRedComponent = model.greyscale("red-component",
            null, "greyscaleOnRedComponent");
    assertNull(greyscaleOnRedComponent);
  }

  @Test
  public void testGreyScaleOnRedComponent() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image greyscaleOnRedComponent = model.greyscale("red-component",
            "original", "greyscaleOnRedComponent");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getRedComponent(),
                greyscaleOnRedComponent.getPixels()[i][j].getColorComponent().getBlueComponent());
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getRedComponent(),
                greyscaleOnRedComponent.getPixels()[i][j].getColorComponent().getGreenComponent());
      }
    }
  }

  @Test
  public void testGreyScaleOnGreenComponentNullImage() {
    Image greyscaleOnGreenComponent = model.greyscale("green-component",
            null, "greyscaleOnGreenComponent");
    assertNull(greyscaleOnGreenComponent);
  }

  @Test
  public void testGreyScaleOnGreenComponent() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image greyscaleOnGreenComponent = model.greyscale("green-component",
            "original", "greyscaleOnGreenComponent");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getGreenComponent(),
                greyscaleOnGreenComponent.getPixels()[i][j].getColorComponent().getBlueComponent());
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getGreenComponent(),
                greyscaleOnGreenComponent.getPixels()[i][j].getColorComponent().getRedComponent());
      }
    }
  }

  @Test
  public void testGreyScaleOnBlueComponentNullImage() {
    Image greyscaleOnBlueComponent = model.greyscale("blue-component",
            null, "greyscaleOnBlueComponent");
    assertNull(greyscaleOnBlueComponent);
  }

  @Test
  public void testGreyScaleOnBlueComponent() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image greyscaleOnBlueComponent = model.greyscale("blue-component",
            "original", "greyscaleOnBlueComponent");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getBlueComponent(),
                greyscaleOnBlueComponent.getPixels()[i][j].getColorComponent().getRedComponent());
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getBlueComponent(),
                greyscaleOnBlueComponent.getPixels()[i][j].getColorComponent().getGreenComponent());
      }
    }
  }

  @Test
  public void testGreyScaleOnLumaComponentOnNullImage() {
    Image greyscaleOnLumaComponent = model.greyscale("luma-component",
            null, "greyscaleOnLumaComponent");
    assertNull(greyscaleOnLumaComponent);
  }

  @Test
  public void testGreyScaleOnLumaComponent() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image greyscaleOnLumaComponent = model.greyscale("luma-component",
            "original", "greyscaleOnLumaComponent");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getLumaComponent(),
                greyscaleOnLumaComponent.getPixels()[i][j].getColorComponent().getRedComponent());
        assertEquals(original.get(0).getPixels()[i][j].getLumaComponent(),
                greyscaleOnLumaComponent.getPixels()[i][j].getColorComponent().getGreenComponent());
        assertEquals(original.get(0).getPixels()[i][j].getLumaComponent(),
                greyscaleOnLumaComponent.getPixels()[i][j].getColorComponent().getBlueComponent());
      }
    }
  }

  @Test
  public void testGreyScaleOnValueComponentOnNullImage() {
    Image greyscaleOnValueComponent = model.greyscale("value-component",
            null, "greyscaleOnValueComponent");
    assertNull(greyscaleOnValueComponent);
  }

  @Test
  public void testGreyScaleOnValueComponent() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image greyscaleOnValueComponent = model.greyscale("value-component",
            "original", "greyscaleOnValueComponent");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getValueComponent(),
                greyscaleOnValueComponent.getPixels()[i][j].getColorComponent().getRedComponent());
        assertEquals(original.get(0).getPixels()[i][j].getValueComponent(),
                greyscaleOnValueComponent.getPixels()[i][j].getColorComponent()
                        .getGreenComponent());
        assertEquals(original.get(0).getPixels()[i][j].getValueComponent(),
                greyscaleOnValueComponent.getPixels()[i][j].getColorComponent().getBlueComponent());
      }
    }
  }

  @Test
  public void testGreyScaleOnIntensityComponentOnNullImage() {
    Image greyscaleOnIntensityComponent = model.greyscale("intensity-component",
            null, "greyscaleOnIntensityComponent");
    assertNull(greyscaleOnIntensityComponent);
  }

  @Test
  public void testGreyScaleOnIntensityComponent() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image greyscaleOnIntensityComponent = model.greyscale("intensity-component",
            "original", "greyscaleOnIntensityComponent");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getIntensityComponent(),
                greyscaleOnIntensityComponent.getPixels()[i][j].getColorComponent()
                        .getRedComponent());
        assertEquals(original.get(0).getPixels()[i][j].getIntensityComponent(),
                greyscaleOnIntensityComponent.getPixels()[i][j].getColorComponent()
                        .getGreenComponent());
        assertEquals(original.get(0).getPixels()[i][j].getIntensityComponent(),
                greyscaleOnIntensityComponent.getPixels()[i][j].getColorComponent()
                        .getBlueComponent());
      }
    }
  }

  @Test
  public void testRGBSplit() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    List<Image> images = model.rgbSplit("original", "redImage",
            "greenImage", "blueImage");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getRedComponent(),
                images.get(0).getPixels()[i][j].getColorComponent().getBlueComponent());
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getRedComponent(),
                images.get(0).getPixels()[i][j].getColorComponent().getGreenComponent());
      }
    }
  }

  @Test
  public void testRGBCombineOnNullRedImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image redImage = null;
    Image greenImage = model.greyscale("green-component",
            "original", "greenImage");
    Image blueImage = model.greyscale("blue-component",
            "original", "blueImage");
    Image resultImageRed = model.rgbCombine("resultImageRed", "redImage",
            "greenImage", "blueImage");
    assertNull(resultImageRed);
  }

  @Test
  public void testRGBCombineOnNullGreenImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image redImage = model.greyscale("red-component",
            "original", "redImage");
    Image greenImage = null;
    Image blueImage = model.greyscale("blue-component",
            "original", "blueImage");
    Image resultImageGreen = model.rgbCombine("resultImageGreen",
            "redImage", "greenImage", "blueImage");
    assertNull(resultImageGreen);
  }

  @Test
  public void testRGBCombineOnNullBlueImage() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image redImage = model.greyscale("red-component",
            "original", "redImage");
    Image greenImage = model.greyscale("green-component",
            "original", "greenImage");
    Image blueImage = null;
    Image resultImageBlue = model.rgbCombine("resultImageBlue",
            "redImage", "greenImage", "blueImage");
    assertNull(resultImageBlue);
  }

  @Test
  public void testRGBCombine() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    Image redImage = model.greyscale("red-component",
            "original", "redImage");
    Image greenImage = model.greyscale("green-component",
            "original", "greenImage");
    Image blueImage = model.greyscale("blue-component",
            "original", "blueImage");
    Image rgbCombinedImage = model.rgbCombine("rgbCombinedImage",
            "redImage", "greenImage", "blueImage");
    for (int i = 0; i < original.get(0).getHeight(); i++) {
      for (int j = 0; j < original.get(0).getWidth(); j++) {
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getRedComponent(),
                rgbCombinedImage.getPixels()[i][j].getColorComponent().getRedComponent());
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getGreenComponent(),
                rgbCombinedImage.getPixels()[i][j].getColorComponent().getGreenComponent());
        assertEquals(original.get(0).getPixels()[i][j].getColorComponent().getBlueComponent(),
                rgbCombinedImage.getPixels()[i][j].getColorComponent().getBlueComponent());
      }
    }
  }

  @Test
  public void testSaveJPEG() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    File f = new File("res/Original.jpg");
    assertFalse(f.exists());
    controller = new Save("res/Original.jpg", "original");
    original = controller.execute(model);
    assertTrue(f.exists());
    f.delete();
  }

  @Test
  public void testSaveBMP() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    File f = new File("res/Original.bmp");
    assertFalse(f.exists());
    controller = new Save("res/Original.bmp", "original");
    original = controller.execute(model);
    assertTrue(f.exists());
    f.delete();
  }

  @Test
  public void testSavePNG() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    File f = new File("res/Original.png");
    assertFalse(f.exists());
    controller = new Save("res/Original.png", "original");
    original = controller.execute(model);
    assertTrue(f.exists());
    f.delete();
  }

  @Test
  public void testSaveMultipleFormats() {
    ImageCommandController controller = new Load("res/dog.ppm", "original");
    List<Image> original = controller.execute(model);
    File f = new File("res/Original.jpg");
    assertFalse(f.exists());
    controller = new Save("res/Original.jpg", "original");
    original = controller.execute(model);
    assertTrue(f.exists());
    controller = new Load("res/Original.jpg", "originaljpg");
    List<Image> originaljpg = controller.execute(model);
    File f1 = new File("res/Original.png");
    assertFalse(f1.exists());
    controller = new Save("res/Original.png", "original");
    originaljpg = controller.execute(model);
    assertTrue(f1.exists());
    f.delete();
    f1.delete();
  }
}