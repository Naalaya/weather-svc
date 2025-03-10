package tx.task.forecastsvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tx.task.forecastsvc.model.ConsumerAudit;

@Repository
public interface ConsumerAuditRepository extends JpaRepository<ConsumerAudit, Long> {
}
