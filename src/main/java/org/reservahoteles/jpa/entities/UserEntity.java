
import jakarta.persistence.*;


@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "number_document_user")
    private String number_document_user;

    @Column(name = "email_user")
    private String email_user;

    @Column(name = "password_user")
    private String password_user;

    @Column(name = "names_user")
    private String names_user;

    @Column(name = "lastnames_user")
    private String lastnames_user;

    @Column(name = "phone_number")
    private Integer phone_number;

    @Column(name = "is_admin")
    private Boolean is_admin;

    @Column(name = "active")
    private Boolean active;
    
    public UserEntity(){
    }

    public UserEntity(Long id_user, String number_document_user, String email_user, String password_user,
            String names_user, String lastnames_user, Integer phone_number, Boolean is_admin, Boolean active) {
        this.id_user = id_user;
        this.number_document_user = number_document_user;
        this.email_user = email_user;
        this.password_user = password_user;
        this.names_user = names_user;
        this.lastnames_user = lastnames_user;
        this.phone_number = phone_number;
        this.is_admin = is_admin;
        this.active = active;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNumber_document_user() {
        return number_document_user;
    }

    public void setNumber_document_user(String number_document_user) {
        this.number_document_user = number_document_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getNames_user() {
        return names_user;
    }

    public void setNames_user(String names_user) {
        this.names_user = names_user;
    }

    public String getLastnames_user() {
        return lastnames_user;
    }

    public void setLastnames_user(String lastnames_user) {
        this.lastnames_user = lastnames_user;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }



}
