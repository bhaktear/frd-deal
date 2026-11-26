package abl.frd.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import abl.frd.model.Menu;
import java.util.*;

@Repository 
public interface MenuRepository  extends JpaRepository<Menu, Integer>{
    @Query("SELECT DISTINCT m FROM Menu m JOIN MenuRole r on m.code=r.menuId WHERE r.roleId = :roleId and m.published = 1 and r.published=1")
    List<Menu> findMenuByRoleId(@Param("roleId") String roleId);
}
