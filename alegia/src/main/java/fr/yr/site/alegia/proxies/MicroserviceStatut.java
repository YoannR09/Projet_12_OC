package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceArticle
 */
@FeignClient(name = "zuul-server", url = "192.168.1.61:9004")
public interface MicroserviceStatut {
}
