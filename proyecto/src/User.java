import java.util.ArrayList;

public class User {

    private String username;
    private String name;
    private String nif;
    private String email;
    private String address;
    private String birthDate;
    private String role;

    //Constructor vacio---------------------------------------
    public User(){
        this.username="";
        this.name="";
        this.nif="";
        this.email="";
        this.address="";
        this.birthDate="";
        this.role="";
    }

    //Constructor sobrecargado------------------------------------
    public User(String role, String birthDate, String address, String email, String nif, String name, String username) {
        this.role = role;
        this.birthDate = birthDate;
        this.address = address;
        this.email = email;
        this.nif = nif;
        this.name = name;
        this.username = username;
    }

    //getters------------------------------

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getNif() {
        return nif;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getRole() {
        return role;
    }



    //setters--------------------------


    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setRole(String role) {
        this.role = role;
    }
}