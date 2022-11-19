package int371.namjai.domain.user_suggestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_suggestion")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSuggestion {
  @EmbeddedId
  private UserSuggestionId userSuggestionId;
}
