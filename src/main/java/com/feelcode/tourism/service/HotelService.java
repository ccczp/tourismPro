package com.feelcode.tourism.service;

import com.feelcode.tourism.entity.Hotel;
import com.feelcode.tourism.entity.HotelRequestPageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelService {
    Hotel save(Hotel hotel);

    Hotel findById(String id);

    void delete(Hotel hotel);

    List<Hotel> findAll();

    Page<Hotel> findAllByPage(HotelRequestPageDTO request);

    Page<Hotel> findAllByKeys(HotelRequestPageDTO request, Pageable pageable);

    Long findAllByCount();
}
