package phamvanthanh.example.phamvanthanh_tuan06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phamvanthanh.example.phamvanthanh_tuan06.model.Manufacturer;
import phamvanthanh.example.phamvanthanh_tuan06.repository.ManufacturerRepository;

import java.util.List;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    public List<Manufacturer> GetAll() {
        return manufacturerRepository.findAll();
    }
}
