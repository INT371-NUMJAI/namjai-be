package int371.namjai.domain.foundation_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoundationProjectRepository extends JpaRepository<FoundationProject, String> {

    @Query("select fp from FoundationProject  fp left join fp.targetCategoriesSet t where t.targetCategoriesID = ?1 and fp.status='OPEN'")
    List<FoundationProject> findByTargetCategoriesSet(String targetCatID);


    @Query(value = "select * from fdn_projects fp where fp.status ='OPEN' order by random()  limit 6 ", nativeQuery = true)
    List<FoundationProject> findTop6AndStatusOpen();

    @Query("select fp from FoundationProject  fp  where  fp.foundation.fdnUUid = ?1 ")
    List<FoundationProject> findByfdnUUID(String fdnUUID);

    @Query("select fp from FoundationProject  fp  where  UPPER(fp.foundation.email) = UPPER(?1) ")
    List<FoundationProject> findByFDNEmailIgnoreCase(String fdnEmail);
//    List<FoundationProject> findTop6By();
}
