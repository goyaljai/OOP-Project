package javasamplecode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class EditImage{

   BufferedImage  image;
   BufferedImage[] list = new BufferedImage[10];
   int width;
   int height;
   String path;
   
   public EditImage(String path) {
	// TODO Auto-generated constructor stub
	   this.path=path;
}
   
   
void returnEditedImages() throws IOException
 {
	File input = new File(path);
    image = ImageIO.read(input);
    
    BufferedImage processed90,processed180;
    int WIDTH = image.getWidth();
    int HEIGHT = image.getHeight();
    processed90 = new BufferedImage(WIDTH,HEIGHT,image.getType());
       float hue = 90/360.0f;
    for(int Y=0; Y<HEIGHT;Y++)
    {
     for(int X=0;X<WIDTH;X++)
     {
      int RGB = image.getRGB(X,Y);
      int R = (RGB >> 16) & 0xff;
      int G = (RGB >> 8) & 0xff;
      int B = (RGB) & 0xff;
      float HSV[]=new float[3];
      Color.RGBtoHSB(R,G,B,HSV);
      processed90.setRGB(X,Y,Color.getHSBColor(hue,HSV[1],HSV[2]).getRGB());
     }
    }
  
    processed180 = new BufferedImage(WIDTH,HEIGHT,image.getType());
       float hue1 = 180/360.0f;
    for(int Y=0; Y<HEIGHT;Y++)
    {
     for(int X=0;X<WIDTH;X++)
     {
      int RGB = image.getRGB(X,Y);
      int R = (RGB >> 16) & 0xff;
      int G = (RGB >> 8) & 0xff;
      int B = (RGB) & 0xff;
      float HSV[]=new float[3];
      Color.RGBtoHSB(R,G,B,HSV);
      processed180.setRGB(X,Y,Color.getHSBColor(hue1,HSV[1],HSV[2]).getRGB());
     }
    }
    
    
	width = image.getWidth();
    height = image.getHeight();
	BufferedImage sepia = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    int sepiaDepth = 20;
    
    WritableRaster raster = sepia.getRaster();

    // We need 3 integers (for R,G,B color values) per pixel.
    int[] pixels = new int[width * height * 3];
    image.getRaster().getPixels(0, 0, width, height, pixels);

    //  Process 3 ints at a time for each pixel.  Each pixel has 3 RGB
    //    colors in array
    for (int i = 0; i < pixels.length; i += 3) {
        int r = pixels[i];
        int g = pixels[i + 1];
        int b = pixels[i + 2];

        int gry = (r + g + b) / 3;
        r = g = b = gry;
        r = r + (sepiaDepth * 2);
        g = g + sepiaDepth;

        if (r > 255) {
            r = 255;
        }
        if (g > 255) {
            g = 255;
        }
        if (b > 255) {
            b = 255;
        }

        // Darken blue color to increase sepia effect
        b -= 80;

        // normalize if out of bounds
        if (b < 0) {
            b = 0;
        }
        if (b > 255) {
            b = 255;
        }

        pixels[i] = r;
        pixels[i + 1] = g;
        pixels[i + 2] = b;
    }
    raster.setPixels(0, 0, width, height, pixels);
    
    

	 try {
		 String sys = System.getProperty("user.home");
         sys=sys.replace('\\', '/');
        
         
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,
               
               red+green+blue,red+green+blue);
               //i=0;
               image.setRGB(j,i,newColor.getRGB());
               //list[0] = image;
              
               //++;
            }
         }
         
         File ouptut = new File("C://Users/"+sys.substring(9, sys.length())+"/Desktop/"+1+".jpg");
         ImageIO.write(image, "jpg", ouptut);
         File ouptut1 = new File("C://Users/"+sys.substring(9, sys.length())+"/Desktop/"+2+".jpg");
         ImageIO.write(sepia, "jpg", ouptut1);
         File ouptut2 = new File("C://Users/"+sys.substring(9, sys.length())+"/Desktop/"+3+".jpg");
         ImageIO.write(processed90, "jpg", ouptut2);
         File ouptut3 = new File("C://Users/"+sys.substring(9, sys.length())+"/Desktop/"+4+".jpg");
         ImageIO.write(processed180, "jpg", ouptut3);
      } catch (Exception e) {}

 }

   
}