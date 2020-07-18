import back.Detector;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import jsonParser.inputData.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;


public class Draw extends Application {

    private Line currentLine ;
    private Image imgfon;

    private int lineCounter = -1;
    private PictureInfo info;
    private Point scale = new Point();
    private Measuring.partsOfBody[] parts = Measuring.partsOfBody.values();
    private int partsCounter = 0;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        var bgImage = new BackgroundImage(
                imgfon,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(imgfon.getWidth(), imgfon.getHeight(), false, false, true, true)
        );

        pane.setBackground(new Background(bgImage));

        pane.setOnMousePressed(e -> {
            currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY());
            pane.getChildren().add(currentLine);

            lineCounter++;
            partsCounter++;

        });

        pane.setOnMouseDragged(e -> {
            currentLine.setEndX(e.getX());
            currentLine.setEndY(e.getY());
        });

        pane.setOnMouseReleased(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setContentText("Are you ok with this line?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.CANCEL){
                pane.getChildren().remove(currentLine);
            } else if(result.get() == ButtonType.OK){
                recordInfo();
            }



        });

        scale = new Point(imgfon.getHeight() / 640, imgfon.getWidth() / 480);
        Scene scene = new Scene(pane, imgfon.getWidth()/scale.getY(), imgfon.getHeight()/scale.getX());

        Button button = new Button("Your size");
        button.setLayoutX(400);
        button.setLayoutY(600);
        button.setStyle("-fx-background-color: lightgrey");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                button.setStyle("-fx-background-color: black");

                Detector detector = new Detector(info);

                Label heightLabel = new Label("Height");
                heightLabel.setLayoutY(0);
                heightLabel.setLayoutX(230);

                Label[] labels = new Label[Measuring.partsOfBody.values().length];
                int i = 0;
                for(var label : labels) {
                    label.setLayoutY(info.getMeasuring(i).getBeginPoint().getY());
                    label.setLayoutX(40);
                    pane.getChildren().add(label);
                }

            }
        });

        pane.getChildren().add(button);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void recordInfo() {
        if(lineCounter >= 0) {
            if(partsCounter >= 3 || partsCounter < 0) {
                partsCounter = 0;
            }
            Measuring meas = new Measuring(parts[partsCounter],
                    new Point(currentLine.getStartX(), currentLine.getStartY()),
                    new Point(currentLine.getEndX(), currentLine.getEndY()));
            if(lineCounter == 0) {
                String path = imgfon.getUrl();
                info = new PictureInfo(path.replace("file:/", ""), meas);
                info.setScale(scale);
            } else {
                info.getMeasuring().add(meas);
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setImg(Image img){
        imgfon = img;
    }
}

