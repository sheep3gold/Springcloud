package cn.itcast.consumer.controller;

import cn.itcast.consumer.feign.UserClient;
import cn.itcast.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author: HuYi.Zhang
 * @create: 2018-08-10 16:48
 **/
@RestController
@RequestMapping("consumer")
//@DefaultProperties(defaultFallback = "defaultFallBack")
@Slf4j
public class ConsumerController {

    /* @Autowired
     private RestTemplate restTemplate;

     @Autowired
     private DiscoveryClient discoveryClient;

     private final Logger logger = Logger.getLogger(getClass());
 //    @Autowired
 //    private RibbonLoadBalancerClient loadBalancerClient;

     *//*@GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        String url = "http://localhost:8081/user/" + id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }*//*

     *//*@GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        // TODO 你必须编写负载均衡算法，从实例集合中获取某个实例
        ServiceInstance instance = instances.get(0);

        // 内置负载均衡
        // ServiceInstance instance1 = loadBalancerClient.choose("user-service");
        // 组装URL路径
        String url = String.format("http://%s:%s/user/%s", instance.getHost(), instance.getPort(), id);
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }*//*

    @GetMapping("{id}")
    @HystrixCommand
    public String queryById(@PathVariable("id") Long id) {
        if (id == 1) {
            throw new RuntimeException("太忙了");
        }
        String url = "http://user-service/user/" + id;
        String user = restTemplate.getForObject(url, String.class);
        return user;
    }

    *//*public String queryByIdFallBack(Long id) {
        logger.error("查询用户信息失败，id:{}" + id);
        return "对不起，网络太拥挤了！";
    }*//*

    public String defaultFallBack() {
        return "默认提示：对不起，网络太拥挤了！";
    }*/
    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userClient.queryById(id);
    }
}
