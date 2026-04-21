package org.abdullaevaziz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Auto {

    @Id
    private String id;
    @Indexed(unique = true)
    private String brand;
    @Indexed(unique = true)
    private int power;
    @Indexed(unique = true)
    private int year;

}
