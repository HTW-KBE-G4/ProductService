package de.tanukihardwarestore.ProductService.services.externalApi.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PixabayResponse implements Serializable {

    private String total;

    private String totalHits;

    private List<Hit> hits;
}
