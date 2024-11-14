package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class JavaFXBrowser extends Application {

    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) {

        tabPane = new TabPane() ;
        // Create initial tab
        createNewTab("https://www.google.com");


        // Back Button
        Button backButton = new Button("<-");
        backButton.setOnAction(e -> {
            BrowserTab currentTab = (BrowserTab) tabPane.getSelectionModel().getSelectedItem();
            if (currentTab != null) {
                currentTab.goBack();
            }
        });

        // Forward Button
        Button forwardButton = new Button("->");
        forwardButton.setOnAction(e -> {
            BrowserTab currentTab = (BrowserTab) tabPane.getSelectionModel().getSelectedItem();
            if (currentTab != null) {
                currentTab.goForward();
            }
        });

        // New Tab Button
        Button newTabButton = new Button("New Tab");
        newTabButton.setOnAction(e -> createNewTab("https://www.google.com"));

        HBox controls = new HBox(newTabButton , backButton ,  forwardButton);
        controls.setSpacing(0);

        BorderPane root = new BorderPane();
        root.setCenter(tabPane);
        root.setTop(controls);

        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createNewTab(String url) {
        // Create a new instance of BrowserTab
        BrowserTab browserTab = new BrowserTab(url);

        // Add the tab to the TabPane and select it
        tabPane.getTabs().add(browserTab);
        tabPane.getSelectionModel().select(browserTab);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
