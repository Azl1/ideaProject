package org.abdullaevaziz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Student {
    @Id
    private String id;
    private String fio;
    private int age;
    @Indexed(unique = true)
    private int num;
    private double salary;

    @DBRef
    @JsonIgnore
    private List<Auto> autos = new ArrayList<>();
}
