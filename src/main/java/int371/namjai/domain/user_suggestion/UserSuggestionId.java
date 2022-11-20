package int371.namjai.domain.user_suggestion;

import int371.namjai.domain.target_catergories.TargetCategories;
import int371.namjai.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class UserSuggestionId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "target_category_id")
    private TargetCategories targetCategories;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSuggestionId)) return false;
        UserSuggestionId that = (UserSuggestionId) o;
        return Objects.equals(getTargetCategories(), that.getTargetCategories()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTargetCategories(), getUser());
    }
}
