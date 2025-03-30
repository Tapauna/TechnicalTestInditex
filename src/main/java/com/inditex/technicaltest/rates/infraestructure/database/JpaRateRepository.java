package com.inditex.technicaltest.rates.infraestructure.database;

import com.inditex.technicaltest.rates.infraestructure.database.entity.RateEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRateRepository extends JpaRepository<RateEntity, Long> {
    /**
     * Call to the database to consult the rates given the chain and the product with JPA
     * @param brandId the brand id
     * @param productId the product id
     * @return The list of recovered rates
     */
    Optional<List<RateEntity>> findByBrandIdAndProductId(Integer brandId,Integer productId);
}
