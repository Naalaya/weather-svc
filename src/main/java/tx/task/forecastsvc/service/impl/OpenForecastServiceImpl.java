package tx.task.forecastsvc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tx.task.forecastsvc.service.ForecastService;
import tx.task.forecastsvc.service.model.WeatherResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenForecastServiceImpl implements ForecastService {

    private final RestTemplate restTemplate;
    @Value("${openWeather-map.api.url}")
    private String forecastUrl;

    @Override
    public WeatherResponse getWeatherFromForecastSvc(String cityName) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("City-Name", cityName);
        return restTemplate.exchange(
                        forecastUrl, HttpMethod.GET, new HttpEntity<>(headers),
                        WeatherResponse.class)
                .getBody();

    }
}

