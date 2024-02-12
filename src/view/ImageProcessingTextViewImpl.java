package view;

import java.io.PrintStream;

/**
 * The ImageProcessingTextViewImpl class is an implementation of the ImageProcessingTextView
 * interface.It is responsible for displaying the menu, error messages,
 * and executing command entered by the user on the command line.
 */
public class ImageProcessingTextViewImpl implements ImageProcessingTextView {

  private final PrintStream out;

  /**
   * Constructor for the ImageProcessingTextViewImpl class that takes a PrintStream object as a
   * parameter.
   * The PrintStream object is used to write messages to the console.
   *
   * @param out the PrintStream object used to write messages to the console
   */
  public ImageProcessingTextViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void displayErrorMessage(String command) {
    this.out.println("Invalid Command Entered" + " " + command);
  }

  @Override
  public void displayErrorWhileRunningScriptFile(String error, String command) {
    this.out.println("Error while running script file: " + error + " " + command);
  }

  @Override
  public void displaySuccessMessage(String command) {
    this.out.println("Successfully executed command: " + command);
  }
}
