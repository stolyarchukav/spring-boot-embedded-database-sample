package test.measure.converter;

import org.springframework.stereotype.Component;
import test.measure.repository.Measurement;
import test.measure.rest.MeasurementResource;

import javax.annotation.Nonnull;
import java.util.Date;

@Component
public class MeasureConverter implements Converter<Measurement, MeasurementResource> {

    @Nonnull
    @Override
    public MeasurementResource toResource(@Nonnull Measurement measurement) {
        return MeasurementResource.builder()
                .id(measurement.getId())
                .date(measurement.getDate())
                .userId(measurement.getUserId())
                .gas(measurement.getGas())
                .coldWater(measurement.getColdWater())
                .hotWater(measurement.getHotWater())
                .build();
    }

    @Nonnull
    @Override
    public Measurement toEntity(@Nonnull MeasurementResource resource) {
        return Measurement.builder()
                .date(new Date())
                .userId(resource.getUserId())
                .gas(resource.getGas())
                .coldWater(resource.getColdWater())
                .hotWater(resource.getHotWater())
                .build();
    }

}
