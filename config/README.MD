### 有关spring cloud 配置中心
* 启动后输入http://localhost:8086/order-dev.yml 可以访问GitHub上面order.yml配置文件
* 需要注意的问题：高可用，可以启动多个应用
* 当应用访问配置中心应用时，配置中心根据负载均衡算法，自动引导访问具体某个应用
* GitHub上修改order-dev.yml 和 同时加载o/4rder.yml 文件，这个是需要注意的