package net.javaguides.springboot.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/add")
    public String addProduct(
        @ModelAttribute Product product,
        @RequestParam("imageFile") MultipartFile imageFile
    ) {
        if (!imageFile.isEmpty()) {
            try {
                product.setImage(imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(p -> model.addAttribute("product", p));
        return "editproduct";
    }
    @GetMapping("/{id}/image")
    public String viewProductImage(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "viewproductimage"; // The name of your HTML template file.
        } else {
            // Handle the case where the product with the given ID is not found.
            return "redirect:/products";
        }
    }


    @PostMapping("/edit/{id}")
    public String editProduct(
        @PathVariable Long id,
        @ModelAttribute Product updatedProduct,
        @RequestParam("imageFile") MultipartFile imageFile
    ) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());

            if (!imageFile.isEmpty()) {
                try {
                    existingProduct.setImage(imageFile.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            productRepository.save(existingProduct);
        }

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}
