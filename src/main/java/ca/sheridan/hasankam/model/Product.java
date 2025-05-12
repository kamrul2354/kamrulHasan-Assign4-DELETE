/*
 * Author: Kamrul Hasan
 * Student ID: 991769795
 * Date: [27-11-2024]
 * 
 * Description:
 * The Product class represents the data model for products in the Product Catalog 
 * Management System. It includes fields for product code, brand, quantity in hand, 
 * and unit price, along with the necessary getters and setters.
 */

package ca.sheridan.hasankam.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id; // primary key
    private String productCode;
    private String productBrand;
    private int quantityInHand;
    private double unitPrice;

    // No-arg constructor
    public Product() {}

    // Getters and Setters for products
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getProductBrand() { return productBrand; }
    public void setProductBrand(String productBrand) { this.productBrand = productBrand; }
    public int getQuantityInHand() { return quantityInHand; }
    public void setQuantityInHand(int quantityInHand) { this.quantityInHand = quantityInHand; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
