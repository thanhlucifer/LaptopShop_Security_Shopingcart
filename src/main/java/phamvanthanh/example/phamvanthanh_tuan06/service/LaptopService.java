package phamvanthanh.example.phamvanthanh_tuan06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phamvanthanh.example.phamvanthanh_tuan06.model.Laptop;
import phamvanthanh.example.phamvanthanh_tuan06.repository.LaptopRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepository laptopRepository;
    public List<Laptop> GetAll() {
        return laptopRepository.findAll();
    }

    public void add(Laptop newLaptop) {
        laptopRepository.save(newLaptop);
    }

    public Laptop get(long id) {
        return laptopRepository.findById(id).stream().findFirst().orElse(null);
    }

    public Laptop getProductById(long id){
        Optional<Laptop> optional = laptopRepository.findById(id);
        Laptop laptop = null;
        if (optional.isPresent()) {
            laptop = optional.get();
        } else {
            throw  new RuntimeException("laptop not found for id :: " + id);
        } return laptop;
    }
    public void edit(Laptop editlaptop) {
        Laptop find = get(editlaptop.getId());
        if(find!=null) {
            ///or implement clon()
            find.setName(editlaptop.getName());
            find.setImage(editlaptop.getImage());
            find.setSpecs(editlaptop.getSpecs());
            find.setPrice(editlaptop.getPrice());
            find.setYear(editlaptop.getYear());
            find.setManufacturer(editlaptop.getManufacturer());
            //    productRepository.save(find);
        }
    }
    public void remove(long id) {
        laptopRepository.deleteById(id);
    }
}
