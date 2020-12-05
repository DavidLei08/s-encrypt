package club.dlblog.encrypt.rsa;import org.apache.tomcat.util.codec.binary.Base64;import javax.crypto.Cipher;import javax.swing.*;import java.security.*;import java.security.interfaces.RSAPrivateKey;import java.security.interfaces.RSAPublicKey;import java.security.spec.PKCS8EncodedKeySpec;import java.security.spec.X509EncodedKeySpec;/** * RSA加解密工具类 * @author machenike */public class RASUtil {    /**     * 密钥对存储场所     */    private static KeyStore keyStore = new LocalKeyStore();    /**     * 当前线程持有的公钥     */    private static ThreadLocal<String> currentPublicKey = new ThreadLocal<>();    /**     * 生成RSA密钥对     * @return     * @throws NoSuchAlgorithmException     */    public static String[] generateKeyPair(){        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象        KeyPairGenerator keyPairGen = null;        try {            keyPairGen = KeyPairGenerator.getInstance("RSA");        } catch (NoSuchAlgorithmException e) {            e.printStackTrace();            e.printStackTrace();            return null;        }        // 初始化密钥对生成器，密钥大小为96-1024位        keyPairGen.initialize(1024,new SecureRandom());        // 生成一个密钥对，保存在keyPair中        KeyPair keyPair = keyPairGen.generateKeyPair();        // 得到私钥        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();        // 得到公钥        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));        // 得到私钥字符串        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));        // 将公钥和私钥保存到String数组        String[] result = new String[2];        result[0] = publicKeyString;        result[1] = privateKeyString;        return result;    }    /**     * 生成RSA密钥对     * @param keySize 密钥最大长度     * @return     * @throws NoSuchAlgorithmException     */    public static String[] generateKeyPair(Integer keySize){        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象        KeyPairGenerator keyPairGen = null;        try {            keyPairGen = KeyPairGenerator.getInstance("RSA");        } catch (NoSuchAlgorithmException e) {            e.printStackTrace();            return null;        }        // 初始化密钥对生成器，密钥大小为96-1024位        keyPairGen.initialize(keySize,new SecureRandom());        // 生成一个密钥对，保存在keyPair中        KeyPair keyPair = keyPairGen.generateKeyPair();        /* 得到私钥 */        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();        // 得到公钥        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));        // 得到私钥字符串        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));        // 将公钥和私钥保存到String数组        String[] result = new String[2];        result[0] = publicKeyString;        result[1] = privateKeyString;        return result;    }    /**     * RSA公钥加密     *     * @param str     *            加密字符串     * @param publicKey     *            公钥     * @return 密文     * @throws Exception     *             加密过程中的异常信息     */    public static String encrypt( String str, String publicKey ) throws Exception{        //base64编码的公钥        byte[] decoded = Base64.decodeBase64(publicKey);        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));        //RSA加密        Cipher cipher = Cipher.getInstance("RSA");        cipher.init(Cipher.ENCRYPT_MODE, pubKey);        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));        return outStr;    }    /**     * 使用私钥进行RSA解密     *     * @param str     *            加密字符串     * @param privateKey     *            私钥     * @return 铭文     * @throws Exception     *             解密过程中的异常信息     */    public static String decrypt(String str, String privateKey) throws Exception{        //64位解码加密后的字符串        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));        //base64编码的私钥        byte[] decoded = Base64.decodeBase64(privateKey);        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));        //RSA解密        Cipher cipher = Cipher.getInstance("RSA");        cipher.init(Cipher.DECRYPT_MODE, priKey);        String outStr = new String(cipher.doFinal(inputByte));        return outStr;    }    /**     * 通过当前的线程的公钥进行RSA私钥解密     *     * @param str     *            加密字符串     *            私钥     * @return 铭文     * @throws Exception     *             解密过程中的异常信息     */    public static String decrypt(String str) throws Exception{        //64位解码加密后的字符串        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));        //base64编码的私钥        byte[] decoded = Base64.decodeBase64(keyStore.getPrivateKey(currentPublicKey.get()));        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));        //RSA解密        Cipher cipher = Cipher.getInstance("RSA");        cipher.init(Cipher.DECRYPT_MODE, priKey);        String outStr = new String(cipher.doFinal(inputByte));        return outStr;    }    /**     * 获取当前线程得公共键     * @param currentPublicKey     */    public static void setCurrentPublicKey(String currentPublicKey){        RASUtil.currentPublicKey.set(currentPublicKey);    }    /**     * 设定存储密钥的实现     * @param keyStore     */    public static void setKeyStore(KeyStroke keyStore){        keyStore = keyStore;    }}