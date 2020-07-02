import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

import java.io.File;
import java.io.IOException;


public class HumanDetector {

        public HumanDetector(File... names) throws IOException {
            int i = 0;
            for (var name : names) {
                CascadeClassifier cascadeClassifier = new CascadeClassifier("cascades/haarcascade_fullbody.xml");
                Mat frame = new Mat();
                Mat image = Imgcodecs.imread(name.getCanonicalPath()/*.getName()*/);
                MatOfRect rect = new MatOfRect();
                cascadeClassifier.detectMultiScale(image, rect);
                Scalar frameRect = new Scalar(255, 0, 0);
                for (Rect dik : rect.toArray()) {
                    Imgproc.rectangle(image, new Point(dik.x, dik.y), new Point(dik.x + dik.width, dik.y + dik.height), frameRect);
                }
                System.out.println(rect.height());
                Imgcodecs.imwrite("haar " + i + ".jpg", image);
                i++;
            }
        }

}
