Image Manipulation and Enhancement Project


Design of our project
--------------------------
We have designed our project using MVC pattern where-in the files are classified as follows:

Controller
------------
We have 'ImageProcessingControllerImpl.class' which implements 'ImageProcessingController.class'
and takes user inputs and delegates the commands to the model to perform.
ImageProcessingController interface contains execute method, which starts the controller.

Model
------------
We have 'ImageProcessingModelImpl.class' (it implements 'ImageProcessingModel') which takes input
data from controller and deals with the commands such as load/save/brighten/grey-scale/
horizontal-flip, vertical-flip, RGB-combine, RGB-split associated with images.

Driver Class
------------
We have 'ImageManipulator.claas' is driver class.It is entry point of the program where we can
create Image Processing Model and run some operations on it. It contains main method in it.


Command Design Pattern
-------------------------
The command design pattern provides a unifying effect, making unconnected lines of code appear to be
 working towards the same goal. This improves cohesion since the controller is no longer performing
 one of ten separate tasks, but rather executing commands.

The command design pattern encourages delegation. We separated each command into its own class
rather than having them all in the controller. This allows us to accommodate new, more complex
instructions that may be implemented in the future without clogging the controller.

ImageCommandController Interface contains an execute method. All command classes implement this
interface and have their own implementation in the execute function. As a result,
all command classes are united.


Features supported by project
---------------------------------------------------------------------------------------------------
1.Load image: Load an image from the specified path and refer it to henceforth in the program by
the given image name.

2.Save image: Save the image with the given name to the specified path which should include the
name of the file.

3.Greyscale image on Component: Create a greyscale image with a component of the image with
the given name, and refer to it henceforth in the program by the given destination name. Similar
commands for green, blue, value, luma, intensity components should be supported. Red, green, blue,
value, luma and intensity are the components that are supported.

4.Horizontal-flip image: Flip an image horizontally to create a new image,
referred to henceforth by the given destination name.

5.Vertical-flip image: Flip an image vertically to create a new image,
referred to henceforth by the given destination name.

6.Brighten image by increment value : brighten the image by the given increment to create a new
image, referred to henceforth by the given destination name. The increment may be positive
(brightening) or negative (darkening).

7.Rgb-split image: split the given image into three greyscale images containing its red, green and
blue components respectively.

8.Rgb-combine image: Combine the three greyscale images into a single image that gets its red,
green and blue components from the three images respectively.

9.Run script-file: Load and run the script commands in the specified file.


Classes and Interfaces in this Project
----------------------------------------
ImageManipulator: The entry point of the program where we can create Image Processing Model and run
some operations on it.

Controller-Related Classes and Interfaces
___________________________________________
ImageProcessingController : This interface represents the main controller of the program.

ImageProcessingControllerImpl : This class implements ImageProcessingController class.The user
inputs/commands are processed at the controller level in this class.

ImageCommandController : Command interface for the command classes.

ImageUtil : This class contains utility methods to load and save a PPM image file.

Command Classes
___________________________________________
BrightenImage : This command class brightens the image by the given increment.

Greyscale : This command class creates a greyscale image with the component of the image with the
given name, and refer to it henceforth in the program by the given destination name.

HorizontalFlip : This command class flips image horizontally to create a new image, referred to
henceforth by the given destination name.

Load : This command class load an image from the specified path and refers it  in the program by
the given image name.

RGBCombine : This command class combines the three greyscale images into a single image that gets
its red, green and blue components from the three images respectively.

RGBSplit : This command class splits the given image into three greyscale images containing its red,
 green and blue components respectively.

Save : This command class saves the image with the given name to the specified path which should
include the name of the file.

VerticalFlip : This command class flips an image vertically to create a new image, referred to
henceforth by the given destination name.

Model-Related Classes and Interfaces:
______________________________________
Color : This class represents RGB color factor for each pixel.

Image : This class represents Image.

ImageProcessingModel : This interface specifies the operations on image.An image is characterized
by its width, height,its pixels representing its respective RGB-color.It can be asked to produce an
 image using on of the commands below.Another command can be used to save this image locally in ppm
 file format.

ImageProcessingModelImpl : This class implements all the operations/commands that can applied on
image.

Pixel : This class represents a pixel.

Test-Related Classes and Interfaces:
______________________________________
CommandControllerTest : This class contains tests for ImageProcessing Controller class.

ImageProcessingImplTest : This class contains tests for ImageProcessing model class.


Instructions to run the script file
--------------------------------------
Step 1: Run the ImageManipulator class
Step 2: Type 'run res/script.txt' in the console and press enter. Feel free to run any other script
file by mentioning the file path after run command. While running a script, the filepath is provided
as a command line argument to the 'run' command.
Step 3: (Optional) Feel free to run any other commands by giving them as input in console.


Citation for the source of our Image
--------------------------------------
We are the sole owners of the images provided in our project. We authorize the usage of the given
images in the project.

______________________________________
--------------------------------------
Version 2:Release on 29th March 2023
______________________________________
---------------------------------------

We have added some new features to this project in addition to the details given above.
We've also updated the project to support images other than ppm files, such as png, jpg, and bmp.

New Features supported in version 2
-------------------------------------
1.Blur Image : An operation to blur an image to create a new image,
               referred to henceforth by the given destination name.

2.Sharpen Image : An operation to sharpen an image to create a new image,
                  referred to henceforth by the given destination name.

3.Greyscale Image : A simple operation is to convert a color image into a greyscale image,
                    referred to henceforth by the given destination name.

4.Sepia Image : An operation to convert a normal color image into a sepia-toned image,
                referred to henceforth by the given destination name.

5.Dither Image : An operation to dither an image,referred to henceforth by the given destination
                    name.

Model
------------
We have added new Interface 'ImprovedImageProcessing.class' which extends 'ImageProcessingModel'
interface. The new interface is implemented in a new class 'ImprovedImageProcessingImpl'.
This class extends the older model class 'ImageProcessingModelImpl'. The new interface is
created to support new features like blur ,sharpen, greyscale, sepia and dither.

We have created a new Interface  'Image' and implemented that in 'RGBImage' class.We have also
created new Interface 'Pixel' and implemented that in 'RGBPixel' class.

Controller
------------
In addition to reading and writing ppm files,in this version our controller supports reading and
writing images with other file formats like png,bmp and jpg.

New Classes and Interfaces in this Project Version
-----------------------------------------------

Controller-Related Classes and Interfaces
___________________________________________

ImageUtil : This class contains utility methods to load and save a image files.

Command Classes
___________________________________________

ColorTransformation:This command class performs color transformations such as sepia and greyscale
                        on an image.

Dither:This command class performs dithering operation on an image.

Filtering:This command class performs filtering operations such as blur and sharpen on an image.


Model-Related Classes and Interfaces:
______________________________________

ImprovedImageProcessing : This interface has new advanced operations on image.

ImprovedImageProcessingImpl : This class implements all  new advanced operations from
                              ImprovedImageProcessing that can applied on image.

Image : The Image interface represents an image, which consists of an array of pixels and its
        properties/attributes.

RGBImage : The RGBImage class represents an RGB image and its properties such as width, height,
            maximum value of color,and an array of pixels that make up the image.

Pixel : The Pixel interface represents a single pixel in an image.It provides methods for
        retrieving color, value, intensity, and luma components of the pixel.

RGBPixel : The RGBPixel class represents a single RGB pixel in an image, storing its row and column
           numbers,as well as the color component of the pixel.

Test-Related Classes and Interfaces:
______________________________________

ImprovedImageProcessingImplTest: This class contains tests for ImprovedImageProcessing model class.

______________________________________
--------------------------------------
Version 3:Release on 12th April 2023
______________________________________
---------------------------------------

We have added view to the application.The new view program features a graphical user interface
using Java Swing.

New Features supported in version 3
-------------------------------------
1.Image Histograms
  The program now includes the ability to create and visualize histograms on gui for an image.
   A histogram is a table of (value, frequency) entries that allows you to visualize the
   distribution of color or intensities in an image. The histogram shows the red, green, blue,
    and intensity components.

Model
------------
We haven't made any changes in model in this version

Controller
------------
We have added new controller to support the GUI-based view.We have added new interface
'ImageMVCController' which is implemented by class 'ImageMVCControllerImpl', the implementation of
the ImageMVCController interface that handles communication between the model, the view, and the
user input. It uses the ImageProcessingView and ImprovedImageProcessing model to execute commands
on images.. It is the main controller for GUI-based  view.

View
-----------
We've introduced two types of views - text-based and GUI-based - in this version. 
We have create two new interfaces for view each for text-based view and GUI-based view .They are
'ImageProcessingTextView' and ImageProcessingView respectively.

The 'ImageProcessingTextViewImpl' class is an implementation of the 'ImageProcessingTextView'
interface.It is responsible for displaying the menu, error messages, and executing command entered
by the user on the command line.

The 'ImageProcessingViewImpl' class implements the ImageProcessingView interface and provides a GUI
for image processing.The GUI includes buttons for loading, saving, flipping, and modifying the
colors of images, as well as panels for displaying images and histograms.This class extends the
'JFrame' class and uses various Swing components to create the GUI.

Driver Class
------------
We have modified our driver class 'ImageManipulator.claas' to determine the program's view based on
three command-line inputs as follows:

1.If the command "java -jar Program.jar -file path-of-script-file" is used, the program opens the
specified script file, executes it, and then closes.

2.If the command "java -jar Program.jar -text" is used, the program opens in an interactive text
mode, allowing the user to type the script and execute it line by line.

3. If the command "java -jar Program.jar" is used or if the jar file is double-clicked, the program
opens in the graphical user interface mode.

New Classes and Interfaces in this Project Version
-----------------------------------------------

Controller-Related Classes and Interfaces
___________________________________________

ImageMVCController : Interface for the Image MVC Controller.It is the main controller for GUI-based
view.It contains a single method "execute()" which is implemented by the concrete class that
controls the Image Processing functionality.

ImageMVCControllerImpl : The implementation of the ImageMVCController interface that handles
communication between the model, the view, and the user input. It uses the ImageProcessingView and
ImprovedImageProcessing model to execute commands on images.


View -Related Classes and Interfaces:
______________________________________

ImageProcessingTextView : The ImageProcessingTextView interface defines the methods that a view for
an image processing application should implement in text-based view . The view is responsible for
displaying the menu, error messages, and executing command entered by the user.

ImageProcessingTextViewImpl : The ImageProcessingTextViewImpl class is an implementation of the
ImageProcessingTextView interface.It is responsible for displaying the menu, error messages, and
executing command entered by the user on the command line.

ImageProcessingView : This interface defines the methods that must be implemented by a class that
serves as the user interface for an image processing application. This interface provides methods
to make the UI visible, set button listeners, get parameters for a command, display the current
image, and display error dialogs.

ImageProcessingViewImpl : The ImageProcessingViewImpl class implements the ImageProcessingView
interface and provides a GUI for image processing. The GUI includes buttons for loading, saving,
flipping, and modifying the colors of images, as well as panels for displaying images and
histograms.This class extends the JFrame class and uses various Swing components to create the GUI.