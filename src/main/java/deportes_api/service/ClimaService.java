package deportes_api.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClimaService {

    private final RestClient restClient;

    public ClimaService() {
        this.restClient = RestClient.create("https://api.open-meteo.com");
    }

    public Map<String, Object> consultarClima(double latitud, double longitud) {

        try {

            Map<String, Object> respuesta = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/forecast")
                            .queryParam("latitude", latitud)
                            .queryParam("longitude", longitud)
                            .queryParam("current", "temperature_2m,relative_humidity_2m,wind_speed_10m")
                            .build())
                    .retrieve()
                    .body(Map.class);

            return respuesta;

        } catch (RestClientException e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "No fue posible consultar el servicio externo de clima",
                    e
            );
        }
    }
}