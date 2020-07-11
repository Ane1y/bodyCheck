import java.io.*;
import java.util.Scanner;

public class HumanDetectorYOLOv4 /*extends Thread*/{

    public HumanDetectorYOLOv4(File... names) throws IOException {
            ProcessBuilder builder = new ProcessBuilder("darknet/darknet.exe",
                    "detector", "test", "darknet/cfg/coco.data",
                    "darknet/cfg/yolov4.cfg",
                    "darknet/cfg/yolov4.weights", "-ext_output", "-dont_show", "-out", "result.json", "imgs/175.jpg");
//data/train.txt - фото которые хотим распознать
     // String paramsString = "darknet/darknet.exe detector test darknet/cfg/coco.data darknet/cfg/yolov4.cfg darknet/cfg/yolov4.weights -ext_output -dont_show -out result.json < data/train.txt";

        //ProcessBuilder builder = new ProcessBuilder(params);
     //       builder.environment().put("DARKNET", "darknet");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                builder.command("imgs/180.jpg");
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}