package tx.task.forecastsvc.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import tx.task.forecastsvc.controller.model.WeatherRequest;
import tx.task.forecastsvc.service.ForecastService;
import tx.task.forecastsvc.service.model.ForecastResponse;

@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController {


    private final ForecastService forecastService;
    @GetMapping("/weather/byCity")
    // convert to @RequestBody?
    public ResponseEntity<?> getForecastByCityName(@RequestBody WeatherRequest request) {
        ResponseEntity<ForecastResponse> response = forecastService.getWeatherByCityName(request.getCityName());
        log.info("Function called: {}, cityName: {}","getWeatherByCityName", request.getCityName());
        return response;
    }
//
//    @GetMapping("weather/byCityName")
//    public ResponseEntity<?> getForecastByCityName(
//            @RequestBody (required = false) WeatherRequest forecastRequest
//    ) {}
}
