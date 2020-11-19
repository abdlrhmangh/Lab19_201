package sample;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Main extends Application {


    Media media;
    MediaPlayer mediaPlayer;
    //declaring the variables in the class so it doesn't need to be final

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        media = new Media("https://liveexample-ppe.pearsoncmg.com/common/sample.mp4");//media is where we are going to save the url
         mediaPlayer = new MediaPlayer(media);//media player controls the media
        MediaView mediaView = new MediaView(mediaPlayer);//media view shows the videos



       mediaView.setFitHeight(450);
       mediaView.setFitWidth(500);

        Button playButton = new Button("Play");
        Button pauseButton = new Button("Pause");
        Button rewindButton = new Button("Rewind");//Rewind button will restart the video
        Button stopButton = new Button("Stop");//Stop button will stop the video and when the user hits play it will play from the beginning



        playButton.setOnAction(e -> mediaPlayer.play());
        pauseButton.setOnAction(e -> mediaPlayer.pause());
        rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));//playing the video from the beginning
        stopButton.setOnAction(e -> mediaPlayer.stop());




        Slider slVolume = new Slider();//Slider to control the volume
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(
                slVolume.valueProperty().divide(100));



        HBox BottomHBox = new HBox(10);
        //the bottom Hbox that will contain 6 elements which are : Play button, pause Button, rewind Button, stop Button,
        //volume label , volume slider


        BottomHBox.setAlignment(Pos.CENTER);
        BottomHBox.getChildren().addAll(playButton, pauseButton, rewindButton,stopButton,
                new Label("Volume"), slVolume);

//        BottomHBox.setSpacing(5);


        HBox TopHBox = new HBox(10);
        TopHBox.setAlignment(Pos.CENTER);
        TextField tf = new TextField();
        Label fileText = new Label("File URL :");
        tf.setMaxWidth(500);

        TopHBox.getChildren().addAll(fileText, tf); // assigning the label and the textfile so the user can change the video
        //by entering url


        tf.setOnAction(e -> {
            //when the user enters a url in the text field the next lines occur
            mediaPlayer.stop();//the media that is showing will stop
           mediaPlayer  = new MediaPlayer(new Media(tf.getText()));//a new value to the mediaPlayer will be assign which we
            //will bring from the textfield (the url that the user entered)
            mediaView.setMediaPlayer(mediaPlayer);
            //assigning the value of mediaPlayer to mediaView
        });


        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(BottomHBox);
        pane.setTop(TopHBox);




        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 650, 500);
        primaryStage.setTitle("MediaDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }




    public static void main(String[] args) {
        launch(args);
    }
}