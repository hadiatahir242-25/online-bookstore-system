package javaproject;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LandingPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Veritas Books");
        primaryStage.setScene(createLandingScene(primaryStage));
        primaryStage.show();
    }

    public Scene createLandingScene(Stage stage) {
        BorderPane root = new BorderPane();
        Image bgImage = new Image(getClass().getResource("/4.jpeg").toExternalForm());
        BackgroundImage bg = new BackgroundImage(bgImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, false, true));

        root.setBackground(new Background(bg));

        // --- Top Navigation Bar ---
        HBox navBar = new HBox(20);
        navBar.setAlignment(Pos.CENTER_RIGHT);
        navBar.setPadding(new Insets(20));

        Button btnUser = new Button("👤 User Login/Sign up");
        Button btnCatalog = new Button("📚 Book Catalog");
        Button btnReview = new Button("⭐ Reviews");
        Button btnCart = new Button("🛒 Cart");
       

        Button[] buttons = {btnUser, btnCatalog, btnReview, btnCart};
        for (Button btn : buttons) {
            btn.setStyle("-fx-background-color: #013220; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 8 16;");
        }

        btnUser.setOnAction(e -> stage.setScene(new LoginPage().getScene(stage)));
        btnCatalog.setOnAction(e -> stage.setScene(new CatalogPage().getScene(stage)));
        btnReview.setOnAction(e -> stage.setScene(new ReviewPage().getScene(stage)));
        btnCart.setOnAction(e -> stage.setScene(new CartPage().getScene(stage)));

        navBar.getChildren().addAll(btnUser, btnCatalog, btnReview, btnCart);
        root.setTop(navBar);

        // --- Center Content with Title ---
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setPadding(new Insets(100));

        Text title = new Text("Lavender Pages");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 44));
        title.setFill(Color.web("white"));

        Text subtitle = new Text("an online bookstore");
        subtitle.setFont(Font.font("Georgia", FontWeight.NORMAL, 24));
        subtitle.setFill(Color.web("white"));

        TextField searchField = new TextField();
        searchField.setPromptText("What are you looking for?");
        searchField.setPrefWidth(300);
        searchField.setStyle("-fx-background-radius: 25; -fx-background-color: white;");

        Button searchBtn = new Button("➤");
        searchBtn.setStyle("-fx-background-color: #013220; -fx-text-fill:white ; -fx-background-radius:25;");

        HBox searchBox = new HBox(searchField, searchBtn);
        searchBox.setSpacing(10);
        searchBox.setAlignment(Pos.CENTER);

        centerContent.getChildren().addAll(title, subtitle, searchBox);
        root.setCenter(centerContent);

        return new Scene(root, 1000, 600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
