package int371.namjai.domain.withdrawal_request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, String> {

    @Query(value = "SELECT wd FROM WithdrawalRequest  wd WHERE UPPER(wd.foundationProject.foundation.email) LIKE UPPER(?1)")
    List<WithdrawalRequest> findWithdrawalRequestByFoundationEmail(String fdnEmail);
}
