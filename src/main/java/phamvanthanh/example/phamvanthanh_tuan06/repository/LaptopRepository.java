package phamvanthanh.example.phamvanthanh_tuan06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phamvanthanh.example.phamvanthanh_tuan06.model.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
