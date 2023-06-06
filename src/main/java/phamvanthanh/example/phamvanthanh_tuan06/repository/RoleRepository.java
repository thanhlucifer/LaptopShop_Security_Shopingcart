package phamvanthanh.example.phamvanthanh_tuan06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phamvanthanh.example.phamvanthanh_tuan06.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
