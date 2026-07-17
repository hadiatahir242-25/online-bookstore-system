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

public class CatalogPage {

    public Scene getScene(Stage stage) {
    	
    	// Top bar
    	HBox topBar = new HBox(20);
    	topBar.setAlignment(Pos.CENTER);
    	topBar.setPadding(new Insets(20, 30, 20, 30)); // top, right, bottom, left


    	Text heading = new Text("📚 Book Catalog");
    	heading.setFont(Font.font("Georgia", FontWeight.BOLD, 40));
    	heading.setFill(Color.web("white"));
    	Region spacer = new Region();
    	HBox.setHgrow(spacer, Priority.ALWAYS); // Pushes the buttons to the right

    	Button cartBtn = new Button("🛒 View Cart");
    	cartBtn.setStyle("-fx-background-color:  white; -fx-text-fill: #013220; -fx-background-radius: 20;");
    	cartBtn.setOnAction(e -> stage.setScene(new CartPage().getScene(stage)));

    	Button backBtn = new Button("🔙 Back");
        backBtn.setStyle("-fx-background-color:  white; -fx-text-fill: #013220; -fx-background-radius: 20;");
        backBtn.setPrefWidth(80); 
        backBtn.setPrefHeight(20);
        backBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 14)); 
        backBtn.setOnAction(e -> stage.setScene(new LandingPage().createLandingScene(stage)));

    	topBar.getChildren().addAll(heading, spacer, cartBtn,backBtn);


        // Category Panel
        VBox categories = new VBox(15);
        categories.setPadding(new Insets(20));
        categories.setStyle("-fx-background-color: white;");
        categories.setPrefWidth(150);

        Label catTitle = new Label("Categories");
        catTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        catTitle.setTextFill(Color.web("black"));
        categories.getChildren().add(catTitle);

        // Book Data
        Object[][] books = {
        		 {"The Summer I Turned Pretty", "Jenny Han", "Romance", "$12.99", "https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781416968290/the-summer-i-turned-pretty-9781416968290_hr.jpg"},
                 {"Ugly Love", "Colleen Hoover", "Romance", "$10.99", "https://d28hgpri8am2if.cloudfront.net/tagged_assets/cvr9781476753188/9781476753188_hr.jpg"},
                 {"13 Reasons Why", "Jay Asher", "Fiction", "$5.50", "https://cdn2.penguin.com.au/covers/original/9780141387772.jpg"},
                 {"Verity", "Colleen Hoover", "Thriller", "$10.49", "https://tse1.mm.bing.net/th?id=OIP.5ZVxkcbMaIXfGY_zgFd3HgHaLc&pid=Api&P=0&h=220"},
                 {"Milk and Honey", "Rupi Kaur", "Poetry", "$8.99", "https://prodimage.images-bn.com/pimages/9781524892876_p0_v6_s600x595.jpg"},
                 {"It Ends with Us", "Colleen Hoover", "Romance", "$11.99", "https://images-na.ssl-images-amazon.com/images/I/81s0B6NYXML.jpg"},
                 {"I Who Have Never Known Men", "Jacqueline Harpman", "Fantasy", "$13.49", "https://prodimage.images-bn.com/pimages/9781945492600_p0_v1_s600x595.jpg"},
                 {"Pillow Thoughts", "Courtney Peppernell", "Poetry", "$7.00", "https://tse2.mm.bing.net/th?id=OIP.Q1ErjfWQQzqHRFCWWHUaOgHaLh&pid=Api&P=0&h=220"},
                 {"The Subtle Art of Not Giving a F*ck", "Mark Manson", "Self Love", "$9.99", "https://images-na.ssl-images-amazon.com/images/I/71QKQ9mwV7L.jpg"},
                 {"My Dress-Up Darling 13", "Shinichi Fukuda", "Manga", "$10.99", "https://prodimage.images-bn.com/pimages/9781646093656_p0_v2_s600x595.jpg"},
                 {"Fearless", "Lauren Roberts", "Fiction", "$4.99", "https://prodimage.images-bn.com/pimages/9781665977821_p0_v3_s600x595.jpg"},
                 {"Watch Me", "Custom", "Journals", "$6.99", "https://prodimage.images-bn.com/pimages/9780063425187_p0_v17_s600x595.jpg"},
                 {"Say You'll Remember Me", "Abby Jimenez", "Romance", "$12.99", "https://prodimage.images-bn.com/pimages/9781538774298_p0_v5_s600x595.jpg"},
                 {"Daydream", "Hannah Grace", "Romance", "$10.99", "https://prodimage.images-bn.com/pimages/9781668026250_p0_v2_s600x595.jpg"},
                 {"Jujutsu Kaisen, Vol. 25", "Gege Akutami", "Manga", "$5.50", "https://prodimage.images-bn.com/pimages/9781974751884_p0_v3_s600x595.jpg"},
                 {"How to Solve Your Own Murder", "Kristen Perrin", "Thriller", "$10.49", "https://prodimage.images-bn.com/pimages/9780593474020_p0_v4_s600x595.jpg"},
                 {"Dog Show: Poems", "Billy Collins", "Poetry", "$8.99", "https://prodimage.images-bn.com/pimages/9780593979419_p0_v2_s600x595.jpg"},
                 {"Jujutsu Kaisen, Vol. 4", "Gege Akutami", "Manga", "$11.99", "https://prodimage.images-bn.com/pimages/9781974714803_p0_v2_s600x595.jpg"},
                 {"Alchemised", "SenLinYu", "Fantasy", "$13.49", "https://prodimage.images-bn.com/pimages/9798217095100_p0_v1_s600x595.jpg"},
                 {"The Little Frog's Guide to Self-Care", "Maybell Eequay", "Self Love", "$7.00", "https://prodimage.images-bn.com/pimages/9781837991013_p0_v11_s600x595.jpg"},
                 {"The Gifts of Imperfection", "Brene Brown", "Self Love", "$9.99", "https://www.libertybooks.com/image/cache/catalog/9781785043543-640x996.jpg?q6"},
                 {"To All The Boys I've Loved Before", "Jenny Han", "Romance", "$10.99", "https://prodimage.images-bn.com/pimages/9781442426719_p0_v7_s600x595.jpg"},
                 {"The Thursday Murder Clu", "Richard Osman", "Thriller", "$5.50", "https://prodimage.images-bn.com/pimages/9781984880987_p0_v16_s600x595.jpg"},
                 {"One Piece, Vol. 108", "Eiichiro Oda", "Manga", "$10.49", "https://prodimage.images-bn.com/pimages/9781974752225_p0_v2_s600x595.jpg"},
                 {"Film for Her", "Orion Carloto", "Poetry", "$8.99", "https://prodimage.images-bn.com/pimages/9781524853778_p0_v1_s600x595.jpg"},
                 {"The Summer of Broken Rules: A Happily Ever After Beach Read", "K. L. Walther", "Romance", "$11.99", "https://tse3.mm.bing.net/th?id=OIP.5o5OjEJ_mG2OJgW62vP4CgHaLH&pid=Api&P=0&h=220"},
                 {"A Court of Thorns and Roses", "Sarah J. Maas", "Fantasy", "$13.49", "https://production.listennotes.com/podcasts/bibliophiles/a-court-of-thorns-and-roses-IJ1xeBapLUd-VFxGLv6Uk93.1400x1400.jpg"},
                 {"You Are Your Only Limit", "Anonymous", "Self Love", "$7.00", "https://c8.alamy.com/comp/R91B5G/you-are-your-only-limit-motivational-quote-and-saying-R91B5G.jpg"},
                 {"The Path to Self-Love", "Ruby Dhal", "Self Love", "$9.99", "https://prodimage.images-bn.com/pimages/9780593796696_p0_v1_s600x595.jpg"},
                 {"Komi Can't Communicate", "Tomohito Oda", "Romance", "$10.99", "https://prodimage.images-bn.com/pimages/9781974751549_p0_v2_s600x595.jpg"}
        };

        VBox centerContent = new VBox(15);
        centerContent.setPadding(new Insets(20));

        // Search Bar
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        TextField searchField = new TextField();
        searchField.setPromptText("Search by title, author, genre or price");
        searchField.setPrefWidth(400);
        searchField.setStyle("-fx-background-radius: 25; -fx-background-color: white;");


        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-background-color: white; -fx-text-fill: #013220;");
        searchBox.getChildren().addAll(searchField, searchBtn);

        // Book Grid inside ScrollPane
        GridPane bookGrid = new GridPane();
        bookGrid.setPadding(new Insets(10));
        bookGrid.setHgap(15);
        bookGrid.setVgap(20);

        ScrollPane scrollPane = new ScrollPane(bookGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setPrefHeight(550);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Book Filter Function
        javafx.util.Callback<String, Void> populateBooks = (filterGenre) -> {
            bookGrid.getChildren().clear();
            int col = 0, row = 0;
            String keyword = searchField.getText().toLowerCase();
            for (Object[] book : books) {
                String titleStr = ((String) book[0]).toLowerCase();
                String authorStr = ((String) book[1]).toLowerCase();
                String genreStr = ((String) book[2]).toLowerCase();
                String priceStr = ((String) book[3]).toLowerCase();

                boolean matchesSearch = keyword.isEmpty() || titleStr.contains(keyword) || authorStr.contains(keyword) || genreStr.contains(keyword) || priceStr.contains(keyword);
                boolean matchesGenre = filterGenre.equals("all") || genreStr.equals(filterGenre);

                if (matchesSearch && matchesGenre) {
                    VBox card = createBookCard((String) book[0], (String) book[3], (String) book[4]);
                    bookGrid.add(card, col++, row);
                    if (col == 10) {
                        col = 0;
                        row++;
                    }
                }
            }
            return null;
        };

        // Add category buttons
        String[] genres = {"All", "Romance", "Poetry", "Fantasy", "Self Love", "Fiction", "Manga"};
        for (String cat : genres) {
            Button catBtn = new Button(cat);
            catBtn.setMaxWidth(Double.MAX_VALUE);
            catBtn.setStyle("-fx-background-color: #013220; -fx-text-fill: white;");
            catBtn.setOnAction(e -> populateBooks.call(cat.toLowerCase()));
            categories.getChildren().add(catBtn);
        }

        searchBtn.setOnAction(e -> populateBooks.call("all"));
        populateBooks.call("all");

        centerContent.getChildren().addAll(searchBox, scrollPane);

       
     // Full Layout
        BorderPane layout = new BorderPane();
        layout.setTop(topBar);
        layout.setLeft(categories);
        layout.setCenter(centerContent);
        layout.setStyle("-fx-background-color: #013220;"); 

        Scene scene = new Scene(layout, 1000, 600);
        return scene;

    }

    private VBox createBookCard(String title, String price, String imagePath) {
        VBox bookCard = new VBox(10);
        bookCard.setAlignment(Pos.CENTER);
        bookCard.setStyle("-fx-background-color: #013220; -fx-padding: 10; -fx-border-radius: 10; -fx-background-radius: 10;");

        ImageView bookImg = new ImageView();
        bookImg.setFitHeight(120);
        bookImg.setFitWidth(90);
        try {
            bookImg.setImage(new Image(imagePath, true));
        } catch (Exception e) {}

        Label bookTitle = new Label(title);
        bookTitle.setStyle("-fx-text-fill: white;");
        bookTitle.setMaxWidth(90);
        bookTitle.setWrapText(true);

        Label bookPrice = new Label(price);
        bookPrice.setStyle("-fx-text-fill: white;");

        Button addToCart = new Button("Add to Cart");
        addToCart.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        addToCart.setOnAction(e -> {
            CartManager.addToCart(new BookItem(title, price, imagePath));
            addToCart.setText("✔ Added");
            addToCart.setDisable(true);
            addToCart.setStyle("-fx-background-color: #013220; -fx-text-fill: white;");
        });

        bookCard.getChildren().addAll(bookImg, bookTitle, bookPrice, addToCart);
        return bookCard;
    }
}