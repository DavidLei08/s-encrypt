package club.dlblog.encrypt.sha;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ConcurrentHashMap;

public class ShaUtil {

    private static ConcurrentHashMap<String, MessageDigest> SHA_MAP = new ConcurrentHashMap<>();

    /***
     * sha加密算法类型
     */
    enum ShaType {
        SHA256("SHA-256"),
        SHA1("SHA-1");
        public String value;

        ShaType(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }

    /***
     * sha默认加密方式
     * @param source 加密数据
     * @return 加密后数据
     */
    public static String encrypt(String source) {
        return encrypt(source, ShaType.SHA256);
    }

    /**
     * sha加密
     * @param source 加密数据
     * @param shaType 加密算法
     * @return 加密后数据
     */
    public static String encrypt(String source, ShaType shaType) {
        try {
            // 获取SHA的消息摘要
            MessageDigest messageDigest = getSHA(shaType);
            // 获取指定编码的字符串字节数组
            byte[] sourceBytes = source.getBytes("UTF-8");
            // 对指定字节数据进行更新
            byte[] digestBytes = messageDigest.digest(sourceBytes);

            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < digestBytes.length; i++) {
                int val = (digestBytes[i] & 0xff);
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }

            return hexValue.toString();
        } catch (UnsupportedEncodingException e) {
            return null;// 获取指定编码的字符串字节数组失败
        }
    }

    /**
     * 获取sha加密用实例
     * @param shaType
     * @return
     */
    private static MessageDigest getSHA(ShaType shaType) {
        if (SHA_MAP.contains(shaType.getValue())) {
            return SHA_MAP.get(shaType.getValue());
        } else {
            try {
                MessageDigest sha = MessageDigest.getInstance(shaType.getValue());
                SHA_MAP.put(shaType.getValue(),sha);
                return sha;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
