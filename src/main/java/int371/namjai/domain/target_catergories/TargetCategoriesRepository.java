package int371.namjai.domain.target_catergories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetCategoriesRepository extends JpaRepository<TargetCategories, String> {
}
