package com.shimh.security;

import com.shimh.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author: shimh
 * @create: 2018年12月
 **/
public class SecurityUtil {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static User getCurrentUser() {
        Object user = SecurityUtils.getSubject().getPrincipal();
        return null == user ? null : (User)user;
    }

    public static void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = getEncryptPassword(user.getPassword(), user.getSalt());

        user.setPassword(newPassword);
    }

    public static String getEncryptPassword(String password, String salt) {

        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(salt),
                hashIterations).toHex();

        return newPassword;
    }

}
