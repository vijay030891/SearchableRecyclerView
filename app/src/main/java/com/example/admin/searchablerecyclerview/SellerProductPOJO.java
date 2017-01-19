package com.example.admin.searchablerecyclerview;

/**
 * Created by User on 07/04/2016.
 */
public class SellerProductPOJO {

    String ProductName;
    String Description;
    String Discount;
    String Size;
    String Colour;
    String AvailableQuantity;
    String ImagePath;
    String isFav = "N";
    String ProductCode;
    String ProductId;
    String qty = "0";
    double Price;

    public String getDiscountedPrice() {
        return DiscountedPrice+"";
    }

    public void setDiscountedPrice(String discountedPrice) {
        DiscountedPrice = discountedPrice;
    }

    String DiscountedPrice;

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    String Unit;
    String DiscountType = "";

    public String getDiscountType() {
        return DiscountType+"";
    }

    public void setDiscountType(String discountType) {
        DiscountType = discountType;
    }

    public String getDiscountAmount() {
        return DiscountAmount+"";
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    public String getDiscountPercentage() {
        return DiscountPercentage+"";
    }

    public void setDiscountPercentage(double discountPercentage) {
        DiscountPercentage = DiscountPercentage;
    }

    double DiscountAmount = 0;
    double DiscountPercentage= 0;


    public String getIsFav() {
        return isFav;
    }

    public void setIsFav(String isFav) {
        this.isFav = isFav;
    }


    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }


    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }


    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }


    public String getPrice() {
        return Price+"";
    }

    public void setPrice(double price) {
        Price = price;
    }


    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }

    public String getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }


}
