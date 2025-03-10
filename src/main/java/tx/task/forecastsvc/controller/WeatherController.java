package tx.task.forecastsvc.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import tx.task.forecastsvc.controller.model.WeatherRequest;
import tx.task.forecastsvc.service.WeatherService;
import tx.task.forecastsvc.service.model.WeatherResponse;

@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController {


    private final WeatherService weatherService;
    @GetMapping("/weather/byCity")
    public ResponseEntity<?> getForecastByCityName(@RequestBody WeatherRequest request) {
        ResponseEntity<WeatherResponse> response = weatherService.getWeatherByCityName(request.getCityName());
        log.info("Function called: {}, cityName: {}","getWeatherByCityName", request.getCityName());
        return response;
    }
}
