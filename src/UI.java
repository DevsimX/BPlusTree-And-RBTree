import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Just a easy UI file , the Untitled.fxml is key.
public class UI extends Application{
    @Override
    public void start(Stage primaryStage){
        try{
            //Load the Untitled.fxml
            Parent fxml = FXMLLoader.load(getClass().getResource("Untitled.fxml"));
            Scene scene = new Scene(fxml,1020,320);
            primaryStage.setTitle("English-Chinese Dictionary");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
