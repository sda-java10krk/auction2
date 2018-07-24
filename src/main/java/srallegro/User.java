package srallegro;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User {

    public String getNick() {
        return nick;
    }
    private String name;
    private String lastName;
    private Integer birthday;
    private String adress;
    private String mail;
    private String password;
    private String nick;

    List<Auction> mySellingList = new LinkedList<Auction>();
    List<Auction> myWonList = new LinkedList<Auction>();

    public String getPassword() {
        return password;
    }

    public List<Auction> getMyWonList() {
        return myWonList;
    }

    public List<Auction> getMySellingList() {
        return mySellingList;
    }

    public User (String name, String lastName, Integer birthDay, String adress, String mail, String password, String nick) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthDay;
        this.adress = adress;
        this.mail = mail;
        this.password = password;
        this.nick = nick;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(adress, user.adress) &&
                Objects.equals(mail, user.mail) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nick, user.nick);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lastName, birthday, adress, mail, password, nick);
    }
}
