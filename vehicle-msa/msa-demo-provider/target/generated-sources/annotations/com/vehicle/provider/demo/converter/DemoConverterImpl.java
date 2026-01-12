package com.vehicle.provider.demo.converter;

import com.vehicle.api.demo.dto.DemoReqDTO;
import com.vehicle.api.demo.dto.DemoResDTO;
import com.vehicle.provider.demo.dto.DemoDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class DemoConverterImpl implements DemoConverter {

    @Override
    public DemoResDTO convertToDemoResDTO(DemoDTO demoDTO) {
        if ( demoDTO == null ) {
            return null;
        }

        DemoResDTO demoResDTO = new DemoResDTO();

        demoResDTO.setGender( demoDTO.getGender() );
        demoResDTO.setId( demoDTO.getId() );
        demoResDTO.setName( demoDTO.getName() );

        return demoResDTO;
    }

    @Override
    public DemoDTO convertToDemoDTO(DemoReqDTO demoReqDTO) {
        if ( demoReqDTO == null ) {
            return null;
        }

        DemoDTO demoDTO = new DemoDTO();

        demoDTO.setGender( demoReqDTO.getGender() );
        demoDTO.setId( demoReqDTO.getId() );
        demoDTO.setName( demoReqDTO.getName() );

        return demoDTO;
    }
}
