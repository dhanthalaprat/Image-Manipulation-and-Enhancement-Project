package view;

import java.util.List;

import controller.ImageMVCController;
import model.Image;

/**
 * This interface defines the methods that must be implemented by a class that serves as the user
 * interface for an image processing application. This interface provides methods to make the UI
 * visible, set button listeners, get parameters for a command, display the current image,
 * and display error dialogs.
 */
public interface ImageProcessingView {

  /**
   * Displays the UI on the screen.
   */
  void makeVisible();

  /**
   * Sets the listener for the command buttons.
   *
   * @param controller the controller object to call the methods when action listener is triggered.
   */
  void setCommandButtonListener(ImageMVCController controller);

  /**
   * Gets the parameters for the given command.
   *
   * @param command the command for which the parameters are to be retrieved.
   * @return a list of parameters for the command.
   */
  List<String> getParameters(String command);

  /**
   * Displays the current image in the UI.
   *
   * @param m the list of images to display.
   */
  void displayCurrentImage(List<Image> m);

  /**
   * Displays an error dialog in case of errors in the application.
   */
  void displayErrorDialog();
}
