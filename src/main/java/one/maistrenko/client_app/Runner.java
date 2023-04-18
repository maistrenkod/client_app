package one.maistrenko.client_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

@Component("runner")
@Slf4j
public class Runner implements CommandLineRunner {
    static String URL = "http://localhost:8090/api/v1/message/update";

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private MessageServiceImpl service;

    @Override
    public void run(String... args) throws Exception {

        whiteNoiseLengthSender();

    }

    public void poissonLengthSender() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        int meanLength = 50;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int length = (int) Math.floor(random.nextGaussian() * meanLength + meanLength);
            String body = service.randomGenerateString(length, true, true);
            HttpStatus statusCode = restTemplate.postForEntity(URL, new HttpEntity<>(body, headers), String.class).getStatusCode();
            if (!statusCode.is2xxSuccessful()) {
                throw new Exception("something went wrong");
            }
            wait(10);
        }
    }

    public void whiteNoiseLengthSender() throws Exception {
        int max = 110;
        int min = 90;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        int meanLength = 50;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int length = random.nextInt(max - min) + min;
            String body = service.randomGenerateString(length, true, true);
            HttpStatus statusCode = restTemplate.postForEntity(URL, new HttpEntity<>(body, headers), String.class).getStatusCode();
            if (!statusCode.is2xxSuccessful()) {
                throw new Exception("something went wrong");
            }
            wait(10);
        }
    }

    //should last about 17 min
    public void whiteNoiseTimeSender() throws Exception {
        String body = service.randomGenerateString(100, true, true);
        int max = 110;
        int min = 90;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int delay = random.nextInt(max - min) + min;
            HttpStatus statusCode = restTemplate.postForEntity(URL, new HttpEntity<>(body, headers), String.class).getStatusCode();
            if (!statusCode.is2xxSuccessful()) {
                throw new Exception("something went wrong");
            }
            wait(delay);
        }
    }

    //should last about 17 min
    public void poissonTimeSender() throws Exception {
        String body = service.randomGenerateString(100, true, true);
        int meanLength = 100;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int delay = (int) Math.floor(random.nextGaussian() * meanLength + meanLength);
            HttpStatus statusCode = restTemplate.postForEntity(URL, new HttpEntity<>(body, headers), String.class).getStatusCode();
            if (!statusCode.is2xxSuccessful()) {
                throw new Exception("something went wrong");
            }
            wait(delay);
        }
    }
}

