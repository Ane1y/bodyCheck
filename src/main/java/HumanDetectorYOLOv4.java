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

    private void activateYOLOv4(File file) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("darknet/darknet.exe",
                "detector", "test", "darknet/cfg/coco.data",
                "darknet/cfg/yolov4.cfg",
                "darknet/cfg/yolov4.weights", "-ext_output", "-dont_show", "-out", resultFile.getCanonicalPath(), file.getCanonicalPath());
        builder.redirectErrorStream(true);
        Process process = builder.start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            builder.command("imgs/180.jpg");
            process.waitFor();
        } catch(IOException | InterruptedException e) {
          e.printStackTrace();
        }

        jsonUtils();
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

    public Rect detectHuman(File file) throws IOException {
        activateYOLOv4(file);
        Gson gson = new Gson();
        Frame[] jsonObject = gson.fromJson(new FileReader(resultFile.getCanonicalPath()), Frame[].class);
        Rect frame = null;
        Objects person = null;
        try {
            for(var obj : jsonObject[0].getObjects()) {
                if(obj.getName().equals("person")) {
                    person = obj;
                    break;
                 }
            }
            Relative_coordinates rc = person.getRelative_coordinates();
            BufferedImage img = ImageIO.read(file);
            frame = new Rect((int) ((rc.getCenter_x() - rc.getWidth() / 2) * img.getWidth()),
                    (int) ((rc.getCenter_y() - rc.getHeight() / 2) * img.getHeight()),
                    (int) (rc.getWidth() * img.getWidth()),
                    (int) (rc.getHeight() * img.getHeight())
            );
            System.out.println(frame.toString());
        } catch(NullPointerException e) {
            System.out.print("Человек не найден");
        }
        return frame;

    }
}