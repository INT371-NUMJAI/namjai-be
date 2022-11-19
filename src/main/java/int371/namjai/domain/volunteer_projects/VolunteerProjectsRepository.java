package int371.namjai.domain.volunteer_projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerProjectsRepository extends JpaRepository<VolunteerProjects, String> {

    @Query("select vp.volunteerProjectName from VolunteerProjects  vp where vp.volunteerProjectsUUID = ?1 ")
    String getVolunteerName(String volunteerProjectUUID);


    List<VolunteerProjects> findVolunteerProjectsByFoundation_Email(String fdnEmail);

    @Query("select vp from VolunteerProjects  vp  where upper(vp.foundation.email) like  UPPER(?1) and vp.status='OPEN' order by vp.createDate desc ")
    List<VolunteerProjects> findVolunteerProjectsByFoundation_EmailAndStatusOpen(String email);


    @Query("select vp from VolunteerProjects  vp  where upper(vp.volunteerProjectName) like  concat('%',UPPER(?1),'%') and vp.status='OPEN'")
    List<VolunteerProjects> findVolunteerProjectsByVolunteerProjectNameContainingIgnoreCase(String volunteerName);

    List<VolunteerProjects> findAllByStatusIsOrderByEndDateDesc(String status);

    @Query("select vp from VolunteerProjects  vp left join vp.targetCategoriesSet t where t.targetCategoriesNameEn = ?1 and vp.status='OPEN' order by vp.createDate asc")
    List<VolunteerProjects> findByTargetCategoriesSet(String targetCatName);

    @Query(value = "SELECT v.* FROM user_suggestion u \n" +
            "JOIN volunteer_project_target_categories vt ON u.target_category_id=vt.target_category_id\n" +
            "JOIN volunteers_projects v ON vt.volunteer_projects_uuid=v.volunteer_projects_uuid\n" +
            "WHERE v.status ='OPEN' AND u.user_uuid=:userID\n" +
            "GROUP BY v.volunteer_projects_uuid,vt.target_category_id ORDER BY vt.target_category_id LIMIT 6", nativeQuery = true)
    List<VolunteerProjects> findVolunteerProjectsByUserSuggestion(@Param("userID") String userUUID);

}
