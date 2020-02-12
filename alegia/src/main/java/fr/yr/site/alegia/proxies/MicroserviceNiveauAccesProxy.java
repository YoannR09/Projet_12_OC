package fr.yr.site.alegia.proxies;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Classe pour récupérer les données du MicroserviceNiveauAcces
 */
@FeignClient(name = "zuul-server", url = "localhost:9004")
public interface MicroserviceNiveauAccesProxy {
}
