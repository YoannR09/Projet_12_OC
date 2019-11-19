package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceAdresse
 */
@FeignClient(value = "microservice-adresse", url = "localhost:9095")
public interface MicroserviceAdresseProxy {
}
