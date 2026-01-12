package com.finhub.framework.shiro.authz;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * shiro 密码加密配置
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@Slf4j
public class PasswordHash {

    private String algorithmName;

    private int hashIterations;

    public static PasswordHash me() {
        PasswordHash passwordHash = new PasswordHash();

        passwordHash.setAlgorithmName("md5");
        passwordHash.setHashIterations(1);

        return passwordHash;
    }

    public String toHex(Object source, Object salt) {
        return hashByShiro(algorithmName, source, salt, hashIterations);
    }

    /**
     * 使用shiro的hash方式
     *
     * @param algorithmName  算法
     * @param source         源对象
     * @param salt           加密盐
     * @param hashIterations hash次数
     * @return 加密后的字符
     */
    private String hashByShiro(String algorithmName, Object source, Object salt, int hashIterations) {
        return new SimpleHash(algorithmName, source, salt, hashIterations).toHex();
    }
}
