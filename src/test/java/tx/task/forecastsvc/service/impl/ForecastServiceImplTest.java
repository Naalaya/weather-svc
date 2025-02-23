package tx.task.forecastsvc.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import tx.task.forecastsvc.model.WeatherHistory;
import tx.task.forecastsvc.service.WeatherRepositoryService;
import tx.task.forecastsvc.service.ForecastConverterService;
import tx.task.forecastsvc.service.WeatherService;
import tx.task.forecastsvc.service.model.ForecastResponse;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ForecastServiceImplTest {

    @InjectMocks
    private ForecastServiceImpl forecastService;

    @Mock
    private WeatherService weatherService;

    @Mock
    private ForecastConverterService forecastConverter;

    @Mock
    private WeatherRepositoryService weatherRepositoryService;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private ConsumerFactory<String, Object> consumerFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        forecastService = new ForecastServiceImpl(weatherService, forecastConverter, weatherRepositoryService, kafkaTemplate, consumerFactory);
    }

    @Test
    void test_getWeatherByCityName_Success() {
        // Arrange
        String cityName = "Hà Nội";
        ForecastResponse forecastResponse = mock(ForecastResponse.class);
        when(weatherService.getWeatherByCityName(cityName)).thenReturn(forecastResponse);

        // Mocking the conversion and saving process
        WeatherHistory weatherHistory = mock(WeatherHistory.class);
        when(forecastConverter.convertToRecord(forecastResponse)).thenReturn(weatherHistory);
        when(forecastResponse.getName()).thenReturn(cityName);
        when(forecastResponse.getMessage()).thenReturn("Get forecast by City Name: " + cityName + " successfully");

        // Act
        ResponseEntity<ForecastResponse> response = forecastService.getWeatherByCityName(cityName);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cityName, response.getBody().getName());
        verify(weatherRepositoryService, times(1)).save(weatherHistory);
    }

//    @Test
//    void test_getWeatherByCityName_throws_HttpClientErrorException() {
//        String cityName = "anycity";
//        HttpClientErrorException exception = mock(HttpClientErrorException.class);
//
//        when(exception.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
//        when(weatherService.getWeatherByCityName(cityName)).thenThrow(exception);
//
//        // Act & Assert
//        HttpClientErrorException thrownException = assertThrows(HttpClientErrorException.class,
//                () -> forecastService.getWeatherByCityName(cityName));
//
//        // Assert the status code
//        assertEquals(HttpStatus.NOT_FOUND, thrownException.getStatusCode());
//
//        // assertEquals("Bad Request", thrownException.getMessage());
//
//        verify(weatherRepositoryService, times(1)).save(any(WeatherHistory.class));
//    }

}

