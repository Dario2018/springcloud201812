package com.dario.order.client;


import com.dario.order.client.config.GoodsClient;

import com.dario.order.common.GoodsInfoOutput;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 应用程序之间的通信实例
 */
@RestController
@RequestMapping("/orderclient")
@Slf4j
public class OrderClientController {

    @Autowired
    private GoodsClient goodsClient;
    //第二种
//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    //第三种
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getGoodsMessage")
    public String getGoodsMessage() {
         //第一种
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/servicegoods/msg", String.class);
        //  第二种
//        RestTemplate restTemplate=new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("GOODS");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());
//        String response = restTemplate.getForObject(url, String.class);
        //第三种注解
//            String response=restTemplate.getForObject("http://GOODS/servicegoods/msg",String.class);
            String response=goodsClient.goodsMsg();
        log.info("response={}", response);
        return response;
    }

    /**
     * 查询商品goods服务逻辑（测试）
     */
    @PostMapping("/queryGoodsIdList")
    public String queryGoodsIdList(){
        List<GoodsInfoOutput> goodsInfoOutputList=goodsClient.listForOrder(Arrays.asList("99999465","15567788"));
        log.info("respose={}",goodsInfoOutputList);
        return "ok";
    }



}
