package vn.chohoa.flower.repository;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.chohoa.flower.model.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    @OnDelete(action = OnDeleteAction.CASCADE)
    void deleteByClassNameAndAndRowId(String className,Long rowID);
}
