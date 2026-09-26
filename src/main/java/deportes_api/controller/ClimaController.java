package deportes_api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deportes_api.service.ClimaService;

@RestController
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping("/clima")
    public Map<String, Object> consultarClima(
            @RequestParam double latitud,
            @RequestParam double longitud) {

        return climaService.consultarClima(latitud, longitud);
    }
}
