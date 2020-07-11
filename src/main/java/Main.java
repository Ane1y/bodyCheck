import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally();
        File folder = new File("../imgs/");
        File[] listOfFiles = folder.listFiles();
    //new HumanDetector(listOfFiles);
 //   new HOGHumanDetector(listOfFiles);
        try {
            HumanDetectorYOLOv4 hd = new HumanDetectorYOLOv4(listOfFiles);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}