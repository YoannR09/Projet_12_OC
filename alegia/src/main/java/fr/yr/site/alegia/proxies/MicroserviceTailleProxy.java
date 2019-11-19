package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceTaille
 */
@FeignClient(value = "microservice-taille", url = "localhost:9095")
public interface MicroserviceTailleProxy {
}
