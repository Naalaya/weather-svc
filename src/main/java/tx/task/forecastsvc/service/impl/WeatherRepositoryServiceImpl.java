package tx.task.forecastsvc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tx.task.forecastsvc.model.WeatherHistory;
import tx.task.forecastsvc.repository.WeatherRepository;
import tx.task.forecastsvc.service.WeatherRepositoryService;

@Service
@RequiredArgsConstructor
public class WeatherRepositoryServiceImpl implements WeatherRepositoryService {

    private final WeatherRepository weatherRepository;

//    public WeatherRepositoryServiceImpl(WeatherRepository weatherRepository) {
//        this.weatherRepository = weatherRepository;
//    }

    @Override
    public WeatherHistory save(WeatherHistory record) {
        return weatherRepository.save(record);
    }
}

