package net.skhu.likelion12thteam03be.location.api.dto;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.category.api.dto.response.CategoryListResDto;
import net.skhu.likelion12thteam03be.location.api.dto.request.LocationSaveReqDto;
import net.skhu.likelion12thteam03be.location.api.dto.request.LocationUpdateReqDto;
import net.skhu.likelion12thteam03be.location.api.dto.response.LocationListResDto;
import net.skhu.likelion12thteam03be.location.application.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    @PostMapping()
    public ResponseEntity<String> locationSave(@RequestBody LocationSaveReqDto locationSaveReqDto) {
        locationService.locationSave(locationSaveReqDto);
        return new ResponseEntity<>("Successful Location Save!", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<LocationListResDto> locationFindAll() {
        LocationListResDto locationListResDto = locationService.locationFindAll();
        return new ResponseEntity<>(locationListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{locationId}")
    public ResponseEntity<String> locationUpdate(@PathVariable Long locationId, @RequestBody LocationUpdateReqDto locationUpdateReqDto) {
        locationService.locationUpdate(locationId, locationUpdateReqDto);
        return new ResponseEntity<>("Successful Location Update! locationId = " + locationId, HttpStatus.OK);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<String> locationDelete(@PathVariable Long locationId) {
        locationService.locationDelete(locationId);
        return new ResponseEntity<>("Successful Location Delete! locationId = " + locationId, HttpStatus.OK);
    }
}
