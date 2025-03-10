package tx.task.forecastsvc.service;

import tx.task.forecastsvc.service.model.WeatherResponse;

public interface ForecastService {
    WeatherResponse getWeatherFromForecastSvc(String cityName);
}
