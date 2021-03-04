package com.feelcode.tourism.service;

import com.feelcode.tourism.entity.Line;
import com.feelcode.tourism.entity.LineRequestPageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LineService {
    Line save(Line line);

    Line findById(String id);

    void delete(Line line);

    List<Line> findAll();

    Page<Line> findAllByPage(LineRequestPageDTO request);

    Long findAllByCount();
}
