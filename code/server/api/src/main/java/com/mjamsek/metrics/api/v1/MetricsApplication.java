package com.mjamsek.metrics.api.v1;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@OpenAPIDefinition(
    info = @Info(title = "Metrics Monitor Server", version = "0.0.1", contact = @Contact()),
    servers = @Server(url = "http://localhost:8080/v1")
)
public class MetricsApplication extends Application {
}
