package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceCompte
 */
@FeignClient(value = "microservice-compte", url = "localhost:9095")
public interface MicroserviceCompteProxy {
}
