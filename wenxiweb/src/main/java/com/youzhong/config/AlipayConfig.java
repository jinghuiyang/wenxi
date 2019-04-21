package com.youzhong.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092700605429";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCtUnwNcd+fdBwau7UDrUq/mheP2wUaRKR6hTc7Pi007nGSItbHWMNHAfW13YDzKVIpexfxZd+75vmtwpAwfXyjeTFAQ9lYKWaLQrPl0rSYrAwmnhHUTgIV4UP6J45P4tNuQuNBToFM0xKQQWmly39o4WX0Z2RFYvpp+KruBHJ/SwQFg2HNQLkhyyHBjzFZ2V6GbsCsxYeHvIme8w+nlDtMnPyowpJtmBhF6CBfw12kTkGTcjxy6Pzd67jcXq/6pdAV2lOkSFPHbflnWeoYieqaW0e07QgKbsIsJRZoMHaXopcjXUF50HZQVifgNpT4slH1xf/FZOsdIaFe1IYnMhZHAgMBAAECggEAQj+W/ySZjz84Rteclu0z9wyVGP1kc+fs3o/wzooziVniuzLp2YSKD6gmHP+vfEI9D3HncvO3kwD0SoBl0MMGUw3Nz3yfpN0ePvid/qiNMxYABvrAYd/3WBmkfAeGk+5lVm3mo124en7rR4xZL3AbISGWcNbcMtFkWgEchKHaieCnfOe1hk0HiwuV9+bo/dPaXqjU14rPJEZ6revA4YzjAp9Oo7BVeNIMlPsI3yj2Zwr18a6cUgTuLfgw/8kRHO2U7sv0a7oG7JGe2Eb3rz+7IFNnVyyok/+NO0VV6a3DjU/KkIVVwJXlrClm0fB8chO51xvKcmmpY1/BTdMk3cuMQQKBgQDU9G4a9+C7sez4tNLA38fK074EN9Qsu1qzL3zzQfvvMnm8PlmPBfGXSNjgYn/pnnzQqP/wCsiHe6FdM6XEvbCpLYVo8/SSoC7RLcYUvT2LdbgT1c1wXSeV77pi+9lVla2qmU8hHWQerOHnvo61woHzbSHcNEwVU6RF9J8rlRVStwKBgQDQWzlY7AlJ8JownhHYZvhKrymeQ7Almsz9fY9YVNuOg4n8d0Gn+AmJmnHZi+OuEdz/4YJIJiLzN+qXQoFwHOboRziyxUXO8ZFo/15n7GGVaI0hvHVrPEoKygifMtP1YSs7Y+V4X2kxK+ULQx8mlE8xO20KI3Ckw3LNtvkiEUKI8QKBgFZlA/Z08fuz54vO8GHHZ+rllIVpkO7E47smXO7jqi4UsEaqlPLtWmIh77q4u2QwJDsTFSJxfYFZqtGBDpOO0YkRKrq1nleOHPBIPcBUNg/KJnAFoV8gq9qsc1WND1Mlcltv7yZkIiY/xTabnJIo40e60q3fma5dTJwE7j5kMU5pAoGANg2UgRcrCb2bosU/R+7etzFeEKrqxba1O4YZbAcHtp81awPdSqA0iThCFPgPpxcHSHLOg70xD/wPphIg1l8JIHVppic7hwZwfNbTPQ67+jDXfEsSAzHu24GyXuqmxl/hAVVXMLU/6vvCPfu51x5etRz/msO682OpIuk3FLl5k2ECgYB15u/lJ5kQK8t5PPNx1ayTvA1ddRVo7bAucZridB58oAGKeTrlUSI9BWHvQqoaC9gXoR9d3ZBkxxlD6kRVBq+AtnLokgLVQmClxGDEUIUGtBIA56BueBBpb+7yH2WufaRjlhnIR6Cz1Gd5MZQc1A3c4pT5eU4/XEIB/aiAkXrUOQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1g2ZbypxoUEz6cYG98C2flkzmc8Z0WltlWDILpQnb/tK+WQMGLVWDihq6+JmQmLHj6A7JBUU1Xnqu8hKhwF9+FwZnIR20EjVgjKBd9umQu0J/uZ0NdDnsXqH//RUmwhm9590RA7FrmRl0Q4HBxme4AlXMIf/PSyQcspHQdH+czfGoOBhwwSEFjY52vnlBfiIG62fDlO5hEdWwlVKs+xHWOu50YtDrNy80C6erU2Sb6oUvTAOxwd3a+85yDYhw8GoTO51dqoJ13iGsx2wW9SOTYtSyAWCvSf9nc7//sy8hiwl37hSUT6kjT6IAvu9mTM0spY9G1wKsGfEbinkzXiBmwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://wenxin.jinghuiyang.top/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://wenxin.jinghuiyang.top/returnUrl";


    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C://";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

