package com.api.Flight.Mapper;


import com.api.Flight.Dto.FlightRequest;
import com.api.Flight.Dto.FlightResponse;
import com.api.Flight.Model.Flight;

public interface FlightMapper {
    Flight toEntity(FlightRequest flightRequest);
    FlightResponse toDto(Flight flight);
}
