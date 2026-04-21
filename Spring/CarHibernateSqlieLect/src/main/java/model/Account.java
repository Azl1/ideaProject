package model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Account {
    @Id
    @Column(name = "person_id")
    private long id;

    // Связь 1 к 1 с человеком
    @ToString.Exclude
    @OneToOne
    @NonNull
    @MapsId
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(nullable = false, unique = true)
    @NonNull
    private String login;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = false)
    @NonNull
    private Date regDate;

    // Список строк
    @ToString.Exclude
    @ElementCollection
    @JoinTable(name = "accounts_logs")
    @Column(name="logs")
    private List<String> logsList = new ArrayList<>();

    public void addLog(){
        int logId = logsList.size() + 1;
        String logTime = new Timestamp(System.currentTimeMillis()).toString();
        String log = "log " + logId + ": " + logTime;
        System.out.println(log);
        this.logsList.add(log);
    }
}
