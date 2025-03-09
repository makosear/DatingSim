
//JÚLIO CÉSAR DA SILVA DOS SANTOS - 202135008
package makosear.datingsim.User;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "profileType" 
)
@JsonSubTypes({
    @Type(value = Admin.class, name = "ADMIN"),
    @Type(value = Default.class, name = "DEFAULT"),
    @Type(value = Guest.class, name = "GUEST")
})

public abstract class User implements Authenticable {
    protected String username;
    protected String password;
    protected String profileType;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

}

