package tx.task.forecastsvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import tx.task.forecastsvc.service.parser.MessageParser;
import tx.task.forecastsvc.service.parser.RegexMessageParser;

@Log4j2
@Configuration
@ComponentScan(basePackages = {"tx.task.forecastsvc"})
@AllArgsConstructor
public class AppConfig {

    private ObjectMapper objectMapper;
    @Bean
    public MessageParser messageParser() {
        return new RegexMessageParser(objectMapper);
    }

    @Bean
    public org.springframework.web.client.RestTemplate restTemplate() {
        return new org.springframework.web.client.RestTemplate();
    }
}
