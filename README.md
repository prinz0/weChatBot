## WeChatKy 是什么  

- 微信个人号功能非常强大和灵活，是一个非常适合用来做ChatBot的载体。它可以灵活不受限制的发送语音短信、视频、图片和文字，支持多人群聊。但是使用微信个人微信号作为ChatBot，需要通过非官方的第三方库接入微信。因为截至2019年初，微信尚无任何官方的ChatBot API发布。

- WeChatKy 是一个开源的的 个人号 微信机器人接口，是一款基于基于MAC、IPAD协议开放性API。融合两种协议优势，非破解性grpc,mmtls研发，我们从IP、设备、网段、地点、基站等七条加密方式杜绝封号，追封，批量封等封号问题，支持多种微信接入。

- 在GitHub上可以找到很多支持微信个人号接入的第三方类库，其中大多都是基于Web Wechat的API来实现的，如基于Python的WeixinBot，基于Node.js的Wechaty等。少数支持非Web协议的库，大多是商业私有闭源的，Wechaty是少有的开源项目支持非Web协议的类库。

- 傻瓜式API，掌握JAVA、Go、PHP、Python等任意一种后端代码，你就可以 通过个人号 搭建一个 微信机器人功能 ，用来自动管理微信消息


## 调用登陆接口获取apikey
```$url = 'http://api.gptouzia.com/foreign/auth/login.html';
$phone = 18013351905; // 账号
$password = '123456'; // 密码
$data = ['phone' => $phone, 'password' => $password];
$uInfo = request($url, $data);
$apikey = $uInfo['data']['apikey'];
```
- 更多功能包括：
消息处理：关键词回复、名片、小程序、公众号、链接等
群管理：自动入群，拉人，踢人、创建群一键获取群成员等
自动处理好友请求、批量添加好友、批量删除好友等
消息群发、朋友圈、人工智能AI
智能对话：通过简单配置，即可加入智能对话系统，完成指定任务
... 请自行开脑


## 我们的优势？
- 我们是一家专业提供个人号API的技术团队，服务于具有SCRM、机器人、营销系统等具有研发能力的公司,同时我们也接收因使用Xposed、ipad协议、PC Hook导致被微信批量封号的厂商，我们合作伙伴目前包含：金融服务行业Top10、知名Xposed群控厂商、提供SCRM的定制型公司、知名协议付费平台等。

- 同时我们目前提供的API可开展的业务包括并且不限于：

![业务](https://blobscdn.gitbook.com/v0/b/gitbook-28427.appspot.com/o/assets%2F-LlzM9qN6gYCbu9GuLAw%2F-Lm3FypTbcDU-UQpumgk%2F-Lm3i9E-9JNR1bADuU3z%2F%E5%BE%AE%E6%8E%A7%E8%AE%A4%E7%9F%A5.png?alt=media&token=137af8fc-24c4-4f02-8d7e-792cfb1a7f69)



## 基于Web 微信的限制    
从2017年6月下旬开始，使用基于web版微信接入方案存在大概率的被限制登陆的可能性。 主要表现为：无法登陆Web 微信，但不影响手机等其他平台。 验证是否被限制登陆： https://wx.qq.com 上扫码查看是否能登陆。
从2018年开始，web api 无法创建群聊和自动拉人入群。
##### 更多内容详见：
 - Web网页端、Xpsoed、PC Hook、iPad协议、群控模拟机、MAC协议到底有什么区别?
 - Can not login with error message: 当前登录环境异常。为了你的帐号安全，暂时不能登录web微信。
 - [谣言] 微信将会关闭网页版本
 
 - 新注册的微信号无法登陆
```
解决方案： 第三方提供了非web协议的解决方案，点击购买token , 更多技术细节查看 wechatky-api
```