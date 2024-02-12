package controller.commands;

import java.util.List;

import model.Image;
import model.ImprovedImageProcessing;

/**
 * Command interface for the classes representing user commands.
 */
public interface ImageCommandController {

  /**
   * Executes the operation which represents the command entered.
   *
   * @param m the model of the program
   * @return the image after processing
   */
  List<Image> execute(ImprovedImageProcessing m);
}
