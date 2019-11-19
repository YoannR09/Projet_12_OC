package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceNiveauAcces
 */
@FeignClient(value = "microservice-niveau", url = "localhost:9095")
public interface MicroserviceNiveauAccesProxy {
}
