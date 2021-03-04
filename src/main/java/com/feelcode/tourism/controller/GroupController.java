package com.feelcode.tourism.controller;

import com.feelcode.tourism.base.controller.BaseController;
import com.feelcode.tourism.base.utils.StateParameter;
import com.feelcode.tourism.entity.*;
import com.feelcode.tourism.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/s/group")
@Slf4j
public class GroupController extends BaseController {

    @Resource
    GroupService groupService;

    @RequestMapping(value="/addGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap addGroup(@RequestBody Group group){
        try {
            if(StringUtils.isEmpty(group.getId())){
                group.setId(getUuid());
            }else{
                group.setUpdateDate(new Date());
            }
            groupService.save(group);
            log.info("保存成功");
            return getModelMap(StateParameter.SUCCESS, null, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "保存失败");
        }
    }

    @RequestMapping(value="/deleteGroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap deleteGroup(@RequestBody Group request){
        try {
            Group group = groupService.findById(request.getId());
            if(group==null){
                return getModelMap(StateParameter.FAULT, request, "找不到该旅团信息");
            }
            groupService.delete(group);
            return getModelMap(StateParameter.SUCCESS, null, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "删除失败");
        }
    }

    @RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap list(@RequestBody GroupRequestPageDTO request){
        GroupResponsePageDTO resList = new GroupResponsePageDTO();
        Long count = groupService.findAllByCount();
        Page<Group> groupPage = groupService.findAllByPage(request);
        resList.setRecordsTotal(count);
        resList.setRecordsFiltered(Integer.parseInt(String.valueOf(count)));
        resList.setGroupList(groupPage.getContent());
        log.info("返回旅团列表：{}", resList);
        return getModelMap(StateParameter.SUCCESS, resList, "获取旅团列表成功");
    }

    @RequestMapping(value="/detail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap detail(@RequestBody Spots request){
        Group group = groupService.findById(request.getId());
        log.info("返回旅团详情：{}", group);
        return getModelMap(StateParameter.SUCCESS, group, "获取旅团详情成功");
    }

}
