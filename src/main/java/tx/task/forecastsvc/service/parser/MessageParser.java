package tx.task.forecastsvc.service.parser;


import tx.task.forecastsvc.model.ConsumerAudit;

public interface MessageParser {
    ConsumerAudit parseAuditLog(String message);

//    WeatherResponse parseWeatherResponse(String content) throws Exception;
}