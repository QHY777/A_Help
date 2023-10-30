package test;

/**
 * @Description:
 * @Author: Qi Hongyu
 * @CreateDate: 2023/9/24
 */

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignUtils {
    private SignUtils() {
    }

    private static final String DIGEST_TYPE = "HmacSha256";

    private static byte[] digest(String msg, String encryptKey, Charset charset)
            throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = encryptKey.getBytes(charset);
// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(key, DIGEST_TYPE);
// 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(DIGEST_TYPE);
// 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = msg.getBytes(charset);
// 完成 Mac 操作
        return mac.doFinal(text);
    }

    /***
     * 获取签名
     *
     * @param requestBody http请求body
     * @param token 调用接口getAccessToken获取的token,即请求参数中authInfo.accessToken
     * @return 签名
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String getSign(String requestBody, String token) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] signBytes = digest(requestBody, token, StandardCharsets.UTF_8);
        return hexEncoding(signBytes);
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String hexEncoding(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
//1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString().toUpperCase();
    }

    // 测试示例，期望生成的签名sign=0C760BEC0BFFAC39F4D75FB7894932F5194EB4EB9B97E4880FE1F1DD0760EFB3
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String token = "650fe1cca0c3b0e4e806decf";
        String sign = SignUtils.getSign("{\"authInfo\":{\"AppKey\":\"obk_zdhx\",\"accessToken\":\"650fe1cca0c3b0e4e806decf\"},\"apiName\":\"QueryBusOrderSettlementService\",\"requestContent\":{\"AccountID\":\"702319\",\"BatchNo\":\"zdhx_702319_20230801\"}}",token);
        System.out.println(sign);
    }
}