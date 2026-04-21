package org.abdullaevaziz.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class NotificationLong {

    private long id;

    @NonNull
    private long orderId;

    @NonNull
    private String email;

    private String sentAt;

}
