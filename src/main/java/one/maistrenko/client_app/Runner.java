package one.maistrenko.client_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Scanner;

@Component("runner")
@Slf4j
public class Runner implements CommandLineRunner {
    static String URL = "http://localhost:8090/api/v1/message/update";

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private MessageDescriptionServiceImpl service;

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        while(true){
            System.out.println("Enter command:");
            switch (scanner.nextLine()){
                case "send-random":
                    sendRandom();
                    break;
                case "send-with-length-between":
                    sendWithLengthBetween(scanner1);
                    break;
                case "send-with-length":
                    sendWithLength(scanner1);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("This command not exists. Try again.");
            }
        }

    }

    public void sendRandom(){
        MessageDescription messageDescription = service.randomSend();
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        messageDescription.setSendTime(LocalDateTime.now());
        service.createMessageDescription(messageDescription);
        HttpEntity<MessageDescription> entity = new HttpEntity<>(messageDescription, headers);
        HttpStatus statusCode = restTemplate.postForEntity(URL, entity, MessageDescription.class).getStatusCode();
        System.out.println(statusCode);
    }

    public void sendWithLength(Scanner scanner){
        System.out.println("Enter length of the message:");
        int length = scanner.nextInt();
        MessageDescription messageDescription = service.sendWithLength(length);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        messageDescription.setSendTime(LocalDateTime.now());
        service.createMessageDescription(messageDescription);
        HttpEntity<MessageDescription> entity = new HttpEntity<>(messageDescription, headers);
        HttpStatus statusCode = restTemplate.postForEntity(URL, entity, MessageDescription.class).getStatusCode();
        System.out.println(statusCode);

    }

    public void sendWithLengthBetween(Scanner scanner){
        System.out.println("Enter min length:");
        int min = scanner.nextInt();
        System.out.println("Enter max length:");
        int max = scanner.nextInt();
        MessageDescription messageDescription = service.sendWithLengthBetween(min, max);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        messageDescription.setSendTime(LocalDateTime.now());
        service.createMessageDescription(messageDescription);
        HttpEntity<MessageDescription> entity = new HttpEntity<>(messageDescription, headers);
        HttpStatus statusCode = restTemplate.postForEntity(URL, entity, MessageDescription.class).getStatusCode();
        System.out.println(statusCode);

    }
}

