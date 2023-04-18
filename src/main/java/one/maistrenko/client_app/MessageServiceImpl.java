package one.maistrenko.client_app;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl {

    public String randomGenerateString(int length, boolean useLetters, boolean useNumbers){
        String str = RandomStringUtils.random(length, useLetters, useNumbers);
        log.info("Generated String {}", str);
        return str;
    }
}
