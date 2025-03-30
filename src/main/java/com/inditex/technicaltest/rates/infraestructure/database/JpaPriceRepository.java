package com.inditex.technicaltest.rates.infraestructure.database;

import com.inditex.technicaltest.rates.infraestructure.database.entity.PriceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<List<PriceEntity>> findByBrandIdAndProductId(Integer brandId,Integer productId);
}
