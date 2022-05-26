package org.client;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.entities.PreMadeProduct;

public class ProductToEditController extends ItemController{

    private PreMadeProduct product;

    @FXML
    private Text price;

    @FXML
    private Text priceBeforeDiscount;

    @FXML
    void goToEditProduct(MouseEvent event) throws InterruptedException {
        clickOnProductEffect(event);
        Controller controller = null;
        controller = this.getSkeleton().changeCenter("EditProduct");
        EditProductController editProduct = (EditProductController) controller;
        editProduct.setProductView(this.product);
    }

    public void setProduct(PreMadeProduct product) {
        this.product = product;
        image.setImage(product.getImage());
        price.setText(product.getPrice() + "₪");
        name.setText(product.getName());

        if(product.getPriceBeforeDiscount() != 0)
            priceBeforeDiscount.setText(product.getPriceBeforeDiscount() + " ₪");
        else
            priceBeforeDiscount.setText("");
    }
}
