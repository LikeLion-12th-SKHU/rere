package net.skhu.likelion12thteam03be.location.api.dto.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.location.domain.Location;

@Builder
public record LocationInfoResDto(
        Long locationId,
        String name
) {
    public static LocationInfoResDto from(Location location) {
        return LocationInfoResDto.builder()
                .name(location.getName())
                .build();
    }
}
