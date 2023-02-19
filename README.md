# secondhand

#### 介绍
校园二手交易市场后端

使用RESTful风格接口设计
获取资源 GET
新建资源 POST
修改资源 PATCH
删除资源 DELETE

# 用户
## 注册
接口 /users  
请求 POST  
传入参数  
```json
{
  "username":"xiaocaomei",
  "password":"abcdefg",
  "resetQuestion":"3",
  "resetAnswer":"happy"
}
```
返回值
```json
{
  "state":200,
  "message":null,
  "data":null
}
```

## 登录
接口 /users/sessions  
请求 POST  
传入参数  
```json
{
    "username": "lower111",
    "password": "123456"
}
```
返回值
```json
{
    "state": 200,
    "message": null,
    "data": {
        "id": "0000000005",
        "username": "lower111",
        "password": null,
        "resetQuestion": null,
        "resetAnswer": null,
        "state": null,
        "createTime": null,
        "updateTime": null,
        "salt": null
    }
}
```

## 修改密码
*用于登录以后修改密码，用户id从session获取，不需要前端传入*  
接口 /users/password  
请求 PATCH  
传入参数
```json
{
    "oldPassword":"888888",
    "newPassword":"123456"
}
```
返回值
```json
{
    "state": 200,
    "message": null,
    "data": null
}
```

## TODO 忘记密码


## 获取用户信息
接口/users/detail
请求GET
传入参数 不需要前端传入参数，登陆后用户id从session中获取
返回值
```json
{
    "state": 200,
    "message": null,
    "data": {
        "id": "0000000007",
        "studentNumber": null,
        "studentCard": null,
        "role": "3",
        "identified": "0",
        "avatar": "/",
        "phoneNumber": "12344556677",
        "email": null,
        "alias": "",
        "description": null,
        "createTime": "2022-12-10T14:33:00.000+00:00",
        "updateTime": null
    }
}
```


## 修改用户信息
*如果用户注册后，自动建立用户信息表填写基本内容，此接口用于维护用户信息*

接口 /users/detail  
请求 POST  
传入参数：**传入需要修改的值**
```json
{
        "studentNumber": null,
        "studentCard": null,
        "role": "3",
        "identified": "0",
        "avatar": "/",
        "phoneNumber": "12344556697",
        "email": "114514@163.com",
        "alias": "什么时候能有书读",
        "description": "随缘出"
}
```
返回值
```json
{
    "state": 200,
    "message": null,
    "data": null
}
```

## 修改用户头像
接口/users/detial/avatar
方法PATCH
接收参数：
form-data
`key:"file", value:图片文件`
返回值：图片存储路径
```json
{
    "state": 200,
    "message": null,
    "data": "/upload/6445fe52-f788-4731-b89f-315c447e49cf.jpg"
}
```

## 修改认证图片
接口/users/detial/studentcard
方法PATCH
接收参数：
form-data
`key:"file", value:图片文件`
返回值：图片存储路径
```json
{
    "state": 200,
    "message": null,
    "data": "/upload/student/b6c17b8f-46dc-45cc-b342-b70a7faf3f3b.png"
}
```


# 商品
## 获取全部商品列表
需要的信息：图片 商品名 价格 描述 位置  
接口/products  
方法GET  
无需参数  
返回值：商品对象列表
```json
{
    "state": 200,
    "message": null,
    "data": [
        {
            "id": "00000000000000000004",
            "seller": null,
            "name": "核酸抗原",
            "category": null,
            "description": "核酸抗原",
            "location": "4",
            "price": 6.0,
            "img": "/",
            "tags": null,
            "state": null,
            "createTime": null,
            "updateTime": null
        },
        {
            "id": "00000000000000000005",
            "seller": null,
            "name": "四六级耳机",
            "category": null,
            "description": "电池没电",
            "location": "4",
            "price": 10.0,
            "img": "/",
            "tags": null,
            "state": null,
            "createTime": null,
            "updateTime": null
        }
    ]
}

```

## 获取分类下商品列表
需要的信息：图片 商品名 价格 描述 位置
接口/products/{cid} 
方法GET
参数 cid为分类id
返回值：商品对象列表
```json
{
    "state": 200,
    "message": null,
    "data": [
        {
            "id": "00000000000000000005",
            "seller": null,
            "name": "四六级耳机",
            "category": null,
            "description": "电池没电",
            "location": "4",
            "price": 10.0,
            "img": "/",
            "tags": null,
            "state": null,
            "createTime": null,
            "updateTime": null
        }
    ]
}

```

## 获取指定商品详情信息
接口/products/detail/{id}  
方法GET  
参数:id为商品id  
返回值
```json
{
    "state": 200,
    "message": null,
    "data": {
        "id": "00000000000000000004",
        "seller": "0000000004",
        "sellerAlias": "什么时候能有书读",
        "sellerAvatar": "/upload/db975cb9-6ee3-49ea-b399-ab9b41b69676.jpg",
        "sellerPhone": "12344556697",
        "name": "核酸抗原",
        "category": "00020",
        "description": "核酸抗原",
        "location": "4",
        "price": 6.0,
        "img": "/",
        "tags": "核酸,抗原",
        "state": "0",
        "createTime": "2022-12-11T13:51:40.000+00:00",
        "updateTime": "2022-12-11T13:51:40.000+00:00"
    }
}
```

## 添加商品
接口/products
方法POST
接收参数：
```json
{
    "name":"卫生纸",
    "category":"00012",
    "description":"超市买的，剩下4包",
    "location":"1",
    "price":"4",
    "tags":"餐巾纸,卫生纸"
}
```

返回值:data-新增商品id
```json
{
    "state": 200,
    "message": null,
    "data": "17"
}
```

## 更新商品信息
接口/products/{pid}
方法PATCH
参数 pid为商品id
```json
{
    "name":"卫生纸",
    "category":"00012",
    "description":"超市买的，剩下4包",
    "location":"1",
    "price":"4",
    "tags":"餐巾纸,卫生纸"
}
```

返回值:
```json
{
    "state": 200,
    "message": null,
    "data": null
}
```

## 上传商品图片
接口/products/img/{id}  
方法PATCH  
接收参数：  
form-data  
`key:"file", value:图片文件`  
返回值：图片存储路径
```json
{
    "state": 200,
    "message": null,
    "data": "/upload/p2f98a7d8-cd34-46fc-aa89-e64042ab85aa.jpg"
}
```

## 下架商品
接口/products/{pid}  
方法DELETE  
参数：pid-商品编号  
返回值：null

# 订单
订单能改变的信息只有pay id,express number和state
## 新增订单
接口/orders/{pid}?trans=0  
方法POST  
接收参数：  
- pid-购买商品id  
- trans-交易方式（0或者1）  

返回值：
```json
{
  "state": 200,
  "message": null,
  "data": null
}
```

## 查询我的订单
### 我购买的
接口/orders/buy  
方法GET  
无传入参数  
返回值  
```json
{
    "state": 200,
    "message": null,
  "data": [
    {
      "id": "00000000000000000001",
      "seller": "0000000004",
      "sellerAlias": "lower",
      "buyer": "0000000014",
      "buyerAlias": null,
      "pid": "00000000000000000004",
      "price": 6.0,
      "transactionMode": "0",
      "payId": "12342876412",
      "expressNumber": "123423785",
      "state": "1",
      "createTime": "2023-01-17T10:22:41.000+00:00",
      "updateTime": "2023-01-17T10:22:40.000+00:00",
      "pname": "核酸抗原"
    }]
}
```
### 我卖出的
接口/orders/sell  
方法GET  
无传入参数  
返回值同上


## 更新订单信息
接口/orders/{orderId}?type=1&number=2147374683  
方法POST  
参数：
- orderId 订单号
- type 修改何种信息【1-支付单号】【2-快递单号】  
- number 单号内容

## 修改订单状态
*订单取消后，商品自动上架*  
接口/orders/state/{orderId}?state=1  
方法POST  
参数：  
- orderId订单号
- state修改为何种状态【1-取消】【2-完成】

# 支付
支付宝支付  
## 生成 APP 支付订单信息
接口/alipay/info  
方法POST  
参数：
- `out_trade_no`订单号
- `total_amount`总价格
- `subject`支付主题（商品名称）  

**参数格式 JSON**
```json
{
    "out_trade_no": "70501111111S001111119",
    "total_amount": "9.00",
    "subject": "大乐透233"
}
```

返回值：  
`data`中包含`orderString`,形如
`
alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2021000122612470&biz_content=%7B%22out_trade_no%22%3A%2270501111111S001111119%22%2C%22total_amount%22%3A%229.00%22%2C%22subject%22%3A%22%E5%A4%A7%E4%B9%90%E9%80%8F233%22%7D&charset=UTF8&format=json&method=alipay.trade.app.pay&sign=HnZRwIYzW4QBNCTHX5hMpWaVXzI7G2rHrFe%2FN9w5FgyP8a3HeYJrlZ6wTjHR2RwDGQOvZD0WPi%2FjQy%2F5zQqJcWzdnvFn%2BGro7Tql%2B%2FjFmmSuZGO8%2B0dvoB4ZP6RqD9sONQjQjLer1dw9hLSnueiXCSHAGQvLbkB6sWdGExcM78m4tXJQrdhgCgS%2B96OdfhkE5Z1%2BTwQ3j%2FHaOG5j79sFqs5plHX53j6xkDxzYimkHE7OspffFYa1blbQ9CcYHMU80fmWt%2F857qXtA0m6IlWP4cXF%2FLn1E3YTyjaZcBt7pIUkZduMbE8aeNtyzTTA6mCohldNzvciNORkvTI92cHanQ%3D%3D&sign_type=RSA2&timestamp=2023-02-09+00%3A34%3A40
`
## 接收异步通知
*该接口用于接收来自支付宝的异步通知，不需要客户端调用*  
<a href="https://opendocs.alipay.com/open/00dn78#%E5%BC%82%E6%AD%A5%E9%80%9A%E7%9F%A5%E7%89%B9%E6%80%A7">参考链接<a/>  
接口/alipay/response  
方法POST  
返回值`"success"`