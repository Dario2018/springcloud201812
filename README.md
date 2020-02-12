# springcloud201812笔记
## springboot
## springcloud
- springcloud 是一个开发工具集，含了多个子项目
   - 利用springboot,两者的对应的版本可以通过spring官网去确定
   - 主要是基于对Netflix 开源组件的进一步封装
   -  SpringCloud的服务调用方式:HTTPS REST
- springcloud Eureka
   - 基于Netflix Eureka  做了二次封装
   - Eureka Server 注册中心
   - Eureka Client 服务注册
- HTTP vs RPC
   - Dubbo：
      - RPC
   - SpringCloud：
      - ;

-------
### eurekaserver
- 注册中心：记录着所有应用的服务状态
- `@EnableEurekaServer` 标志该服务为注册服务中心
- `@EnableDiscoveryClient` 标志该服务为注册客户端
- 如果是eureka客户端还需要配置具体的注册地址，可以参考order应用的启动class
- 输入 mvn clean package 打包成war/jar包
- 实现Eureka Server的高可用:将两个eureka互相注册
- 心跳检查，健康检查，负载均衡等功能
- 生成上服务注册中心建议至少有两台以上
- 服务端发现：Nginx、Zookeeper、Kubernetes
- 客户端发现：Eureka
- 微服务的特点：异构
   - 不同语言
   - 不同类型的数据库
- 服务网关
   - 可以用实现反爬虫，登录验证等
 
### user
### goods
### order
### api-gateway
### schedule
