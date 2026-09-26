package deportes_api.service;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

import deportes_api.repository.JugadorRepository;

@Service
public class MetricasService {

    public MetricasService(
            MeterRegistry meterRegistry,
            JugadorRepository jugadorRepository) {

        Gauge.builder(
                "deportes.jugadores.total",
                jugadorRepository,
                repository -> repository.count()
        )
        .description("Cantidad total de jugadores registrados")
        .register(meterRegistry);
    }
}
