package ch.oester.robin.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping("/api/test")
  public MessageDto test() {
    MessageDto dto = new MessageDto();
    dto.setMessage("Testing the api");
    return dto;
  }
}

