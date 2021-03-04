package com.feelcode.tourism.dao;

import com.feelcode.tourism.entity.Spots;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpotsDao extends PagingAndSortingRepository<Spots, Long>, JpaSpecificationExecutor<Spots>, JpaRepository<Spots,Long> {

    Spots findById(String id);

}
