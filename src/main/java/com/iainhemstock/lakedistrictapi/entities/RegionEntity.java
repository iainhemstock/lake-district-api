package com.iainhemstock.lakedistrictapi.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URL;

@Entity
@Table(name = "regions")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegionEntity {

    private static String API_BASE_URL = "http://localhost:8080/api";

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    public URL getUrl() throws MalformedURLException {
        return new URL(API_BASE_URL + "/region/" + id);
    }
}
