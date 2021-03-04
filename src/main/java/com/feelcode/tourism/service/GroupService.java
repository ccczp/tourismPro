package com.feelcode.tourism.service;

import com.feelcode.tourism.entity.Group;
import com.feelcode.tourism.entity.GroupRequestPageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GroupService {
    Group save(Group group);

    Group findById(String id);

    void delete(Group group);

    List<Group> findAll();

    Page<Group> findAllByPage(GroupRequestPageDTO request);

    Long findAllByCount();

    List<Group> findByIds(String ids);
}
