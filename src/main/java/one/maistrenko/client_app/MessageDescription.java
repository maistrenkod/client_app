package one.maistrenko.client_app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class MessageDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String message;

    private int length;

    private LocalDateTime sendTime;

    private LocalDateTime receiveTime;

}
