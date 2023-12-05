package com.demo.shop.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProductForm {
    private Long productId;
    private String productName;
    private Long brandId;

    private BigDecimal productPrice;

    private String productDes;

    private String productImg;

    private BigDecimal productDis;

    private int productCode;

    private String productDetails;

    private int productSold;

    private int productBestSell;

    private Long categoryId;
    private Set<Long> sizeIds;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Long> getSizeIds() {
        return sizeIds;
    }

    public void setSizeIds(Set<Long> sizeIds) {
        this.sizeIds = sizeIds;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public int getProductSold() {
        return productSold;
    }

    public void setProductSold(int productSold) {
        this.productSold = productSold;
    }

    public int getProductBestSell() {
        return productBestSell;
    }

    public void setProductBestSell(int productBestSell) {
        this.productBestSell = productBestSell;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public BigDecimal getProductDis() {
        return productDis;
    }

    public void setProductDis(BigDecimal productDis) {
        this.productDis = productDis;
    }


}
