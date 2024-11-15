package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class JavaFXBrowser extends Application {

    private TabPane tabPane;
    private Tab historyTab;

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

        // Key event to toggle history tab on Ctrl+H
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN).match(event)) {
                toggleHistoryTab();
                event.consume();
            }
        });



        primaryStage.show();
    }

    private void createNewTab(String url) {
        // Create a new instance of BrowserTab
        BrowserTab browserTab = new BrowserTab(url);

        // Add the tab to the TabPane and select it
        tabPane.getTabs().add(browserTab);
        tabPane.getSelectionModel().select(browserTab);
    }

    private void toggleHistoryTab() {
        // If the history tab already exists, remove it
        if (historyTab != null && tabPane.getTabs().contains(historyTab)) {
            tabPane.getTabs().remove(historyTab);
            historyTab = null;
        } else {
            // Create a new history tab and populate it with the current tab's history
            historyTab = new Tab("History");
            ListView<String> historyListView = new ListView<>();

            BrowserTab currentTab = (BrowserTab) tabPane.getSelectionModel().getSelectedItem();
            if (currentTab != null) {
                historyListView.getItems().addAll(currentTab.getHistoryEntries());
            }

            historyTab.setContent(historyListView);
            tabPane.getTabs().add(historyTab);
            tabPane.getSelectionModel().select(historyTab);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
