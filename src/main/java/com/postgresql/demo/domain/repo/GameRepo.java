package com.postgresql.demo.domain.repo;

import com.postgresql.demo.domain.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

@RepositoryRestResource
public interface GameRepo extends JpaRepository<Game,Long> {
    boolean existsByNameIgnoreCaseAndCategoryIgnoreCase(String name, String category);

    Page<Game> findByCategoryIgnoreCase(String category, Pageable pageable);

    Page<Game> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<Game> findByName(String name, Pageable pageable);

    interface CategoryAvgPrice {
        String getCategory();
        BigDecimal getAvgPrice();
    }

    @Query("""
        select g.category as category, avg(g.price) as avgPrice
        from Game g
        group by g.category
    """)
    java.util.List<CategoryAvgPrice> averagePriceByCategory();

}
