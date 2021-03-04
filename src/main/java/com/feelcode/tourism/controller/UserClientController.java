package com.feelcode.tourism.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feelcode.tourism.base.controller.BaseController;
import com.feelcode.tourism.base.utils.RedisConstants;
import com.feelcode.tourism.base.utils.RedisUtil;
import com.feelcode.tourism.base.utils.StateParameter;
import com.feelcode.tourism.entity.User;
import com.feelcode.tourism.entity.UserSessionEntity;
import com.feelcode.tourism.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/c/user")
@Slf4j
public class UserClientController extends BaseController {

    @Resource
    UserService userService;
    @Resource
    RedisUtil redisUtil;

    @RequestMapping(value="/logon", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap logon(@RequestBody User user){
        try {
            User us = userService.findByUserName(user.getUserName());
            if(us!=null){
                return getModelMap(StateParameter.FAULT, null, "注册失败，用户已存在");
            }else {
                user.setId(getUuid());
                user.setUserName(user.getUserName());
                user.setPassword(user.getPassword());
                userService.save(user);
                log.info("注册成功");
                return getModelMap(StateParameter.SUCCESS, us, "注册成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "注册失败");
        }
    }

    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap login(@RequestBody User user){
        try {
            User us = userService.findByUserNameAndPassword(user.getUserName(),user.getPassword());
            if(us!=null){
                UserSessionEntity userSessionEntity = new UserSessionEntity();
                userSessionEntity.setUserName(us.getUserName());
                us.setUserInfo(userSessionEntity);
                String userJsonString = JSON.toJSONString(us);
                redisUtil.set("user_session_"+us.getId(), userJsonString, RedisConstants.datebase1,300);
                if("admin".equals(us.getUserName())){
                    us.setIsAdmin("true");
                }
                return getModelMap(StateParameter.SUCCESS, us, "登录成功");
            }else {
                return getModelMap(StateParameter.FAULT, null, "登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "登录失败");
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap logout(@RequestBody User user){
        try {
            User us = userService.findByUserName(user.getUserName());
            if(us!=null){
                redisUtil.del(RedisConstants.datebase1, "user_session_"+us.getId());
                return getModelMap(StateParameter.SUCCESS, us, "注销成功");
            }else {
                return getModelMap(StateParameter.FAULT, null, "注销失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "注销失败");
        }
    }

    @RequestMapping(value="/getSession", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap getSession(@RequestBody User user){
        try {
            Object obj = redisUtil.get("user_session_"+user.getId(),RedisConstants.datebase1);
            if(obj!=null){
                redisUtil.expire("user_session_"+user.getId(),RedisConstants.datebase1,300);
                User us = JSON.parseObject(obj.toString(),User.class);
                UserSessionEntity userSessionEntity = new UserSessionEntity();
                userSessionEntity.setUserName(us.getUserName());
                us.setUserInfo(userSessionEntity);
                return getModelMap(StateParameter.SUCCESS, us, "已登录");
            }else {
                return getModelMap(StateParameter.FAULT, null, "未登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "登录态检测失败");
        }
    }

}
