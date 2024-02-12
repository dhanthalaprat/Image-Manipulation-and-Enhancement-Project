package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import controller.ImageMVCController;
import model.Color;
import model.Image;
import model.Pixel;

/**
 * The ImageProcessingViewImpl class implements the ImageProcessingView interface and provides a
 * GUI for image processing.
 * The GUI includes buttons for loading, saving, flipping, and modifying the colors of images,
 * as well as panels for displaying images and histograms.
 * This class extends the JFrame class and uses various Swing components to create the GUI.
 */
public class ImageProcessingViewImpl extends JFrame implements ImageProcessingView {
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton horizontalFlipButton;
  private final JButton verticalFlipButton;
  private final JButton rgbSplitButton;
  private final JButton rgbCombineButton;
  private final JButton sepiaButton;
  private final JButton greyscaleButton;
  private final JButton ditherButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton brightenButton;
  private final JLabel histogramLabel;
  private final JLabel imageLabel;
  private final JRadioButton redRadioButton;
  private final JRadioButton greenRadioButton;
  private final JRadioButton blueRadioButton;
  private final JRadioButton lumaRadioButton;
  private final JRadioButton intensityRadioButton;
  private final JRadioButton valueRadioButton;
  private final JButton loadRedButton;
  private final JButton loadGreenButton;
  private final JButton loadBlueButton;
  private final JButton saveRedButton;
  private final JButton saveGreenButton;
  private final JButton saveBlueButton;
  private String redImageFilePath;
  private String greenImageFilePath;
  private String blueImageFilePath;

  /**
   * Creates an instance of the ImageProcessingViewImpl class, which is a JFrame that displays the
   * graphical user interface for the Image Processing application.
   * Sets the title of the JFrame to "Image Processing" and the size to 1000 x 700 pixels.
   * Configures the default close operation to exit the application when the JFrame is closed.
   * Initializes and adds components to the JFrame, including buttons for loading and saving images,
   * a panel for displaying the loaded image and its histogram, and buttons for applying various
   * image processing operations.
   * Also initializes and adds components for selecting RGB color channels,
   * loading and saving split/composite images, and viewing text commands.
   */
  public ImageProcessingViewImpl() {
    super();
    this.setTitle("Image Processing");
    this.setSize(1100, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    JPanel loadSaveButtonPanel = new JPanel();
    loadSaveButtonPanel.setLayout(new FlowLayout());
    this.add(loadSaveButtonPanel, BorderLayout.NORTH);

    loadButton = new JButton("Load Image");
    loadButton.setActionCommand("load");
    loadSaveButtonPanel.add(loadButton);

    saveButton = new JButton("Save Image");
    saveButton.setActionCommand("save");
    loadSaveButtonPanel.add(saveButton);

    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new FlowLayout());
    this.add(imagePanel, BorderLayout.CENTER);

    JPanel imageScrollPanel = new JPanel(new GridLayout(1, 2));

    imageLabel = new JLabel(new ImageIcon());
    JScrollPane imagePane = new JScrollPane(imageLabel);
    imagePane.setPreferredSize(new Dimension(450, 450));
    imagePane.setBorder(BorderFactory.createEmptyBorder());
    imagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    imageScrollPanel.add(imagePane);

    histogramLabel = new JLabel(new ImageIcon());
    histogramLabel.setPreferredSize(new Dimension(300, 450));
    imageScrollPanel.add(histogramLabel);

    imagePanel.add(imageScrollPanel, BorderLayout.CENTER);

    JPanel imageProcessingButtonPanel = new JPanel();
    imageProcessingButtonPanel.setLayout(new FlowLayout());
    this.add(imageProcessingButtonPanel, BorderLayout.SOUTH);

    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten");
    imageProcessingButtonPanel.add(brightenButton);

    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("greyscale");
    imageProcessingButtonPanel.add(greyscaleButton);

    rgbSplitButton = new JButton("RGBSplit");
    rgbSplitButton.setActionCommand("rgb-split");
    imageProcessingButtonPanel.add(rgbSplitButton);

    rgbCombineButton = new JButton("RGBCombine");
    rgbCombineButton.setActionCommand("rgb-combine");
    imageProcessingButtonPanel.add(rgbCombineButton);

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("blur");
    imageProcessingButtonPanel.add(blurButton);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("sharpen");
    imageProcessingButtonPanel.add(sharpenButton);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("sepia");
    imageProcessingButtonPanel.add(sepiaButton);

    ditherButton = new JButton("Dither");
    ditherButton.setActionCommand("dither");
    imageProcessingButtonPanel.add(ditherButton);

    horizontalFlipButton = new JButton("Horizontal Flip");
    horizontalFlipButton.setActionCommand("horizontal-flip");
    imageProcessingButtonPanel.add(horizontalFlipButton);

    verticalFlipButton = new JButton("Vertical Flip");
    verticalFlipButton.setActionCommand("vertical-flip");
    imageProcessingButtonPanel.add(verticalFlipButton);

    redRadioButton = new JRadioButton("Red-component");
    redRadioButton.setActionCommand("red-component");
    greenRadioButton = new JRadioButton("Green-component");
    greenRadioButton.setActionCommand("green-component");
    blueRadioButton = new JRadioButton("Blue-component");
    blueRadioButton.setActionCommand("blue-component");
    lumaRadioButton = new JRadioButton("Luma-component");
    lumaRadioButton.setActionCommand("luma-component");
    intensityRadioButton = new JRadioButton("Intensity-component");
    intensityRadioButton.setActionCommand("intensity-component");
    valueRadioButton = new JRadioButton("Value-component");
    valueRadioButton.setActionCommand("value-component");

    loadRedButton = new JButton("Load Red Image");
    loadRedButton.setActionCommand("load redCombineImage");
    loadGreenButton = new JButton("Load Green Image");
    loadGreenButton.setActionCommand("load greenCombineImage");
    loadBlueButton = new JButton("Load Blue Image");
    loadBlueButton.setActionCommand("load blueCombineImage");

    saveRedButton = new JButton("Save Red Image");
    saveRedButton.setActionCommand("save-rgbsplit redSplitImage");
    saveGreenButton = new JButton("Save Green Image");
    saveGreenButton.setActionCommand("save-rgbsplit greenSplitImage");
    saveBlueButton = new JButton("Save Blue Image");
    saveBlueButton.setActionCommand("save-rgbsplit blueSplitImage");
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandButtonListener(ImageMVCController controller) {
    ActionListener listener = evt -> controller.processImage(evt.getActionCommand());
    loadButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    horizontalFlipButton.addActionListener(listener);
    verticalFlipButton.addActionListener(listener);
    brightenButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    rgbSplitButton.addActionListener(listener);
    rgbCombineButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    ditherButton.addActionListener(listener);
    saveRedButton.addActionListener(listener);
    saveGreenButton.addActionListener(listener);
    saveBlueButton.addActionListener(listener);
    loadRedButton.addActionListener(evt -> redImageFilePath = showLoadImageDialog());
    loadGreenButton.addActionListener(evt -> greenImageFilePath = showLoadImageDialog());
    loadBlueButton.addActionListener(evt -> blueImageFilePath = showLoadImageDialog());
  }

  @Override
  public List<String> getParameters(String command) {
    String[] commands = command.split(" ");
    String imageName = "image";
    if (commands.length > 1) {
      imageName = commands[1];
    }
    command = commands[0];
    switch (command) {
      case "load":
        String loadFilePath = showLoadImageDialog();
        if (loadFilePath != null) {
          return List.of(new String[]{"load", loadFilePath, imageName});
        } else {
          return null;
        }
      case "save":
        String saveFilePath = showSaveImageDialog();
        if (saveFilePath != null) {
          return List.of(new String[]{"save", saveFilePath, imageName});
        } else {
          return null;
        }
      case "brighten":
        String brightenValue = JOptionPane.showInputDialog("Please enter numeric value by which "
                + "the image should be brightened or darkened");
        if (brightenValue == null) {
          return null;
        }
        return List.of(new String[]{"brighten", brightenValue, "image", "image"});
      case "horizontal-flip":
        return List.of(new String[]{"horizontal-flip", "image", "image"});
      case "vertical-flip":
        return List.of(new String[]{"vertical-flip", "image", "image"});
      case "greyscale":
        String greyscaleComponent = showOptionsForGreyScale();
        if (greyscaleComponent == null) {
          return null;
        }
        return List.of(new String[]{"greyscale", greyscaleComponent, "image", "image"});
      case "sepia":
        return List.of(new String[]{"sepia", "image", "image"});
      case "dither":
        return List.of(new String[]{"dither", "image", "image"});
      case "blur":
        return List.of(new String[]{"blur", "image", "image"});
      case "sharpen":
        return List.of(new String[]{"sharpen", "image", "image"});
      case "rgb-split":
        return List.of(new String[]{"rgb-split", "image",
            "redSplitImage", "greenSplitImage", "blueSplitImage"});
      case "rgb-combine":
        redImageFilePath = "";
        greenImageFilePath = "";
        blueImageFilePath = "";
        List<String> filePaths = showRGBCombineLoadDialog();
        if (filePaths == null) {
          return null;
        }
        for (String filePath : filePaths) {
          if (filePath == null || filePath.equals("")) {
            displayErrorDialog();
            return null;
          }
        }
        return List.of(new String[]{"load-rgbcombine", filePaths.get(0), "redCombineImage",
            "load-rgbcombine", filePaths.get(1), "greenCombineImage",
            "load-rgbcombine", filePaths.get(2), "blueCombineImage",
            "rgb-combine", "image", "redCombineImage", "greenCombineImage", "blueCombineImage"});
      case "save-rgbsplit":
        String saveRGBSplitFilePath = showSaveImageDialog();
        if (saveRGBSplitFilePath != null) {
          return List.of(new String[]{"save-rgbsplit", saveRGBSplitFilePath, imageName});
        } else {
          return null;
        }
      default:
        return null;
    }
  }

  @Override
  public void displayCurrentImage(List<Image> m) {
    if (m.size() == 1) {
      BufferedImage image = getImageToDisplay(m.get(0));
      BufferedImage histogramImage = displayHistogram(image);
      imageLabel.setIcon(new ImageIcon(image));
      histogramLabel.setIcon(new ImageIcon(histogramImage));
      validate();
    } else if (m.size() != 2) {
      showRGBSplitSaveDialog();
    }

  }

  @Override
  public void displayErrorDialog() {
    JOptionPane.showMessageDialog(this, "Operation failed", "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  private BufferedImage getImageToDisplay(Image m) {
    BufferedImage image = new BufferedImage(m.getWidth(), m.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    Pixel[][] listOfPixels = m.getPixels();
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        model.Color rgb = new Color(listOfPixels[y][x].getColorComponent().getRedComponent(),
                listOfPixels[y][x].getColorComponent().getGreenComponent(),
                listOfPixels[y][x].getColorComponent().getBlueComponent());
        try {
          image.setRGB(x, y, rgb.getRGB());
        } catch (NullPointerException e) {
          System.out.println("y" + y + "x" + x);
          return null;
        }
      }
    }
    return image;
  }

  private void showRGBSplitSaveDialog() {
    JOptionPane optionPane = new JOptionPane();
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(saveRedButton);
    buttonPanel.add(saveGreenButton);
    buttonPanel.add(saveBlueButton);
    JDialog dialog = null;
    optionPane.setMessage("Please save RGB Split images");
    optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
    optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
    optionPane.add(buttonPanel, 1);
    dialog = optionPane.createDialog(this, "Save RGB Split Images");
    dialog.setVisible(true);
  }

  private List<String> showRGBCombineLoadDialog() {
    JOptionPane optionPane = new JOptionPane();
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(loadRedButton);
    buttonPanel.add(loadGreenButton);
    buttonPanel.add(loadBlueButton);
    JDialog dialog = null;
    optionPane.setMessage("Please load images to combine");
    optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
    optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
    optionPane.add(buttonPanel, 1);
    dialog = optionPane.createDialog(this, "Load RGB Combine Images");
    dialog.setVisible(true);
    if (redImageFilePath == null) {
      redImageFilePath = "";
    } else if (greenImageFilePath == null) {
      greenImageFilePath = "";
    } else if (blueImageFilePath == null) {
      blueImageFilePath = "";
    }
    return List.of(new String[]{redImageFilePath, greenImageFilePath, blueImageFilePath});
  }

  private String showLoadImageDialog() {
    JFileChooser fc = new JFileChooser(".");
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      return file.getPath();
    } else {
      return null;
    }
  }

  private String showSaveImageDialog() {
    JFileChooser fc = new JFileChooser(".");
    int returnVal = fc.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      return file.getPath();
    } else {
      return null;
    }
  }

  private BufferedImage displayHistogram(BufferedImage image) {
    int[] redHistogram = new int[256];
    int[] greenHistogram = new int[256];
    int[] blueHistogram = new int[256];
    int[] intensityHistogram = new int[256];
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Color c = new Color(image.getRGB(x, y));
        redHistogram[c.getRed()]++;
        greenHistogram[c.getGreen()]++;
        blueHistogram[c.getBlue()]++;
        int intensity = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
        intensityHistogram[intensity]++;
      }
    }
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (int i = 0; i < 256; i++) {
      dataset.addValue(redHistogram[i], "Red", Integer.toString(i));
      dataset.addValue(greenHistogram[i], "Green", Integer.toString(i));
      dataset.addValue(blueHistogram[i], "Blue", Integer.toString(i));
      dataset.addValue(intensityHistogram[i], "Intensity", Integer.toString(i));
    }
    JFreeChart chart = ChartFactory.createLineChart("Histogram", "Value",
            "Frequency", dataset, PlotOrientation.VERTICAL, true,
            true, false);
    return chart.createBufferedImage(450, 450);
  }

  private String showOptionsForGreyScale() {
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
    JLabel label = new JLabel("Select an option:");
    radioPanel.add(label);
    radioPanel.add(redRadioButton);
    radioPanel.add(greenRadioButton);
    radioPanel.add(blueRadioButton);
    radioPanel.add(lumaRadioButton);
    radioPanel.add(intensityRadioButton);
    radioPanel.add(valueRadioButton);
    ButtonGroup greyscaleOptions = new ButtonGroup();
    greyscaleOptions.add(redRadioButton);
    greyscaleOptions.add(greenRadioButton);
    greyscaleOptions.add(blueRadioButton);
    greyscaleOptions.add(lumaRadioButton);
    greyscaleOptions.add(intensityRadioButton);
    greyscaleOptions.add(valueRadioButton);
    JOptionPane optionPaneRB = new JOptionPane();
    JDialog dialog;
    optionPaneRB.setMessage("Please select an option");
    optionPaneRB.setMessageType(JOptionPane.PLAIN_MESSAGE);
    optionPaneRB.setOptionType(JOptionPane.DEFAULT_OPTION);
    optionPaneRB.add(radioPanel, 1);
    dialog = optionPaneRB.createDialog(this, "Greyscale on component");
    dialog.setVisible(true);
    if (optionPaneRB.getValue() == null || greyscaleOptions.getSelection() == null) {
      return null;
    }
    return greyscaleOptions.getSelection().getActionCommand();
  }
}
