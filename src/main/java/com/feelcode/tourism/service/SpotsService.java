package com.feelcode.tourism.service;

import com.feelcode.tourism.entity.Spots;
import com.feelcode.tourism.entity.SpotsRequestPageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpotsService {
    Spots save(Spots spots);

    Spots findById(String id);

    void delete(Spots spots);

    List<Spots> findAll();

    List<Spots> findRecommendList(String id);

    Page<Spots> findAllByPage(SpotsRequestPageDTO request);

    Page<Spots> findAllByKeys(String spotsName, Pageable pageable);

    Long findAllByCount();
}
