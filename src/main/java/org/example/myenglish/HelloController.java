package org.example.myenglish;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private Dictionary dictionary = new Dictionary();

    @FXML
    private Button addWord;
    @FXML
    private Label notification;
    @FXML
    private TextField meaning;

    @FXML
    private TextField nameWord;

    @FXML
    private Button play;

    @FXML
    private TextField pronunciation;
    @FXML
    private Button buttonSearch;
    @FXML
    private Label showResult;
    @FXML
    private TextField enterSearch;
    @FXML
    private TextField type;

    @FXML
    void action(ActionEvent event) throws IOException {
        if (event.getSource() == addWord) {
            if (!dictionary.addWord(nameWord.getText(), pronunciation.getText(), type.getText(), meaning.getText())) {
                notification.setText("Adding word fail !");
            } else {
                notification.setText("Adding word succeed !");
                nameWord.clear();
                pronunciation.clear();
                type.clear();
                meaning.clear();
            }
            notification.setVisible(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(a -> notification.setVisible(false));
            delay.play();
        } else if (event.getSource() == play) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("learning-location.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) play.getScene().getWindow();
            stage.setTitle("Dương's App");
            stage.setScene(new Scene(root));
        } else if (event.getSource() == buttonSearch) {
            setShowResult();
        }
    }

    @FXML
    void actionKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            setShowResult();
        }


    }

    public void setShowResult() {
        String search=enterSearch.getText().toLowerCase();
        Word word = dictionary.getDictWord().get(search);
        if(word!=null){
        showResult.setText("/ "+word.getWord() + " /- "
                + word.getPronunciation() + " -/ "
                + word.getType() + " /-/ "
                + word.getMeaning()+" /");}
        else {
            showResult.setText("No result ");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notification.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
    }
}
