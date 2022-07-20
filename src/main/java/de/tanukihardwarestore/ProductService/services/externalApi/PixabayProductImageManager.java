package de.tanukihardwarestore.ProductService.services.externalApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tanukihardwarestore.ProductService.services.externalApi.reponse.PixabayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PixabayProductImageManager implements ProductImageManager {

    @Autowired
    private RestTemplate restTemplate;

    private String API_ENDPOINT = "https://pixabay.com/api/";
    private String API_KEY = "3366866-4dd763c62e27f0670efe79b42";
    private String KEYWORD = "computer";

    private PixabayResponse pixabayResponse;

    @Override
    public String getImage(long product_id) {
        String connectionString = getConnectionString(KEYWORD);

        return fetchImageFromPixabay(connectionString, product_id);
    }

    private String getConnectionString(String keyword) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(API_ENDPOINT);
        stringBuilder.append("?key=");
        stringBuilder.append(API_KEY);
        stringBuilder.append("&q=");
        stringBuilder.append(keyword);
        stringBuilder.append("&image_type=photo");

        return stringBuilder.toString();
    }

    private String fetchImageFromPixabay(String connectionString, long product_id) {
        if (this.pixabayResponse == null) {
            ResponseEntity<String> response = restTemplate.getForEntity(connectionString, String.class);

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                this.pixabayResponse = objectMapper.readValue(response.getBody(), PixabayResponse.class);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
        int index = (int) (product_id % pixabayResponse.getHits().size());

        String imageURL = pixabayResponse.getHits().get(index).getLargeImageURL();

        return imageURL;
    }



}
