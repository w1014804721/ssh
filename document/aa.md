# 接口文档

| 接口url                                | 参数                                       | 返回值                             | 注释                   |
| ------------------------------------ | ---------------------------------------- | ------------------------------- | -------------------- |
| /ssh/PartTimeOrder/ShowDetail        | orderId userid                           | Map                             | 展示某条具体兼职信息           |
| /ssh/PartTimeOrder/ShowLatest        | time(从0开始，用于记录下拉次数)                      | List<BriefPartTimeOrder>        | 查看最新发布的订单            |
| /ssh/Publish/PublishPartTimeOrder    | 太多了……                                    | Message消息                       | 发布新的订单               |
| /ssh/PartTimeOrder/ShowByPay         | time(从0开始，用于记录下拉次数)                      | List<BriefPartTimeOrder>        | 价格由高到低排序查看           |
| /ssh/PartTimeOrder/DeleteOrder       | partId                                   | Message消息                       | 删除订单                 |
| /ssh/PartTimeOrder/ShowByKey         | keyWord     time                         | List<BriefPartTimeOrder>        | 关键字匹配                |
| /ssh/PartTimeOrder/ShowByDistance    | time  longitude  latitude                | List<BriefPartTimeOrder>        | 按距离排序                |
| /ssh/PartTimeOrder/ShowByStar        | time                                     | List<BriefPartTimeOrder>        | 按照信誉度排序              |
| /ssh/PartTimeOrder/ShowOneCategory   | time(Int) ,  Cid(String)                 | List<BriefPartTimeOrder>        | 点击小分类后出现相应的订单简略信息    |
| /ssh/PartTimeOrder/Enroll            | orderId userid 都是int                     | Message消息                       | 点击报名                 |
| /ssh/PartTimeOrder/Hire              | orderId userid 都是int                     | Message消息                       | 录用                   |
| /ssh/PartTimeOrder/Reject            | orderId int                              | Message消息                       | 拒绝报名者                |
| 下面开始跑腿模块：                            |                                          |                                 |                      |
| /ssh/RunOffOrder/ShowDetail          | userid                                   | Map                             | 展示某条具体跑腿信息           |
| /ssh/Publish/PublishRunoffOrder      | 看截图                                      | Message消息                       | 发布新的跑腿               |
| /ssh/RunOffOrder/ShowLatest          | time（int）                                | Message消息 或者List<BriefRunOrder> | 最新发布的跑腿              |
| /ssh/RunOffOrder/ShowByDistance      | time(int) longitude(f)  latitude(f)      | Message消息 或者List<BriefRunOrder> | 按距离                  |
| /ssh/RunOffOrder/ShowByPay           | time                                     | Message消息 或者List<BriefRunOrder> | 按报酬                  |
| /ssh/RunOffOrder/ShowByStar          | time                                     | Message消息 或者List<BriefRunOrder> | 按信誉度                 |
| /ssh/RunOffOrder/ShowByKey           | keyWord（String）     time                 | List<BriefRunOrder>             | 按关键字                 |
| /ssh/RunOffOrder/DeleteOrder         | partId (int)                             | Message消息                       | 删除                   |
| /ssh/RunOffOrder/Enroll              | orderId  userid                          | Message消息                       | 报名跑腿                 |
| 查看订单模块：                              |                                          |                                 |                      |
| /ssh/BossOrder/ShowAll               | userid （int）                             | Message消息 或者List<OrderReview>   | 展示全部                 |
| /ssh/BossOrder/UnChosen              | userid （int）                             | Message消息 或者List<OrderReview>   | 展示人员未满的              |
| /ssh/BossOrder/OnGoing               | userid （int）                             | Message消息 或者List<OrderReview>   | 展示正在进行的              |
| /ssh/BossOrder/NoPay                 | userid （int）                             | Message消息 或者List<OrderReview>   | 展示未付款的               |
| /ssh/BossOrder/NoComment             | userid （int）                             | Message消息 或者List<OrderReview>   | 展示未评价的               |
| /ssh/BossOrder/Finished              | userid （int）                             | Message消息 或者List<OrderReview>   | 展示彻底完成的              |
| /ssh/BossOrder/ShowDetail            | orderid（int）                             | Message消息 或者List<OrderReview>   | 我发布的兼职展示详情（有报名者）     |
| /ssh/BossOrder/ShowHired             | orderid（int）                             | Message消息 或者List<OrderReview>   | 我发布的兼职展示详情（所有参与工作的人） |
| /ssh/BossOrder/onlyDetail            | orderid（int）                             | Message消息 或者List<OrderReview>   | 我发布的兼职展示详情（没有报名者）    |
| 我参与的订单                               |                                          |                                 |                      |
| /ssh/WorkerOrder/ShowAll             | userid （int）                             | Message消息 或者List<OrderReview>   | 展示全部                 |
| /ssh/WorkerOrder/Enroll              | userid （int）                             | Message消息 或者List<OrderReview>   | 展示  已报名的             |
| /ssh/WorkerOrder/OnGoing             | userid （int）                             | Message消息 或者List<OrderReview>   | 展示 正在进行              |
| /ssh/WorkerOrder/NoPay               | userid （int）                             | Message消息 或者List<OrderReview>   | 展示 未付款               |
| /ssh/WorkerOrder/NoComment           | userid （int）                             | Message消息 或者List<OrderReview>   | 展示 未评价               |
| /ssh/WorkerOrder/Finished            | userid （int）                             | Message消息 或者List<OrderReview>   | 展示已完成                |
| /ssh/WorkerOrder/Rejected            | userid （int）                             | Message消息 或者List<OrderReview>   | 展示被拒绝的               |
| 我发布的跑腿 模块                            |                                          |                                 |                      |
| /ssh/BossRun/ShowAll                 | userid （int）                             | Message消息 或者List<OrderReview>   | 展示全部                 |
| /ssh//BossRun/UnChosen               | userid （int）                             | Message消息 或者List<OrderReview>   | 展示人员未满的              |
| /ssh//BossRun/OnGoing                | userid （int）                             | Message消息 或者List<OrderReview>   | 展示正在进行的              |
| /ssh//BossRun/NoPay                  | userid （int）                             | Message消息 或者List<OrderReview>   | 展示未付款的               |
| /ssh//BossRun/NoComment              | userid （int）                             | Message消息 或者List<OrderReview>   | 展示未评价的               |
| /ssh//BossRun/Finished               | userid （int）                             | Message消息 或者List<OrderReview>   | 展示彻底完成的              |
| /ssh//BossRun/ShowDetail             | orderid （int）                            | Message消息 或者List<OrderReview>   | 展示详情                 |
| /ssh//BossRun/DetailWithEnroll       | orderid （int）                            | Message消息 或者List<OrderReview>   | 展示详情 带有报名者           |
| 我报名的跑腿 模块                            |                                          |                                 |                      |
| /ssh/WorkerRun/ShowAll               | userid （int）                             | Message消息 或者List<OrderReview>   | 展示全部                 |
| /ssh/WorkerRun/Enroll                | userid （int）                             | Message消息 或者List<OrderReview>   | 展示  已报名的             |
| /ssh/WorkerRun/OnGoing               | userid （int）                             | Message消息 或者List<OrderReview>   | 展示 正在进行              |
| /ssh/WorkerRun/NoPay                 | userid （int）                             | Message消息 或者List<OrderReview>   | 展示 未付款               |
| /ssh/WorkerRun/NoComment             | userid （int）                             | Message消息 或者List<OrderReview>   | 展示 未评价               |
| /ssh/WorkerRun/Finished              | userid （int）                             | Message消息 或者List<OrderReview>   | 展示已完成                |
| 短信接口：                                |                                          |                                 |                      |
| /ssh/User/SendMessages               | phoneNumber(String)                      | Message消息                       | 发送短信 判断登录或注册         |
| /ssh/User/Register                   | checkNumber（String  这个是验证码）phoneNumber(String) | Message消息                       | 注册                   |
| /ssh/User/getToken                   | userid                                   | 十六位随机字符                         | 获取Token              |
| /ssh/User/identify                   | idNumber （String）  trueName(Strting)  userid(int) | Message消息                       | 实名认证成功后存入数据库         |
| /ssh/User/BindPhone                  | phoneNumber(String)  userid(int)         | Message消息                       | 更新手机号码               |
| /ssh/User/tokenForLogin              | phoneNumber(String)                      | Map 包含userid 和 token            | 手机号获得userid 和token   |
| 发布                                   |                                          |                                 |                      |
| /ssh/Publish/PublishWorkComment      | 看截图                                      | Message消息                       | 发布新的兼职评论             |
| /ssh/Publish/PublishRunComment       |                                          | Message消息                       | 发布新的跑腿评论             |
| /ssh/User/InsertInfo                 | userid NickName gender age Occupation Education | Message消息                       | 第一次新增评论              |
| /ssh/User/UpdateInfo                 | userid NickName  Occupation Education    | Message消息                       | 编辑个人信息               |
| /ssh/PartTimeComment/CommentAsBoss   | orderid                                  |                                 | 查看作为发布者 受到的所有评价      |
| /ssh/PartTimeComment/CommentAsWorker | orderid                                  |                                 | 查看作为工作者参与别人工作 收到的评价  |
| /ssh/PartTimeComment/getStar         | orderid                                  |                                 | 获得  两个平均的信誉度         |
| /ssh/User/ShowInfo                   | userid                                   |                                 | 看个人信息                |
| /ssh/PartTimeOrder/ShowBossInfo      | userid                                   |                                 | 看雇主个人信息              |
| /ssh/RunOffOrder/ShowBossInfo        | userid                                   |                                 | 跑腿雇主信息               |
| /ssh/User/MyCoupon                   | userid                                   |                                 | 个人优惠券信息              |
| /ssh/User/safty                      | userid                                   |                                 | 账户与安全                |
| /ssh/PersonalCenter/enter            | userid                                   |                                 | 个人中心                 |
| /ssh/Publish/HeadImg                 | UserId 其余图片信息                            |                                 | 发布或者更新头像             |
| /ssh/User/tokenForQQ                 | QQNumber                                 |                                 | QQ号登陆 获取userid和token |
| /ssh/User/tokenForWeChat             | WeChat                                   |                                 | 微信号获取userid和token    |
| /ssh/User/BindPhoneForQQ             | phoneNumber  QQNumber                    |                                 | 为QQ用户绑定手机号           |
| /ssh/User/BindPhoneForWeChat         | phoneNumber  WeChat                      |                                 | 为微信用户绑定手机号           |
| /ssh/Pay/ConfirmPay                  | orderid                                  |                                 | 确认付款 显示收款人信息         |
| /ssh/Pay/AddBalance                  | List<Integer> userid   pay               |                                 | 付款给每个人               |
| /ssh/Pay/DecreaseBalance             | UserId pay                               |                                 | 提现                   |
| /ssh/Pay/AlipaySubmit                |                                          |                                 |                      |
| /ssh/User/setPayPassword             | password userid                          |                                 | 设置支付密码               |
| ssh/Pay/PassRight                    | PayPassword  UserId                      |                                 | 判断密码是否正确             |
| ssh/Pay/BackMoney                    | detail                                   |                                 | 退款                   |
| ssh/Pay/useCoupon                    | id                                       |                                 | 使用优惠券                |
| /ssh/PartTimeOrder/WaitComment       | orderId                                  |                                 | 订单状态变带评价             |
| /ssh/PartTimeOrder/Finished          | orderId                                  |                                 | 彻底完成                 |
| /ssh/PartTimeOrder/CheckEnroll       | userid orderId                           |                                 | 是否可以报名 0可以 1不行       |
| /ssh/RunOffOrder/ConfirmPay          | orderId                                  |                                 | 跑腿确认付款人信息            |
| /ssh/RunOffOrder/WaitComment         | orderId                                  |                                 | 跑腿带评价                |
| /ssh/RunOffOrder/Finished            | orderId                                  |                                 | 彻底结束                 |
| /ssh/RunOffOrder/WorkDone            | orderId                                  |                                 | 待付款                  |
| /ssh/User/getHeadImg                 | userid                                   |                                 | 获取头像名称               |