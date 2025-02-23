package tx.task.forecastsvc.service;

import org.springframework.http.ResponseEntity;
import tx.task.forecastsvc.service.model.ForecastResponse;


public interface ForecastService {
    ResponseEntity<ForecastResponse> getWeatherByCityName(String cityName);
}


