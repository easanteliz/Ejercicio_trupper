
package com.home.trupper.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class OrderRequest {


    private int id;
    @JsonProperty("fecha")
    private Date fecha;
    @JsonProperty("total")
    private Double total;
    @JsonProperty("productEntities")
    private List<ProductRequest> productEntities;

}
