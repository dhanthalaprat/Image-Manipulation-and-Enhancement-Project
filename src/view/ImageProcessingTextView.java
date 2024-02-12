package view;

/**
 * The ImageProcessingTextView interface defines the methods that a view for an image processing
 * application should implement in text-based view .
 * The view is responsible for displaying the menu, error messages, and executing command entered
 * by the user.
 */
public interface ImageProcessingTextView {

  /**
   * Displays an error message for an invalid command entered by the user.
   *
   * @param command the invalid command entered by the user
   */
  void displayErrorMessage(String command);

  /**
   * Displays an error message for an error that occurred while running a script file.
   *
   * @param error   the error message
   * @param command the command that caused the error
   */
  void displayErrorWhileRunningScriptFile(String error, String command);

  /**
   * Displays a success message after successfully executing a command.
   *
   * @param command the command executed successfully
   */
  void displaySuccessMessage(String command);
}
