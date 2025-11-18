package io.jiangbyte.email;

import io.jiangbyte.app.EmailService;
import io.jiangbyte.app.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ZhangJiangHu
 * @version v1.0
 * @date 18/11/2025
 * @description TODO
 */
@SpringBootTest(classes = SystemApplication.class)
public class EmailTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() throws Exception {
    }
}
