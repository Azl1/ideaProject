package com.abdullaevaziz.userfilesspringbootfx.model;

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

}
