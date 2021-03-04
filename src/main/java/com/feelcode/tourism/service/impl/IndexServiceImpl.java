package com.feelcode.tourism.service.impl;

import com.feelcode.tourism.base.constant.SystemConstant;
import com.feelcode.tourism.dao.*;
import com.feelcode.tourism.entity.*;
import com.feelcode.tourism.entity.vo.OrderDateCountVO;
import com.feelcode.tourism.service.IndexService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service(value = "indexService")
public class IndexServiceImpl implements IndexService {
    @Resource
    CommentDao commentDao;
    @Resource
    GroupDao groupDao;
    @Resource
    HotelDao hotelDao;
    @Resource
    LineDao lineDao;
    @Resource
    PlaneDao planeDao;
    @Resource
    ScoreDao scoreDao;
    @Resource
    SpotsDao spotsDao;
    @Resource
    UserDao userDao;
    @Resource
    OrderDao orderDao;

    @Override
    public IndexResponseDTO getIndexData(IndexRequestDTO request) {
        IndexResponseDTO indexResponseDTO = new IndexResponseDTO();

        List<Order> orderList = orderDao.findAll();
        indexResponseDTO.setOrderList(orderList);
        Long hotelNum = 0L;
        Long planeNum = 0L;
        Long lineNum = 0L;
        for (Order order:orderList){
            if(SystemConstant.ProductType.hotel.equals(order.getProductType())){
                hotelNum++;
            }
            if(SystemConstant.ProductType.plane.equals(order.getProductType())){
                planeNum++;
            }
            if(SystemConstant.ProductType.line.equals(order.getProductType())){
                lineNum++;
            }
        }
        BigDecimal totalSum = new BigDecimal(orderList.size());
        BigDecimal hotelSum = new BigDecimal(hotelNum);
        BigDecimal planeSum = new BigDecimal(planeNum);
        BigDecimal lineSum = new BigDecimal(lineNum);
        BigDecimal hotelPercent = hotelSum.divide(totalSum,2,BigDecimal.ROUND_DOWN).multiply(BigDecimal.valueOf(100));
        indexResponseDTO.setHotelPercent(hotelPercent);
        BigDecimal planePercent = planeSum.divide(totalSum,2,BigDecimal.ROUND_DOWN).multiply(BigDecimal.valueOf(100));
        indexResponseDTO.setPlanePercent(planePercent);
        BigDecimal linePercent = lineSum.divide(totalSum,2,BigDecimal.ROUND_DOWN).multiply(BigDecimal.valueOf(100));
        indexResponseDTO.setLinePercent(linePercent);
        BigDecimal otherPercent = BigDecimal.valueOf(100).subtract(hotelPercent).subtract(planePercent).subtract(linePercent);
        indexResponseDTO.setOtherPercent(otherPercent);

        //Long todayOrder = orderDao.countByDealingTimeLike();
        Long todayOrder = orderDao.count();
        indexResponseDTO.setTodayOrder(todayOrder);
        Long totalOrder = Long.parseLong(String.valueOf(orderList.size()));
        indexResponseDTO.setTotalOrder(totalOrder);
        Long totalSubmitOrder = orderDao.countByOrOrderStatus(SystemConstant.OrderStatus.submit);
        Long totalPassOrder = orderDao.countByOrOrderStatus(SystemConstant.OrderStatus.pass);
        Long totalRefuseOrder = orderDao.countByOrOrderStatus(SystemConstant.OrderStatus.refuse);

        Long totalSpots = spotsDao.count();
        Long totalHotel = hotelDao.count();
        Long totalLine = lineDao.count();
        Long totalGroup = groupDao.count();
        Long totalPlane = planeDao.count();
        Long totalComment = commentDao.count();
        Long totalUser = userDao.count();

        List<Spots> topTenSpots = null;//spotsDao.findTopTenSpots();
        List<Hotel> topTenHotel = null;//orderDao.count();
        List<Comment> topTenCommentUser = null;//orderDao.count();

        indexResponseDTO.setTotalSubmitOrder(totalSubmitOrder);
        indexResponseDTO.setTotalPassOrder(totalPassOrder);
        indexResponseDTO.setTotalRefuseOrder(totalRefuseOrder);
        indexResponseDTO.setTotalSpots(totalSpots);
        indexResponseDTO.setTotalHotel(totalHotel);
        indexResponseDTO.setTotalLine(totalLine);
        indexResponseDTO.setTotalGroup(totalGroup);
        indexResponseDTO.setTotalPlane(totalPlane);
        indexResponseDTO.setTotalComment(totalComment);
        indexResponseDTO.setTotalUser(totalUser);
        indexResponseDTO.setTopTenSpots(topTenSpots);
        indexResponseDTO.setTopTenHotel(topTenHotel);
        indexResponseDTO.setTopTenCommentUser(topTenCommentUser);

        //所有订单-日期折线图数据
        List<OrderDateCountVO> dateOrder = orderDao.getDateOrder();
        indexResponseDTO.setDateOrder(dateOrder);
        //酒店订单-日期折线图数据
        List<OrderDateCountVO> hotelDateOrder = orderDao.getHotelDateOrder();
        indexResponseDTO.setHotelDateOrder(hotelDateOrder);
        //机票订单-日期折线图数据
        List<OrderDateCountVO> planeDateOrder = orderDao.getPlaneDateOrder();
        indexResponseDTO.setPlaneDateOrder(planeDateOrder);

        //获取最新的评论
        Sort sort = new Sort(Sort.Direction.DESC,"commentTime");
        Pageable pageable = new PageRequest(1, 3, sort);
        Page<Comment> commentPage = commentDao.findAll(pageable);
        List<Comment> commentList = commentPage.getContent();
        indexResponseDTO.setCommentList(commentList);

        return indexResponseDTO;
    }

}
