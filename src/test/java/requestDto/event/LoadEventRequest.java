package requestDto.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.Set;
@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoadEventRequest {

private Set<String> externalEventIds;


}
