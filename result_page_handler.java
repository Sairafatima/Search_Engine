package searchengine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class result_page_handler implements Initializable{

    result_page_handler() throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("results_page.fxml"));
        Scene resultScene=new Scene(root,640,373);
        SearchEngine.mainStage.setScene(resultScene);
        SearchEngine.mainStage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
