
package com.home.trupper.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Sucursal {

    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("orderEntities")
    private List<OrderRequest> orderEntities;

}
