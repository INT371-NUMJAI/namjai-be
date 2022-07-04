package int371.namjai.domain.foundation_document;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoundationDocumentsRepo extends JpaRepository<FoundationDocuments,String> {
    Optional<FoundationDocuments> findByFoundationUUid(String fdnUUid);
}
