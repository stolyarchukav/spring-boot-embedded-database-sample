package test.measure.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import test.measure.converter.Converter;
import test.measure.repository.MeasureRepository;
import test.measure.repository.Measurement;
import test.measure.validator.ResourceValidator;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestController("measure")
public class MeasureController {

    private final MeasureRepository repository;

    private final Converter<Measurement, MeasurementResource> converter;

    private final ResourceValidator<MeasurementResource> validator;

    @Transactional(readOnly = true)
    @GetMapping
    public MeasurementResources findMeasurements(@RequestParam(value = "userId") String userId) {
        log.info("Get list of measurements for user: {}", userId);
        List<MeasurementResource> resources = repository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(converter::toResource)
                .collect(Collectors.toList());
        int count = resources.size();
        log.info("Found {} measurements", count);
        return new MeasurementResources(resources, count);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<MeasurementResource> saveMeasurement(@RequestBody MeasurementResource resource) {
        log.info("Save measurement request: {}", resource);
        validator.validate(resource);
        Measurement saved = repository.save(converter.toEntity(resource));
        log.info("Saved successfully: {}", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toResource(saved));
    }

}
