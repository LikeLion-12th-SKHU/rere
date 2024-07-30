package net.skhu.likelion12thteam03be.location.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.location.api.dto.response.LocationInfoResDto;
import net.skhu.likelion12thteam03be.location.api.dto.response.LocationListResDto;
import net.skhu.likelion12thteam03be.location.domain.Location;
import net.skhu.likelion12thteam03be.location.domain.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationListResDto locationFindAll() {
        List<Location> locations = locationRepository.findAll();

        List<LocationInfoResDto> locationInfoResDtoList = locations.stream()
                .map(LocationInfoResDto::from)
                .toList();

        return LocationListResDto.from(locationInfoResDtoList);
    }
}
