package test.measure.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@RequiredArgsConstructor
@Builder
@Getter
public class MeasurementResource {

    @Null
    private final Long id;

    @Null
    private final Date date;

    @NotEmpty
    private final String userId;

    @NotNull
    private final BigDecimal gas;

    @NotNull
    private final BigDecimal coldWater;

    @NotNull
    private final BigDecimal hotWater;

}
