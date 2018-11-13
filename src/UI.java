import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class UI extends Application{
    @Override
    public void start(Stage primaryStage){
        try{
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
//        controller controller = new controller();
//        controller.Controller(new RB_Tree() , new BplusTree<String, String>(4));
        Application.launch(args);
    }
}
