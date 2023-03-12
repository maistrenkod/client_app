package one.maistrenko.client_app;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class MessageDescriptionServiceImpl {

    @Autowired
    private MessageDescriptionRepository repository;

    public String randomGenerateString(int length, boolean useLetters, boolean useNumbers){
        String str = RandomStringUtils.random(length, useLetters, useNumbers);
        log.info("Generated String {}", str);
        return str;
    }

    public MessageDescription randomSend(){
        Random random = new Random();
        int length = Math.abs(random.nextInt(255));
        MessageDescription messageDescription = new MessageDescription();
        messageDescription.setMessage(randomGenerateString(length, true, true));
        messageDescription.setLength(length);
        return messageDescription;
    }

    public MessageDescription sendWithLength(int length){
        MessageDescription messageDescription = new MessageDescription();
        messageDescription.setMessage(randomGenerateString(length, true, true));
        messageDescription.setLength(length);
        return messageDescription;
    }

    public MessageDescription sendWithLengthBetween(int min, int max){
        Random random = new Random();
        int length =  random.nextInt(max - min) + min;
        MessageDescription messageDescription = new MessageDescription();
        messageDescription.setMessage(randomGenerateString(length, true, true));
        messageDescription.setLength(length);
        return messageDescription;
    }

    public MessageDescription createMessageDescription(MessageDescription messageDescription){
        return repository.saveAndFlush(messageDescription);
    }

}
