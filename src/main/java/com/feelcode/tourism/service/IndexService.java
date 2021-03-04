package com.feelcode.tourism.service;

import com.feelcode.tourism.entity.IndexRequestDTO;
import com.feelcode.tourism.entity.IndexResponseDTO;

public interface IndexService {
    IndexResponseDTO getIndexData(IndexRequestDTO request);
}
