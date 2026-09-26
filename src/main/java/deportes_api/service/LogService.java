package deportes_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private static final Logger logger =
            LoggerFactory.getLogger(LogService.class);

    public void registrarInfo(String mensaje) {
        logger.info(mensaje);
    }

    public void registrarWarn(String mensaje) {
        logger.warn(mensaje);
    }

    public void registrarError(String mensaje) {
        logger.error(mensaje);
    }
}