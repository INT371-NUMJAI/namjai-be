package int371.namjai.domain.bank_account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    List<BankAccount> findBankAccountsByFoundation_FdnUUid(String fdnUUID);
}
