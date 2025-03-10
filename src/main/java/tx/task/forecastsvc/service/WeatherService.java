package tx.task.forecastsvc.service;

import org.springframework.http.ResponseEntity;
import tx.task.forecastsvc.service.model.WeatherResponse;


public interface WeatherService {
    ResponseEntity<WeatherResponse> getWeatherByCityName(String cityName);
}


