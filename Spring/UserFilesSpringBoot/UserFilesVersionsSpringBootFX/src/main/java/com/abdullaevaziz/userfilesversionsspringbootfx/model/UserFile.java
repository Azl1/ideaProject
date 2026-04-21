package com.abdullaevaziz.userfilesversionsspringbootfx.model;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserFile {


    private long id;

    @NonNull
    private String filename;

    private String serverFilename;

    @NonNull
    private User user;
    private int version;

    @Override
    public String toString() {
        return
                "filename='" + filename + '\'' +
                        ", version=" + version;
    }
}
