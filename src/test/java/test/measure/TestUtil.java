package test.measure;

import test.measure.repository.Measurement;
import test.measure.rest.MeasurementResource;

import java.math.BigDecimal;
import java.util.Date;

public class TestUtil {

    public static final String USER_ID = "user_1";

    public static Measurement measurement() {
        Date date = new Date();
        return Measurement.builder()
                .id(date.getTime())
                .date(date)
                .userId(USER_ID)
                .gas(BigDecimal.ONE)
                .coldWater(BigDecimal.TEN)
                .hotWater(BigDecimal.ZERO)
                .build();
    }

    public static MeasurementResource resource() {
        return MeasurementResource.builder()
                .userId(USER_ID)
                .gas(BigDecimal.ONE)
                .coldWater(BigDecimal.TEN)
                .hotWater(BigDecimal.ZERO)
                .build();
    }

}
