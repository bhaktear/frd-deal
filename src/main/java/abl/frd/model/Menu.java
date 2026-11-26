package abl.frd.model;
import javax.persistence.*;

@Entity
@Table(name="menu", uniqueConstraints = { @UniqueConstraint(name = "uk_code", columnNames = "code")},
    indexes = { 

    }
)
public class Menu {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int  id;
    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "parent_id", nullable = false, length = 10)
    private String parentId;
    @Column(name = "url", nullable = false)
    private String url;;
    @Column(name = "icon", nullable = false, length = 50)
    private String icon;
    @Column(name = "published", columnDefinition = "TINYINT(1) DEFAULT 0")
    private int published;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPublished() {
        return this.published;
    }

    public void setPublished(int published) {
        this.published = published;
    }
}
