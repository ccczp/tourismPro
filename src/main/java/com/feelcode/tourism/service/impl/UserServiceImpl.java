package com.feelcode.tourism.service.impl;

import com.feelcode.tourism.dao.UserDao;
import com.feelcode.tourism.entity.User;
import com.feelcode.tourism.entity.UserRequestPageDTO;
import com.feelcode.tourism.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Resource
    UserDao userDao;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName,password);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Page<User> findAllByPage(UserRequestPageDTO request) {
        // 排序方式，这里是以“recordNo”为标准进行降序
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = new PageRequest(request.getStart(), request.getLength(), sort); // （当前页， 每页记录数， 排序方式）
        return userDao.findAll(pageable);
    }

    @Override
    public Long findAllByCount() {
        return userDao.count();
    }

}
