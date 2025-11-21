package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Car;
import cz.uhk.kppro.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

}