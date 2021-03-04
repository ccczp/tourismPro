package com.feelcode.tourism.controller;

import com.feelcode.tourism.base.controller.BaseController;
import com.feelcode.tourism.base.utils.StateParameter;
import com.feelcode.tourism.entity.*;
import com.feelcode.tourism.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/s/index")
@Slf4j
public class indexController extends BaseController {

    @Resource
    IndexService indexService;

    @RequestMapping(value="/indexData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap indexData(@RequestBody IndexRequestDTO request){
        IndexResponseDTO indexData = indexService.getIndexData(request);
        log.info("返回首页报表数据：{}", indexData);
        return getModelMap(StateParameter.SUCCESS, indexData, "获取首页报表数据成功");
    }

}
