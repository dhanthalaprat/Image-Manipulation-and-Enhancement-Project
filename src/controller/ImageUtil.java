package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Color;
import model.Image;
import model.Pixel;
import model.RGBImage;
import model.RGBPixel;

/**
 * This utility class supports I/O operations to load and save image files of different formats.
 */
public class ImageUtil {

  /**
   * Reads an image from a file into an Image object.
   *
   * @param filepath the filepath of the image
   * @return the Image object after reading the image file
   */
  public static Image readImage(String filepath) {
    try {
      BufferedImage image = ImageIO.read(new File(filepath));
      if (image == null) {
        return null;
      }
      Pixel[][] listOfPixels = new RGBPixel[image.getHeight()][image.getWidth()];
      Image finalImage = new RGBImage(image.getWidth(), image.getHeight(), 255,
              listOfPixels);
      for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
          Color rgb = new Color(image.getRGB(x, y));
          try {
            listOfPixels[y][x] = new RGBPixel(y, x, rgb.getRed(), rgb.getGreen(), rgb.getBlue());
          } catch (NullPointerException e) {
            System.out.println("y" + y + "x" + x);
          }
        }
      }
      return finalImage;
    } catch (FileNotFoundException e) {
      System.out.println("File " + filepath + " not found!");
      return null;
    } catch (IOException e) {
      System.out.println("Unable to read file " + filepath);
      return null;
    }
  }

  /**
   * Creates a file from an Image object.
   *
   * @param imageExtension the format in which image must be saved
   * @param filepath       the filepath where the image file is created
   * @param m              the Image object which is saved
   */
  public static void createImage(String imageExtension, String filepath, Image m) {
    try {
      BufferedImage image = new BufferedImage(m.getWidth(), m.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      Pixel[][] listOfPixels = m.getPixels();
      for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
          Color rgb = new Color(listOfPixels[y][x].getColorComponent().getRedComponent(),
                  listOfPixels[y][x].getColorComponent().getGreenComponent(),
                  listOfPixels[y][x].getColorComponent().getBlueComponent());
          try {
            image.setRGB(x, y, rgb.getRGB());
          } catch (NullPointerException e) {
            System.out.println("y" + y + "x" + x);
          }
        }
      }
      File myObj = new File(filepath);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
      ImageIO.write(image, imageExtension, new File(filepath));
      System.out.println("Image saved successfully.");
    } catch (IOException e) {
      System.out.println("Unable to create file " + filepath);
    }
  }

  /**
   * Reads an image from a PPM file into an Image object.
   *
   * @param filename the filepath of PPM file
   * @return the Image object after reading the image file
   */
  public static Image readPPM(String filename) {
    Scanner sc;
    Image m;
    Pixel[][] listOfPixels;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    listOfPixels = new RGBPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        listOfPixels[i][j] = new RGBPixel(j, i, sc.nextInt(), sc.nextInt(), sc.nextInt());
      }
    }
    m = new RGBImage(width, height, maxValue, listOfPixels);
    return m;
  }

  /**
   * Creates a PPM file from an Image object.
   *
   * @param filepath the filepath where the image file is created
   * @param m        the Image object which is saved
   */
  public static void createPPM(String filepath, Image m) {
    try {
      File myObj = new File(filepath);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    try {
      FileWriter myWriter = new FileWriter(filepath, false);
      myWriter.write("P3\n"
              + "# Created by GIMP version 2.10.20 PNM plug-in\n");
      myWriter.write(m.getWidth() + " " + m.getHeight() + "\n" + m.getMaxValueOfColor() + "\n");
      Pixel[][] listOfPixels = m.getPixels();
      for (int i = 0; i < m.getHeight(); i++) {
        for (int j = 0; j < m.getWidth(); j++) {
          if (listOfPixels[i][j] == null || listOfPixels[i][j].getColorComponent() == null) {
            return;
          }
          myWriter.write(listOfPixels[i][j].getColorComponent().getRedComponent() + "\n"
                  + listOfPixels[i][j].getColorComponent().getGreenComponent() + "\n"
                  + listOfPixels[i][j].getColorComponent().getBlueComponent() + "\n");
        }
      }
      myWriter.close();
      System.out.println("Successfully wrote to the file." + filepath);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
