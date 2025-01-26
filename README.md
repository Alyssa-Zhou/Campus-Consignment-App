# Campus Consignment App Backend

This backend follows RESTful API design principles to manage resources:  

- **GET**: Retrieve resources  
- **POST**: Create new resources  
- **PATCH**: Update existing resources  
- **DELETE**: Remove resources  

## Features Overview
This application enables students to trade goods and manage their transactions conveniently within a campus environment. Key features include:

1. User registration, login, and profile management.
2. Listing, updating, and managing goods.
3. Order creation, updates, and tracking.
4. Integration with payment systems like Alipay.
5. Authentication for users and secure image handling for avatars and student IDs.

---

## **User Management**
### Resigter
POST /users  
```json
{
  "username":"xiaocaomei",
  "password":"abcdefg",
  "resetQuestion":"3",
  "resetAnswer":"happy"
}
```

### Login
POST /users/sessions  
```json
{
    "username": "lower111",
    "password": "123456"
}
```

### Modify Password
*Get user id from session*  
PATCH /users/password  
```json
{
    "oldPassword":"888888",
    "newPassword":"123456"
}
```

### Get user info
GET /users/detail


### Update user info
*如果用户注册后，自动建立用户信息表填写基本内容，此接口用于维护用户信息*

POST /users/detail  
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

### Update avatar
PATCH /users/detial/avatar

form-data
`key:"file", value:图片文件`

### Update student id card
PATCH /users/detial/studentcard

form-data
`key:"file", value:图片文件`


## Goods Management
### Get all goods list
GET /products  

example of return
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

### Get goods by category
GET /products/{cid} 

### Get goods by id
GET /products/detail/{id}  

### Add Goods
POST /products
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

### Update product detail
PATCH /products/{pid}
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

### Upload product img
PATCH /products/img/{id}    
form-data  
`key:"file", value:图片文件`  

### Delete product
DELETE /products/{pid}  

## Orders Management
### Create new order
POST /orders/{pid}?trans=0    
- pid 
- trans-transaction mode  

### Check my order
#### Bought
GET /orders/buy  

#### Sold
GET /orders/sell  

### Update order details
POST /orders/{orderId}?type=1&number=2147374683  
Arguments:
- orderId 订单号
- type 修改何种信息【1-支付单号】【2-快递单号】  
- number 单号内容

### Update order status
POST /orders/state/{orderId}?state=1  

## Online Payment
Using Alipay
### Generate order info log
POST /alipay/info  
Arguments：
- `out_trade_no`订单号
- `total_amount`总价格
- `subject`支付主题（商品名称）  

Example:
```json
{
    "out_trade_no": "70501111111S001111119",
    "total_amount": "9.00",
    "subject": "大乐透233"
}
```

### Receive Asynchronous Message
POST /alipay/response  
<a href="https://opendocs.alipay.com/open/00dn78#%E5%BC%82%E6%AD%A5%E9%80%9A%E7%9F%A5%E7%89%B9%E6%80%A7">Click here for details.<a/>  

