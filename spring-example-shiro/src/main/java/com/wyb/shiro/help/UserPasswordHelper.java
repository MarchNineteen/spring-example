package com.wyb.shiro.help;

import com.wyb.shiro.dao.model.UserDo;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 1;

    /**
     * @param randomNumberGenerator 私盐生成器
     * @param algorithmName 加密算法
     * @param hashIterations 加密次数
     */
    public UserPasswordHelper(RandomNumberGenerator randomNumberGenerator, String algorithmName, int hashIterations) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.algorithmName = algorithmName;
        this.hashIterations = hashIterations;
    }

    public UserPasswordHelper() {
    }

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public String encryptPassword(UserDo userDo) {

        userDo.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                userDo.getPassword(),
                ByteSource.Util.bytes(userDo.getSalt()),
                hashIterations).toHex();

        return newPassword;
    }

    public static void main(String[] args) {
        UserPasswordHelper userPasswordHelper = new UserPasswordHelper();
        UserDo userDo = new UserDo();
        userDo.setPassword("123456");
        System.out.printf(userPasswordHelper.encryptPassword(userDo));
    }
}
