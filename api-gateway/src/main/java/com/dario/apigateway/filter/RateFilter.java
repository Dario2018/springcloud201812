package com.dario.apigateway.filter;




import com.dario.apigateway.exception.RateLimiterException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流：通过Google的插件令牌算法
 * */
@Component
public class RateFilter extends ZuulFilter {
//每秒往池中放100个令牌
   private static final RateLimiter RATER_LIMITER=RateLimiter.create(100);
    //
    @Override
    public String filterType() {

        return PRE_TYPE;
    }

    //定义执行顺序，在SERVLET_DETECTION_FILTER_ORDER之前执行
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(">>>>>>RateFilter Google 的令牌法算法 实现限流功能");
        if (!RATER_LIMITER.tryAcquire()){
            throw new RateLimiterException();
        }
        return null;
    }
}
