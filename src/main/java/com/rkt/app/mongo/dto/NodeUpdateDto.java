package com.rkt.app.mongo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeUpdateDto {

    @NotBlank
    private String id; // id cannot be changeable..
    @NotBlank
    private String title;
    private String path;
    private String icon;

}
