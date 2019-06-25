package icefrog.com.latte.ec.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class UserProfile extends LitePalSupport{

    @Column(unique = true)
    private long id;
    @Column(defaultValue = "unknown")
    private String name;
    @Column(defaultValue = "unknown")
    private String gender;
    @Column(defaultValue = "unknown")
    private String address;

    public UserProfile(){}

    public UserProfile(long id, String name, String gender, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
