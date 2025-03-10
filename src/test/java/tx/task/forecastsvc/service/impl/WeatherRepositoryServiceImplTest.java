//package tx.task.forecastsvc.service.impl;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import tx.task.forecastsvc.model.WeatherHistory;
//import tx.task.forecastsvc.repository.WeatherRepository;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class WeatherRepositoryServiceImplTest {
//
//    @Mock
//    private WeatherRepository weatherRepository;
//
//    @InjectMocks
//    private WeatherRepositoryServiceImpl forecastRepositoryService;
//
//    private WeatherHistory weatherHistory;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void test_saveForecastHistory() {
//        weatherHistory = weatherHistory.builder()
//                .id(1L)
//                .cityName("abc")
//                .build();
//        when(weatherRepository.save(any(WeatherHistory.class))).thenReturn(weatherHistory);
//
//        WeatherHistory savedForecast = forecastRepositoryService.save(weatherHistory);
//
//        assertNotNull(savedForecast);
//        assertEquals(1L, savedForecast.getId());
//        assertEquals("abc", savedForecast.getCityName());
//
//        verify(weatherRepository, times(1)).save(any(WeatherHistory.class));
//    }
//}
