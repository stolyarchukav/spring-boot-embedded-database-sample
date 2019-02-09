package test.measure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import test.measure.rest.MeasurementResource;
import test.measure.rest.MeasurementResources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static test.measure.TestUtil.USER_ID;
import static test.measure.TestUtil.resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeasureApplicationIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void saveMeasurement() {
		MeasurementResource resource = resource();
		ResponseEntity<MeasurementResource> response = postMeasurement(resource);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		MeasurementResource body = response.getBody();
		assertNotNull(body);
		assertNotNull(body.getId());
		assertNotNull(body.getDate());
		assertEquals(resource.getUserId(), body.getUserId());
		assertEquals(resource.getGas(), body.getGas());
		assertEquals(resource.getColdWater(), body.getColdWater());
		assertEquals(resource.getHotWater(), body.getHotWater());
	}

	@Test
	public void listOfMeasurements() {
		postMeasurement(resource(USER_ID));
		postMeasurement(resource(USER_ID));
		postMeasurement(resource(USER_ID + 1));
		postMeasurement(resource(USER_ID));

		ResponseEntity<MeasurementResources> response = restTemplate.getForEntity("/measure?userId=" + USER_ID, MeasurementResources.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		MeasurementResources body = response.getBody();
		assertNotNull(body);
		assertEquals(3, body.getCount());
		assertEquals(3, body.getMeasurements().size());
	}

	private ResponseEntity<MeasurementResource> postMeasurement(MeasurementResource resource) {
		return restTemplate.postForEntity("/measure",
				resource, MeasurementResource.class);
	}

}
