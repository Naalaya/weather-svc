package tx.task.forecastsvc.service;

import tx.task.forecastsvc.service.model.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeatherByCityName(String cityName);
}
