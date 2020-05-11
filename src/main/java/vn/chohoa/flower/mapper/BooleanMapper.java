package vn.chohoa.flower.mapper;

import lombok.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BooleanMapper {

    @Named("activeToStatus")
    default int activeToStatus(Boolean active) {
        if (active != null && Boolean.TRUE.equals(active)){
            return 0;
        }
        return 1;
    }

    @Named("booleanToInt")
    default int booleanToInt(Boolean b){
        if (b != null && Boolean.TRUE.equals(b)){
            return 1;
        }
        return 0;
    }

    @Named("intToBoolean")
    default boolean intToBoolean(Integer i) {

        if (i != null && i == 1) {
            return true;
        }

        return false;
    }
}
