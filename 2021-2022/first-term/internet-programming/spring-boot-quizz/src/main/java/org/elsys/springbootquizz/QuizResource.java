package org.elsys.springbootquizz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizResource {
    @GetMapping("/quiz/{question}")
    public String quiz(@PathVariable String question) {
        if (question.equals("How are you?")) {
            return "I am fine";
        }
    }
}
