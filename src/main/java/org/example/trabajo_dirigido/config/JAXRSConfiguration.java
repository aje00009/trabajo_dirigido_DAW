package org.example.trabajo_dirigido.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configura la aplicación JAX-RS.
 * Todas las rutas de los controladores MVC estarán prefijadas por @ApplicationPath.
 */
@ApplicationPath("/mvc") // Define la ruta base para todos los recursos JAX-RS/MVC
public class JAXRSConfiguration extends Application {
}

