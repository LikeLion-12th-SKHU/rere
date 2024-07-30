package net.skhu.likelion12thteam03be.location.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record LocationListResDto(
        List<LocationInfoResDto> locaitonList
) {
    public static LocationListResDto from(List<LocationInfoResDto> locationList) {
        return LocationListResDto.builder()
                .locaitonList(locationList)
                .build();
    }
}
