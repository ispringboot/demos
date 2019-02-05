# starter

#### 项目介绍

快速继承支付宝支付功能，只需要引入alipay-starter依赖即可用户支付宝支付功能,目前只接入了支付宝，后续回逐步接入微信支付，文件上传等等功能

#### 软件架构

Spring Boot起步依赖

#### 使用说明

1. 引入依赖
		<dependency>
			<groupId>net.ijiangtao.tech.framework.spring.ispringboot</groupId>
			<artifactId>alipay-starter</artifactId>
			<version>0.0.1</version>
		</dependency>
2. 配置支付宝相关属性
```
alipay.gateway-url=
alipay.app-id=
alipay.merchantPrivateKey=
alipay.format=json
alipay.charset=UTF-8
alipay.alipayPublicKey=
alipay.signType=RSA2

alipay.return_url=http://ip/alipay/callback/return

alipay.notify_url=http://ip/alipay/callback/notify
```
3. 实现支付回调接口

编译example项目：$ mvn install -DskipTests

4. 发起支付请求调起支付页面
http://localhost:8902/alipay/pc/page/pay?outTradeNo=20181232103812831023123123&totalAmount=100&subject=支付测试


#### 接口文档

https://documenter.getpostman.com/view/1406683/RWMJpmJ1


