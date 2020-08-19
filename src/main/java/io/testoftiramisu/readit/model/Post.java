package io.testoftiramisu.readit.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long postId;

  @NotBlank(message = "Post name can't be empty or Null")
  private String postName;

  @Nullable private String url;

  @Nullable @Lob // persistent property should be persisted as a large object
  private String description;

  private Integer voteCount;

  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;

  private Instant createDate;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "id", referencedColumnName = "id")
  private SubReadit subReadIt;
}
