package com.gem.gateway.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy")
public class ProxyController {
  private final RestTemplate rest = new RestTemplate();

  private static final String ORDER = "http://localhost:8081/api";
  private static final String INVENTORY = "http://localhost:8082/api";
  private static final String PRODUCT = "http://localhost:8083/api";
  private static final String NOTIFY = "http://localhost:8084/api";

  @GetMapping("/orders")
  public ResponseEntity<String> orders(HttpServletRequest req) { return forward(req, ORDER + "/orders"); }

  @PostMapping("/orders")
  public ResponseEntity<String> createOrder(HttpServletRequest req, @RequestBody String body) {
    return forwardWithBody(req, ORDER + "/orders", body, HttpMethod.POST);
  }

  @GetMapping("/orders/{id}")
  public ResponseEntity<String> orderById(HttpServletRequest req, @PathVariable String id) {
    return forward(req, ORDER + "/orders/" + id);
  }

  @PostMapping("/orders/{id}/cancel")
  public ResponseEntity<String> cancel(HttpServletRequest req, @PathVariable String id) {
    return forwardWithBody(req, ORDER + "/orders/" + id + "/cancel", "{}", HttpMethod.POST);
  }

  @GetMapping("/inventory/stock")
  public ResponseEntity<String> stock(HttpServletRequest req) { return forward(req, INVENTORY + "/inventory/stock"); }

  @GetMapping("/products")
  public ResponseEntity<String> products(HttpServletRequest req) { return forward(req, PRODUCT + "/products"); }

  @GetMapping("/notifications")
  public ResponseEntity<String> notifications(HttpServletRequest req) { return forward(req, NOTIFY + "/notifications"); }

  private ResponseEntity<String> forward(HttpServletRequest req, String url) {
    HttpHeaders headers = new HttpHeaders();
    String auth = req.getHeader(HttpHeaders.AUTHORIZATION);
    if (auth != null) headers.set(HttpHeaders.AUTHORIZATION, auth);
    return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
  }

  private ResponseEntity<String> forwardWithBody(HttpServletRequest req, String url, String body, HttpMethod method) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    String auth = req.getHeader(HttpHeaders.AUTHORIZATION);
    if (auth != null) headers.set(HttpHeaders.AUTHORIZATION, auth);
    return rest.exchange(url, method, new HttpEntity<>(body, headers), String.class);
  }
}
