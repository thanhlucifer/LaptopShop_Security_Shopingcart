package phamvanthanh.example.phamvanthanh_tuan06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phamvanthanh.example.phamvanthanh_tuan06.model.CartItem;
import phamvanthanh.example.phamvanthanh_tuan06.model.Laptop;
import phamvanthanh.example.phamvanthanh_tuan06.service.LaptopService;
import phamvanthanh.example.phamvanthanh_tuan06.service.ShoppingCartService;

@Controller
@RequestMapping("shoppingcart")
public class ShoppingCartController {

    @Autowired
    LaptopService laptopService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("view")
    public String viewCart(Model model){
        model.addAttribute("all_items_in_shoppingcart", shoppingCartService.getAllItems());
        model.addAttribute("total_amount", shoppingCartService.getAmount());
        return "view_shoppingcart";
    }

    @GetMapping("add/{id}")
    public String addItem(@PathVariable("id") Integer id) {
        Laptop laptop = laptopService.getProductById(id);
        if(laptop != null) {
            CartItem item = new CartItem();
            item.setProductId(laptop.getId());
            item.setName(laptop.getName());
            item.setPrice(laptop.getPrice());
            item.setQuantity(1);
            shoppingCartService.add(item);
        }
        return "redirect:/shoppingcart/view";
    }

    @GetMapping("clear")
    public String clearCart() {
        shoppingCartService.clear();
        return "redirect:/shoppingcart/view";
    }

    @GetMapping("remove/{id}")
    public String removeItem(@PathVariable("id") Integer id){
        shoppingCartService.remove(id);
        return "redirect:/shoppingcart/view";
    }

    @PostMapping("update")
    public String updateItem(@RequestParam("productId") Integer productId, @RequestParam("quantity")Integer quantity) {
        shoppingCartService.update(productId,quantity);
        return "redirect:/shoppingcart/view";
    }
}
