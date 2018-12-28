package searchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchEngine{
    
    public static Scene searchScene;
    public static Stage mainStage;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String search;
        int in;
        System.out.println("Choose one of the following.\n1. Search\n2. Open Article\n0. Exit");
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        in=input.nextInt();
        System.out.println(in);
        while(in!=0){
            if(in==1){
                System.out.println("Enter text to be searched.");
                search s=new search(input2.nextLine());
                s.doSearch();
            }
            else if(in==2){
                System.out.println("Enter Title of Text.");
                search s=new search("");
                search=input2.nextLine();
                System.out.println(s.article("muhammad iqbal"));
            }else
                System.out.println("Enter Valid input.");
            
            System.out.println("Choose one of the following.\n1. Search\n2. Open Article\n0. Exit");
            in=input.nextInt();
        }
    }
}
