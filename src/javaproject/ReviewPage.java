package javaproject;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class ReviewPage {

    public Scene getScene(Stage stage) {
    	  BorderPane root = new BorderPane();
          Image bgImage = new Image(getClass().getResource("/6.jpeg").toExternalForm());
          BackgroundImage bg = new BackgroundImage(bgImage,
                  BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                  BackgroundPosition.DEFAULT,
                  new BackgroundSize(100, 100, true, true, false, true));

          root.setBackground(new Background(bg));

        // --- Header ---
        VBox header = new VBox(20);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(40, 20, 20, 20));

        Text heading = new Text("Leave a Review");
        heading.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
        heading.setFill(Color.web("black"));

        Text instructions = new Text("Please share your thoughts and experiences with us!");
        instructions.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        instructions.setFill(Color.web("black"));

        header.getChildren().addAll(heading, instructions);

        // --- Review Form ---
        VBox form = new VBox(15);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(0, 20, 40, 20));

        // Bubbly Username Label & TextField
        Label usernameLabel = new Label("Enter your username");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        usernameLabel.setTextFill(Color.web("white"));
        usernameLabel.setStyle("-fx-background-color:  #013220; -fx-background-radius: 25; -fx-padding: 10 20; -fx-font-weight: bold;");
        
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle("-fx-background-radius: 5; -fx-background-color: white;");
        usernameField.setPrefWidth(300);
        usernameField.setMaxWidth(300);

        // Review Label & TextArea
        Label reviewLabel = new Label("Write your review");
        reviewLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        reviewLabel.setTextFill(Color.web("white"));
        reviewLabel.setStyle("-fx-background-color: #013220; -fx-background-radius: 25; -fx-padding: 10 20; -fx-font-weight: bold;");

        TextArea reviewField = new TextArea();
        reviewField.setPromptText("Write your review or complaint...");
        reviewField.setStyle("-fx-background-radius: 5; -fx-background-color: white;");
        reviewField.setPrefWidth(300);
        reviewField.setMaxWidth(300);
        reviewField.setPrefRowCount(5);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color:  #013220; -fx-text-fill: white; -fx-background-radius: 25;");
        submitButton.setPrefWidth(100); 
        submitButton.setPrefHeight(20);
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Thank you message after submitting the review
        Label thankYouMessage = new Label();
        thankYouMessage.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        thankYouMessage.setTextFill(Color.web("black"));
        thankYouMessage.setVisible(false); // Initially hidden

        submitButton.setOnAction(e -> {
            // Simple validation
            if (usernameField.getText().isEmpty() || reviewField.getText().isEmpty()) {
                showError("Please fill in all fields!");
            } else {
                // Simulate successful submission
                thankYouMessage.setText("Thank you for your review!");
                thankYouMessage.setVisible(true); // Show the thank you message
                usernameField.clear();
                reviewField.clear(); // Clear fields after submission
            }
        });

     // --- Back Button ---
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color:  #013220; -fx-text-fill: white; -fx-background-radius: 25;");
        backButton.setPrefWidth(80); 
        backButton.setPrefHeight(20);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14)); 
        backButton.setOnAction(e -> stage.setScene(new LandingPage().createLandingScene(stage))); // Back to landing page

        // Add elements to form
        form.getChildren().addAll(usernameLabel, usernameField, reviewLabel, reviewField, submitButton, thankYouMessage, backButton);
        VBox.setMargin(backButton, new Insets(20, 0, 0, 0)); // Optional spacing above the back button

        // Layout setup
        root.setTop(header);
        root.setCenter(form);
        return new Scene(root, 1000, 600);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}








