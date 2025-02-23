package tx.task.forecastsvc.service;

import tx.task.forecastsvc.model.WeatherHistory;

public interface WeatherRepositoryService {
    WeatherHistory save(WeatherHistory record);
}

