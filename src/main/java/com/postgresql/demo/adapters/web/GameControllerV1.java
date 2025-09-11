package com.postgresql.demo.adapters.web;

import com.postgresql.demo.application.dto.GameDtos.*;
import com.postgresql.demo.domain.service.GameService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/games")
public class GameControllerV1 {

    private final GameService service;
    public GameControllerV1(GameService service) { this.service = service; }

    @PostMapping
    public GameResponse create(@Valid @RequestBody GameCreateRequest req) {
        return service.create(req);
    }

    @GetMapping
    public PageResponse<GameResponse> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        String[] s = sort.split(",");
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(s[1]), s[0]));
        var p = service.list(pageable);
        return new PageResponse<>(p.getContent(), p.getNumber(), p.getSize(), p.getTotalElements(), p.getTotalPages());
    }

    @GetMapping("/{id}")
    public GameResponse get(@PathVariable Long id) { return service.get(id); }

    @PatchMapping("/{id}")
    public GameResponse update(@PathVariable Long id, @Valid @RequestBody GameUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }

    // custom
    @GetMapping("/search/by-category")
    public PageResponse<GameResponse> byCategory(@RequestParam String category,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        var p = service.searchByCategory(category, pageable);
        return new PageResponse<>(p.getContent(), p.getNumber(), p.getSize(), p.getTotalElements(), p.getTotalPages());
    }

    @GetMapping("/search/by-price-range")
    public PageResponse<GameResponse> byPrice(@RequestParam BigDecimal min,
                                              @RequestParam BigDecimal max,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size, Sort.by("price").ascending());
        var p = service.searchByPriceRange(min, max, pageable);
        return new PageResponse<>(p.getContent(), p.getNumber(), p.getSize(), p.getTotalElements(), p.getTotalPages());
    }

    @GetMapping("/stats/avg-price-by-category")
    public java.util.List<?> avgPriceByCategory() { return service.averagePriceByCategory(); }
}
