package javaproject;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LoginPage {

    private boolean isLoginMode = true; // toggle flag

    public Scene getScene(Stage stage) {
        BorderPane root = new BorderPane();
        Image bgImage = new Image(getClass().getResource("/4.jpeg").toExternalForm());
        BackgroundImage bg = new BackgroundImage(bgImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, false, true));

        root.setBackground(new Background(bg));

        VBox container = new VBox(20);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(40));

        // Heading
        Text heading = new Text("Welcome to Veritas Books");
        heading.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        heading.setFill(Color.web("white"));

        // Dynamic subheading
        Label subheading = new Label("Login to continue");
        subheading.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        subheading.setTextFill(Color.web("white"));

        // Username
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        usernameLabel.setTextFill(Color.web("white"));
        usernameLabel.setStyle("-fx-background-color: #013220; -fx-background-radius: 20; -fx-padding: 10 20;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle("-fx-background-radius: 5; -fx-background-color: white;");
        usernameField.setPrefWidth(300);
        usernameField.setMaxWidth(300);
        
        // Password
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        passwordLabel.setTextFill(Color.web("white"));
        passwordLabel.setStyle("-fx-background-color: #013220; -fx-background-radius: 20; -fx-padding: 10 20;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setStyle("-fx-background-radius: 5; -fx-background-color: white;");
        passwordField.setPrefWidth(300);
        passwordField.setMaxWidth(300);


        // Email field (only for sign-up)
        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        emailLabel.setTextFill(Color.web("white"));
        emailLabel.setStyle("-fx-background-color: #013220; -fx-background-radius: 20; -fx-padding: 10 20;");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setStyle("-fx-background-radius: 5; -fx-background-color: white;");
        emailField.setPrefWidth(300);
        emailField.setMaxWidth(300);
        
        // Email Center
        HBox emailFieldBox = new HBox(emailField);
        emailFieldBox.setAlignment(Pos.CENTER);

        VBox emailBox = new VBox(5, emailLabel, emailFieldBox);
        emailBox.setAlignment(Pos.CENTER); // center the label too
        emailBox.setVisible(false);

     
        // Action Button
        Button actionBtn = new Button("Login");
        actionBtn.setStyle("-fx-background-color: #013220; -fx-text-fill: white; -fx-background-radius: 25;");
        actionBtn.setPrefWidth(100);   // make it wider
        actionBtn.setPrefHeight(20);   // make it taller
        actionBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));  // increase text size

        Label message = new Label();
        message.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        message.setTextFill(Color.web("white"));

        // Switch link
        Hyperlink switchLink = new Hyperlink("Don't have an account? Sign up");
        switchLink.setTextFill(Color.web("white"));
        switchLink.setOnAction(e -> {
            isLoginMode = !isLoginMode;
            if (isLoginMode) {
                subheading.setText("Login to continue");
                actionBtn.setText("Login");
                switchLink.setText("Don't have an account? Sign up");
                emailBox.setVisible(false);
            } else {
                subheading.setText("Create a new account");
                actionBtn.setText("Sign Up");
                switchLink.setText("Already have an account? Login");
                emailBox.setVisible(true);
            }
            usernameField.clear();
            passwordField.clear();
            emailField.clear();
            message.setText("");
        });

        // Handle Login/Signup
        actionBtn.setOnAction(e -> {
            String user = usernameField.getText().trim();
            String pass = passwordField.getText().trim();
            String email = emailField.getText().trim();

            if (user.isEmpty() || pass.isEmpty() || (!isLoginMode && email.isEmpty())) {
                message.setText("Please fill in all fields.");
            } else {
                if (isLoginMode) {
                    boolean success = UserUtil.validateLogin(user, pass);
                    if (success) {
                        message.setText("Login successful ✨ Welcome, " + user);
                        // Proceed to the main app page here
                    } else {
                        message.setText("Invalid username or password.");
                    }
                } else {
                    boolean created = UserUtil.createUser(user, pass, email);
                    if (created) {
                        message.setText("Sign-up successful 🎉 Welcome aboard, " + user);
                    } else {
                        message.setText("Username already exists.");
                    }
                }
            }
        });


        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #013220; -fx-text-fill: white; -fx-background-radius: 25;");
        backButton.setPrefWidth(80); 
        backButton.setPrefHeight(20);   // same height
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // same font size and weight

        backButton.setOnAction(e -> stage.setScene(new LandingPage().createLandingScene(stage)));

        VBox form = new VBox(10,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                emailBox,
                actionBtn,
                switchLink,
                message
        );
        form.setAlignment(Pos.CENTER);

        container.getChildren().addAll(heading, subheading, form, backButton);
        root.setCenter(container);

        return new Scene(root, 1000, 600);
    }
}          
