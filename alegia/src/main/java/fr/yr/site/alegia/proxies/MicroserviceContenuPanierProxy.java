package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceContenuPanier
 */
@FeignClient(value = "microservice-contenu", url = "localhost:9095")
public interface MicroserviceContenuPanierProxy {
}
