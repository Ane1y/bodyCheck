package back;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class HeightCalculate {
    private double bookHeight;
    private double bookWidth;
    private double realHumanHeight;

    public HeightCalculate(File file, double imgHumanHeight) throws IOException {

        ReadFile(file);
        realHumanHeight = imgHumanHeight / bookHeight * 29.7;
        // return realHumanHeight;
    }

    private void ReadFile(File file) throws IOException {
        try {
            // File resultFile = new File(file);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                bookHeight = Double.parseDouble(myReader.nextLine());
                bookWidth = Double.parseDouble(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось прочитать файл");
            e.printStackTrace();
        }
    }

    public double getRealHumanHeight() {
        return realHumanHeight;
    }

}
