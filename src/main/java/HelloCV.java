import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class HelloCV {
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
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());

    }
}