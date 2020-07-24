package vn.chohoa.flower.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.chohoa.flower.model.Menu;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    Menu findByName(String name);
}
