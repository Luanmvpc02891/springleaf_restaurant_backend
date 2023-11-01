package com.springleaf_restaurant_backend.google_drive_api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class GoogleDriveFileDTO extends AbstractDTO<GoogleDriveFileDTO> implements Serializable {

    private String size;
    private String thumbnailLink;
    private boolean shared;
}