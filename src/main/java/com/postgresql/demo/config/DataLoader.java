package com.postgresql.demo.config;

import com.postgresql.demo.domain.model.Game;
import com.postgresql.demo.domain.repo.GameRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seed(GameRepo repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(Game.builder().name("Stardew Valley").description("Farming sim").category("Indie").image("").price(14.99).build());
                repo.save(Game.builder().name("Hades").description("Roguelike action dungeon crawler").category("Action").image("").price(24.99).build());
                repo.save(Game.builder().name("Minecraft").description("Sandbox survival and building game").category("Sandbox").image("").price(29.99).build());
                repo.save(Game.builder().name("The Witcher 3: Wild Hunt").description("Open world RPG adventure").category("RPG").image("").price(39.99).build());
                repo.save(Game.builder().name("Celeste").description("Challenging indie platformer").category("Indie").image("").price(14.99).build());
                repo.save(Game.builder().name("Hollow Knight").description("Metroidvania with deep lore and exploration").category("Indie").image("").price(15.99).build());
                repo.save(Game.builder().name("Dark Souls III").description("Action RPG with tough combat").category("RPG").image("").price(49.99).build());
                repo.save(Game.builder().name("Overwatch 2").description("Team-based hero shooter").category("Shooter").image("").price(0.00).build());
                repo.save(Game.builder().name("Among Us").description("Multiplayer social deduction game").category("Party").image("").price(4.99).build());
                repo.save(Game.builder().name("Elden Ring").description("Open-world action RPG.").category("RPG").image("").price(59.99).build());
                repo.save(Game.builder().name("God of War").description("Action-adventure based on Norse mythology").category("Action").image("").price(39.99).build());
                repo.save(Game.builder().name("Red Dead Redemption 2").description("Open world western adventure").category("Adventure").image("").price(59.99).build());
                repo.save(Game.builder().name("Sekiro: Shadows Die Twice").description("Action-adventure with stealth and samurai combat").category("Action").image("").price(49.99).build());
                repo.save(Game.builder().name("Cuphead").description("Run and gun indie game with cartoon style").category("Indie").image("").price(19.99).build());
                repo.save(Game.builder().name("League of Legends").description("Multiplayer online battle arena (MOBA)").category("MOBA").image("").price(0.00).build());
                repo.save(Game.builder().name("Fortnite").description("Battle royale shooter with building mechanics").category("Shooter").image("").price(0.00).build());
                repo.save(Game.builder().name("The Legend of Zelda: Breath of the Wild").description("Open world adventure in Hyrule").category("Adventure").image("").price(59.99).build());
                repo.save(Game.builder().name("Super Mario Odyssey").description("3D platformer with exploration").category("Platformer").image("").price(49.99).build());
                repo.save(Game.builder().name("Pokemon Scarlet").description("Monster catching and battling RPG").category("RPG").image("").price(59.99).build());
                repo.save(Game.builder().name("Final Fantasy XV").description("JRPG with action combat").category("RPG").image("").price(39.99).build());
            }
        };
    }
}
