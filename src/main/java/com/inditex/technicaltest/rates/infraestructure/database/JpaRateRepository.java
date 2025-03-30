package com.inditex.technicaltest.rates.infraestructure.database;

import com.inditex.technicaltest.rates.infraestructure.database.entity.RateEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRateRepository extends JpaRepository<RateEntity, Long> {

    Optional<List<RateEntity>> findByBrandIdAndProductId(Integer brandId,Integer productId);
}
