package tx.task.forecastsvc.service;

import tx.task.forecastsvc.model.WeatherHistory;
import tx.task.forecastsvc.service.model.WeatherResponse;

public interface ForecastConverterService {
    WeatherHistory convertToRecord(WeatherResponse response);
}

