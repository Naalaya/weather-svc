package tx.task.forecastsvc.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="consumer_audit")
public class ConsumerAudit {

    @Id
    private long id;

    @Column(name="created_at")
    @CreationTimestamp
    private Timestamp created_at;

    @Column(name="event_type")
    private String event_type;

    @Column(name="state")
    private String state;

    @Column(name="content", columnDefinition="LONGTEXT")
    private String content;
}
