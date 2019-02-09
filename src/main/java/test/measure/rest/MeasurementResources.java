package test.measure.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@RequiredArgsConstructor
@Getter
public class MeasurementResources {

    private final List<MeasurementResource> measurements;

    private final int count;

}
