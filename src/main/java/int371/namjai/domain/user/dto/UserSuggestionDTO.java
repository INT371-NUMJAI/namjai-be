package int371.namjai.domain.user.dto;

import int371.namjai.domain.target_catergories.TargetCategories;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSuggestionDTO {
    private Set<TargetCategories> targetCategoriesSuggestion;
    private String userEmail;
}
