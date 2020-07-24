package vn.chohoa.flower.util;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.Arrays;
import java.util.List;

public class PasswordUtil {

    public PasswordUtil() {
    }

    public static String makeRadomPassword() {

        /**
         * thiết lập quy tắc sinh mật khẩu:  CharacterRule() method
         *
         * EnglishCharacterData.Digit: kiểu số
         * password chứa ít nhất 1 số
         * */
        List rule = Arrays.asList(new CharacterRule(EnglishCharacterData.Digit, 1));

        /** tạo bộ khỏi tạo mật khẩu*/
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        /** sinh mật khẩu có độ đài tối thiểu là 6 tuân theo quy tắc rule*/
        String pass = passwordGenerator.generatePassword(6, rule);
        return pass;
    }
}
