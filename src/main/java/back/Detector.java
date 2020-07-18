package back;

import jsonParser.inputData.PictureInfo;
import jsonParser.inputData.Point;
import jsonParser.outputData.ResultData;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.IOException;

public class Detector {
    public static final String mainFolder = "";/*C:/Programming*/
    public static String path = mainFolder + "/imgs/sanyaandlove.jpg";

    private ResultData[] results;
    public Detector(PictureInfo info) {
        nu.pattern.OpenCV.loadLocally();

        File img = new File(info.getFilename());
        for(var i : info.getMeasuring()){
            System.out.println("scale " + info.getScale());
            System.out.println(i.toString());

        }
        info.setScaledPoint(info.getScale()); //этой строкой растягиваешь координаты, если что то криво выходит пиши мне
        double[] distances = new double[info.getMeasuring().size()];
        int j = 0;
        for(var i : info.getMeasuring()) {
            distances[j] = Math.sqrt(Math.pow(i.getBeginPoint().getX() - i.getEndPoint().getX(), 2) + Math.pow(i.getBeginPoint().getY() - i.getEndPoint().getY(), 2));
            j += 1;
            System.out.println(i.toString());
        }

        try {
            HumanDetectorYOLOv4 hd = new HumanDetectorYOLOv4(img);
            hd.detectHuman();
          /*  Mat image_roi = new Mat(Imgcodecs.imread(img.getAbsolutePath()), hd.detectBook());
            Imgcodecs.imwrite(mainFolder + "/imgs/cropimage.jpg", image_roi);
*/
            //results лалала
            //сюда ответы записывай


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

    public ResultData[] getResults() {
        return results;
    }
}