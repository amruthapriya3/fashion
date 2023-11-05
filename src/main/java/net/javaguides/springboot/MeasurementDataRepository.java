package net.javaguides.springboot.mes;

import org.springframework.data.jpa.repository.JpaRepository;



public interface MeasurementDataRepository extends JpaRepository<MeasurementData, Long> {
}
