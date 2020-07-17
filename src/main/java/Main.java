import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application{

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Pane root = (Pane)loader.load();

        Controller controller = (Controller)loader.getController();
        controller.setStageAndRoot(stage, root);


        Scene scene1 = new Scene(root, 460, 680);

        stage.setScene(scene1);
        stage.setResizable(false);
        stage.setTitle("BodyCheck");

        stage.show();

    }

}

