package org.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.entities.PreMadeProduct;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class AddProductController extends Controller {

    int imageAdded = 0;

    FileChooser fileChooser = new FileChooser();

    String newImagePath = null;

    @FXML
    private Button addImageBtn;

    @FXML
    private TextArea descriptionText;

    @FXML
    private ImageView mainImage;

    @FXML
    private TextField nameText;

    @FXML
    private TextField discountText;

    @FXML
    private TextField priceText;

    @FXML
    private Button saveBtn;

    Pattern pattern1 = Pattern.compile(".{0,2}");
    TextFormatter<String> formatter1 = new TextFormatter<String>(change-> {
        change.setText(change.getText().replaceAll("[^0-9]", ""));
        return pattern1.matcher(change.getControlNewText()).matches() ? change : null;
    });

    TextFormatter<String> formatter2 = new TextFormatter<String>(change-> {
        change.setText(change.getText().replaceAll("[^0-9]", ""));
        return change;
    });

    @FXML
    void addImage(ActionEvent event) throws InterruptedException {
        coolButtonClick((Button) event.getTarget());
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imageAdded++;
            newImagePath = selectedFile.getAbsolutePath();
            mainImage.setImage(new Image(newImagePath));
        }
    }

    @FXML
    void clickedAdd(ActionEvent event) throws InterruptedException {
        coolButtonClick((Button) event.getTarget());

        if(alertMsg("Add Product","add a product!" , isProductInvalid())) {
            addProduct();
            globalSkeleton.changeCenter("EditCatalog");
        }

    }

    private boolean isProductInvalid() {
        if(nameText.getText().isEmpty() || priceText.getText().isEmpty() ||
                discountText.getText().isEmpty() || descriptionText.getText().isEmpty() || imageAdded == 0)
            return true;
        if(nameText.getText().matches ("^[a-zA-Z0-9_ ]*$")  && priceText.getText().matches("^[0-9]*$") &&
                discountText.getText().matches("^[0-9]*$"))
            return false;
        return true;
    }

    private void addProduct() { //create a new product with information from worker, then save on DB
        String add = "#ADD";
        LinkedList<Object> msg = new LinkedList<Object>();  //msg has string message with all data in next nodes
        PreMadeProduct p = new PreMadeProduct(this.nameText.getText(), newImagePath, Integer.parseInt(this.priceText.getText()),
        descriptionText.getText(),Integer.parseInt(this.priceBeforeDiscountText.getText()),false);
        msg.add(add);          // adds #ADD command for server
        msg.add(p);             //adds data to msg list
        App.client.setController(this);
        try {
            App.client.sendToServer(msg); //Sends a msg contains the command and the controller for the catalog.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addImageBtn != null : "fx:id=\"addImageBtn\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'AddProduct.fxml'.";
        this.discountText.setTextFormatter(formatter1);
        assert discountText != null : "fx:id=\"discountText\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert mainImage != null : "fx:id=\"mainImage\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'AddProduct.fxml'.";
        this.priceText.setTextFormatter(formatter2);
        assert priceText != null : "fx:id=\"priceText\" was not injected: check your FXML file 'AddProduct.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'AddProduct.fxml'.";

    }

}
