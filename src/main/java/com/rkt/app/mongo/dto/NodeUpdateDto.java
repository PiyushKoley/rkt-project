package com.rkt.app.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeUpdateDto {
    private String id;
    private String title;
    private String path;
    private String icon;

}
