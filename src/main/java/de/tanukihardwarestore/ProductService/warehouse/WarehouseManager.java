package de.tanukihardwarestore.ProductService.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseManager implements ComponentManager {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * Map of Components fetched by WarehouseService
     */
    private Map<Long, PCComponent> componentList = new HashMap<>();

    @Override
    public Collection<PCComponent> getAll() { return this.componentList.values(); }

    @Override
    public PCComponent getByID(Long id) { return this.componentList.get(id); }

    @Override
    public boolean fetchData(String url) {

        if (this.componentList.size() > 0) {
            //list has already been fetched. no more need to...
            return true;
        }

        ResponseEntity<List<PCComponent>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PCComponent>>() { }
        );

        List<PCComponent> list = response.getBody();
        if (list != null) {
            for (PCComponent component:
                 list ) {
                this.componentList.put(component.getId(), component);
            }
            return true;
        }

        //got null reponse -> return false...
        return false;
    }
}
