package net.skhu.likelion12thteam03be.location.api.dto;

import lombok.RequiredArgsConstructor;
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

    @GetMapping()
    public ResponseEntity<LocationListResDto> locationFindAll() {
        LocationListResDto locationListResDto = locationService.locationFindAll();
        return new ResponseEntity<>(locationListResDto, HttpStatus.OK);
    }
}
