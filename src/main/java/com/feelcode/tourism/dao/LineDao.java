package com.feelcode.tourism.dao;

import com.feelcode.tourism.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LineDao extends PagingAndSortingRepository<Line, Long>, JpaSpecificationExecutor<Line>, JpaRepository<Line,Long> {
    Line findById(String id);
}
