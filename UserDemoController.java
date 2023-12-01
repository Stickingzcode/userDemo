package com.user.userDemo;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class UserDemoController {
    private static final String message = "src/main/resources/message.txt";

    private Path getPathFile(String pathFileName){
        return Paths.get(pathFileName);
    }

    private void logActivity() throws IOException{
        String log = "src/main/resources/log.txt";
        Files.write(getPathFile(log), "New message created".concat("\n").getBytes());
    }

    @PostMapping("/save/message")
    public void saveMessage(@RequestBody String messageBody) throws IOException {
        Files.write(getPathFile(message), messageBody.concat("\n").getBytes());
        logActivity();
    }

    @GetMapping("/all/message")
    public List<String> getMessage() throws IOException{
        return Files.readAllLines(getPathFile(message));
    }

    @GetMapping("/count/message")
    public int getMessageCount() throws IOException{
        return Files.readAllLines(getPathFile(message)).size() -2;
    }

}
