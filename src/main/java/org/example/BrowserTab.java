package org.example;

import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class BrowserTab extends Tab {

    private final WebView webView;
    private final WebEngine webEngine;

    public BrowserTab(String initialUrl) {
        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.load(initialUrl);

        setContent(webView);
        setText("New Tab");

        // Update the tab title based on the page title
        webEngine.titleProperty().addListener((obs, oldTitle, newTitle) -> {
            setText(newTitle != null ? newTitle : "New Tab");
        });

        // Release resources when the tab is closed
        setOnClosed(e -> webEngine.load(null));
    }

    // Method to go back in history
    public void goBack() {
        WebHistory history = webEngine.getHistory();
        if (history.getCurrentIndex() > 0) {
            history.go(-1); // Go to the previous page
        }
    }

    // Method to go forward in history
    public void goForward() {
        WebHistory history = webEngine.getHistory();
        if (history.getCurrentIndex() < history.getEntries().size() - 1) {
            history.go(1); // Go to the next page
        }
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }
}

