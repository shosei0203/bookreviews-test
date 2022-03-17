package model;

public class AccountDTO {
    private String loginId;
    private String pass;
    private String userName;
    private String mail;

    public AccountDTO(String loginId, String pass, String userName, String mail) {
        this.loginId = loginId;
        this.pass = pass;
        this.userName = userName;
        this.mail = mail;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPass() {
        return pass;
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }
}