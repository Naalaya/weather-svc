package tx.task.forecastsvc.service;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tx.task.forecastsvc.model.ConsumerAudit;
import tx.task.forecastsvc.repository.ConsumerAuditRepository;
import tx.task.forecastsvc.service.parser.MessageParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class KafkaConsumerService {

    private final ConsumerAuditRepository consumerAuditRepository;
    private final MessageParser messageParser;

    @KafkaListener(topics = "forecastNotificationTopic", groupId = "forecastNotificationTopic")
    public void consumeMessage(String message) {
        try {
            ConsumerAudit audit = messageParser.parseAuditLog(message);
            consumerAuditRepository.save(audit);
//            WeatherResponse msg = objectMapper.readValue(message, WeatherResponse.class);
            log.info("Received message from Kafka: {}", audit);
        } catch (Exception e) {
            log.error("Error while processing Kafka message: {}", e.getMessage(), e);
        }
    }
}