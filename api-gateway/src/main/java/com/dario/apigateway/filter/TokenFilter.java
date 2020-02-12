package com.dario.apigateway.filter;

/**
 * @created by Dario
 * @description: zuul实现服务网关：鉴权，限流，负载均衡、反向代理（路由）,过滤，跨域等
 */

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
@Slf4j
public class TokenFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

    /*前置过滤器*/
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(">>>>>TokenFilter 标记一下");
//        获取当前的上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        TokenFilter.LOGGER.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        //发送的请求必须需要带上token，token获取可以从url参数里面获取，也可以从cookie，header 里面获取
//       String token=request.getParameter("token");
//       if (StringUtils.isEmpty(token)){
//           requestContext.setSendZuulResponse(false);
//           //返回给客户端是401 ，没有授权
//           requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//       }
        return null;
    }
}
