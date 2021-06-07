# s-encrypt

![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cuZGxibG9nLmNsdWIvZmlsZS8yMDIwMDQyNS9welBtaUhzay5wbmc?x-oss-process=image/format,png)

||加密方式| 安全性 | 是否可逆 |
|--|--|--|--|
| 1 | MD5 | 高| 不可逆|
| 2 | SHA | 高| 不可逆|
| 3 | AES | 中| 可逆|
| 4 | RSA | 高| 可逆|
| 5 | Base64 | 低| 可逆|
***
### 1.MD5加密

> MD5即Message-Digest Algorithm 5（信息-摘要算法5），用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法）
 
### 2.SHA加密 
> sha加密算法是安全哈希算法（Secure Hash Algorithm）的简称，主要适用于数字签名标准（Digital Signature Standard DSS）里面定义的数字签名算法（Digital Signature Algorithm DSA）。主要包括SHA-1，SHA-224，SHA-256，SHA-384，和SHA-512这几种单向散列算法。本篇就利用jdk自带的方法实现其中的几种加密算法。

### 3.AES加密

> 高级加密标准(AES,Advanced Encryption Standard)为最常见的对称加密算法(微信小程序加密传输就是用这个加密算法的)。也就是我们常说的对称密钥加密，加密和解密用相同的密钥。

### 4.RSA加密

> RSA是一种非对称加密算法。现在，很多登陆表单的密码的都采用RSA加密，例如京东中的登陆使用公钥对密码进行加密。

### 5.Base64加密

> Base64编码是从二进制到字符的过程，可用于在HTTP环境下传递较长的标识信息。采用Base64编码具有不可读性，需要解码后才能阅读
