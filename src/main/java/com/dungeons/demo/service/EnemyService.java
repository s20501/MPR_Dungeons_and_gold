package com.dungeons.demo.service;

import com.dungeons.demo.model.Enemy;
import com.dungeons.demo.repository.EnemyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EnemyService {
    private EnemyRepository enemyRepository;


    public EnemyService(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;

    }

    public Enemy save(Enemy enemy) {
        return enemyRepository.save(enemy);

    }

    public Enemy update(Enemy enemy) {
        if (enemyRepository.findById(enemy.getId()).isEmpty()) {
            throw new RuntimeException();
        } else {
            return enemyRepository.save(enemy);
        }

    }

    public void deleteById(Long id) {
        enemyRepository.deleteById(id);
    }

    public List<Enemy> findAll() {
        return enemyRepository.findAll();
    }

    public Optional<Enemy> findById(Long id) {

        return enemyRepository.findById(id);
    }

    public Enemy getRandomEnemy(int level) {
        Random rand = new Random();
        Optional<List<Enemy>> enemyList = enemyRepository.findAllByLevel(level);

        if (enemyList.get().isEmpty()) {
            throw new RuntimeException();

        }

        return enemyList.get().get(rand.nextInt(enemyList.get().size()));

    }

    public void deleteAll() {
        enemyRepository.deleteAll();
    }

}
