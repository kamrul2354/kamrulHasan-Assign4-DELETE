package ca.sheridan.hasankam.controller;

import ca.sheridan.hasankam.dao.ProductsDatabaseAccess;
import ca.sheridan.hasankam.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductsDatabaseAccess pda;

    // Login and Home
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    //logout 
    @GetMapping("/logout")
    public String logoutPage() {
        return "logout"; // Maps to the logout.html template
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Mapping for Access Denied
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    // Add Product - ADMIN only
    @GetMapping("/productDataInput")
    public String productDataInput(Model model) {
        model.addAttribute("product", new Product());
        return "AddProduct/productDataInput";
    }

    @PostMapping("/productDataInput")
    public String addProduct(Model model, @ModelAttribute Product product) {
        long numOfRows = pda.addProduct(product);
        String message = (numOfRows > 0) 
            ? "Success! The product was successfully added to the database."
            : "Failure! The product was not added to the database.";
        model.addAttribute("message", message);
        return "AddProduct/productAddConfirm";
    }

    // List Products - SALES and ADMIN
    @GetMapping("/listOfProducts")
    public String viewListOfProducts(Model model) {
        List<Product> products = pda.selectProducts();
        model.addAttribute("products", products);
        return "ListProducts/listOfProducts";
    }

//    // Edit/Delete Product - ADMIN only
//    @GetMapping("/editableListOfProducts")
//    public String editableListOfProducts(Model model) {
//        List<Product> products = pda.selectProducts();
//        model.addAttribute("products", products);
//        return "EditProduct/editableListOfProducts";
//    }
    
 // Edit Product List - ADMIN only
    @GetMapping("/listEditableProducts")
    public String listEditableProducts(Model model) {
        List<Product> products = pda.selectProducts();
        model.addAttribute("products", products);
        return "EditProduct/editableListOfProduct";
    }

    // Delete Product List - ADMIN only
    @GetMapping("/listDeletableProducts")
    public String listDeletableProducts(Model model) {
        List<Product> products = pda.selectProducts();
        model.addAttribute("products", products);
        return "DeleteProduct/listDeletableProducts";
    }
    

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        Product product = pda.getProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product not found.");
            return "error";
        }
        model.addAttribute("product", product);
        return "EditProduct/editProductData";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(Model model, @ModelAttribute Product product) {
        int numOfRows = pda.updateProduct(product);
        String message = (numOfRows > 0)
            ? "Success! The product was successfully updated."
            : "Failure! The product was not updated.";
        model.addAttribute("message", message);
        return "EditProduct/productUpdateConfirm";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, Model model) {
        int numOfRows = pda.deleteProduct(id);
        String message = (numOfRows > 0)
            ? "Success! The product was successfully deleted."
            : "Failure! The product was not deleted.";
        model.addAttribute("message", message);
        return "EditProduct/productDeleteConfirm";
    }

    // List Products by Brand - SALES and ADMIN
    @GetMapping("/productsByBrand")
    public String productsByBrandForm(Model model) {
        model.addAttribute("product", new Product());
        return "AddProduct/productsByBrand";
    }

    @PostMapping("/productsByBrand")
    public String productsByBrand(Model model, @ModelAttribute Product product) {
        List<Product> products = pda.selectProductsByBrand(product.getProductBrand());
        model.addAttribute("products", products);
        return "ListProducts/listOfProductsByBrand";
    }

    // List Products by Quantity Threshold - SALES and ADMIN
    @GetMapping("/productsByQuantity")
    public String productsByQuantityForm(Model model) {
        model.addAttribute("product", new Product());
        return "AddProduct/productsByQuantity";
    }

    @PostMapping("/productsByQuantity")
    public String productsByQuantity(Model model, @ModelAttribute Product product) {
        List<Product> products = pda.selectProductsByQuantity(product.getQuantityInHand());
        model.addAttribute("products", products);
        return "ListProducts/listOfProductsByQuantity";
    }
}
