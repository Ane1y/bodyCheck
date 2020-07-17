import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {
    private Stage stage1;
    private Pane root1;
    private Image imgdots;

    @FXML
    private Button openbtn;

    @FXML
    private Button gobtn;

    @FXML
    private ImageView img = new ImageView();;

    @FXML
    private void click(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage1);
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            imgdots = image1;
            img.setImage(image1);
            img.setPreserveRatio(true);
            img.setFitHeight(300);
            img.setFitWidth(350);
            root1.getChildren().add(img);
        }
    }


    @FXML
    public void gonext(ActionEvent actionEvent) throws IOException {

       try{
        if(imgdots == null) {
            throw new IOException("choose image");
        }
        Draw draw = new Draw();
        draw.setImg(imgdots);
        draw.start(stage1);
    } catch (Exception e){
           System.out.println("Выберите картинку");
       }
    }


    public void setStageAndRoot(Stage stage, Pane root) {
        stage1 = stage;
        root1 = root;
    }

}