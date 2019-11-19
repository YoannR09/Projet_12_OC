package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceArticle
 */
@FeignClient(value = "microservice-statut", url = "localhost:9095")
public interface MicroserviceStatut {
}
