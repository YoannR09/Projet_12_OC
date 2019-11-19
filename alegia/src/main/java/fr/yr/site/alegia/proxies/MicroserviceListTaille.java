package fr.yr.site.alegia.proxies;


import fr.yr.site.alegia.beans.ListTaille;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Classe pour récupérer les données du MicroserviceListTaille
 */
@FeignClient(value = "microservice-ligne", url = "localhost:9016")
public interface MicroserviceListTaille {

    @GetMapping(value = "/ListTaille/{id}")
    ListTaille findById(@PathVariable int id);



    @GetMapping(value = "/ListTaille/Categorie/{categorieId}")
    List<ListTaille> findByCategorieId(@PathVariable int categorieId);
}
