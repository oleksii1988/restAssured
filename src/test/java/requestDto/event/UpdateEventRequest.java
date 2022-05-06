package requestDto.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UpdateEventRequest {

private Long startDate;
private Boolean suspended;

    public UpdateEventRequest(Long startDate) {
        this.startDate = startDate;
    }

    public UpdateEventRequest(Boolean suspended) {
        this.suspended = suspended;
    }
}
