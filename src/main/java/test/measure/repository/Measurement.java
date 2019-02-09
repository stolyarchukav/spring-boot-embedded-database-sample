package test.measure.repository;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
@Getter
@Entity
public class Measurement {

    @Id
    @GeneratedValue
    private final Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date date;

    private final String userId;

    private final BigDecimal gas;

    private final BigDecimal coldWater;

    private final BigDecimal hotWater;

}
