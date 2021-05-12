package fontys.sem3.group.sioux.controller;

import fontys.sem3.group.sioux.logic.TextSender;
import fontys.sem3.group.sioux.model.SMS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/texts")
public class TextController {
    private static final TextSender textSender = new TextSender();
    public TextController() {}

    @PostMapping()
    public ResponseEntity<SMS> sendSMS(@RequestBody SMS sms){
        textSender.sentText(sms);
        return new ResponseEntity("text send", HttpStatus.OK);
    }
}
