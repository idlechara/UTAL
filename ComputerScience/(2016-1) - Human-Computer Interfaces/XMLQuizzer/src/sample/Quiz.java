package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.ObservableList;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jvarred on 6/12/16.
 */
public class Quiz {
    class Question{
        public String text;
        public String[] answers;
        public int correct;
        public String image;
        Question(String text, ObservableList<Answer> data, String image){
            this.text = text;
            int idx = 0;
            this.answers = new String[data.size()];
            for(Answer q :data){
                if(q.getCorrect())
                    correct = idx;
                this.answers[idx] = q.getText();
                idx++;
            }
            this.image = image;
        }
    }
    ArrayList<Question> questions;

    Quiz(){
        this.questions = new ArrayList<>();
    }

    void addQuestion(String text, ObservableList<Answer> data, BufferedImage img){
        String base64img = encodeToString(img, "png");
        questions.add(new Question(text, data, base64img));
    }
    void addQuestion(String text, ObservableList<Answer> data){
        questions.add(new Question(text, data, "present"));
    }

    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, bos);
//            BASE64Encoder encoder = new BASE64Encoder();
            imageString = "data:image/png;base64,"+ DatatypeConverter.printBase64Binary(bos.toByteArray());
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static BufferedImage decodeToImage(String imageString) {
        String data = imageString.substring(imageString.indexOf(',')+1);
        byte[] bytes = DatatypeConverter.parseBase64Binary(data);

        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
            return ImageIO.read(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String toJSON(){
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(this);
        return json;
    }
}
