import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HOGHumanDetector {

    List<String> images = new ArrayList<String>();

    HOGHumanDetector(String name) {
            // Создаем матрицу изображения для OpenCV и помещаем в нее нашу фотографию
            Mat mat = Imgcodecs.imread("../imgs/" + name);
            // Переконвертируем матрицу с RGB на градацию серого
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY, 4);
            HOGDescriptor hog = new HOGDescriptor();
            //Получаем стандартный определитель людей и устанавливаем его нашему дескриптору
            MatOfFloat descriptors = HOGDescriptor.getDefaultPeopleDetector();

            hog.setSVMDetector(descriptors);
            // Определяем переменные, в которые будут помещены результаты поиска ( locations - прямоуголные области, weights - вес (можно сказать релевантность) соответствующей локации)
            MatOfRect locations = new MatOfRect();
            MatOfDouble weights = new MatOfDouble();
            // Собственно говоря, сам анализ фотографий. Результаты запишутся в locations и weights
            hog.detectMultiScale(mat, locations, weights);
            //Переменные для выделения областей на фотографии
            Point rectPoint1 = new Point();
            Point rectPoint2 = new Point();
            Scalar fontColor = new Scalar(0, 0, 0);
            Point fontPoint = new Point();
            // Если есть результат - добавляем на фотографию области и вес каждой из них
            if (locations.rows() > 0) {
                List<Rect> rectangles = locations.toList();
                int i = 0;
                List<Double> weightList = weights.toList();
                for (Rect rect : rectangles) {
                    float weigh = weightList.get(i++).floatValue();
                    rectPoint1.x = rect.x;
                    rectPoint1.y = rect.y;
                    fontPoint.x = rect.x;
                    fontPoint.y = rect.y - 4;
                    rectPoint2.x = rect.x + rect.width;
                    rectPoint2.y = rect.y + rect.height;
                    final Scalar rectColor = new Scalar(0, 0, 0);
                    // Добавляем на изображения найденную информацию
                    Imgproc.rectangle(mat, rectPoint1, rectPoint2, rectColor, 2);
                    Imgproc.putText(mat,
                            String.format("%1.2f", weigh),
                            fontPoint, Imgproc.FONT_HERSHEY_PLAIN, 1.5, fontColor,
                            2, Imgproc.LINE_AA, false);

                }
            }
        Imgcodecs.imwrite("dene2.jpg", mat);

        fontPoint.x = 15;
           // fontPoint.y = bitmap.getHeight() - 20;
            // Добавляем дополнительную отладочную информацию

    }
}