package com.demo.shop.controller;

import com.demo.shop.dto.ProductForm;
import com.demo.shop.entities.*;
import com.demo.shop.repository.BrandRepository;
import com.demo.shop.repository.CategoryRepository;
import com.demo.shop.repository.ProductRepository;
import com.demo.shop.repository.SizeRepository;
import com.demo.shop.service.BrandService;
import com.demo.shop.service.CategoryService;
import com.demo.shop.service.ProductService;
import com.demo.shop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    public ProductService productService;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public SizeService sizeService;

    @Autowired
    public SizeRepository sizeRepository;

    @Autowired
    public BrandService brandService;

    @Autowired
    public BrandRepository brandRepository;

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public CategoryRepository categoryRepository;

    @GetMapping("/details/profolio")
    public String detailsProduct(Model model){
        return "common/product/porfolio_details";
    }

    @GetMapping("/admin/product")
    public String indexProduct(Model model){
        List<Product> products = productService.findAll();

        model.addAttribute("products", products);
        return "common/product/list_product";
    }

    @GetMapping("/admin/addproduct")
    public String addProduct(Model model ){


        model.addAttribute("product", new ProductForm());
        List<Size> sizes =  sizeService.findAll();
        model.addAttribute("sizes", sizes);
        List<Brand> brands =  brandService.findAll();
        model.addAttribute("brands", brands);
        List<Category> categories =  categoryService.findAll();
        model.addAttribute("categories", categories);

        model.addAttribute("productForm", new ProductForm());
        return "common/product/add_product";
    }

    @PostMapping("/admin/saveproduct")
    public String addProductByAdmin(@Valid Product product, @ModelAttribute ProductForm productForm , BindingResult result, Model model, @RequestParam("sizeIds") List<Long> sizeIds){

        if(result.hasErrors()){
            return "common/product/add_product";
        }

        product.setProduct_title(productForm.getProductName());
        product.setProduct_price(productForm.getProductPrice());
        product.setProduct_shortDes(productForm.getProductDes());
        product.setProduct_image(productForm.getProductImg());
        product.setProduct_discount(productForm.getProductDis());
        product.setProduct_shortDetails("New");
        product.setProduct_code(1);
        product.setProduct_sold(1);
        product.setProduct_best_seller(1);

        Brand brand = brandService.findById(productForm.getBrandId());
        product.setBrand(brand);

        Category category = categoryService.findById(productForm.getCategoryId());
        product.setCategory(category);

        Set<Size> sizes = sizeRepository.findAllById(sizeIds).stream().collect(Collectors.toSet());
        //Set<Brand> brands = brandRepository.findAllById(brandIds).stream().collect(Collectors.toSet());

        product.setSizes(sizes);
        //product.setBrand((Brand) brands);
        productRepository.save(product);

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/editproduct/{id}")
    public String editProduct(@PathVariable("id") long id, Model model, @ModelAttribute ProductForm productForm){
        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Khong hop le ID:" + id));
        model.addAttribute("product", product);
        List<Size> sizes = sizeService.findAll();
        model.addAttribute("sizes", sizes );
        List<Brand> brands =  brandService.findAll();
        model.addAttribute("brands", brands);
        List<Category> categories =  categoryService.findAll();
        model.addAttribute("categories", categories);

        productForm.setProductId(product.getId());
        productForm.setBrandId(product.getBrand().getId());
        productForm.setProductName(product.getProduct_title());
        productForm.setProductPrice(product.getProduct_price());
        productForm.setProductDes(product.getProduct_shortDes());
        productForm.setProductImg(product.getProduct_image());
        productForm.setProductDis(product.getProduct_discount());
        productForm.setProductDetails(product.getProduct_shortDetails());
        productForm.setProductCode(product.getProduct_code());
        productForm.setProductSold(product.getProduct_sold());
        productForm.setProductBestSell(product.getProduct_best_seller());
        productForm.setSizeIds(product.getSizes().stream().map(Size::getId).collect(Collectors.toSet()));
        productForm.setCategoryId(product.getCategory().getId());

        model.addAttribute("productForm", productForm);
        return "common/product/edit_product";
    }

    @PostMapping("/admin/updateProduct/{id}")
    public String updateProduct(@PathVariable("id") long id,@ModelAttribute("product") Product product , Model model,
                             @ModelAttribute ProductForm productForm,  BindingResult result , @RequestParam("sizeIds") List<Long> sizeIds) {
         product = productService.findById(productForm.getProductId());

        if (result.hasErrors()) {
            //product.setId(id);
            return "common/product/edit_product";
        }
        if(productForm != null){
            product.setId(productForm.getProductId());
            product.setProduct_title(productForm.getProductName());
            product.setProduct_price(productForm.getProductPrice());
            product.setProduct_shortDes(productForm.getProductDes());
            product.setProduct_image(productForm.getProductImg());
            product.setProduct_discount(productForm.getProductDis());
            product.setProduct_shortDetails("New bb");
            product.setProduct_code(20);
            product.setProduct_sold(2);
            product.setProduct_best_seller(2);
        }


        /*product.setProduct_title(product.getProduct_title());
        product.setProduct_price(product.getProduct_price());
        product.setProduct_shortDes(product.getProduct_shortDes());
        product.setProduct_image(product.getProduct_image());
        product.setProduct_discount(product.getProduct_discount());
        product.setProduct_shortDetails("New bb");
        product.setProduct_code(20);
        product.setProduct_sold(2);
        product.setProduct_best_seller(2);*/


        product.getSizes().clear();
        Set<Size> sizes = sizeRepository.findAllById(productForm.getSizeIds()).stream().collect(Collectors.toSet());
        product.setSizes(sizes);

        Brand brand = brandService.findById(productForm.getBrandId());
        product.setBrand(brand);

        Category category = categoryService.findById(productForm.getCategoryId());
        product.setCategory(category);

        //productService.update(id, product);
        productRepository.save(product);
        return "redirect:common/product/list_product";
    }
    @GetMapping ("/admin/deleteproduct/{id}")
    public String deleleProduct(@PathVariable("id") long id){


        productService.delete(id);
        return "redirect:/admin/product";
    }
}
