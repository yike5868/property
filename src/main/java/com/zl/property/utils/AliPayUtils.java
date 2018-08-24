package com.zl.property.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class AliPayUtils {
    public static final String URL = "";
    public static final String APP_ID = "2018080760933353";
    public static final String PID= "2088131907926585";
    public static final String APP_PRIVATE_KEY= "MIIEpAIBAAKCAQEAqAxs6Q+CaVvnB/O82bnVpWtlCH/Est1kSh1HY5XMApXbwzuX" +
            "XlwUT189Ij2yAdg8k7mMP7cfPaYd1XUKe8J6Ga7iUCByuWA+7/d2LZldIeOxIxAY" +
            "ZdhKRsh/VbNFHDuKLaIRvw282aE02hnfgCia8smLb5smRZ0JTy3pgT+GJ0RHMRMp" +
            "UKLo9ItezUHI0Dh2Z6jKLdIpTcEidFr9y7B1bBFlQ04RyHr/9tn5qbvARHOe55lN" +
            "kB9vPkuvKF7y1qhEo6zPULkHnm+BjFJi/fuP14L1vQZ+QaRH42QVvJo/Cf1VPlIM" +
            "ZsOMUfYgDfWWw3/+8AltueoFKcFTVKvkUhNWzQIDAQABAoIBAEe1tavmYGIRQ48T" +
            "JV1Lfa2vHFf6Hbet3aP6xoHqZDoeNL8k6LztUgVkwLOh3BoJaiAnp39w5zXpcwWp" +
            "giW5oVzgUdabYhlUxsajtJxUu3dAsFAkCCU9nMSDvkKV00Hu7lH2vNfoRtQfhGwl" +
            "akhIC4bbFlMzw6slDdnp36C1uMt8GE6YzMpmQZgc5FWqR3jxeoy6qfV1Ig2ZDnfB" +
            "W46kGTZSqeSCEQ2mctYU3g+8zjhCMdUhqWV0NCmr1ZB1rE5kxE0uaeT3nvpAb/0O" +
            "ogWzCHjvcAVny/hGS0hQc+q7hWYc0H0zHZGn+0KVJ7XMQH5DMJlkJQBRCan1G1H4" +
            "Bse/kAkCgYEA08XO/4XhMpA5FaTRhobz5ZZb0uKwTuITUGmktQRmQIKOIb0HJlFA" +
            "jmW6tOc9RgUjJHv8+5FagEiwB3GOEvGXaH+Qnr/F+AFYaRDqfqmLKLFoOwwluUvV" +
            "iJPZRLD1ESFSVItdwvQQCfCE/aHBQEklzrWQbHE1qPGQ0nao9Mi8bLcCgYEAyyT0" +
            "IGOZou1aGWMu3u+kSMYfJzj6pF5uo5+vdjNUBWYw5PQIldLh5uXMkf7UQCou5o2h" +
            "pKCGDJUyy1IE9CSMSSWkNTjbJrzcH6B0lnaOMDmtKm9WVJXabox96Eklkl4vSloN" +
            "8paNYj657Jat4a/VLFwwaRFJOOmkLfoxgRYynJsCgYBGPIzy4oxWIM9OBmQXohqy" +
            "QrQhtV2UTBbrzJ38C4F+U86gEfmVE829bLAH38nKt1l4eEbniMXjVjhLv7XHQqlc" +
            "1zI39JLMNmYEMsATUlf9Hxnau6SnWCdyLNcamTYugEa2E6L9TcUkBsmU9VkK4TfQ" +
            "3xcPYFzTs4q8wu42gX088QKBgQDCrEMW1tvgeryhH59HHTe+Vb59A+mNosE8JEct" +
            "arrWAbxRbFrd2R2K/CUys5YLd2FCp++DqCFN5Zyro4oDvRKC9oOKTVYWpV39IPMZ" +
            "TbutE1iTFecRYBnXXuM5uv86aO/AvNQo6YXL5Hif9TXk1W/f9uidh2c4Fw6y0NSn" +
            "8/HEiQKBgQDAWQ3RnVElI66jUfxzUr60061Twjdz1xeRMUWFg9ZzRBD479AZ6BHF" +
            "AMiZvuHGVRys/YSmOAHxu6Rl4EpQ7QBzEZ/iihDuZ3XdyJ3c81Mc89A5L298Iakp" +
            "BoZaRppURVsRPXuzu0tSEJLtWH1hZ0PLSFEtKmpZsIs8e+cWGCU/aQ==";
    public static final String FORMAT = "json";
    public static final String CHARSET = "UTF-8";
    public static final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuTi5trP3kH6pjLAuowejQ4wqm0EW6v2QcVrlb/JjQDUU1ipcTC/bS26rTeKUmtKVEZqy+aCRUhLFVe2dJ8Sb+ctvm4vZm75s7B0/IezDZUgnSohhoiu9R1gdDaY3PowYlc06xxONrOo48KXz1EjHwCpaC802/Vnu5W/j0TtjUCr/66+NdrWJEMyVjroDFsvjKSa7tNQOmR9bBYCf0AMGxZ5ScjPn2Fmoty4vjwDI+Ym67ggpQ73k5TGFfD1KsRmuM/1bUXoTyo+Yt6Zko+fMou4gYrwwHol/7UTTFkubPcmadrfaPxxGT1q09QNKtQF4qBudp8nE+29WM2g6pIqrKwIDAQAB";
    public static final String SIGN_TYPE = "RSA2";
    //该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天
    // （1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    //注：若为空，则默认为15d。
    private static final String timeout_express = "1c";

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
        orderMap.put("body",body);
        //商品的标题/交易标题/订单标题/订单关键字等。  物业费
        orderMap.put("subject",subject);
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
        orderMap.put("passback_params",null);
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
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

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
