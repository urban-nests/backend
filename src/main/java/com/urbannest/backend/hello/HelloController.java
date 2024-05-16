package com.urbannest.backend.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("hello this is sping boot");
    }

    @GetMapping("/name")
    public ResponseEntity<?> helloName() {
        return ResponseEntity.ok("Default Name");
    }

    @PostMapping("/name")
    public ResponseEntity<?> helloUser(@RequestBody Map<String, String> reqMap) {
        if (reqMap.containsKey("name")) {
            return ResponseEntity.ok("Hello "+reqMap.get("name"));
        }
        return ResponseEntity.badRequest().build();
    }
}
