/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationdemo;

import com.jvarred.DemoWebService;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jvarred
 */
public class JavaFXApplicationDemo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                System.out.println(hello("KuKy"));
            }
        });
        
        Button btn2 = new Button();
        btn2.setText("Initialize");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println(initialize());
            }
        });
        
        
        Button btn3 = new Button();
        btn3.setText("Add number");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Random rand = new Random();
                String input =  "Valor:" + rand.nextInt();
                System.out.println("insertando: '" + input + "'");
                System.out.println(insertString(input));
                
            }
        });
        
        
        Button btn4 = new Button();
        btn4.setText("Get all numbers");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println(getAllStrings());
            }
        });
        
        HBox root = new HBox();
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        root.getChildren().add(btn4);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static String hello(java.lang.String name) {
        com.jvarred.DemoWebService_Service service = new com.jvarred.DemoWebService_Service();
        com.jvarred.DemoWebService port = service.getDemoWebServicePort();
        return port.hello(name);
    }

    private static String initialize() {
        com.jvarred.DemoWebService_Service service = new com.jvarred.DemoWebService_Service();
        com.jvarred.DemoWebService port = service.getDemoWebServicePort();
        return port.initialize();
    }

    private static String getAllStrings() {
        com.jvarred.DemoWebService_Service service = new com.jvarred.DemoWebService_Service();
        com.jvarred.DemoWebService port = service.getDemoWebServicePort();
        return port.getAllStrings();
    }

    private static String insertString(java.lang.String text) {
        com.jvarred.DemoWebService_Service service = new com.jvarred.DemoWebService_Service();
        com.jvarred.DemoWebService port = service.getDemoWebServicePort();
        return port.insertString(text);
    }
    
}
