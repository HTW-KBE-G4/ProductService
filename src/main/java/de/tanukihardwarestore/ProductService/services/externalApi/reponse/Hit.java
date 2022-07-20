package de.tanukihardwarestore.ProductService.services.externalApi.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hit implements Serializable {

    private long id;

    private String pageURL;

    private String type;

    private String tags;

    private String previewURL;

    private long previewWidth;

    private long previewHeight;

    private String webformatURL;

    private long webformatWidth;

    private long webformatHeight;

    private String largeImageURL;

    private String fullHDURL;

    private String imageURL;

    private long imageWidth;

    private long imageHeight;

    private long imageSize;

    private long views;

    private long downloads;

    private long collections;

    private long likes;

    private long comments;

    private long user_id;

    private String user;

    private String userImageURL;
}
