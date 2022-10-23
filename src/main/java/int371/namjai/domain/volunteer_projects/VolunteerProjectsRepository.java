package int371.namjai.domain.volunteer_projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerProjectsRepository extends JpaRepository<VolunteerProjects, String> {

//    @Query(value = "select * from fdn_projects fp where fp.status ='OPEN' order by random()  limit 6 ", nativeQuery = true)
//    List<FoundationProject> findTop6AndStatusOpen();

//    @Query(value = "select * from volunteers_projects  vp  where fp.volunteer_projects_uuid ='OPEN' order by random()  limit 6 ", nativeQuery = true)


//    List<FoundationProject> findByfdnUUID(String fdnUUID);

}
