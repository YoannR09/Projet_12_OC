package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceImage
 */
@FeignClient(value = "microservice-image", url = "localhost:9095")
public interface MicroserviceImageProxy {
}
