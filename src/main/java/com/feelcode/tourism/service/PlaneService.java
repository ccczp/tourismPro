package com.feelcode.tourism.service;

import com.feelcode.tourism.entity.Plane;
import com.feelcode.tourism.entity.PlaneRequestPageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlaneService {
    Plane save(Plane plane);

    Plane findById(String id);

    void delete(Plane plane);

    List<Plane> findAll();

    Page<Plane> findAllByPage(PlaneRequestPageDTO request);

    Long findAllByCount();
}
