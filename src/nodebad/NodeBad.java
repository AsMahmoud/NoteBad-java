/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodebad;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author win 10
 */
public class NodeBad extends Application {
    
    @Override
    public void start(Stage primaryStage) {
             
        //add top menue
        MenuBar bar=new MenuBar();
        Menu file=new Menu("File");
        Menu edit=new Menu("Edit");
        Menu help=new Menu("Help");
        //file items
        MenuItem file_new=new MenuItem("New");
        MenuItem open=new MenuItem("Open");
        MenuItem save=new MenuItem("Save");
        MenuItem exit=new MenuItem("Exit");
        //edit items
        MenuItem undo=new MenuItem("Undo");
        MenuItem cut=new MenuItem("Cut");
        cut.setAccelerator(KeyCombination.keyCombination("ctrl+n"));
        MenuItem copy=new MenuItem("Copy");
        MenuItem paste=new MenuItem("Paste");
        MenuItem delete=new MenuItem("Delete");
        MenuItem select_all=new MenuItem("Select All");
        //help items
        MenuItem about=new MenuItem("About Notepad");
        
        SeparatorMenuItem file_sep = new SeparatorMenuItem(); 
        file.getItems().addAll(file_new,open,save,exit);
        file.getItems().add(3, file_sep);
        
        edit.getItems().addAll(undo,cut,copy,paste,delete,select_all);
        SeparatorMenuItem edit_sep1 = new SeparatorMenuItem();
        edit.getItems().add(1, edit_sep1);
        SeparatorMenuItem edit_sep2 = new SeparatorMenuItem();
        edit.getItems().add(6, edit_sep2);
        help.getItems().addAll(about);
        bar.getMenus().addAll(file,edit,help);
        
        // add textarea
        TextArea area=new TextArea();
        area.setPrefSize(500, 400);
        
        ////////////////////////////////// edit handler
        
        undo.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
        area.undo();  
        }});
          
        cut.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
        area.cut();  
        }});
        
        copy.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
        area.copy();
        }});
        
        paste.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
        area.paste();
        }});
        
        delete.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
         area.deleteText(area.getSelection());
        }});
        
        select_all.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
         area.selectAll();
        }});
        
        /////////////////////////  file handler
        
        file_new.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
           area.clear();
        }});
        
        //------------------------------
        FileChooser fileChooser = new FileChooser();
        open.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
           File selectedFile = fileChooser.showOpenDialog(null);
           
           FileInputStream fis = null;
            try {
                fis = new FileInputStream(selectedFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NodeBad.class.getName()).log(Level.SEVERE, null, ex);
            }
            int size = 0;
            try {
                size = fis.available();
            } catch (IOException ex) {
                Logger.getLogger(NodeBad.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] b = new byte[size];
            try {
                fis.read(b);
            } catch (IOException ex) {
                Logger.getLogger(NodeBad.class.getName()).log(Level.SEVERE, null, ex);
            }
            area.setText(new String(b));
           // System.out.println(new String(b));
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(NodeBad.class.getName()).log(Level.SEVERE, null, ex);
            }

                  
        }});
        //--------------
        save.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
           FileWriter fileWriter = null;
           PrintWriter printWriter = null; 
           File selectedFile = fileChooser.showSaveDialog(null);
           
           try{
                fileWriter = new FileWriter(selectedFile, true);
                printWriter = new PrintWriter(fileWriter);
                printWriter.println(area.getText());

            }catch (IOException e2){
            e2.printStackTrace();
            }
            finally{ //Closing the resources
            try{
            printWriter.close();
            fileWriter.close();
            }catch (IOException e3)
            {
            e3.printStackTrace();
            }
            }
            
        }});
        
        exit.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
           primaryStage.close();
        }});
        
        /////////////////////////////// help
 
        help.setOnAction(new EventHandler<ActionEvent>()  
        {  
        public void handle(ActionEvent e)  
        {  
            Alert a1 = new Alert(AlertType.NONE,  
                              "Notepad Written By Asmaa Mahmoud",ButtonType.CLOSE); 
           a1.show();
        }});

        Label label=new Label();
        label.setText("Written By Asmaa Mahmoud");
        
        BorderPane pane=new BorderPane();
        pane.setTop(bar);
        pane.setCenter(area);
        pane.setBottom(label);
        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setTitle("My Notebad ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
