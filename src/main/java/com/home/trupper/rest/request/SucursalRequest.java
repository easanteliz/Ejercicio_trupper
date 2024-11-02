
package com.home.trupper.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SucursalRequest {

    @JsonProperty("sucursal")
    private Sucursal sucursal;

}
