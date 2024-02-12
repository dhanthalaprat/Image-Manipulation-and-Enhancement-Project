package controller;

import java.util.ArrayList;
import java.util.List;

import controller.commands.Brighten;
import controller.commands.ColorTransformation;
import controller.commands.Dither;
import controller.commands.Filtering;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageCommandController;
import controller.commands.Load;
import controller.commands.RGBCombine;
import controller.commands.RGBSplit;
import controller.commands.Save;
import controller.commands.VerticalFlip;
import model.Image;
import model.ImprovedImageProcessing;
import view.ImageProcessingView;

/**
 * The implementation of the ImageMVCController interface that handles communication
 * between the model, the view, and the user input. It uses the ImageProcessingView and
 * ImprovedImageProcessing model to execute commands on images.
 */
public class ImageMVCControllerImpl implements ImageMVCController {
  private final ImprovedImageProcessing model;
  private final ImageProcessingView view;

  /**
   * Constructs an ImageMVCControllerImpl object with the provided ImprovedImageProcessing model
   * and ImageProcessingView view.
   *
   * @param model The ImprovedImageProcessing model.
   * @param view  The ImageProcessingView.
   */
  public ImageMVCControllerImpl(ImprovedImageProcessing model, ImageProcessingView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void execute() {
    this.view.setCommandButtonListener(this);
    this.view.makeVisible();
  }

  @Override
  public void processImage(String command) {
    List<String> commands = this.view.getParameters(command);
    if (commands != null) {
      try {
        List<Image> m = readCommands(commands);
        if (m == null) {
          this.view.displayErrorDialog();
        } else {
          this.view.displayCurrentImage(m);
        }
      } catch (Exception ex) {
        this.view.displayErrorDialog();
      }
    }
  }

  private List<Image> readCommands(List<String> inputArray) {
    ImageCommandController cmd = null;
    try {
      int i = 0;
      while (i < inputArray.size()) {
        switch (inputArray.get(i)) {
          case "load":
            cmd = new Load(inputArray.get(++i), inputArray.get(++i));
            break;
          case "save":
            cmd = new Save(inputArray.get(++i), inputArray.get(++i));
            break;
          case "horizontal-flip":
            cmd = new HorizontalFlip(inputArray.get(++i), inputArray.get(++i));
            break;
          case "vertical-flip":
            cmd = new VerticalFlip(inputArray.get(++i), inputArray.get(++i));
            break;
          case "greyscale":
            cmd = new Greyscale(inputArray.get(++i), inputArray.get(++i), inputArray.get(++i));
            break;
          case "sepia":
            cmd = new ColorTransformation("sepia", inputArray.get(++i),
                    inputArray.get(++i));
            break;
          case "blur":
            cmd = new Filtering("blur", inputArray.get(++i), inputArray.get(++i));
            break;
          case "sharpen":
            cmd = new Filtering("sharpen", inputArray.get(++i), inputArray.get(++i));
            break;
          case "dither":
            cmd = new Dither(inputArray.get(++i), inputArray.get(++i));
            break;
          case "brighten":
            cmd = new Brighten(Integer.parseInt(inputArray.get(++i)), inputArray.get(++i),
                    inputArray.get(++i));
            break;
          case "rgb-split":
            cmd = new RGBSplit(inputArray.get(++i), inputArray.get(++i), inputArray.get(++i),
                    inputArray.get(++i));
            break;
          case "rgb-combine":
            cmd = new RGBCombine(inputArray.get(++i), inputArray.get(++i), inputArray.get(++i),
                    inputArray.get(++i));
            break;
          case "load-rgbcombine":
            cmd = new Load(inputArray.get(++i), inputArray.get(++i));
            cmd.execute(model);
            break;
          case "save-rgbsplit":
            cmd = new Save(inputArray.get(++i), inputArray.get(++i));
            Image rgbImage = cmd.execute(model).get(0);
            List<Image> image = new ArrayList<>();
            image.add(rgbImage);
            image.add(null);
            return image;
          default:
            return null;
        }
        i++;
      }
    } catch (NumberFormatException e) {
      return null;
    }
    if (cmd != null) {
      return cmd.execute(model);
    } else {
      return null;
    }
  }
}
