package srallegro;

public class User {

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

    public static User noWinner () {
        User newUser = new User ("", "", null, "", "", "", "Jeszcze nikt nie złożył oferty");
        return newUser;
    }

    public static Builder personBuilder (){
        return new Builder();
    }
    public static class Builder{
        private String name;
        private String lastName;
        private Integer birthday;
        private String adress;
        private String mail;
        private String password;
        private String nick;

        public Builder withName(String name) {

            this.name = name;
            return this;
        }
        public Builder withLastName(String lastName) {

            this.lastName = lastName;
            return this;
        }
        public Builder withBirthday(Integer birthday) {
            this.birthday = birthday;
            return this;
        }
        public Builder withAdress(String adress) {
            this.adress = adress;
            return this;
        }
        public Builder withMail(String mail) {
            this.mail = mail;
            return this;
        }
        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder withNick(String nick) {
            this.nick = nick;
            return this;
        }
        public User build() {
            return new User (name, lastName, birthday, adress, mail, password, nick);
        }




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
