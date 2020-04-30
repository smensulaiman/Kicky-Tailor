package com.ekattorit.kickytailor.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {

    String productId;
    String productName;
    String productDetails;
    String productPrice;
    String productImage;
    String productColor;
    String productStock;

    public ProductModel() {
    }

    public ProductModel(String productId, String productName, String productDetails, String productPrice, String productImage, String productColor, String productStock) {
        this.productId = productId;
        this.productName = productName;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productColor = productColor;
        this.productStock = productStock;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public static Creator<ProductModel> getCREATOR() {
        return CREATOR;
    }

    protected ProductModel(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        productDetails = in.readString();
        productPrice = in.readString();
        productImage = in.readString();
        productColor = in.readString();
        productStock = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(productDetails);
        dest.writeString(productPrice);
        dest.writeString(productImage);
        dest.writeString(productColor);
        dest.writeString(productStock);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };
}
