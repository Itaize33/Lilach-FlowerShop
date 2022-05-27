package org.entities;

import javafx.scene.image.Image;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

@MappedSuperclass
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;         // id generated for each product

    @Column(name = "image", length = 65555)
    private byte[] image;

    private int price;

    private int amount;

    private boolean isOrdered = false;

    public Product(String path, int price) {      //constructor

        this.price = price;
        this.amount = 0;

        File file = new File(path);         //converts string pth into bytecode image
        this.image = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(this.image);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Product(byte[] image, int price) {
        //this.name = name;
        //image=image;
        this.price = price;
        //this.priceBeforeDiscount = priceBeforeDiscount;
        this.image = image;


    }

    public Product(int price) {
        //this.name = name;
        //image=image;
        this.price = price;
        //this.priceBeforeDiscount = priceBeforeDiscount;
        //this.image = image;
    }

    public Product() {

    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Image getImage() {
        return new Image(new ByteArrayInputStream(this.image));
    }

    public byte[] getByteImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getAmount()
    {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}

