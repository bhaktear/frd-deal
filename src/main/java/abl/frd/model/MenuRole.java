package abl.frd.model;
import javax.persistence.*;

@Entity
@Table(name="menu_role", uniqueConstraints = { @UniqueConstraint(name = "uk_key", columnNames = {"menu_id","role_id"}) },
    indexes = { 

    }
)
public class MenuRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  id;
    @Column(name = "menu_id", nullable = false, length = 10)
    private String menuId;
    @Column(name = "role_id", nullable = false, length = 10)
    private String roleId;
    @Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
    private int published;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public int getPublished() {
        return this.published;
    }

    public void setPublished(int published) {
        this.published = published;
    }


}
