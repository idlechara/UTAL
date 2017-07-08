package sample;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {
    public Stage mainApp;
    private Quiz quiz;
    private Quiz local_data;
    private BufferedImage bimage;
    private String bimage_type;
    @FXML private TextArea source;
    @FXML private TextField questionTextField;
    @FXML private TableView answerTable;
    @FXML private ImageView imagePane;
    @FXML private Label imgRoute;
    @FXML private TableColumn<Answer, String> col_text;
    @FXML private TableColumn<Answer, Boolean> col_ok;
    @FXML private TableColumn<Answer, Integer> col_id;

    private ObservableList<Answer> data =
            FXCollections.observableArrayList(
                    new Answer("Respuesta 1", true, 1),
                    new Answer("Respuesta 2", false, 2),
                    new Answer("Respuesta 3", false, 3),
                    new Answer("Respuesta 4", false, 4)
                    );

    public void addNewQuestion(){
        data.add(new Answer("", false, data.size()+1));
    }

//    String encodedImage = null;

    public void reset(){
        if(data.size() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Debe existir al menos una respuesta para cada pregunta.");
            alert.showAndWait();
        }
        if(questionTextField.getText()==""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("La pregunta debe tener enunciado");
            alert.showAndWait();
        }
        if(imagePane.getImage()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("La pregunta debe tener imagen");
            alert.showAndWait();
        }

        quiz.addQuestion(questionTextField.getText(), data, bimage);
        local_data.addQuestion(questionTextField.getText(), data);
        data.clear();
        imagePane.setImage(null);
        imgRoute.setText("Sin imagen");
        questionTextField.setText("");
        source.setText(local_data.toJSON());
            //Validation test for base64 image
//        encodedImage = Quiz.encodeToString(bimage, "png");
//        Image i = SwingFXUtils.toFXImage(Quiz.decodeToImage(s),null);
//        this.imagePane.setImage(i);
    }

    public void clear(){
        data.clear();
        imagePane.setImage(null);
        imgRoute.setText("Sin imagen");
        questionTextField.setText("");
//        source.setText(quiz.toJSON());
//        if(encodedImage == null){
//            encodedImage = Quiz.encodeToString(bimage, "png");
//        }
//        else {
//            Image i = SwingFXUtils.toFXImage(Quiz.decodeToImage(encodedImage), null);
//            this.imagePane.setImage(i);
//        }
    }


    public void delete(){
        int sel_idx = answerTable.getSelectionModel().getSelectedIndex();
        answerTable.getItems().remove(sel_idx);
//        source.setText(quiz.toJSON());
//        if(encodedImage == null){
//            encodedImage = Quiz.encodeToString(bimage, "png");
//        }
//        else {
//            Image i = SwingFXUtils.toFXImage(Quiz.decodeToImage(encodedImage), null);
//            this.imagePane.setImage(i);
//        }
    }

    public void save(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save JSON");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "JSON Files (*.json)", "*.json"));
        File file = fileChooser.showSaveDialog(mainApp);
        if (file != null) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(quiz.toJSON());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public void initialize(){
        this.quiz = new Quiz();
        this.local_data = new Quiz();
        this.source.getStyleClass().add("copyable-label");
        answerTable.setEditable(true);
        col_id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        col_ok.setCellValueFactory(cellData -> cellData.getValue().correctProperty().asObject());
        col_text.setCellValueFactory(cellData -> cellData.getValue().textProperty());


        col_id.setCellFactory(TextFieldTableCell.<Answer, Integer>forTableColumn(new IntegerStringConverter()));
        col_id.setOnEditCommit(
                (TableColumn.CellEditEvent<Answer, Integer> t) -> {
                    ((Answer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setId(t.getNewValue());
                });
        col_ok.setCellFactory(new Callback<TableColumn<Answer, Boolean>, TableCell<Answer, Boolean>>() {
            public TableCell<Answer, Boolean> call(TableColumn<Answer, Boolean> p) {
                return new CheckBoxTableCell<Answer, Boolean>();
            }
        });
        col_ok.setOnEditCommit(
                (TableColumn.CellEditEvent<Answer, Boolean> t) -> {
                    ((Answer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setCorrect(t.getNewValue());
                });
        col_text.setCellFactory(TextFieldTableCell.forTableColumn());
        col_text.setOnEditCommit(
                (TableColumn.CellEditEvent<Answer, String> t) -> {
                    ((Answer) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setText(t.getNewValue());
                });
        answerTable.setItems(data);





        imagePane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        imagePane.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    // Only get the first file from the list
                    final File file = db.getFiles().get(0);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(file.getAbsolutePath());
                            try {
                                Image img = new Image(new FileInputStream(file.getAbsolutePath()));
                                imgRoute.setText(file.getAbsolutePath());
                                imagePane.setImage(img);
                                bimage = ImageIO.read(file);
                            } catch (java.io.IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }
//    @FXML public void mouseDragDropped(Event e) {
//
//        System.out.println("WEAA");
//        final Dragboard db = ((DragEvent)e).getDragboard();
//        boolean success = false;
//        if (db.hasFiles()) {
//            success = true;
//            // Only get the first file from the list
//            final File file = db.getFiles().get(0);
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(file.getAbsolutePath());
//                    try {
//                        Image img = new Image(new FileInputStream(file.getAbsolutePath()));
//                        imagePane.setImage(img);
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            });
//        }
//        ((DragEvent)e).setDropCompleted(success);
//        e.consume();
//    }
}
