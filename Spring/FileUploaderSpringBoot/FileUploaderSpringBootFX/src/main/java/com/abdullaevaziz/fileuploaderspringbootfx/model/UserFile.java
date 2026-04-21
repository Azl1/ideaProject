package com.abdullaevaziz.fileuploaderspringbootfx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFile {

    @NonNull
    private String path;
    @NonNull
    private UserFileType userFileType;

    @Override
    public String toString() {
        /*String result = path.replaceFirst("^[^/\\\\]*[/\\\\]", "");
        *//*String result = path;
        int slashIndex = result.indexOf("/");
        if (slashIndex == -1){
            slashIndex = result.indexOf("\\");
        }
        if (slashIndex != -1) {
            result = result.substring(slashIndex + 1);
        }*//*
        return result;*/

        if (userFileType == UserFileType.RETURN) return "...";

        String[] parts = path.replace("\\", "/").split("/");
        return parts[parts.length - 1];
    }
}