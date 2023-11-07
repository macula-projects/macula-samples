package dev.macula.samples.service2.utils;

import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    //生成秘钥对密钥大小为2048位
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

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes){
        Base64.Encoder encoder = Base64.getEncoder();
        return new String(encoder.encode(bytes));
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException{
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64Key);
    }
}