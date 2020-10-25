import java.util.*;
import java.text.*;
import java.io.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;

public class Form extends Application
{

   FlowPane root = new FlowPane();     
   FlowPane contents = new FlowPane();  
   ComboBox comboBox = new ComboBox();  
   Canvas canvas = new Canvas();        
   GraphicsContext gc = canvas.getGraphicsContext2D();   
   
   CheckBox thicknessBox = new CheckBox("Thiccness");   
   RadioButton noSpace = new RadioButton("No spaces");  
   RadioButton oneSpace = new RadioButton("1 space");   
   TextField textField = new TextField("Enter an A or an I"); 
   
   public void start(Stage stage)
   {
      comboBox.setValue("10");       
      comboBox.getItems().add("10"); 
      comboBox.getItems().add("50"); 
      comboBox.getItems().add("100");      
      
      canvas.setWidth(600);
      canvas.setHeight(600);      
      for (int i=0; i<10; i++) //draw initial 10 lines
      {
         gc.fillRect(0,10+i*10,100,1);         
      }
      
      ToggleGroup group = new ToggleGroup(); //ToggleGroup only allows you to press one at a time
      noSpace.setToggleGroup(group);          
      oneSpace.setToggleGroup(group);         
      
      comboBox.setOnAction(new ComboBoxListener());       
      thicknessBox.setOnAction(new ComboBoxListener());  
      noSpace.setOnAction(new ComboBoxListener());      
      oneSpace.setOnAction(new ComboBoxListener());     
      textField.setOnAction(new TextFieldListener());
         
      contents.getChildren().add(comboBox);
      contents.getChildren().add(thicknessBox);
      contents.getChildren().add(noSpace);
      contents.getChildren().add(oneSpace);
      contents.getChildren().add(textField);
      
      root.getChildren().add(contents); 
      root.getChildren().add(canvas);    
      contents.setHgap(10);
      
            
      Scene scene = new Scene(root,400,300);
      stage.setScene(scene);
      stage.setTitle("Lab 6");
      stage.show();
      
   }
      
   public static void main(String[]args)
   {
      launch(args);
   }
      
      public class ComboBoxListener implements EventHandler<ActionEvent>
      {
         public void handle(ActionEvent e)
         {
         
            int thiccness = 1;
            if (thicknessBox.isSelected())
            {
               thiccness = 2;
            }
         
            //options to draw 10, 50, or 100 lines
            switch((String)comboBox.getValue())
            {
               case "10":
               {
                  gc.clearRect(0,0,400,300);
                  for (int i=0; i<10; i++) //draw 10 lines
                  {
                     gc.fillRect(0,10+i*10,100,thiccness);
                  }
                  break;
               }
               
               case "50":
               {
                  gc.clearRect(0,0,400,300);
                  for (int i=0; i<50; i++)  //draw 50 lines
                  {
                     gc.fillRect(0,2+i*4,100,thiccness);
                  }
                  break;
               }
                  
               case "100":
               {
                  gc.clearRect(0,0,400,300);
                  for (int i=0; i<100; i++)  //draw 100 lines
                  {
                     gc.fillRect(0,1+i*2,100,thiccness);
                  }
                  break;
               }
            }
            
            //clear space if 1 space is selected
            if (oneSpace.isSelected())
            {
               gc.clearRect(40,0,20,400);
            }            
            
         }
      }
      
      public class TextFieldListener implements EventHandler<ActionEvent>
      {
         public void handle(ActionEvent e)
         {
         
            if(textField.getText().equals("A"))
            {              
               comboBox.setValue("100");
               thicknessBox.fire();
               oneSpace.fire();              
            }
            else if(textField.getText().equals("I"))
            {
               comboBox.setValue("10");
               noSpace.fire();
               if(thicknessBox.isSelected())
               {
                  thicknessBox.fire();
               }
            }         
         }
      }
 
}

