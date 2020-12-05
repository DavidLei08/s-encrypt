package club.dlblog.encrypt.base64;import java.util.Base64;/** * Base64解码编码工具类 */public class Base64Util {    //解码器    private static final Base64.Decoder decoder = Base64.getDecoder();    //编码器    private static final Base64.Encoder encoder = Base64.getEncoder();    /**     * Base64编码     * @param data     * @return     */    public static String encode(String data){        return encoder.encodeToString(data.getBytes());    }    /**     * Base64解码     * @param data     * @return     */    public static String decode(String data){        return new String(decoder.decode(data.getBytes()));    }}