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
请求 PATCH
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
参数 id为商品id
返回值
```json
{
    "state": 200,
    "message": null,
    "data": {
        "id": "00000000000000000004",
        "seller": "0000000004",
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

返回值:
```json
{
    "state": 200,
    "message": null,
    "data": null
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

## 更新支付信息

## 更新物流信息

## 取消订单

## 完成订单

