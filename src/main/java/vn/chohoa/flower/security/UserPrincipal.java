package vn.chohoa.flower.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.EAN;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.chohoa.flower.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Tự tao Userprincipal không sử dụng mặc định của spring security ??
 * <p>
 * đặt jsonignore cho thuộc tính password để bỏ qua filed data khi chuyển thành json
 * để gửi đi
 */
public class UserPrincipal implements UserDetails {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private boolean active;

    public UserPrincipal() {
    }

    /**
     * lấy tập quyền của user
     * chuyển thành list <grandtedAuthority>
     * tap grandted authori để làm gì??
     */
    public UserPrincipal(final User user) {

        List<GrantedAuthority> authorities = user.getRoles().stream().map(

                r -> new SimpleGrantedAuthority(r.getCode())
        ).collect(Collectors.toList());

        this.id = user.getId();

        this.username = user.getUserName();

        this.password = user.getPassword();

        this.authorities = authorities;

        this.active = user.getIsActive();
    }


    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null || obj.getClass() != getClass()) return false;

        UserPrincipal userPrincipal = (UserPrincipal) obj;

        return Objects.equals(id, userPrincipal.id);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(id);
    }
}


