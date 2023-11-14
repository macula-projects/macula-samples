package dev.macula.samples.service2.utils;

import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code RSAUtil} RSA非对称密钥对工具类
 *
 * @author qyh
 * @since 2023/11/07 10:13
 */
public class RSAUtil {

    /**
     * 生成秘钥对密钥大小为2048位
     * @return 用Map包装的RSA密钥对
     */
    public static Map<String,String> getKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            String publicKey = byte2Base64(keyPair.getPublic().getEncoded());
            String privateKey = byte2Base64(keyPair.getPrivate().getEncoded());
            HashMap<String, String> map = new HashMap<>();
            map.put("publicKey", publicKey);
            map.put("privateKey", privateKey);
            return map;
        } catch (NoSuchAlgorithmException e){
            return new HashMap<>();
        }
    }

    /**
     * 字节数组转Base64编码
     * @param bytes 字节数组
     * @return Base64编码的字符
     */
    public static String byte2Base64(byte[] bytes){
        Base64.Encoder encoder = Base64.getEncoder();
        return new String(encoder.encode(bytes));
    }
}