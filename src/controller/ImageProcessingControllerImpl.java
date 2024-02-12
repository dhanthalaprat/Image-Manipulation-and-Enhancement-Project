package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

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
import view.ImageProcessingTextView;

/**
 * This class implements ImageProcessingController class.
 * The user inputs/commands are processed at the controller level in this class.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private final ImprovedImageProcessing model;
  private final InputStream in;
  private final ImageProcessingTextView view;

  /**
   * This method is the constructor to the ImageProcessingControllerImpl class.
   *
   * @param model takes in model object.
   * @param in    input of the InputStream.
   */
  public ImageProcessingControllerImpl(ImprovedImageProcessing model, InputStream in,
                                       ImageProcessingTextView view) {
    this.model = model;
    this.in = in;
    this.view = view;
  }

  private void readCommands(String[] inputArray, String command) {
    ImageCommandController cmd = null;
    try {
      if (inputArray.length == 3) {
        switch (inputArray[0]) {
          case "load":
            cmd = new Load(inputArray[1], inputArray[2]);
            break;
          case "save":
            cmd = new Save(inputArray[1], inputArray[2]);
            break;
          case "horizontal-flip":
            cmd = new HorizontalFlip(inputArray[1], inputArray[2]);
            break;
          case "vertical-flip":
            cmd = new VerticalFlip(inputArray[1], inputArray[2]);
            break;
          case "greyscale":
            cmd = new Greyscale("luma-component", inputArray[1], inputArray[2]);
            break;
          case "sepia":
            cmd = new ColorTransformation("sepia", inputArray[1], inputArray[2]);
            break;
          case "blur":
            cmd = new Filtering("blur", inputArray[1], inputArray[2]);
            break;
          case "sharpen":
            cmd = new Filtering("sharpen", inputArray[1], inputArray[2]);
            break;
          case "dither":
            cmd = new Dither(inputArray[1], inputArray[2]);
            break;
          default:
            this.view.displayErrorMessage(command);
            return;
        }
      } else if (inputArray.length == 4) {
        if (inputArray[0].equalsIgnoreCase("brighten")) {
          cmd = new Brighten(Integer.parseInt(inputArray[1]), inputArray[2],
                  inputArray[3]);
        }
        if (inputArray[0].equalsIgnoreCase("greyscale")) {
          cmd = new Greyscale(inputArray[1], inputArray[2], inputArray[3]);
        }
      } else if (inputArray.length == 5) {
        if (inputArray[0].equalsIgnoreCase("rgb-split")) {
          cmd = new RGBSplit(inputArray[1], inputArray[2], inputArray[3], inputArray[4]);
        } else if (inputArray[0].equalsIgnoreCase("rgb-combine")) {
          cmd = new RGBCombine(inputArray[1], inputArray[2], inputArray[3], inputArray[4]);
        }
      } else {
        this.view.displayErrorMessage(command);
        return;
      }
    } catch (NumberFormatException e) {
      this.view.displayErrorMessage(command);
      return;
    }
    if (cmd != null) {
      List<Image> m = cmd.execute(model);
      if (m == null || m.size() == 0) {
        this.view.displayErrorMessage(command);
      } else {
        this.view.displaySuccessMessage(command);
      }
    } else {
      this.view.displayErrorMessage(command);
    }
  }

  private void readScript(String filepath) {
    String command = "";
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filepath));
      String line = reader.readLine();
      command = line;
      while (line != null) {
        String[] runScriptInputArray = line.split(" ");
        runScriptInputArray = Arrays.stream(runScriptInputArray)
                .filter(Predicate.not(String::isEmpty))
                .toArray(String[]::new);
        readCommands(runScriptInputArray, line);
        line = reader.readLine();
      }
      reader.close();
    } catch (Exception e) {
      this.view.displayErrorWhileRunningScriptFile(e.getMessage(), command);
    }
  }

  @Override
  public void execute() {
    String[] inputArray;
    Scanner scan = new Scanner(this.in);
    while (scan.hasNext()) {
      String input = scan.nextLine();
      inputArray = input.split(" ");
      inputArray = Arrays.stream(inputArray)
              .filter(Predicate.not(String::isEmpty))
              .toArray(String[]::new);
      if (inputArray.length > 0) {
        if ((inputArray.length == 2) && (inputArray[0].equals("run"))) {
          readScript(inputArray[1]);
        } else {
          readCommands(inputArray, input);
        }
      }
    }
  }
}