package javaproject;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;

public class CartPage {

    public Scene getScene(Stage stage) {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color:#013220;");

        // Top Bar with Clear All and Back buttons
        HBox topBar = new HBox(10);
        topBar.setAlignment(Pos.CENTER_RIGHT);

        Button backBtn = new Button("← Back to Catalog");
        backBtn.setStyle("-fx-background-color: white; -fx-text-fill: #013220; -fx-background-radius: 25;");
        backBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 14)); 
        backBtn.setOnAction(e -> stage.setScene(new CatalogPage().getScene(stage)));

        Button clearAllBtn = new Button("🧹 Clear All");
        clearAllBtn.setStyle("-fx-background-color: white; -fx-text-fill: #013220 -fx-background-radius: 25;");
        clearAllBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Clear the entire cart?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Clear Cart");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    CartManager.cartItems.clear();
                    stage.setScene(new CartPage().getScene(stage));
                }
            });
        });

        topBar.getChildren().addAll(clearAllBtn, backBtn);

        // Title
        Text title = new Text("🛒 Your Cart");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 35));
        title.setFill(Color.web("white"));

        // Cart Items Display
        FlowPane cartPane = new FlowPane(15, 15);
        cartPane.setPadding(new Insets(10));
        cartPane.setPrefWrapLength(950);

        double total = 0;

        for (Map.Entry<BookItem, Integer> entry : CartManager.cartItems.entrySet()) {
            BookItem item = entry.getKey();
            int quantity = entry.getValue();

            VBox itemBox = new VBox(8);
            itemBox.setPadding(new Insets(10));
            itemBox.setStyle("-fx-background-color: #013220; -fx-background-radius: 10;");
            itemBox.setAlignment(Pos.CENTER);
            itemBox.setPrefWidth(120);

            ImageView img = new ImageView(new Image(item.getImageUrl(), 80, 120, true, true));
            Text titleText = new Text(item.getTitle());
            titleText.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
            titleText.setFill(Color.web("white"));

            Text priceText = new Text(item.getPrice());
            priceText.setFill(Color.web("white")); 


            // Quantity Controls
            Button addBtn = new Button("+");
            Button subBtn = new Button("-");
            Label qtyLabel = new Label(String.valueOf(quantity));
            qtyLabel.setStyle("-fx-background-color: white; -fx-border-color: white; -fx-padding: 3 8; -fx-border-radius: 5;");
            qtyLabel.setMinWidth(30);
            qtyLabel.setAlignment(Pos.CENTER);

            addBtn.setOnAction(e -> {
                CartManager.cartItems.put(item, quantity + 1);
                stage.setScene(new CartPage().getScene(stage));
            });

            subBtn.setOnAction(e -> {
                if (quantity > 1) {
                    CartManager.cartItems.put(item, quantity - 1);
                } else {
                    CartManager.cartItems.remove(item);
                }
                stage.setScene(new CartPage().getScene(stage));
            });

            HBox qtyBox = new HBox(5, subBtn, qtyLabel, addBtn);
            qtyBox.setAlignment(Pos.CENTER);

            itemBox.getChildren().addAll(img, titleText, priceText, qtyBox);
            cartPane.getChildren().add(itemBox);

            try {
                double price = Double.parseDouble(item.getPrice().replace("$", ""));
                total += price * quantity;
            } catch (NumberFormatException ignored) {}
        }

        ScrollPane scrollPane = new ScrollPane(cartPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setPrefHeight(500);

        // Bottom Bar with Total & Checkout
        HBox bottomBar = new HBox(10);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);
        bottomBar.setPadding(new Insets(10, 0, 0, 0));

        Text totalText = new Text("Total: $" + String.format("%.2f", total));
        totalText.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        totalText.setFill(Color.web("white"));

        Button checkoutBtn = new Button("✔ Checkout");
        checkoutBtn.setStyle("-fx-background-color: white; -fx-text-fill: #013220;");
        checkoutBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Proceed to checkout?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Confirm Checkout");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    CartManager.cartItems.clear();
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Checkout successful! Thank you!");
                    success.showAndWait();
                    stage.setScene(new CartPage().getScene(stage));
                }
            });
        });

        bottomBar.getChildren().addAll(totalText, checkoutBtn);

        mainLayout.getChildren().addAll(topBar, title, scrollPane, bottomBar);
        return new Scene(mainLayout, 1000, 600);
    }
}
