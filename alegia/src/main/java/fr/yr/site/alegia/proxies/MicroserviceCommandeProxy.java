package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceCommande
 */
@FeignClient(value = "microservice-commande", url = "localhost:9001")
public interface MicroserviceCommandeProxy {
}
