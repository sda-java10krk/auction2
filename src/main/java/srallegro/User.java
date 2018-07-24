package srallegro;

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

    //user-atrapa; domyślny wygrywający każdej nowej aukcji

}
