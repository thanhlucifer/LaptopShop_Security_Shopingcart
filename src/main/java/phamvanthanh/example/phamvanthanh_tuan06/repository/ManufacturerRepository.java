package phamvanthanh.example.phamvanthanh_tuan06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phamvanthanh.example.phamvanthanh_tuan06.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
