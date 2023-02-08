package com.swjtu.secondhand.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.swjtu.secondhand.entity.Alipay;
import com.swjtu.secondhand.entity.Order;
import com.swjtu.secondhand.interceptor.AlipayConfig;
import com.swjtu.secondhand.service.IOrderService;
import com.swjtu.secondhand.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class AlipayController extends BaseController{

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/alipay/info",method = RequestMethod.POST)
    public JsonResult<String> pay(Alipay aliPay) {

        AlipayTradeAppPayResponse response = null;
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                "2021000122612470",
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCyuXdYEJmkcb1iYuWbv6W/GRmdh1u3wVdqLzwkKXS0SyTwtjYEDlCToiuV+Dzkzj0qhipxYffI5D86PnbzFVdYLhjnonNfPI7AVb7obaoGPUORJWcSDSWuWlnn3DdNW0CIoN9D7b1Hrq/4qeJHKkkTjfxGd77vrHMHyN0XY5akezbZy/zXC7D24S3mqWzF9Kzmuf4V+EjSTa64jjXKYqV+a02dvyjUC2UeDGknn+J81GzS4apiM0TK0401DaufGi3QsFoEfn9vooM809dODeXm8aUHxuDiFHbG4QYx6ZkwlmOtwjZaF0Npo8nlldcWReKusnYnhS+bSi3An7EPPqF/AgMBAAECggEAOYh8w6usWgSHS6m8clPSXl8NIOnW+6NQqY0FFjsGJ5fdD2QIpWuaQrw8va1IpPtDM3I3LYK1CkV3mR9MmVnwYSGNwNtJuom7Bm1T5GyRBCsqmgnpUSVMUQEi2gHJB6ztbE4lMS/R88ZnPWwqvG//UD0vzdt0aqEf8oyVp5bWgqHhVYCh3USe0YniO3EDBnQQFyhPp8IIR5Idi29b/dvOWAJHdztQMHaCf/DBKfCA+MQgdWeVUIW8tMLtduF74BHfJyaewf1oM/lWW+bd1LDPpERWlwPm3YxICgz+6VYgtxwbhFuFkSyMlVOkZ7GTy/nEmleriZR6/elioahJJZedgQKBgQDnXwXPFfMSq5kVchQKdJ3SxtoFjlfTUZ6d0n9Bqih6bai/YE5DqJ9GJYugD4SJksB3jrb3NO9NVpZqBZ4eEnB9qjvQJRfUiGePvZ+9BtVByLwb2qVrickLbbVCJPe8jg8t7MuX22SQJa0RRO7Kpw9UCQBYZTylgMZpsxLFuiM3QQKBgQDFv8uP3MG8Gz67BDEG1KUTezSyFq7vVk4+eAlnK3Q6n8GzUQ2LqBtzpsMyK31SXuH8W8SLJ/yELt7pL2Aue00yYGl5G4ylYKDv3aSdxbDKyhsMUiWZAKVXJxFG3idpE2q5DgQ5BLWRIgJGxCJBbk7tolsBMQKBN2b22N0hVQZovwKBgEGAu2pHcouknTBfHaFgJpZXyb2s8NxYzSktQO3cOJ2AkjesEjxBbuYzFvZ3rjQsoeS3OMF/8QfaLbdSY9NrsQFCwrBI8BgxjfA55RFk79cx91+DZTWuyoY7xwc05BYzucRst68EY9ITeI3dECW7ajGGYtyjvKm2bXXbRsNH0VSBAoGATUG1Qas5dUgfpejpToXdSFMmLz11csQBJZ637bqoWg74GhpcdGdk8jYApKUg/QFcwL8JdmRU3z+AjxGXPhGmAzxveIUu2Z16wruRCKKng1KaZqHhVnlOH1KACXPZac/QLiQvuicA2Ia05PjTeUSjSWvYqgp/tAP1EhN/sPoK2vkCgYA0tCbmusr3p0DLJ5UaC7Wl6yHQK0U6n7x04IYYf18VikzxjdvPcwR4foud9dWI8OexBJr31DRBZz8oIBbv0bEkxVkorni+chyzQ5g1He9cSADxUcIFGrR+g0ILbjrv0OFR0L0Nxq6OWJcaD7igHrFENF+JRDw/efvr1yWRqtC69A==",
                "json",
                "UTF8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0DQ8l6TH6sTkvCmx4NTICc+xCpdfvuYKsBF4R+/BjlFdPnYHvGKTFK4G4hrDTWqiOIzeOz0rAW5VcRqtcVIUsxwlmHx5azKvloH/mKg/thLaE+2UpipLOXQmtT6rKYYZ025+wxFjB8XqBmjRmnSIg7/lmkNaeIFnTdLHDISQJr83y5KsBtGRJ57QNIUrXyEQcj9Zgdvh+GCPZlRLTYgpt/jLGnumXWAcmnPaJtBTzZs2Y6BEcWWekI6kmO7C2HtOOKGijgDL5V4m/6Nrv22FdNnysabo/op+tfICYWw9WH44uSCuu9kOw/UW7EJ6BQVd+QhgkGBV9DdzS1rHwVC92QIDAQAB",
                "RSA2");

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        // 获取参数，设置请求
        model.setOutTradeNo(aliPay.getOutTradeNo());
        model.setSubject(aliPay.getSubject());
        model.setTotalAmount(aliPay.getSubject());

        request.setBizModel(model);
        request.setNotifyUrl("https://662804g8y4.zicp.fun/alipay/response");

        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        String data = response.getBody();
        return new JsonResult<String>(OK,data);
    }

    //
    @RequestMapping(value = "/alipay/response",method = RequestMethod.POST)
    public String payNotify(HttpServletRequest request, HttpSession session) throws AlipayApiException {

        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();

            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            // 支付宝验签
            boolean flag = AlipaySignature.rsaCheckV1(params,
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0DQ8l6TH6sTkvCmx4NTICc+xCpdfvuYKsBF4R+/BjlFdPnYHvGKTFK4G4hrDTWqiOIzeOz0rAW5VcRqtcVIUsxwlmHx5azKvloH/mKg/thLaE+2UpipLOXQmtT6rKYYZ025+wxFjB8XqBmjRmnSIg7/lmkNaeIFnTdLHDISQJr83y5KsBtGRJ57QNIUrXyEQcj9Zgdvh+GCPZlRLTYgpt/jLGnumXWAcmnPaJtBTzZs2Y6BEcWWekI6kmO7C2HtOOKGijgDL5V4m/6Nrv22FdNnysabo/op+tfICYWw9WH44uSCuu9kOw/UW7EJ6BQVd+QhgkGBV9DdzS1rHwVC92QIDAQAB",
                    "UTF8",
                    "RSA2");

            if (flag) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 更新订单信息,存入支付单号
                String uid = getIdFromSession(session);
                orderService.changeOrderInfo(uid,params.get("out_trade_no"),params.get("trade_no"),"1");

                // 将获取的支付信息存入数据库

            }
        }
        return "success";
    }

}
