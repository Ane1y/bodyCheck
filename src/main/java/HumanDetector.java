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
            for (File name : names) {
                CascadeClassifier cascadeClassifier = new CascadeClassifier("./hogcascades/hogcascades_pedestrians.xml");
                Mat frame = new Mat();
                Mat image = Imgcodecs.imread(name.getCanonicalPath()/*.getName()*/);
             //   Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2GRAY, 4);
                MatOfRect detectedObj = new MatOfRect();
                cascadeClassifier.detectMultiScale(image, detectedObj);
                Scalar frameRect = new Scalar(255, 0, 0);
                for (Rect object : detectedObj.toArray()) {
                    Imgproc.rectangle(image, new Point(object.x, object.y),
                                      new Point(object.x + object.width, object.y + object.height), frameRect);
                }
                System.out.println(detectedObj.height());
                Imgcodecs.imwrite("eyes " + i + ".jpg", image);
                i++;
            }
        }

}
