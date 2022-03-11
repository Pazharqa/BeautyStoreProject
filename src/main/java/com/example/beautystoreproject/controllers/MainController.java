package com.example.beautystoreproject.controllers;

import com.example.beautystoreproject.entities.Category;
import com.example.beautystoreproject.entities.Gender;
import com.example.beautystoreproject.entities.Product;
import com.example.beautystoreproject.entities.UniUser;
import com.example.beautystoreproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private GenderService genderService;

    @Autowired
    private Product emptyProduct;

    @Autowired
    private UniUserService uniUserService;

    @Autowired
    private UniUser emptyUniUser;

    @Autowired
    private HttpSession session;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("genders", genderService.getAllGenders());
        model.addAttribute("product", emptyProduct);
        model.addAttribute("currentUser", getUserData());
        return "index";
    }

    @PostMapping(value = "/add-product")
    public String addProduct(@ModelAttribute(name = "product") Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }


    @GetMapping(value = "/details/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getProductById(Model model, @PathVariable(name = "productId") Long id) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("genders", genderService.getAllGenders());
        model.addAttribute("currentUser", getUserData());
        return "/details";
    }

    @PostMapping(value = "/update-product/{productId}")
    public String updateProduct(@PathVariable(name = "productId") Long id,
                                @ModelAttribute(name = "product") Product product) {
        product.setId(id);
        productService.updateProduct(product);
        return "redirect:/";
    }

    @GetMapping(value = "/delete-product/{productId}")
    public String deleteProductById(@PathVariable(name = "productId") Long productId) {
        productService.deleteProductById(productId);
        return "redirect:/";
    }

    @GetMapping(value = "/filter-by-category/{categoryId}")
    public String allProductsByCategory(@PathVariable(name = "categoryId") Long categoryId,
                                        Model model){
    model.addAttribute("products", productService.findAllByCategoryId(categoryId));
    model.addAttribute("categories", categoryService.getAllCategories());
    model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
    model.addAttribute("genders", genderService.getAllGenders());
    model.addAttribute("product", emptyProduct);
    model.addAttribute("currentUser", getUserData());
    return "index";
    }

    @GetMapping(value = "/filter-by-subcategory/{subcategoryId}")
    public String allProductsBySubcategory(@PathVariable(name = "subcategoryId") Long subcategoryId,
                                        Model model){
        model.addAttribute("products", productService.findAllBySubcategoryId(subcategoryId));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("genders", genderService.getAllGenders());
        model.addAttribute("product", emptyProduct);
        model.addAttribute("currentUser", getUserData());
        return "index";
    }
    @GetMapping(value = "/filter-by-gender/{genderId}")
    public String allProductsByGender(@PathVariable(name = "genderId") Long genderId,
                                        Model model){
        model.addAttribute("products", productService.findAllByGenderId(genderId));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("genders", genderService.getAllGenders());
        model.addAttribute("product", emptyProduct);
        model.addAttribute("currentUser", getUserData());
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "/login";
    }

    @GetMapping(value = "/403")
    public String accessDenied(){
        return "/403";
    }

    public UniUser getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User) authentication.getPrincipal();
            UniUser uniUser = uniUserService.getUserByEmail(secUser.getUsername());
            return uniUser;
        }
        return null;
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("uniUser", emptyUniUser);
        return "/register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute(name = "uniUser") UniUser newUser,
                           @RequestParam(name = "uni_user_repassword") String repass) {

        if (newUser.getPassword().equals(repass)) {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            UniUser user = uniUserService.createUser(newUser);

            if (user != null) {
                return "redirect:/login?successRegister";
            }
            return "redirect:/register?errorEmail";
        }
        return "redirect:/register?errorPass";
    }

    @PostMapping(value = "/add-to-cart/{productId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addToCart(Model model, @PathVariable(name = "productId") Long id) {
        Product product = productService.getProductById(id);
        if (session.getAttribute("cart") == null){
            List<Product> cart = new ArrayList<>();
            cart.add(product);
            session.setAttribute("cart", cart);
        } else {
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            for (Product p : cart){
                if (p.getId() == product.getId()){
                    break;
                }
            }
            cart.add(product);
            session.setAttribute("cart", cart);
        }
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("genders", genderService.getAllGenders());
        model.addAttribute("currentUser", getUserData());
        return "/index";
    }

    @GetMapping(value = "/cart")
    public  String getCart(Model model){
        model.addAttribute("cart", session.getAttribute("cart"));
        return "cart";
    }
}
