package com.feelcode.tourism.controller;

import com.feelcode.tourism.base.controller.BaseController;
import com.feelcode.tourism.base.utils.StateParameter;
import com.feelcode.tourism.entity.Order;
import com.feelcode.tourism.entity.OrderRequestPageDTO;
import com.feelcode.tourism.entity.OrderResponsePageDTO;
import com.feelcode.tourism.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: 朱利尔
 * @Description:
 * @Date: Created in 23:37 2020/5/7
 * @Modified By:
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/s/order")
@Slf4j
public class OrderController extends BaseController {

    @Resource
    OrderService orderService;

    @RequestMapping(value="/addOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap addOrder(@RequestBody Order order){
        try {
            Order newOrder = new Order();
            if(StringUtils.isEmpty(order.getId())){
                order.setId(getUuid());
                BeanUtils.copyProperties(order,newOrder);
            }else{
                newOrder = orderService.findById(order.getId());
                newOrder.setOrderStatus(order.getOrderStatus());
                newOrder.setUpdateDate(new Date());
            }
            orderService.save(newOrder);
            log.info("保存成功");
            return getModelMap(StateParameter.SUCCESS, null, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

    @RequestMapping(value="/deleteOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap deleteOrder(@RequestBody Order request){
        try {
            Order order = orderService.findById(request.getId());
            if(order==null){
                return getModelMap(StateParameter.FAULT, request, "找不到该订单信息");
            }
            orderService.delete(order);
            return getModelMap(StateParameter.SUCCESS, null, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "删除失败");
        }
    }

    @RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ModelMap list(@RequestBody OrderRequestPageDTO request){
        OrderResponsePageDTO resList = new OrderResponsePageDTO();
        Long count = orderService.findAllByCount();
        Page<Order> orderPage = orderService.findAllByPage(request);
        resList.setRecordsTotal(count);
        resList.setRecordsFiltered(Integer.parseInt(String.valueOf(count)));
        resList.setOrderList(orderPage.getContent());
        log.info("返回订单列表：{}", resList);
        return getModelMap(StateParameter.SUCCESS, resList, "获取订单列表成功");
    }

}
