package com.dario.apigateway.filter;

import com.dario.apigateway.constanst.ConstantUtil;
import com.dario.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author Dairo
 * @Date 2018-12-28
 *
 * */
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(">>>>>AuthFilter run");
       RequestContext requestContext= RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
       /**
        * /order/create 只能买家访问(cookie 里面有openid)
        * /order/finish 只能卖家可以访问 里面有token,并且对应的redis中值
        * /goods/list 都可以访问
        * */
       if ("/order/order/create".equals(request.getRequestURI())){
           Cookie cookie= CookieUtil.get(request,"openid");
           if (cookie==null|| StringUtils.isEmpty(cookie.getValue())){
               requestContext.setSendZuulResponse(false);
               requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
           }
       }
       if ("/order/order/finish".equals(request.getRequestURI())){
           Cookie cookie=CookieUtil.get(request,"token");
           System.out.println(stringRedisTemplate.opsForValue().get(String.format(ConstantUtil.TOKEN,cookie.getValue())));
           if (cookie==null ||StringUtils.isEmpty(cookie)
                   || StringUtils.isEmpty( stringRedisTemplate.opsForValue().get(String.format(ConstantUtil.TOKEN_TEMPLATE,cookie.getValue())) ) ){
                  requestContext.setSendZuulResponse(false);
                  requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
           }
       }
        return null;
    }
}
