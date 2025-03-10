package tx.task.forecastsvc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import tx.task.forecastsvc.model.WeatherHistory;
import tx.task.forecastsvc.repository.WeatherRepository;
import tx.task.forecastsvc.service.WeatherConverterService;
import tx.task.forecastsvc.service.WeatherService;
import tx.task.forecastsvc.service.ForecastService;
import tx.task.forecastsvc.service.model.WeatherResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final ForecastService forecastService;
    private final WeatherConverterService weatherConverter;
    private final WeatherRepository weatherRepository;

    @Override
    public ResponseEntity<WeatherResponse> getWeatherByCityName(String cityName) {
        try {
            WeatherResponse response = forecastService.getWeatherFromForecastSvc(cityName);

            assert response != null;
                response.setMsg("Get weather by City Name: " + cityName + " successfully");

                WeatherHistory weatherHistory = weatherConverter.convertToRecord(response);
                log.info("Weather for city: {}, msg: {}", response.getCityName(), response.getMsg());
            weatherRepository.save(weatherHistory);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (HttpClientErrorException exception) {
            log.error("Error: {}", exception.getResponseBodyAsString());
            WeatherHistory record = WeatherHistory.builder()
                    .cityName(cityName)
                    .cod(exception.getStatusCode().value())
                    .message(exception.getMessage())
                    .build();
            weatherRepository.save(record);
            throw exception;
        }
    }
}
