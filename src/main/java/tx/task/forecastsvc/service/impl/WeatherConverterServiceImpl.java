package tx.task.forecastsvc.service.impl;

import org.springframework.stereotype.Component;
import tx.task.forecastsvc.model.WeatherHistory;
import tx.task.forecastsvc.service.WeatherConverterService;
import tx.task.forecastsvc.service.model.WeatherResponse;

@Component
public class WeatherConverterServiceImpl implements WeatherConverterService {

    @Override
    public WeatherHistory convertToRecord(WeatherResponse response) {
        return WeatherHistory.builder()
                .cityName(response.getCityName())
//                .cod()
                .message(response.getMsg())
                .weatherMain(response.getWeather().getWeather_main())
                .weatherDescription(response.getWeather().getWeather_description())
                .temperature(response.getTemperature().getTemp_main())
                .temperatureMin(response.getTemperature().getTemp_min())
                .temperatureMax(response.getTemperature().getTemp_max())
                .humidity(response.getTemperature().getHumidity())
                .pressure(response.getTemperature().getPressure())
                .windSpeed(response.getWind().getSpeed())
                .windDeg(response.getWind().getDirection())
                .cloudPercentage(response.getCloud_percentage())
                .sunrise(response.getSun_sys().getDawn())
                .sunset(response.getSun_sys().getGust())
                .build();
    }
}
