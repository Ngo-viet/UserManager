package com.demo.shop.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@RequiredArgsConstructor
public class Product extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column(name = "product_title", length = 500, nullable = false)
    private String product_title;

    @Column(name = "product_price", precision = 13, scale = 2, nullable = false)
    private BigDecimal product_price;

    @Column(name = "product_description", length = 3000, nullable = false)
    private String product_shortDes;

    @Column(name = "product_details", length = 500, nullable = false)
    private String product_shortDetails;

    @Column(name = "product_image", length = 500, nullable = false)
    private String product_image;

    @Column(name = "product_code", length = 500, nullable = false)
    private int product_code;

    @Column(name = "product_sold",  nullable = false)
    private int product_sold;

    @Column(name = "product_best_seller",  nullable = false)
    private int product_best_seller;

    @Column(name = "product_discount", precision = 13, scale = 2, nullable = false)
    private BigDecimal product_discount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="tbl_product_size",
            joinColumns = @JoinColumn(name="product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "size_id", referencedColumnName = "id")
    )
    private Set<Size> sizes = new HashSet<>();


    public long getId() {
        return id;
    }

    public int getProduct_code() {
        return product_code;
    }

    public String getProduct_title() {
        return product_title;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public String getProduct_shortDes() {
        return product_shortDes;
    }

    public String getProduct_shortDetails() {
        return product_shortDetails;
    }

    public String getProduct_image() {
        return product_image;
    }

    public int getProduct_sold() {
        return product_sold;
    }

    public int getProduct_best_seller() {
        return product_best_seller;
    }

    public BigDecimal getProduct_discount() {
        return product_discount;
    }

    public Category getCategory() {
        return category;
    }

    public Brand getBrand() {
        return brand;
    }

    public List<Image> getImages() {
        return images;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProduct_code(int product_code) {
        this.product_code = product_code;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public void setProduct_shortDes(String product_shortDes) {
        this.product_shortDes = product_shortDes;
    }

    public void setProduct_shortDetails(String product_shortDetails) {
        this.product_shortDetails = product_shortDetails;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public void setProduct_sold(int product_sold) {
        this.product_sold = product_sold;
    }

    public void setProduct_best_seller(int product_best_seller) {
        this.product_best_seller = product_best_seller;
    }

    public void setProduct_discount(BigDecimal product_discount) {
        this.product_discount = product_discount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }


}
