| 表名称               | 字段                                       | 注释                        |
| ----------------- | ---------------------------------------- | ------------------------- |
| user              | Id、手机号、认证方式、QQ号、微信号、微博号。                 |                           |
| user-info         | ID、UserId、身份证号、真名、登陆密码、银行卡号、年龄、头像、信誉度（2个）、性别、学历、 余额（用户可以向账号中充值） 、昵称 |                           |
| classification    | Id、Pid、name                              | 工作性质的大分类、小分类              |
| part-time-order   | Id、名称、详细简介（用户自己输入）、金额、工作位置、经度、纬度、发布时间、工作开始时间、截止时间、性别要求（1、2、3）、年龄要求、学历要求、职业要求、所需人数、现有人数、用户id、CId(小分类的id)、保险金额，状态码status（***0工作人员未确定 1正在进行中  2完成为确认付款  3已确认未评价 4已经评价过了 彻底完成***      ***8 发布者单方面对工作人员评价 9工作者单方面对发布者进行评价***）、模式类型（竞标0或抢单1  ） | 用户id找到相应的头像和昵称            |
| part-time-picture | Id、图片URL                                 | id对应的是part-time-order表的id |
| run-order         | Id、名称、发布时间、送达地点、送达地点坐标、送达时间、内容、金额、用户ID（发布者）、状态码 | 内容用户自己手动输入                |
| run-picture       | Id、图片URL                                 |                           |
| work-comment      | Id、内容、订单Id、UserId(被评价人的userid)、类型（int值 0表示改评价是发布者的  1是工作者）star(0--5星)  PublisherId(发布评论人的userid) | 兼职评价                      |
| run-comment       | Id、内容、订单Id、UserId(被评价人的userid)、类型（int值 0表示改评价是对发布者的  1是对工作者）  star(0--5星)  PublisherId(发布评论人的id) | 跑腿评价                      |
| average-star      | Id  UserId PTPublisher PTWorker RUNpublisher RunWorker   （这种表中 用户注册后 先插入一条空数据  之后一旦有新的评价 就更新该表中数据  在订单中 根据信用排序时 从这个表中去除数据PTPublisher） | 每个用户的平均信用分数               |
| PartTimeRelation  | Id、 OrderId(参与的订单的id)、UserId（报名者的Id） Hired（是否被录用 0没有 1录用） | 维护兼职报名工作的关系               |
| Coupon            | Id  UserId  优惠额度  满减额度  截止日期  种类（0代表兼职使用 1是跑腿使用）  是否过期（0未过期 1已过期） | 用户的优惠券记录                  |
|                   |                                          |                           |
|                   |                                          |                           |







