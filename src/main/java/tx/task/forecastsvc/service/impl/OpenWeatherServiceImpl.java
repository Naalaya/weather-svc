package tx.task.forecastsvc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tx.task.forecastsvc.service.WeatherService;
import tx.task.forecastsvc.service.model.ForecastResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenWeatherServiceImpl implements WeatherService {

    private final RestTemplate restTemplate;

    // should make it as an Object, and should not make an Unit Test for this function
    @Value("${openWeather-map.api.url}")
    private String forecastUrl;

    @Override
    public ForecastResponse getWeatherByCityName(String cityName) {

        String url = forecastUrl.replace("{city}", cityName);
        try {
            return restTemplate.getForObject(url, ForecastResponse.class);
        } catch (HttpClientErrorException exception) {
//            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error fetching data from OpenWeather");
            throw exception;
        } catch(Exception e) {
            log.error("New exception has occurred with msg: {} ",e.getMessage());
            throw e;
        }
    }
}

