package com.kdu.smarthome.model.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InventoryResponseObjectDTO {
    private String kickstoneId;
    private String name;
    private String password;
}
