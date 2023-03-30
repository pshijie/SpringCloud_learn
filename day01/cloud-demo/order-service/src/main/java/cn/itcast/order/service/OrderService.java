package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.查询用户
        String url_ori = "http://localhost:8081/user/" + order.getUserId();  // 未使用Eureka
        String url= "http://userservice/user/" + order.getUserId(); // 使用Eureka
        User user = restTemplate.getForObject(url, User.class);  // 发送http请求
        // 3.封装user信息
        order.setUser(user);  // 注意：Order类中具有User类型的字段，这与数据库中的字段不一致
        // 4.返回
        return order;
    }
}
