package tx.task.forecastsvc.service;

import tx.task.forecastsvc.service.model.ForecastResponse;

public interface WeatherService {
    ForecastResponse getWeatherByCityName(String cityName);
}
