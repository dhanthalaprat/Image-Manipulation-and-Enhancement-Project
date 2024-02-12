package controller;

/**
 * Interface for the Image MVC Controller.It is the main controller for gui view.
 * It contains a single method "execute()" which is implemented by the concrete class
 * that controls the Image Processing functionality.
 */
public interface ImageMVCController extends ImageProcessingController {

  /**
   * This method is responsible for executing the actions and functionalities of the
   * ImageMVCController triggered by the action listeners from the view.
   *
   * @param command the action command passed from the action listener in the view.
   */
  void processImage(String command);
}
