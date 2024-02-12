package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;
import model.Pixel;
import model.RGBImage;
import view.ImageProcessingTextView;
import view.ImageProcessingTextViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class contains tests for ImageProcessingController class.
 */
public class CommandControllerTest {

  private OutputStream out;

  @Before
  public void setup() {
    out = new ByteArrayOutputStream();
  }

  /**
   * MockModel class to test Controller.
   */
  public class MockModel implements ImprovedImageProcessing {
    private StringBuilder sb;
    private Image image = new RGBImage(0, 0, 0, new Pixel[0][0]);
    private List<Image> list = List.of(new Image[]{image});

    /**
     * Constructs MockModel with the log data to be set.
     *
     * @param sb input data.
     */
    public MockModel(StringBuilder sb) {
      this.sb = sb;
    }

    @Override
    public Image loadImage(Image image, String imageName) {
      sb.append("Received inputs " + imageName);
      return image;
    }

    @Override
    public Image saveImage(String imageName) {
      sb.append("Received inputs " + imageName);
      return image;
    }

    @Override
    public Image greyscale(String component, String sourceImageName, String destImageName) {
      sb.append("Received inputs " + component + ", " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image horizontalFlip(String sourceImageName, String destImageName) {
      sb.append("Received inputs " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image verticalFlip(String sourceImageName, String destImageName) {
      sb.append("Received inputs " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image brighten(int increment, String sourceImageName, String destImageName) {
      sb.append("Received inputs " + increment + ", " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public List<Image> rgbSplit(String sourceImageName, String redImageName, String greenImageName,
                                String blueImageName) {
      sb.append("Received inputs " + sourceImageName + ", " + redImageName + ", " + greenImageName
              + " and " + blueImageName);
      return list;
    }

    @Override
    public Image rgbCombine(String destImageName, String redImageName, String greenImageName,
                            String blueImageName) {
      sb.append("Received inputs " + destImageName + ", " + redImageName + ", " + greenImageName
              + " and " + blueImageName);
      return image;
    }

    @Override
    public Image filtering(String operation, String sourceImageName, String destImageName) {
      sb.append("Received inputs " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image colorTransformation(String transformation, String sourceImageName,
                                     String destImageName) {
      sb.append("Received inputs " + sourceImageName + " and " + destImageName);
      return image;
    }

    @Override
    public Image dither(String sourceImageName, String destImageName) {
      sb.append("Received inputs " + sourceImageName + " and " + destImageName);
      return image;
    }
  }


  @Test
  public void testLoadCommand() {
    InputStream in = null;
    String a = "res/image.ppm";
    String b = "image";
    String input = "load " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + b, mockLog.toString());
  }


  @Test
  public void testSaveCommand() {
    InputStream in = null;
    String a = "res/image.ppm";
    String b = "image";
    String input = "save " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + b, mockLog.toString());
  }

  @Test
  public void testHorizontalFlipCommand() {
    InputStream in = null;
    String a = "image";
    String b = "image-horizontal";
    String input = "horizontal-flip " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mockLog.toString());
  }

  @Test
  public void testVerticalFlipCommand() {
    InputStream in = null;
    String a = "image";
    String b = "image-vertical";
    String input = "vertical-flip " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mockLog.toString());
  }

  @Test
  public void testBrightenCommand() {
    InputStream in = null;
    int a = 10;
    String b = "image";
    String c = "image-brighten";
    String input = "brighten " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mockLog.toString());
  }

  @Test
  public void testRGBSplitCommand() {
    InputStream in = null;
    String a = "image";
    String b = "red-image";
    String c = "green-image";
    String d = "blue-image";
    String input = "rgb-split " + a + " " + b + " " + c + " " + d;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + ", " + c + " and " + d,
            mockLog.toString());
  }

  @Test
  public void testRGBCombineCommand() {
    InputStream in = null;
    String a = "image";
    String b = "red-image";
    String c = "green-image";
    String d = "blue-image";
    String input = "rgb-combine " + a + " " + b + " " + c + " " + d;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + ", "
            + c + " and " + d, mockLog.toString());
  }

  @Test
  public void testRedGreyscaleCommand() {
    InputStream in = null;
    String a = "red-component";
    String b = "image";
    String c = "red-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mockLog.toString());
  }

  @Test
  public void testGreenGreyscaleCommand() {
    InputStream in = null;
    String a = "green-component";
    String b = "image";
    String c = "green-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mockLog.toString());
  }

  @Test
  public void testBlueGreyscaleCommand() {
    InputStream in = null;
    String a = "blue-component";
    String b = "image";
    String c = "blue-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mockLog.toString());
  }

  @Test
  public void testValueGreyscaleCommand() {
    InputStream in = null;
    String a = "value-component";
    String b = "image";
    String c = "value-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mockLog.toString());
  }

  @Test
  public void testIntensityGreyscaleCommand() {
    InputStream in = null;
    String a = "intensity-component";
    String b = "image";
    String c = "intensity-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mockLog.toString());
  }

  @Test
  public void testLumaGreyscaleCommand() {
    InputStream in = null;
    String a = "luma-component";
    String b = "image";
    String c = "luma-image";
    String input = "greyscale " + a + " " + b + " " + c;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + ", " + b + " and " + c, mockLog.toString());
  }

  @Test
  public void testRunScriptCommand() {
    InputStream in = null;
    String a = "test/controller/testScript.txt";
    String input = "run " + a;
    //in = new ByteArrayInputStream(input.getBytes());
    try {
      in = new FileInputStream(a);
    } catch (FileNotFoundException e) {
      fail("Input file not present");
    }
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs dog and dogvReceived inputs dog and "
            + "dog-horizontalReceived inputs dog-horizontalReceived inputs dogv",
            mockLog.toString());
  }

  @Test
  public void testWrongCommandInput() {
    InputStream in = null;
    String input = "dummy a b";
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());
  }

  @Test
  public void testWrongGreyscaleComponentCommandInput() {
    InputStream in = null;
    String input = "greyscale dummy-component a b";
    in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Successfully executed command: " + input,
            out.toString().stripTrailing());
  }

  @Test
  public void testWrongNumberOfInputsForLoad() {
    InputStream in = null;
    String input = "load res/image.ppm";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());

    String input1 = "load res/image.ppm a b c";
    in = new ByteArrayInputStream(input1.getBytes());
    controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered load res/image.ppm\nInvalid Command Entered "
            + input1, out.toString().stripTrailing());
  }

  @Test
  public void testWrongNumberOfInputsForBrighten() {
    InputStream in = null;
    String input = "brighten 3 image";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());

    String input1 = "brighten 10 a b c";
    in = new ByteArrayInputStream(input1.getBytes());
    controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered brighten 3 image\n" + "Invalid Command Entered "
            + input1, out.toString().stripTrailing());
  }

  @Test
  public void testWrongNumberOfInputsForHorizontalFlip() {
    InputStream in = null;
    String input = "horizontal-flip image";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());

    String input1 = "horizontal-flip a b c";
    in = new ByteArrayInputStream(input1.getBytes());
    controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered horizontal-flip image\n"
            + "Invalid Command Entered "
            + input1, out.toString().stripTrailing());
  }

  @Test
  public void testWrongNumberOfInputsForVerticalFlip() {
    InputStream in = null;
    String input = "vertical-flip image";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());

    String input1 = "vertical-flip a b c";
    in = new ByteArrayInputStream(input1.getBytes());
    controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered vertical-flip image\n"
            + "Invalid Command Entered "
            + input1, out.toString().stripTrailing());
  }

  @Test
  public void testWrongNumberOfInputsForSave() {
    InputStream in = null;
    String input = "save res/image.ppm";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());

    String input1 = "save res/image.ppm a b c";
    in = new ByteArrayInputStream(input1.getBytes());
    controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered save res/image.ppm\n" + "Invalid Command Entered "
            + input1, out.toString().stripTrailing());
  }

  @Test
  public void testWrongNumberOfInputsForRGBSplit() {
    InputStream in = null;
    String input = "rgb-split image";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());

    input = "rgb-split dest a b c d";
    InputStream in1 = new ByteArrayInputStream(input.getBytes());
    OutputStream out1 = new ByteArrayOutputStream();
    ImageProcessingTextView view1 = new ImageProcessingTextViewImpl(new PrintStream(out1));
    controller = new ImageProcessingControllerImpl(model, in1, view1);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out1.toString().stripTrailing());
  }

  @Test
  public void testWrongNumberOfInputsForRGBCombine() {
    InputStream in = null;
    String input = "rgb-combine image";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());

    input = "rgb-combine dest a b c d";
    InputStream in1 = new ByteArrayInputStream(input.getBytes());
    OutputStream out1 = new ByteArrayOutputStream();
    ImageProcessingTextView view1 = new ImageProcessingTextViewImpl(new PrintStream(out1));
    controller = new ImageProcessingControllerImpl(model, in1, view1);
    controller.execute();
    assertEquals("Invalid Command Entered "
            + input, out1.toString().stripTrailing());
  }

  @Test
  public void testBrightenWithStringInsteadOfInt() {
    InputStream in = null;
    String input = "brighten a image image-brighten";
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Invalid Command Entered " + input, out.toString().stripTrailing());
  }

  @Test
  public void testDitherCommand() {
    InputStream in = null;
    String a = "image";
    String b = "image-dither";
    String input = "dither " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mockLog.toString());
  }

  @Test
  public void testBlurCommand() {
    InputStream in = null;
    String a = "image";
    String b = "image-blur";
    String input = "blur " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mockLog.toString());
  }

  @Test
  public void testSharpenCommand() {
    InputStream in = null;
    String a = "image";
    String b = "image-sharpen";
    String input = "sharpen " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mockLog.toString());
  }

  @Test
  public void testSepiaCommand() {
    InputStream in = null;
    String a = "image";
    String b = "image-sepia";
    String input = "sepia " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());

    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs " + a + " and " + b, mockLog.toString());
  }

  @Test
  public void testImprovedGreyscaleCommand() {
    InputStream in = null;
    String a = "image";
    String b = "image-greyscale";
    String input = "greyscale " + a + " " + b;
    in = new ByteArrayInputStream(input.getBytes());
    StringBuilder mockLog = new StringBuilder();
    ImprovedImageProcessing model = new MockModel(mockLog);
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(new PrintStream(out));
    ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, view);
    controller.execute();
    assertEquals("Received inputs luma-component, " + a + " and " + b, mockLog.toString());
  }
}