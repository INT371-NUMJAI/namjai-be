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
}
