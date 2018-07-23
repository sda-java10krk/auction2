package srallegro;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String name;
    private String lastName;
    private Integer birthday;
    private String adress;
    private String mail;
    private String password;
    private String nick;

    public  User (String name, String lastName, Integer birthDay, String adress, String mail, String password, String nick) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthDay;
        this.adress = adress;
        this.mail = mail;
        this.password = password;
        this.nick = nick;
    }
    public String getNick() {
        return nick;
    }
    @Override
    public String toString() {
        return "srallegro.User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", adress='" + adress + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
