package tx.task.forecastsvc.service;

import tx.task.forecastsvc.model.WeatherHistory;
import tx.task.forecastsvc.service.model.ForecastResponse;

public interface ForecastConverterService {
    WeatherHistory convertToRecord(ForecastResponse response);
}

