import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import controller.ImageMVCController;
import controller.ImageMVCControllerImpl;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImprovedImageProcessing;
import model.ImprovedImageProcessingImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingTextViewImpl;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

/**
 * The entry point of the program where we can create Image Processing Model and
 * run some operations on it.
 */

public class ImageManipulator {
  /**
   * Main method that starts the application.
   *
   * @param args accepts a single argument of type array.
   */
  public static void main(String[] args) {
    ImprovedImageProcessing model = new ImprovedImageProcessingImpl();
    ImageProcessingTextView textView = new ImageProcessingTextViewImpl(System.out);
    if (args.length == 2 && args[0].equalsIgnoreCase("-file")) {
      try {
        InputStream in = new FileInputStream(args[1]);
        ImageProcessingController controller = new ImageProcessingControllerImpl(model, in,
                textView);
        controller.execute();
      } catch (FileNotFoundException e) {
        System.out.println("File Not Found");
      }
    } else if (args.length == 1 && args[0].equalsIgnoreCase("-text")) {
      InputStream in = System.in;
      ImageProcessingController controller = new ImageProcessingControllerImpl(model, in, textView);
      controller.execute();
    } else if (args.length == 0) {
      ImageProcessingView view = new ImageProcessingViewImpl();
      ImageMVCController controller = new ImageMVCControllerImpl(model, view);
      controller.execute();
    } else {
      System.out.println("Command Line Argument is invalid. Program exited.");
    }
  }
}
