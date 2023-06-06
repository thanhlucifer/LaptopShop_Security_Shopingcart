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
import java.util.UUID;

@RequestMapping("admin/products")
@Controller
public class AdminLaptopController {
    @Autowired
    private LaptopService laptopService;
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("")
    public String index(Model model)
    {
        model.addAttribute("listlaptop", laptopService.GetAll());
        return "admin/products/index";
    }
    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("laptop", new Laptop());
        model.addAttribute("listmanu", manufacturerService.GetAll() );
        return "admin/products/create";
    }
    @PostMapping("/create")
    public String create(@Valid Laptop newlaptop,
                         @RequestParam MultipartFile imageProduct,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors())
        {
            model.addAttribute("laptop", newlaptop);
            model.addAttribute("listmanu", manufacturerService.GetAll() );
            return "admin/products/create";
        }
        if(imageProduct != null && imageProduct.getSize() > 0)
        {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                String newImageFile = UUID.randomUUID() +  ".png";
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                newlaptop.setImage(newImageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        laptopService.add(newlaptop);
        return "redirect:/admin/products";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model)
    {
        model.addAttribute("laptop", laptopService.get(id));
        model.addAttribute("listmanu", manufacturerService.GetAll() );
        return "admin/products/edit";
    }
    @PostMapping("/edit")
    public String edit(@Valid Laptop editProduct,
                       @RequestParam MultipartFile imageProduct,
                       BindingResult result,
                       Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("laptop", editProduct);
            model.addAttribute("listmanu", manufacturerService.GetAll() );
            return "admin/products/edit";
        }
        if(imageProduct != null && imageProduct.getSize() > 0)
        {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + editProduct.getImage());
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        laptopService.edit(editProduct);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.laptopService.remove(id);
        return "redirect:/admin/products";
    }
}
