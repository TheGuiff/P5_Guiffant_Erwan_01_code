package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.web.dto.FireAndFloodByAddressDto;
import com.safetynet.alerts.web.dto.FireAndFloodByStationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FloodService {

    @Autowired
    FireService fireService;

    @Autowired
    FireStationService fireStationService;

    public List<FireAndFloodByStationDto> flood (List<Integer> listStations) {
        return listStations.stream()
                .map(s -> {
                    FireAndFloodByStationDto fireAndFloodByStationDto = new FireAndFloodByStationDto();
                    fireAndFloodByStationDto.setStation(s);
                    fireAndFloodByStationDto.setListAddresses(fireStationService.listAddressesByStation(s).stream()
                            .map(a -> {
                                FireAndFloodByAddressDto fireAndFloodByAddressDto = new FireAndFloodByAddressDto();
                                fireAndFloodByAddressDto.setAddress(a);
                                fireAndFloodByAddressDto.setInhabitantsList(fireService.fire(a).getInhabitantsList());
                                return fireAndFloodByAddressDto;
                            })
                            .collect(Collectors.toList()));
                    return fireAndFloodByStationDto;
                })
                .collect(Collectors.toList());
    }
}
