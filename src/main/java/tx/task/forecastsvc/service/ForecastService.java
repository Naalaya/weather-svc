package tx.task.forecastsvc.service;

import org.springframework.http.ResponseEntity;
import tx.task.forecastsvc.service.model.WeatherResponse;


public interface ForecastService {
    ResponseEntity<WeatherResponse> getWeatherByCityName(String cityName);
}


