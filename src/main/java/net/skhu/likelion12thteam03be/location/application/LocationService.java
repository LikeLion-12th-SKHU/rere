package net.skhu.likelion12thteam03be.location.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.location.api.dto.request.LocationSaveReqDto;
import net.skhu.likelion12thteam03be.location.api.dto.request.LocationUpdateReqDto;
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

    @Transactional
    public void locationSave(LocationSaveReqDto locationSaveReqDto) {
        if (locationRepository.existsByName(locationSaveReqDto.name())) {
            throw new IllegalArgumentException("해당 위치가 이미 존재합니다.");
        }
        Location location = Location.builder()
                .name(locationSaveReqDto.name())
                .build();

        locationRepository.save(location);
    }

    public LocationListResDto locationFindAll() {
        List<Location> locations = locationRepository.findAll();

        List<LocationInfoResDto> locationInfoResDtoList = locations.stream()
                .map(LocationInfoResDto::from)
                .toList();

        return LocationListResDto.from(locationInfoResDtoList);
    }

    @Transactional
    public void locationUpdate(Long locationId, LocationUpdateReqDto locationUpdateReqDto) {
        Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new IllegalArgumentException("해당 위치가 존재하지 않습니다. 새 위치로 등록해주세요.")
        );
        location.update(locationUpdateReqDto.name());
    }

    @Transactional
    public void locationDelete(Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(
                () -> new IllegalArgumentException("해당 위치가 존재하지 않습니다.")
        );
        locationRepository.delete(location);
    }
}
