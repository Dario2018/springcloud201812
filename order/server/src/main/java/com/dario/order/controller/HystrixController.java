package com.dario.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 通过SpringCloudHystrix 组件实现：服务降级、服务熔断、监控（Hystrix Dashboard）、依赖隔离
 */
@RestController
@RequestMapping("/hystrixcontroller")
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {


    /**
     * 通过HystrixCommand实现服务降级
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/getGoodsInfoList")
    public String getGoodsInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8089/servicegoods/listForOrder", Arrays.asList("15567788", "99999465"), String.class);
    }

    public String fallback() {
        return "当前网络拥挤，请稍后再试···";
    }

    /*通过配置文件定制服务熔断时间*/
    @HystrixCommand(fallbackMethod = "getGoodsInfoList01Fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("/getGoodsInfoList01")
    public String getGoodsInfoList01() throws InterruptedException {
        Thread.sleep(2000);
        return "hello world";
    }

    @GetMapping("/test")
    public String test() {
        return "test hystrixcontroller";
    }

    public String getGoodsInfoList01Fallback() {
        return "getGoodsInfoList01Fallback····";
    }

    public String defaultFallback() {
        return "服务降级时默认调用的方法。。。。。";
    }

}
