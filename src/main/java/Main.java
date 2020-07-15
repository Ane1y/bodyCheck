import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.IOException;

public class Main {
    public static String path="C:/Programming/imgs/sanyaandlove.jpg";
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally();
       /* File folder = new File("../imgs/");
        File[] listOfFiles = folder.listFiles();*/
    //new HumanDetector(listOfFiles);
 //   new HOGHumanDetector(listOfFiles);
        File img = new File(path);

        try {
            HumanDetectorYOLOv4 hd = new HumanDetectorYOLOv4(img);
           hd.detectHuman();
          //  hd.detectBook();
            Mat image_roi = new Mat(Imgcodecs.imread(img.getAbsolutePath()), hd.detectBook());
         //   Mat image_roi2 = new Mat(Imgcodecs.imread(img.getAbsolutePath()), hd.detectHuman());
            Imgcodecs.imwrite("C:/Programming/imgs/cropimage.jpg",image_roi);
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  File cropimg = new File("C:/Programming/imgs/cropimage.jpg");
      /*  try {
            DetectRectangle dr = new DetectRectangle(cropimg);
        } catch(IOException e) {
            e.printStackTrace();
        }*/
    }
}