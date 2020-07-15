import com.google.gson.Gson;
import jsonParser.Frame;
import jsonParser.Objects;
import jsonParser.Relative_coordinates;
import org.opencv.core.Rect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class HumanDetectorYOLOv4 {

    private File resultFile = new File("./result.json");
    private File image;

    private Frame[] resultObjects;
double imgHumanHeight;
    public HumanDetectorYOLOv4(File file) throws IOException {
        image = file;

        ProcessBuilder builder = new ProcessBuilder("C:/Programming/darknet-master/build/darknet/x64/darknet.exe",
                "detector", "test", "C:/Programming/darknet-master/cfg/coco.data",
                "C:/Programming/darknet-master/cfg/yolov4.cfg",
                "C:/Programming/darknet-master/build/darknet/yolov4.weights", "-ext_output",
                "-dont_show", "-out", "result.json", Main.path);
         builder.redirectErrorStream(true);
        Process process = builder.start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            builder.command(Main.path);
            process.waitFor();

        } catch(IOException | InterruptedException e) {
          e.printStackTrace();
        }

        jsonUtils();

        Gson gson = new Gson();
        resultObjects = gson.fromJson(new FileReader(resultFile.getCanonicalPath()), Frame[].class);


    }

    /**
     * handle output json file from darknet YOLOv4
     *replaces the slash in the path with a double backslash
     * @throws IOException
     */
    private void jsonUtils() throws IOException {
        File fileToBeModified = new File(resultFile.getCanonicalPath());
        StringBuilder oldContent = new StringBuilder();
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null) {
                oldContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            String newContent = oldContent.toString().replace("\\", "\\\\");
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        finally {
                assert reader != null;
                reader.close();
                assert writer != null;
                writer.close();
        }
    }

    /**
     * @param name name of detected object
     * @return first coincident object's top left point, width and height
     */
    private Rect detectObject(String name) {
        Rect frame = null;
        Objects person = null;
        try {
            for(var obj : resultObjects[0].getObjects()) {
                if(obj.getName().equals(name)) {
                    person = obj;
                    break;
                }
            }
            Relative_coordinates rc = person.getRelative_coordinates();
            BufferedImage img = ImageIO.read(image);
            frame = new Rect((int) ((rc.getCenter_x() - rc.getWidth() / 2) * img.getWidth()),
                    (int) ((rc.getCenter_y() - rc.getHeight() / 2) * img.getHeight()),
                    (int) (rc.getWidth() * img.getWidth()),
                    (int) (rc.getHeight() * img.getHeight()));
            if(name=="book"){
                //String command = "python /c start python C:/Users/Somn117/PycharmProjects/DetectRect/q.py";
               // Process p = Runtime.getRuntime().exec(command);
                String prg = "import sys";
                BufferedWriter out = new BufferedWriter(new FileWriter("C:/Programming/DetectRect/q.py"));
                out.write(prg);
                out.close();
                Process p = Runtime.getRuntime().exec("python C:/Programming/DetectRect/q.py");
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String ret = in.readLine();
                System.out.println("value is : "+ret);
            }
            if (name=="person"){
                imgHumanHeight=frame.height;
                 File result = new File("C:/Programming/result/result.txt");
                 HeightCalculate hc= new HeightCalculate(result,imgHumanHeight);
                 System.out.print(hc.realHumanHeight);
            }
        } catch(NullPointerException e) {
            System.out.print(name + " не найден");
        } catch(IOException e) {
            System.out.println("Изображение не найдено");
        }
        return frame;
    }
    public Rect detectBook() {
       // var book = detectObject("book");

            return(detectObject("book"));

    }
    public Rect detectHuman() throws IOException {
        return detectObject("person");
    }
}