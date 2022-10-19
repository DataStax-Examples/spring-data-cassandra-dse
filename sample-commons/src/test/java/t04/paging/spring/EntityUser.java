package t04.paging.spring;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "users")
public class EntityUser {
    
    @PrimaryKey
    private String email;
    
    @Column("lastname")
    private String lastname;
    
    @Column("firstname")
    private String firsname;
    
    public EntityUser() {}

    public EntityUser(String email, String lastname, String firsname) {
        super();
        this.email = email;
        this.lastname = lastname;
        this.firsname = firsname;
    }

    /**
     * Getter accessor for attribute 'email'.
     *
     * @return
     *       current value of 'email'
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter accessor for attribute 'email'.
     * @param email
     * 		new value for 'email '
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter accessor for attribute 'lastname'.
     *
     * @return
     *       current value of 'lastname'
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter accessor for attribute 'lastname'.
     * @param lastname
     * 		new value for 'lastname '
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Getter accessor for attribute 'firsname'.
     *
     * @return
     *       current value of 'firsname'
     */
    public String getFirsname() {
        return firsname;
    }

    /**
     * Setter accessor for attribute 'firsname'.
     * @param firsname
     * 		new value for 'firsname '
     */
    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

}
