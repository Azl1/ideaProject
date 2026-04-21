package com.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserFile {

    @NonNull
    private String path;
    @NonNull
    private UserFileType userFileType;

}