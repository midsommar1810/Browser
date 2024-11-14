package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class JavaFXBrowser extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set up WebView and WebEngine
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Set up the address bar
        TextField addressBar = new TextField("https://");
        addressBar.setOnAction(e -> {
            String url = addressBar.getText();
            if (!url.startsWith("http")) {
                url = "https://" + url;  // Ensure URL has a protocol
            }
            loadPage(webEngine, url);
        });

        // Load initial page
        loadPage(webEngine, "https://www.google.com");

        // Add toolbar and WebView to the layout
        ToolBar toolBar = new ToolBar(addressBar);
        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(webView);

        // Set up the scene and stage
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("JavaFX Web Browser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadPage(WebEngine webEngine, String url) {
        try {
            webEngine.load(url);
        } catch (Exception e) {
            // Show an error dialog if the page fails to load
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load the page");
            alert.setContentText("Please check the URL and your internet connection.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
