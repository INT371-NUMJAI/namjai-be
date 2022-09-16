package int371.namjai.domain.foundation_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundationProjectRepository extends JpaRepository<FoundationProject, String> {

    //    @Query("select m from Movie m left join m.movieGenre g where g.genre_id = ?1")
//    List<Movie> findByMovieGenre(int genre_id);
//
    @Query("select fp from FoundationProject  fp left join fp.targetCategoriesSet t where t.targetCategoriesID = ?1")
    List<FoundationProject> findByTargetCategoriesSet(String targetCatID);


    @Query(value = "select * from fdn_projects fp where fp.status ='OPEN' order by random()  limit 6 ", nativeQuery = true)
    List<FoundationProject> findTop6AndStatusOpen();

//    List<FoundationProject> findTop6By();
}
