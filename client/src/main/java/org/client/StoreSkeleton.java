/**
 * Sample Skeleton for 'First catalog try.fxml' Controller Class
 */

package org.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.entities.Guest;
import org.entities.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StoreSkeleton {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="catalogBtn"
    private Button catalogBtn; // Value injected by FXMLLoader

    @FXML // fx:id="mainScreen"
    private BorderPane mainScreen; // Value injected by FXMLLoader

    @FXML // fx:id="cartBtn"
    private Button cartBtn; // Value injected by FXMLLoader

    @FXML
    void MoveToCatalog(ActionEvent event) {
        changeCenter("Catalog");
    }

    /**
     * Changed to public from private, returns Controller and set Skeleton to it.
     */
    public Controller changeCenter(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name + ".fxml"));
        mainScreen.getChildren().remove(mainScreen.getCenter()); //remove existing fxml from center.
        Controller controller = null;
        try {
            mainScreen.setCenter(fxmlLoader.load());
            controller = fxmlLoader.getController();

            controller.setSkeleton(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

    private void changeLeft(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name + ".fxml"));
        mainScreen.getChildren().remove(mainScreen.getLeft()); //remove existing fxml from left.
        Controller controller = null;
        try {
            mainScreen.setLeft(fxmlLoader.load());
            controller = fxmlLoader.getController();
            controller.setSkeleton(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert catalogBtn != null : "fx:id=\"catalog\" was not injected: check your FXML file 'First catalog try.fxml'.";
        assert mainScreen != null : "fx:id=\"mainScreen\" was not injected: check your FXML file 'First catalog try.fxml'.";
        assert cartBtn != null : "fx:id=\"openImage\" was not injected: check your FXML file 'First catalog try.fxml'.";

        changeCenter("Catalog");
        changeLeft("WorkerMenu");
    }

}
