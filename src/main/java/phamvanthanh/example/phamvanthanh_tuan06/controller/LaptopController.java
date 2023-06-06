package phamvanthanh.example.phamvanthanh_tuan06.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import phamvanthanh.example.phamvanthanh_tuan06.model.Laptop;
import phamvanthanh.example.phamvanthanh_tuan06.service.LaptopService;
import phamvanthanh.example.phamvanthanh_tuan06.service.ManufacturerService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/products")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("")
    public String index(Model model)
    {
        model.addAttribute("listlaptop", laptopService.GetAll());
        return "products/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model)
    {
        model.addAttribute("product", laptopService.get(id));
        model.addAttribute("listCategory", manufacturerService.GetAll() );
        return "/products/edit";
    }
    @PostMapping("/edit")
    public String edit(@Valid Laptop editlaptop,
                       @RequestParam MultipartFile imageProduct,
                       BindingResult result,
                       Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("product", editlaptop);
            model.addAttribute("listManu", manufacturerService.GetAll() );
            return "/products/edit";
        }
        if(imageProduct != null && imageProduct.getSize() > 0)
        {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + editlaptop.getImage());
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        laptopService.edit(editlaptop);
        return "redirect:/products";
    }
}
