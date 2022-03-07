package bookreviews.model;

public class UserForm {

    // login.jspで入力された情報を格納するForm
    private String loginId;
    private String pass;

    public UserForm() {
    }

    public UserForm(String loginId, String pass) {
        this.loginId = loginId;
        this.pass = pass;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPass() {
        return pass;
    }

}
