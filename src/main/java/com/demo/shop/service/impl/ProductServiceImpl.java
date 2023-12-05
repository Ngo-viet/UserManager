package com.demo.shop.service.impl;

import com.demo.shop.entities.Product;
import com.demo.shop.entities.User;
import com.demo.shop.repository.ProductRepository;
import com.demo.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository productRepository;


    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product update(Long id, Product product) {
        Product pro = productRepository.findById(id).orElse(null);
        if (pro == null) {
            return null;
        }
        pro.setProduct_title(product.getProduct_title());
        pro.setProduct_shortDetails(product.getProduct_shortDetails());
        pro.setProduct_price(product.getProduct_price());
        pro.setProduct_shortDes(product.getProduct_shortDes());
        pro.setProduct_discount(product.getProduct_discount());
        pro.setProduct_sold(product.getProduct_sold());
        pro.setProduct_code(product.getProduct_code());
        pro.setProduct_best_seller(product.getProduct_best_seller());
        pro.setProduct_image(product.getProduct_image());
        pro.setBrand(product.getBrand());
        pro.setSizes(product.getSizes());
        pro.setCategory(product.getCategory());

        return productRepository.save(pro);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không hợp lệ:" + id));

        // Xóa liên kết với các vai trò trước
        product.getSizes().clear();
        productRepository.save(product);

        // Sau đó xóa người dùng
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
