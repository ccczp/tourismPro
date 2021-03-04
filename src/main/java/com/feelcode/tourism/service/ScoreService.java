package com.feelcode.tourism.service;

import com.feelcode.tourism.entity.Score;

import java.util.List;

public interface ScoreService {
    Score save(Score score);

    Score findById(String id);

    void delete(Score score);

    List<Score> findAll();

    Score findByUserIdAndProductId(String userId, String productId);

    Long findAllByCount();
}
