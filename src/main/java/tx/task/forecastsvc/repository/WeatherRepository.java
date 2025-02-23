package tx.task.forecastsvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tx.task.forecastsvc.model.WeatherHistory;


public interface WeatherRepository extends JpaRepository<WeatherHistory, Long> {
}
