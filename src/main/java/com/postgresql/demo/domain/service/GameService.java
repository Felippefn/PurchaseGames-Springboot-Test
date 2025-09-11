package com.postgresql.demo.domain.service;

import com.postgresql.demo.application.dto.GameDtos.*;
import com.postgresql.demo.application.mapper.GameMapper;
import com.postgresql.demo.domain.model.Game;
import com.postgresql.demo.domain.repo.GameRepo;
import com.postgresql.demo.shared.error.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class GameService {

    private final GameRepo repo;
    public GameService(GameRepo repo) { this.repo = repo; }

    // regra simples: 10% se category = INDIE, +5% se price >= 100
    public BigDecimal computeFinalPrice(Game g) {
        BigDecimal price = g.getPrice() == null ? BigDecimal.ZERO : g.getPrice();
        BigDecimal discount = BigDecimal.ZERO;

        if ("INDIE".equalsIgnoreCase(g.getCategory())) {
            discount = discount.add(new BigDecimal("0.10"));
        }
        if (price.compareTo(new BigDecimal("100")) >= 0) {
            discount = discount.add(new BigDecimal("0.05"));
        }
        return price.multiply(BigDecimal.ONE.subtract(discount)).setScale(2, RoundingMode.HALF_UP);
    }

    @Transactional
    public GameResponse create(GameCreateRequest req) {
        boolean dup = repo.existsByNameIgnoreCaseAndCategoryIgnoreCase(req.name, req.category);
        if (dup) throw new IllegalArgumentException("Game name already exists in this category");
        Game saved = repo.save(GameMapper.toEntity(req));
        return GameMapper.toResponse(saved, computeFinalPrice(saved));
    }

    public Page<GameResponse> list(Pageable pageable) {
        return repo.findAll(pageable).map(g -> GameMapper.toResponse(g, computeFinalPrice(g)));
    }

    public GameResponse get(Long id) {
        Game g = repo.findById(id).orElseThrow(() -> new NotFoundException("Game not found"));
        return GameMapper.toResponse(g, computeFinalPrice(g));
    }

    @Transactional
    public GameResponse update(Long id, GameUpdateRequest req) {
        Game g = repo.findById(id).orElseThrow(() -> new NotFoundException("Game not found"));

        if (req.name != null) g.setName(req.name);
        if (req.description != null) g.setDescription(req.description);
        if (req.image != null) g.setImage(req.image);
        if (req.category != null) g.setCategory(req.category);
        if (req.price != null) g.setPrice(req.price);

        Game saved = repo.save(g);
        return GameMapper.toResponse(saved, computeFinalPrice(saved));
    }

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Game not found");
        repo.deleteById(id);
    }

    // buscas/agg
    public Page<GameResponse> searchByCategory(String category, Pageable pageable) {
        return repo.findByCategoryIgnoreCase(category, pageable)
                .map(g -> GameMapper.toResponse(g, computeFinalPrice(g)));
    }

    public Page<GameResponse> searchByPriceRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return repo.findByPriceBetween(min, max, pageable)
                .map(g -> GameMapper.toResponse(g, computeFinalPrice(g)));
    }

    public List<GameRepo.CategoryAvgPrice> averagePriceByCategory() {
        return repo.averagePriceByCategory();
    }
}
