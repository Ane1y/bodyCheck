import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main( String[] args ) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("C:/Programming/darknet-master/build/darknet/x64/darknet.exe",
                "detector", "test", "C:/Programming/darknet-master/cfg/coco.data",
                "C:/Programming/darknet-master/cfg/yolov4.cfg",
                "C:/Programming/darknet-master/build/darknet/x64/yolov4.weights", "C:/Programming/imgs/iam.jpg");
        builder.environment().put( "DARKNET", "C:/Programming/darknet-master/share/" );
        builder.redirectErrorStream( true );
        Process process = builder.start();

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream())) ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        process.waitFor();
    }
}