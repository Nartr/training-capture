package ch.oester.robin.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

  @GetMapping(value = "/principal")
  @ResponseBody
  public PrincipalDto principal() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication == null ? null : authentication.getName();
    PrincipalDto dto = new PrincipalDto();
    dto.setUsername(username);
    return dto;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }
}
