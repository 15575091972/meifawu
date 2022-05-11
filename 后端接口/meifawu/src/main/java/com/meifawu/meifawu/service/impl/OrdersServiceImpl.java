package com.meifawu.meifawu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meifawu.meifawu.dao.OrdersMapper;
import com.meifawu.meifawu.entity.Orders;
import com.meifawu.meifawu.service.IOrdersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘诚可
 * @since 2021-12-20
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
