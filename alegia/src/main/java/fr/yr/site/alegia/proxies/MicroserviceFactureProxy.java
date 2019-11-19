package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceFacture
 */
@FeignClient(value = "microservice-facture", url = "localhost:9095")
public interface MicroserviceFactureProxy {
}
