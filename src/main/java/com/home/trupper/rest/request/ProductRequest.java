
package com.home.trupper.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class ProductRequest {

    private int id;
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonProperty("precio")
    private Double precio;

}
