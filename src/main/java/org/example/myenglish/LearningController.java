package org.example.myenglish;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LearningController implements Initializable {
    private LearningApp learningApp = new LearningApp();
    private String secretWord = null;
    ObservableList<Word> bookList;

    @FXML
    private TableColumn<Word, String> colMeaning;

    @FXML
    private TableColumn<Word, String> colMistake;

    @FXML
    private TableColumn<Word, String> colPronun;
    @FXML
    private AnchorPane showMistake;
    @FXML
    private TableColumn<Word, String> colType;

    @FXML
    private TableColumn<Word, String> colWord;
    @FXML
    private TableView<Word> tableMistake;
    @FXML
    private Button enterConfirm;
    @FXML
    private Button exitTable;
    @FXML
    private TextField enterWord;
    @FXML
    private Button enterStop;
    @FXML
    private Label randomKey;
    @FXML
    private Button enterExit;
    @FXML
    private Label notification;

    @FXML
    void action(ActionEvent event) throws IOException {
        if (event.getSource() == enterExit) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) enterExit.getScene().getWindow();
            stage.setTitle("Hello!");
            stage.setScene(new Scene(root));
        } else if (event.getSource() == enterConfirm) {
            setNextRound();
        } else if (event.getSource() == exitTable) {
            showMistake.setVisible(false);
        } else if (event.getSource()==enterStop) {
            showTable();
        }

    }

    @FXML
    void actionKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            setNextRound();
        }
    }

    public void setNextRound() {
        if (learningApp.enterWord(secretWord, enterWord.getText())) {
            secretWord = learningApp.wordProvider();
            if(secretWord==null){
                notification.setText("Game over! Please exit !");
                notification.setVisible(true);
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(a -> notification.setVisible(false));
                delay.play();
                return;
            }
            enterWord.clear();
            randomKey.setText(secretWord);
        } else {
            notification.setText("Please enter again!");
            notification.setVisible(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(a -> notification.setVisible(false));
            delay.play();
        }
    }

    public void showTable() {

        showMistake.setVisible(true);
        bookList = FXCollections.observableArrayList(learningApp.getMistakeMemory());
        colWord.setCellValueFactory(new PropertyValueFactory<>("word"));
        colPronun.setCellValueFactory(new PropertyValueFactory<>("pronunciation"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colMeaning.setCellValueFactory(new PropertyValueFactory<>("meaning"));
        colMistake.setCellValueFactory(new PropertyValueFactory<>("mistakeMeaning"));
        tableMistake.setItems(bookList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        secretWord = learningApp.wordProvider();
        randomKey.setText(secretWord);

    }

}
