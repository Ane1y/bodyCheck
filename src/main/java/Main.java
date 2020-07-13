import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally();
       /* File folder = new File("../imgs/");
        File[] listOfFiles = folder.listFiles();*/
    //new HumanDetector(listOfFiles);
 //   new HOGHumanDetector(listOfFiles);
        File img = new File("imgs/175.jpg");
        HumanDetectorYOLOv4 hd = new HumanDetectorYOLOv4();
        try {
            hd.detectHuman(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}