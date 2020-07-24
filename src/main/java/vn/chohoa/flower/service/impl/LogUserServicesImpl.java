package vn.chohoa.flower.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.chohoa.flower.model.LogUser;
import vn.chohoa.flower.model.User;
import vn.chohoa.flower.repository.LogUserRepository;
import vn.chohoa.flower.security.UserPrincipal;
import vn.chohoa.flower.service.LogUserService;

public class LogUserServicesImpl implements LogUserService {

    @Autowired
    private LogUserRepository logUserRepository;

    @Override
    public void insert() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){

            final Object o = auth.getPrincipal();
            if (o instanceof UserPrincipal){

                UserPrincipal userPrincipal = (UserPrincipal) o;
                LogUser logUser;

            }
        }
    }
}
