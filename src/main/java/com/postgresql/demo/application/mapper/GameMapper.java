package com.postgresql.demo.application.mapper;

import com.postgresql.demo.application.dto.GameDtos.GameCreateRequest;
import com.postgresql.demo.application.dto.GameDtos.GameResponse;
import com.postgresql.demo.domain.model.Game;

import java.math.BigDecimal;

public class GameMapper {

    public static Game toEntity(GameCreateRequest req) {
        return Game.builder()
                .name(req.name)
                .description(req.description)
                .image(req.image)
                .category(req.category)
                .price(req.price)
                .build();
    }

    public static GameResponse toResponse(Game g, BigDecimal finalPrice) {
        return new GameResponse(
                g.getId(),
                g.getName(),
                g.getDescription(),
                g.getImage(),
                g.getCategory(),
                g.getPrice(),
                finalPrice
        );
    }
}
