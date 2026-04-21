package com.abdullaevaziz.userfilesservletsfx.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "files", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "filename"})})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class UserFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String filename;


    private String serverFilename;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private User user;


}