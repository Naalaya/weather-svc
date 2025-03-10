package tx.task.forecastsvc.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tx.task.forecastsvc.model.ConsumerAudit;

@Slf4j
@RequiredArgsConstructor
public class RegexMessageParser implements MessageParser {

    private final ObjectMapper objectMapper;

    @Override
    public ConsumerAudit parseAuditLog(String message) {
        try {

            log.info("Audit: {}",objectMapper.readValue(message, ConsumerAudit.class));
            ConsumerAudit node = objectMapper.readValue(message, ConsumerAudit.class);

            ConsumerAudit audit = ConsumerAudit.builder()
                    .id(node.getId())
                    .state(node.getState())
                    .event_type(node.getEvent_type())
                    .created_at(node.getCreated_at())
                    .content(objectMapper.writeValueAsString(node.getContent()))
                    .build();
            return audit;
        } catch (Exception e) {
            log.error("Error while parsing message: {}", e.getMessage(), e);
            return null;
        }
    }
}
