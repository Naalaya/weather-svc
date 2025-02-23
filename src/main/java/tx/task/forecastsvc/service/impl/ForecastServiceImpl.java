package tx.task.forecastsvc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionExecutionListener;
import org.springframework.web.client.HttpClientErrorException;
import tx.task.forecastsvc.model.WeatherHistory;
import tx.task.forecastsvc.service.WeatherRepositoryService;
import tx.task.forecastsvc.service.ForecastService;
import tx.task.forecastsvc.service.WeatherService;
import tx.task.forecastsvc.service.ForecastConverterService;
import tx.task.forecastsvc.service.model.ForecastResponse;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ForecastServiceImpl implements ForecastService {

    private final WeatherService weatherService;
    private final ForecastConverterService forecastConverter;
    private final WeatherRepositoryService weatherRepositoryService;
    private final KafkaTemplate<String ,Object> kafkaTemplate;
    private final ConsumerFactory<String, Object> consumerFactory;

    @Override
    @KafkaListener(topics = "forecastNotificationTopic")
    public ResponseEntity<ForecastResponse> getWeatherByCityName(String cityName) {

            // ...
            log.info(String.format("#### -> Consumed message -> %s", consumerFactory.createConsumer()));

        try {
            // Call the WeatherService to get forecast data from API
            ForecastResponse response = weatherService.getWeatherByCityName(cityName);

            assert response != null;

//            Optional.ofNullable(response)

            // Convert the response to RecordForecastHistory before saving it to the DB
            response.setMessage("Get weather by City Name: " + cityName + " successfully");
            WeatherHistory weatherHistory = forecastConverter.convertToRecord(response);
            log.info("Weather for city: {}, msg: {}", response.getName(), response.getMessage());

            // Save the forecast history record to the database
            weatherRepositoryService.save(weatherHistory);

            // Return the response to the client
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            log.error("Error: {}", exception.getResponseBodyAsString());
            // Handle exception and record the error in the database
            WeatherHistory record = WeatherHistory.builder()
                    .cityName(cityName)
                    .cod(exception.getStatusCode().value())
                    .message(exception.getResponseBodyAs(Object.class).toString())
                    .build();
            weatherRepositoryService.save(record);
            throw exception;
        }
    }
}
