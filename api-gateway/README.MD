### 服务网关-spring cloud zuul
* spring cloud zuul 组件和nginx 类似，可以作为所有服务的请求入口。
* 一次http请求的生命周期:
   - Http Request
   - pre filters(custorm filters)
   - routing filters
      - 动态路由到相应的应用服务
      - 应用服务处理完返回 routing filter
   - post filters(error filters)
   - Http Response  