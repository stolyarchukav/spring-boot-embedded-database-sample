package test.measure.converter;

import org.junit.Before;
import org.junit.Test;
import test.measure.repository.Measurement;
import test.measure.rest.MeasurementResource;

import static org.junit.Assert.assertNotNull;
import static test.measure.TestUtil.measurement;
import static test.measure.TestUtil.resource;

public class MeasureConverterTest {

    private MeasureConverter converter;

    @Before
    public void setUp() {
        converter = new MeasureConverter();
    }

    @Test
    public void toResource() {
        MeasurementResource resource = converter.toResource(measurement());

        //TODO add all required assertions
        assertNotNull(resource);
    }

    @Test
    public void toEntity() {
        Measurement entity = converter.toEntity(resource());

        //TODO add all required assertions
        assertNotNull(entity);
    }

}
