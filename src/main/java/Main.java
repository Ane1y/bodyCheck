import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally(); // Use in case loadShared() doesn't work
//        Если не скачивала опенсиви на компуктер используй:
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
//        Если уже скачала и установила, пропиши как библиотеку проекта в свойствах
//        и используй либо это
//        nu.pattern.OpenCV.loadShared();
//        либо это
//        nu.pattern.OpenCV.loadLocally(); // Use in case loadShared() doesn't work
//        с чем то точно должно заработать
        String name = "190.jpg";

        File folder = new File("../imgs/");
        File[] listOfFiles = folder.listFiles();
try {
    new HumanDetector(listOfFiles);
    new HOGHumanDetector(listOfFiles);
} catch (IOException e) {
    e.printStackTrace();

}
    }
}