# helloworld springboot jwt

## 一、token 与 session 的区别

token与session的不同主要在

- ①认证成功后，会对当前用户数据进行加密，生成一个加密字符串token，返还给客户端（服务器端并不进行保存）
- ②浏览器会将接收到的token值存储在Local Storage中，（通过js代码写入Local Storage，通过js获取，并不会像cookie一样自动携带）
-
③再次访问时服务器端对token值的处理：服务器对浏览器传来的token值进行解密，解密完成后进行用户数据的查询，如果查询成功，则通过认证，实现状态保持，所以，即时有了多台服务器，服务器也只是做了token的解密和用户数据的查询，它不需要在服务端去保留用户的认证信息或者会话信息，这就意味着基于token认证机制的应用不需要去考虑用户在哪一台服务器登录了，这就为应用的扩展提供了便利，解决了session扩展性的弊端。

## 二、启动nacos

解压nacos-server-1.4.0.zip并运行bin/startup.sh <br />
注意：运行需要选择 standalone 模式，运行命令.\startup.cmd -m standalone

## 三、启动多个Provider

定位到 com.mfanw.helloworld.nacos.NacosProviderApplication 启动多次，注意每启动一次要修改一下端口号，确保启动端口号不重复。<br />
修改端口号方法：打开application.yml文件，修改server:port:后的端口号

## 四、启动Consumer进行验证

定位到 com.mfanw.helloworld.nacos.JwtApplication 启动，注意观察最后的消息，如果执行无异常且接收到消息则表示验证成功
