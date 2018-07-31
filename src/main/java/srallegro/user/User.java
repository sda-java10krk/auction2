package srallegro.user;

import srallegro.auction.Auction;
import srallegro.exception.BirthdayException;
import srallegro.exception.EmptyNickException;

import srallegro.exception.PasswordTooShortException;
import srallegro.exception.UserWithSameNicknameExists;


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
    private String address;
    private String mail;
    private String password;
    private String nick;

    public List<Auction> mySellingList = new LinkedList<Auction>();
    public List<Auction> myWonList = new LinkedList<Auction>();

    public String getPassword() {
        return password;
    }

    public List<Auction> getMyWonList() {
        return myWonList;
    }

    public List<Auction> getMySellingList() {
        return mySellingList;
    }

    Database database = Database.getInstance();

    public User(String name, String lastName, Integer birthDay, String adress, String mail, String password, String nick) throws PasswordTooShortException, BirthdayException, EmptyNickException, UserWithSameNicknameExists {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthDay;
        if (birthDay == null) {
            throw new BirthdayException();
        }
        this.address = adress;
        this.mail = mail;
        this.password = password;
        if (password.length() < 5) {
            throw new PasswordTooShortException();
        }
        if (database.getUserByNickname(nick) == null) {
            this.nick = nick;
        } else {
            throw new UserWithSameNicknameExists();
        }
        if (nick.length() == 0) {
            throw new EmptyNickException();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "nick='" + nick + '\'' +
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
                Objects.equals(address, user.address) &&
                Objects.equals(mail, user.mail) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nick, user.nick);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lastName, birthday, address, mail, password, nick);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
