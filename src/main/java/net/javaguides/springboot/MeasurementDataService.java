package net.javaguides.springboot.mes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementDataService {
    @Autowired
    private MeasurementDataRepository repository;

    public void saveMeasurementData(MeasurementData data) {
        repository.save(data);
    }
}
