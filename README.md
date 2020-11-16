# shop-mall

### 介绍
   #### spring cloud + spring cloud alibaba 全家桶
  * 1.使用spring cloud Hoxton版 当前最新版本
  * 2.spring cloud Hoxton.SR8版 对应cloud alibab 2.2.3版以及spring boot 2.3.2版本   
  * 3.本项目单纯为了学习spring cloud 和 spring cloud alibaba技术栈，包含了：nacos注册中心，
    sentinel服务熔断，限流， Sleuth+Zipkin服务链路追踪，rocketmq消息队列，spring cloud gateway网关服务，
    和分布式事务seata等。
### 软件架构说明

 * 1.采用maven多模块项目
 * 2.采用（当前2020-11-05）最新的spring cloud 和spring cloud alibaba 技术栈

### 模块说明
    shop-common ----- 项目公共模块
    shop-user   ----- 用户模块
    shop-product ---- 商品模块
    shop-order  -----  订单模块
    shop-gateway ----  网关模块
### 安装教程
1. 在gitee上clone项目
2. 本地环境：jdk8,maven3.2.5,idea(需要安装lombok插件),mysql5.6及以上版本
3. 项目所需的第三方插件，数据库文件都放在跟随项目的plugin文件夹下
4. 项目的sql及插件所需的sql文件都存放在doc/sql文件夹下面，doc文件下包含本人测试的一些截图
5. 先启动对应的插件服务，再启动项目，到nacos注册中心查看服务，本人测试图片都存放在doc文件目录下，供参考

### 参与贡献
1.  木木 本人版权

