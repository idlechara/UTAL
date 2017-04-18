/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chibbi;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Erik Andres Regla Torres
 */
public class Chibbi extends Application {

    public void play() {
        pathTransition.play();
        pathTransition2.play();
    }

    @Override public void stop() {
        pathTransition.stop();
    }

    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
    
    Stage starter;
    int stage;
    PathTransition pathTransition;
    PathTransition pathTransition2;
        Group questions = new Group();
        Group route = new Group();
        DiamondChibbi pregunta1, pregunta2,pregunta3,pregunta4;   
        CircleChibbi fin1, fin2, fin3;
        RectangleChibbi cuadro1, cuadro13, cuadro2, cuadro3, cuadro4, cuadro5, cuadro6, cuadro7, cuadro8, cuadro9, cuadro10, cuadro11, cuadro12;
        Path path;
                Path path2;
        
    public void move(){
        Rectangle rect = new Rectangle (90, 90, 20, 20);
        rect.setArcHeight(6);
        rect.setArcWidth(6);
        rect.setFill(Color.ORANGE);
        route.getChildren().add(rect);
        
        
        
        path  = new Path();
        path.setStroke(Color.DODGERBLUE);
        route.getChildren().add(path);
        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setPath(path);
        pathTransition.setNode(rect);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(true);
        
        Rectangle rect2 = new Rectangle (90, 90, 20, 20);
        rect2.setArcHeight(6);
        rect2.setArcWidth(6);
        rect2.setFill(Color.ORANGE);
        route.getChildren().add(rect2);
        
        
        
        path2  = new Path();
        path2.setStroke(Color.DODGERBLUE);
        route.getChildren().add(path2);
        pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.seconds(0.5));
        pathTransition2.setPath(path2);
        pathTransition2.setNode(rect2);
        pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition2.setCycleCount(1);
        pathTransition2.setAutoReverse(true);
        
                path.getElements().clear();
                path2.getElements().clear();
        if(stage == 1){
                path.getElements().add(new MoveTo(100, 100));
                path.getElements().add(new LineTo(100, 200));
        }else if(stage == 2){
                path.getElements().add(new MoveTo(100, 200));
                path.getElements().add(new LineTo(100, 300));
        }else if(stage == 3){
                path.getElements().add(new MoveTo(100, 200));
                path.getElements().add(new LineTo(215, 200));
                path.getElements().add(new LineTo(215, 100));
                path.getElements().add(new LineTo(300, 100));
        }else if(stage == 4){
                path.getElements().add(new MoveTo(100, 300));
                path.getElements().add(new LineTo(100, 400));
        }else if(stage == 5){
                path.getElements().add(new MoveTo(300, 100));
                path.getElements().add(new LineTo(300, 250));
        }else if(stage == 6){
                path.getElements().add(new MoveTo(300, 250));
                path.getElements().add(new LineTo(300, 400));
        }else if(stage == 7){
                path.getElements().add(new MoveTo(300, 400));
                path.getElements().add(new LineTo(400, 400));
                path.getElements().add(new LineTo(400, 100));
                path.getElements().add(new LineTo(500, 100));
                
                path2.getElements().add(new MoveTo(300, 400));
                path2.getElements().add(new LineTo(400, 400));
                path2.getElements().add(new LineTo(400, 460));
                path2.getElements().add(new LineTo(300, 460));
                path2.getElements().add(new LineTo(300, 620));
                path2.getElements().add(new LineTo(500, 620));
        }else if(stage == 8){
                path.getElements().add(new MoveTo(500, 100));
                path.getElements().add(new LineTo(500, 250));
        }else if(stage == 9){
                path.getElements().add(new MoveTo(500, 250));
                path.getElements().add(new LineTo(500, 370));
        }else if(stage == 10){
                path.getElements().add(new MoveTo(500, 377));
                path.getElements().add(new LineTo(500, 500));
        }else if(stage == 11){
                path.getElements().add(new MoveTo(500, 500));
                path.getElements().add(new LineTo(500, 620));
        }else if(stage == 12){
                path.getElements().add(new MoveTo(500, 375));
                path.getElements().add(new LineTo(900, 375));
                path.getElements().add(new LineTo(900, 100));
                path.getElements().add(new LineTo(1000, 100));
        }else if(stage == 13){
                path.getElements().add(new MoveTo(500, 500));
                path.getElements().add(new LineTo(750, 500));
        }else if(stage == 14){
                path.getElements().add(new MoveTo(750, 500));
                path.getElements().add(new LineTo(750, 600));
        }else if(stage == 15){
                path.getElements().add(new MoveTo(1000, 100));
                path.getElements().add(new LineTo(1000, 200));
        }else if(stage == 16){
                path.getElements().add(new MoveTo(1000, 226));
                path.getElements().add(new LineTo(1000, 338));
        }else if(stage == 17){
                path.getElements().add(new MoveTo(990, 340));
                path.getElements().add(new LineTo(990, 437));
        }else if(stage == 18){
                path.getElements().add(new MoveTo(750, 610));
                path.getElements().add(new LineTo(1000, 610));
        }else if(stage == 19){
                path.getElements().add(new MoveTo(1000, 610));
                path.getElements().add(new LineTo(1200, 610));
        }else if(stage == 20){
                path.getElements().add(new MoveTo(500, 610));
                path.getElements().add(new LineTo(750, 610));
        }else if(stage == 21){
                path.getElements().add(new MoveTo(750, 500));
                path.getElements().add(new LineTo(900, 500));
                path.getElements().add(new LineTo(900, 100));
                path.getElements().add(new LineTo(1000, 100));
        }
        route.toBack();
        questions.toFront();
        play();
    }
    
    public void init(Stage primarystage) {
        final Stage primary = primarystage;
        Group root = new Group();
        Scene scene = new Scene(root, 1366, 680, Color.WHITE);
        scene.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                System.out.println("x:"+me.getSceneX() +" y:" +me.getSceneY());
            }
        }));
        primarystage.setScene(scene);

        primarystage.show();

        
        /**
         * lo que falta por implementar sera utilizada multiples rutas.
         * el usuario clickea en las etapas para que la cosa avanze ? 
         * 
         */
        cuadro1 = new RectangleChibbi(100, 100, "Recibir paquete\nen la interfaz", questions);
        cuadro1.enableItem();
        cuadro1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                pregunta1.enableItem();
                stage = 1;
                move();
            }
        });
        
         pregunta1 = new DiamondChibbi(100, 200, "¿Los datos son\npara el router?", questions);
         pregunta1.setNoListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro1.on){
                pregunta1.yesNo=false;
                pregunta1.choice = true;
                cuadro2.enableItem();
                stage = 2;
                move();
                }
            }
        });
         pregunta1.setYesListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro1.on){
                    pregunta1.yesNo=true;
                pregunta1.choice = true;
                cuadro3.enableItem();
                stage = 3;
                move();
                }
            }
        });
         cuadro2 = new RectangleChibbi(100, 300, "Descartar los datos", questions);
         cuadro2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(pregunta1.on&&pregunta1.choice&&!pregunta1.yesNo){
                fin1.enableItem();
                stage = 4;
                move();
                }
            }
        });
         fin1 = new CircleChibbi(100, 400, "fin", questions);
         cuadro3 = new RectangleChibbi(300, 100, "Eliminar el\nencabezado\nde la trama\ndel paquete", questions);
         cuadro3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(pregunta1.on&&pregunta1.choice&&pregunta1.yesNo){
                cuadro4.enableItem();
                stage = 5;
                move();
                }
            }
        });
         cuadro4 = new RectangleChibbi(300, 250, "Extraer la \ndireccion IP\ndel destino del\npaquete", questions);
         cuadro4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro3.on){
                cuadro5.enableItem();
                stage = 6;
                move();
                }
            }
        });
         cuadro5 = new RectangleChibbi(300, 400, "Obtener la\nprimera entrada\nde la tabla de\nenrutamiento", questions);
         cuadro5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro4.on){
                cuadro6.enableItem();
                cuadro8.enableItem();
                stage = 7;
                move();
                }
            }
        });
         cuadro6 = new RectangleChibbi(500, 100, "Aplicar mascara\nde entrada de\ntabla de\nenrutamiento a", questions);
         cuadro6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                
                if(cuadro5.on){
                cuadro7.enableItem();
                stage = 8;
                move();
                }
            }
        });
         cuadro7 = new RectangleChibbi(500, 250, "Comparar la\ndireccion IP\nde destino\nenmascarada con\nla direccion de\nred de entrada", questions);
         cuadro7.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override

            public void handle(MouseEvent me) {
                if(cuadro6.on){
                pregunta2.enableItem();
                stage = 9;
                move();
                }
            }
        });
         pregunta2 = new DiamondChibbi(500, 375, "¿Existe alguna\ncoincidencia?", questions);        
         pregunta2.setNoListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro7.on){
                    pregunta2.yesNo = false;
                pregunta2.choice = true;
                pregunta3.enableItem();
                stage = 10;
                move();
                }
            }
        });
         pregunta2.setYesListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro7.on){
                    pregunta2.yesNo=true;
                pregunta2.choice = true;
                cuadro10.enableItem();
                stage = 12;
                move();
                }
            }
        });
         pregunta3 = new DiamondChibbi(500, 500, "¿Existe alguna otra \nentrada para la tabla\nde enrutamiento?", questions);
         pregunta3.setNoListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(pregunta2.on&&pregunta2.choice&&!pregunta2.yesNo){
                    pregunta3.yesNo=false;
                pregunta3.choice = true;
                stage = 11;
                move();
                }
            }
        });
         pregunta3.setYesListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(pregunta2.on&&pregunta2.choice&&!pregunta2.yesNo){
                    pregunta3.yesNo=true;
                pregunta3.choice = true;
                pregunta4.enableItem();
                stage = 13;
                move();
                }
            }
        });
         cuadro8 = new RectangleChibbi(500, 620, "Ir a la siguente\nentrada", questions);
         cuadro8.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro5.on){
                cuadro9.enableItem();
                stage = 20;
                move();
                }
            }
        });
         pregunta4 = new DiamondChibbi(750, 500, "¿Existe alguna otra\nruta por defecto?", questions);
         pregunta4.setNoListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(pregunta3.on&&pregunta3.choice&&pregunta3.yesNo){
                    pregunta4.yesNo=false;
                pregunta4.choice = true;
                cuadro9.enableItem();
                stage = 14;
                move();
                }
            }
        });
         pregunta4.setYesListener(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(pregunta3.on&&pregunta3.choice&&pregunta3.yesNo){
                    pregunta4.yesNo=true;
                pregunta4   .choice = true;
                cuadro10.enableItem();
                stage = 21;
                move();
                }
            }
        });
         cuadro9 = new RectangleChibbi(750, 620, "Descartar los\ndatos", questions);
         cuadro9.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro8.on||(pregunta4.on&&pregunta4.choice&&!pregunta4.yesNo)){
                cuadro13.enableItem();
                stage = 18;
                move();
                }
            }
        });
         cuadro10 = new RectangleChibbi(1000, 100, "Enviar el paquete de\ndatos a la interfaz de\nla entrada de la tabla\nde enrutamiento", questions);
         cuadro10.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(pregunta2.on||(pregunta4.on&&pregunta4.choice&&pregunta2.yesNo)){
                cuadro11.enableItem();
                stage = 15;
                move();
                }
            }
        });
         cuadro12 = new RectangleChibbi(1000, 350, "Enviar la nueva\ntrama", questions);
         cuadro12.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro11.on){
                fin2.enableItem();
                stage = 17;
                move();
                }
            }
        });
         cuadro11 = new RectangleChibbi(1000, 235, "Encapsular el paquete\nde datos en la\ntrama apropiada", questions);
         cuadro11.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro10.on){
                cuadro12.enableItem();
                stage = 16;
                move();
                }
            }
        });
         fin2 = new CircleChibbi(1000, 450 ,"fin", questions);
         cuadro13 = new RectangleChibbi(1000, 620, "Enviar en mensaje\nde respuesta destino", questions);
         cuadro13.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if(cuadro9.on){
                fin3.enableItem();
                stage = 19;
                move();
                }
            }
        });
         fin3 = new CircleChibbi(1200, 620 ,"fin", questions);
        
//        
//         Group button = new Group();
//         VBox cont = new VBox();
//         Button reinit = new Button("Re-Init!");
//         reinit.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent arg0) {
//                
//            }
//        });
//         cont.getChildren().add(reinit);
//         cont.setLayoutX(100);
//         cont.setLayoutY(600);
//        button.getChildren().add(cont);
//        
        
//        CircleChibbi fin2 = new CircleChibbi(500, 600, "fin", questions);
        
//        pregunta.setStrokeType(StrokeType.OUTSIDE);
//        pregunta.setStroke(Color.BLACK);
//        pregunta.setStrokeWidth(3);

        root.getChildren().add(questions);
        root.getChildren().add(route);
//        root.getChildren().add(button);
        questions.toFront();
        
        
//        Timeline timeline = new Timeline();
//            timeline.getKeyFrames().addAll(
//                    new KeyFrame(Duration.ZERO, // set start position at 0
//                    new KeyValue(questions.translateXProperty(), 0 * 800),
//                    new KeyValue(questions.translateYProperty(), 0 * 600)),
//                    new KeyFrame(new Duration(4000), // set end position at 40s
//                    new KeyValue(questions.translateXProperty(), 0.1 * 800),
//                    new KeyValue(questions.translateYProperty(), 0.1 * 600)));
//            
//        timeline.play();
    }
}
