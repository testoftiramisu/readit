package io.testoftiramisu.readit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class SubReadit {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotBlank(message = "Subreadit name is required")
  private String name;

  @NotBlank(message = "Description is required")
  private String description;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Post> posts;

  private Instant createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
