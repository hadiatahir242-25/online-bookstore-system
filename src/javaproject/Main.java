package javaproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private int mouseClickCount = 0;
    private int keyPressCount = 0;

    @Override
    public void start(Stage primaryStage) {
     
        Label messageLabel = new Label("Interact using mouse or keyboard.");

        StackPane root = new StackPane(messageLabel);
        root.setStyle("-fx-background-color: lightgray;");

        Scene scene = new Scene(root, 500, 300);


        scene.setOnMouseEntered(e -> {
            messageLabel.setText("Mouse entered the stage");
            root.setStyle("-fx-background-color: #d1ffd1;"); 
        });

        scene.setOnMouseExited(e -> {
            messageLabel.setText("Mouse exited the stage");
            root.setStyle("-fx-background-color: #ffd1d1;"); 
        });

   
        scene.setOnMouseClicked(e -> handleMouseClick(e, messageLabel));

        
        scene.setOnKeyPressed(e -> handleKeyPress(e, messageLabel));

        primaryStage.setTitle("JavaFX Mouse and Keyboard Events");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void handleMouseClick(MouseEvent e, Label label) {
        mouseClickCount++;
        double x = e.getSceneX();
        double y = e.getSceneY();
        label.setText(String.format("Mouse Clicked at X: %.0f, Y: %.0f | Total Clicks: %d", x, y, mouseClickCount));
        System.out.println("Mouse clicked at (" + x + ", " + y + ")");
    }

    private void handleKeyPress(KeyEvent e, Label label) {
        keyPressCount++;
        String keyType;
        e.getText();

        if (e.getCode().isLetterKey()) {
            keyType = "Letter";
        } else if (e.getCode().isDigitKey()) {
            keyType = "Number";
        } else {
            keyType = "Control";
        }

        label.setText("You pressed: " + e.getCode() + " (" + keyType + ") | Total Key Presses: " + keyPressCount);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
