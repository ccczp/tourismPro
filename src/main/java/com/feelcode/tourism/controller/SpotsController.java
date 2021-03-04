package com.feelcode.tourism.controller;

import com.feelcode.tourism.base.controller.BaseController;
import com.feelcode.tourism.base.utils.StateParameter;
import com.feelcode.tourism.entity.Spots;
import com.feelcode.tourism.entity.SpotsRequestPageDTO;
import com.feelcode.tourism.entity.SpotsResponsePageDTO;
import com.feelcode.tourism.service.SpotsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/s/spots")
@Slf4j
public class SpotsController extends BaseController {

    @Resource
    SpotsService spotsService;

    @RequestMapping(value="/addSpots", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap addSpots(@RequestBody Spots spots){
        try {
            if(StringUtils.isEmpty(spots.getId())){
                spots.setId(getUuid());
            }else{
                spots.setUpdateDate(new Date());
            }
            spotsService.save(spots);
            log.info("保存成功");
            return getModelMap(StateParameter.SUCCESS, null, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "保存失败");
        }
    }

    @RequestMapping(value="/deleteSpots", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap deleteSpots(@RequestBody Spots request){
        try {
            Spots spots = spotsService.findById(request.getId());
            if(spots==null){
                return getModelMap(StateParameter.FAULT, request, "找不到该景点信息");
            }
            spotsService.delete(spots);
            return getModelMap(StateParameter.SUCCESS, null, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "删除失败");
        }
    }

    @RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap list(@RequestBody SpotsRequestPageDTO request){
        SpotsResponsePageDTO resList = new SpotsResponsePageDTO();
        Long count = spotsService.findAllByCount();
        Page<Spots> spotsPage = spotsService.findAllByPage(request);
        resList.setRecordsTotal(count);
        resList.setRecordsFiltered(Integer.parseInt(String.valueOf(count)));
        resList.setSpotsList(spotsPage.getContent());
        log.info("返回景点列表：{}", resList);
        return getModelMap(StateParameter.SUCCESS, resList, "获取景点列表成功");
    }

    @RequestMapping(value="/detail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap detail(@RequestBody Spots request){
        Spots spots = spotsService.findById(request.getId());
        log.info("返回景点详情：{}", spots);
        return getModelMap(StateParameter.SUCCESS, spots, "获取景点详情成功");
    }

}
