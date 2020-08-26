package io.testoftiramisu.readit.controller;

import io.testoftiramisu.readit.dto.SubreaditDto;
import io.testoftiramisu.readit.service.SubreaditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubreaditController {
  private final SubreaditService subreaditService;

  @PostMapping
  public ResponseEntity<SubreaditDto> createSubreadit(@RequestBody SubreaditDto subreaditDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(subreaditService.save(subreaditDto));
  }

  @GetMapping
  public void getAllSubreadits() {
    ResponseEntity.status(HttpStatus.OK).body(subreaditService.getAll());
  }
}
