package com.zl.property.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class AliPayUtils {
    public static final String URL = "";
    public static final String APP_ID = "2018080760933353";
    public static final String PID= "2088131907926585";
    public static final String APP_PRIVATE_KEY= "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDMqHTtTaZEnoJZJWlWTmBXprWKt8LB2BoLJBiA0z8kAjoBQp0Jby2DwPQ1iOsYCkKsbg0w1doMCTM1z4fgh2EyV6t1hGHe9M5BY+95IhBtXOSvQI3VayB8mnQS6iYh6PkF2LIMbHJ0BNgeHrE9+PyOGdcRXdYh59G/YD7JOJzh1P1ErTKXPMNgOgFjQq2TPbV27cfPjTfFY9G/1k55g4qZurhuREoDrYZ6jzyVirCpcguD3Vy7PpdS6AU75Ty9e4vLIKw+2DKXzLzo6FdJQbFd4fb0FX6p5Y/6BKaDZKrovZATOZbbCF+S1oysBL1dvWhxrnX8WxhHSply1JiTTcrDAgMBAAECggEBAMnfXL76+gfzprXSbk5Ar02mVqqHSqe0xzO4HidsuHqHa7n0gDxfGzE8MmdgMC2daVj2kTJbr2Jx7L6mf+wc/eYPjkQ0Geo35f+gWQZOcI+/sp+f+IfMzNW78MT7jDOkv4Zh0nbovhjs8lT7h2+O22g72uX5fpAhP8cbY7NOkYk7relEfxgJt83FWAueWVX4iWSvDmB6EU72oOxwl+MpSXX/cYQ/KjioIjLxjndlHZnhWckSs5FZUEjiQ1UGr8vY6i/vK+J1KDKQjtAw9AdEVqe77cIPaMaBBedf5XI1+H+XyIxi8OSL48T9OzslMGY+9h5nkL8G6+qwAsai1okesJECgYEA/PPoL9ehvnu/LXcrlMqfGh0/dEbhu8zDBRZEZf9kFPvpXikJy67k2oOR2swlohAykhhECRSFCKsRW4nrJWZ4biR65h6/G1tQe0PDps+nrbo2kWNzwwM8J6nkaGPN/8QVoQds6A/fABLdMCiXbpyPeAne7gSij56dU2h+oQ02v2sCgYEAzx+cgmxwwPKCgtjzFXzT8Z+7sP1oV3+n0jbTBcdaFAPVgzwdFoSk5JT3dcP6oCvp/Tnr8Iq6O17EzE0iFrpZJAX6NSz53uvCieHQFMd8uceHn5CCItfoAMRW65tIxVAtH3cm1EQYf3NMPcD+KmIn2dnyFxlKtXkKjTp01cm3MAkCgYBLQZ2wriy26UYllrDmoanYMR9xzqzWHMNgvhE3b1YtUA1MMFVzveLkiARa3iXMI5mIEaQcN3ik7ZBKdav6ApSN4FiYIkmlL/Ov2Kda96jhbOdTNg141XuOKWOFEzx0fbgZooXqPN6RvQsFiu4SwI2GnxkuIz7iD8SqzgnujGnfZQKBgAaWFr/++sbn70aB55jzUzoHvkwugrbllEyW+bbhU/f29z8pFjO1HBk4s3XHOZ0GSHaI9Pu9LZbduJxWh5L5cgQ3PHGccIWqMZF44MkIXur5cVKdocXB39rGY40ybun3Vrf9bfqp/61t2SrNjEeEv4ep6ozZfQqwQqmjBjwWIgEhAoGAWqq20MCcFprqJUjB0gcEbzV7klpf6n5mbGB0ARy5bFIEmVkk2ZncaUvF7IfwvoQsh+zYYQIzQPOFlgrhgst8UfYT/6l6rRkLFMcv7cczfEbJ4uZ4oog3EonJn2EU+TIpn2asVw6HH+HOaAPoV1lNwotPzGuUW9lBd9sdFTkuRNI=";
    public static final String FORMAT = "json";
    public static final String CHARSET = "UTF-8";
    public static final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuTi5trP3kH6pjLAuowejQ4wqm0EW6v2QcVrlb/JjQDUU1ipcTC/bS26rTeKUmtKVEZqy+aCRUhLFVe2dJ8Sb+ctvm4vZm75s7B0/IezDZUgnSohhoiu9R1gdDaY3PowYlc06xxONrOo48KXz1EjHwCpaC802/Vnu5W/j0TtjUCr/66+NdrWJEMyVjroDFsvjKSa7tNQOmR9bBYCf0AMGxZ5ScjPn2Fmoty4vjwDI+Ym67ggpQ73k5TGFfD1KsRmuM/1bUXoTyo+Yt6Zko+fMou4gYrwwHol/7UTTFkubPcmadrfaPxxGT1q09QNKtQF4qBudp8nE+29WM2g6pIqrKwIDAQAB";
    public static final String SIGN_TYPE = "RSA2";
    //该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天
    // （1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    //注：若为空，则默认为15d。
    private static final String timeout_express = "1c";



    public static String getAlipayInfo(String outtradeno,String body,String subject,String totalAmount){

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(outtradeno);
        model.setTimeoutExpress(timeout_express);
        model.setTotalAmount(totalAmount);
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
//        request.setNotifyUrl("商户外网可以访问的异步地址");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return  response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     *
     * @param target_id
     * @param body
     * @param subject
     * @param out_trade_no
     * @param total_amount
     * @return
     */
    public static String getOrderInfo(String target_id,String body,String subject,String out_trade_no,String total_amount){
        Map<String,String>  orderMap = buildOrderInfoMap( target_id, body, subject, out_trade_no, total_amount);
        String orderParam =  buildOrderParam(orderMap);
        String sign = getSign(orderMap);
        String authInfo = orderParam + "&" + sign;
        return authInfo;
    }


    /**
     * 构造订单详细参数
     *
     */
    public static Map<String,String> buildOrderInfoMap(String target_id,String body,String subject,String out_trade_no,String total_amount){

        Map<String,String> orderMap = buildAuthInfoMap(target_id);
        //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body 301物业费
        orderMap.put("body","property");
        //商品的标题/交易标题/订单标题/订单关键字等。  物业费
        orderMap.put("subject","orderproperty");
        //商户网站唯一订单号
        orderMap.put("out_trade_no",out_trade_no);
        //订单失效时间
        orderMap.put("timeout_express",timeout_express);
        //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        orderMap.put("total_amount",total_amount);
        //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        orderMap.put("product_code","QUICK_MSECURITY_PAY");
        //商品主类型：0—虚拟类商品，1—实物类商品
        //注：虚拟类商品不支持使用花呗渠道
        orderMap.put("goods_type","0");
       // 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
//        orderMap.put("passback_params",null);
        //可用渠道，用户只能在指定渠道范围内支付
        //当有多个渠道时用“,”分隔
        //注：与disable_pay_channels互斥
        //balance余额 moneyFund余额宝coupon红包creditCard信用卡
        orderMap.put("enable_pay_channels","balance,moneyFund,bankPay,creditCard,coupon");
        return  orderMap;
    }
    /**
     * 构造授权参数列表  公共参数
     *
     * @param target_id
     * @return
     */
    public static Map<String, String> buildAuthInfoMap(String target_id) {
        Map<String, String> keyValues = new HashMap<String, String>();

        // 商户签约拿到的app_id，如：2013081700024223
        keyValues.put("app_id", APP_ID);

        // 商户签约拿到的pid，如：2088102123816631
        keyValues.put("pid", PID);

        // 服务接口名称， 固定值
        keyValues.put("apiname", "com.alipay.account.auth");

        // 商户类型标识， 固定值
        keyValues.put("app_name", "mc");

        // 业务类型， 固定值
        keyValues.put("biz_type", "openservice");

        // 产品码， 固定值
        keyValues.put("product_id", "APP_FAST_LOGIN");

        // 授权范围， 固定值
        keyValues.put("scope", "kuaijie");

        // 商户唯一标识，如：kkkkk091125
        keyValues.put("target_id", target_id);

        // 授权类型， 固定值
        keyValues.put("auth_type", "AUTHACCOUNT");

        // 签名类型
        keyValues.put("sign_type", "RSA2");

        return keyValues;
    }


    /**
     * 对支付参数信息进行签名
     *
     * @param map
     *            待签名授权信息
     *
     * @return
     */
    public static String getSign(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, true));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, true));

        String oriSign = SignUtils.sign(authInfo.toString(), APP_PRIVATE_KEY, true);
        String encodedSign = "";

        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "sign=" + encodedSign;
    }
    /**
     * 拼接键值对
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }
    /**
     * 构造支付订单参数信息
     *
     * @param map
     * 支付订单参数
     * @return
     */
    public static String buildOrderParam(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }
}
