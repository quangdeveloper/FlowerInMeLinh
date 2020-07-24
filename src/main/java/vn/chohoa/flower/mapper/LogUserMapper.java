package vn.chohoa.flower.mapper;

import org.mapstruct.Mapper;
import vn.chohoa.flower.model.LogUser;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.security.UserPrincipal;

@Mapper(componentModel = "spring")
public interface LogUserMapper {

//    LogUser toLogUserFromUser(User p);
}
