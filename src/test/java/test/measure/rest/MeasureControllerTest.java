package test.measure.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.measure.converter.Converter;
import test.measure.exception.InvalidRequestException;
import test.measure.repository.MeasureRepository;
import test.measure.repository.Measurement;
import test.measure.validator.ResourceValidator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static test.measure.TestUtil.*;

@RunWith(MockitoJUnitRunner.class)
public class MeasureControllerTest {

    @Mock
    private MeasureRepository repository;

    @Mock
    private Converter<Measurement, MeasurementResource> converter;

    @Mock
    private ResourceValidator<MeasurementResource> validator;

    @InjectMocks
    private MeasureController controller;

    @Test
    public void findMeasurements() {
        when(repository.findByUserIdOrderByDateDesc(USER_ID)).thenReturn(Arrays.asList(measurement(), measurement()));
        when(converter.toResource(any())).thenReturn(resource());

        MeasurementResources measurements = controller.findMeasurements(USER_ID);

        assertEquals(2, measurements.getMeasurements().size());
        assertEquals(2, measurements.getCount());
    }

    @Test
    public void saveMeasurement() {
        MeasurementResource resource = resource();
        Measurement entity = measurement();
        when(converter.toEntity(resource)).thenReturn(entity);
        when(converter.toResource(entity)).thenReturn(resource);
        when(repository.save(entity)).thenReturn(entity);

        ResponseEntity<MeasurementResource> response = controller.saveMeasurement(resource);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(resource, response.getBody());
    }

    @Test (expected = InvalidRequestException.class)
    public void saveMeasurementValidationFailed() {
        MeasurementResource resource = resource();
        doThrow(InvalidRequestException.class).when(validator).validate(resource);

        controller.saveMeasurement(resource);
    }

}
