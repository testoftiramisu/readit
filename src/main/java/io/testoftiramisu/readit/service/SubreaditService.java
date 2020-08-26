package io.testoftiramisu.readit.service;

import io.testoftiramisu.readit.dto.SubreaditDto;
import io.testoftiramisu.readit.model.Subreadit;
import io.testoftiramisu.readit.repository.SubreaditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubreaditService {
  private final SubreaditRepository subreaditRepository;

  @Transactional
  public SubreaditDto save(SubreaditDto subreaditDto) {
    Subreadit save = subreaditRepository.save(mapSubreaditDto(subreaditDto));
    subreaditDto.setId(save.getId());
    return subreaditDto;
  }

  @Transactional(readOnly = true)
  public List<SubreaditDto> getAll() {
    return subreaditRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
  }

  private SubreaditDto mapToDto(Subreadit subreadit) {
    return SubreaditDto.builder()
        .name(subreadit.getName())
        .id(subreadit.getId())
        .numberOfPosts(subreadit.getPosts().size())
        .build();
  }

  private Subreadit mapSubreaditDto(SubreaditDto subreaditDto) {
    return Subreadit.builder()
        .name(subreaditDto.getName())
        .description(subreaditDto.getDescription())
        .build();
  }
}
