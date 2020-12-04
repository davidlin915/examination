package cn.jy.domain;

public class User {

    private String employee_id;
    private String password;
    private String url_address;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl_address() {
        return url_address;
    }

    public void setUrl_address(String url_address) {
        this.url_address = url_address;
    }

    @Override
    public String toString() {
        return "User{" +
                "employee_id='" + employee_id + '\'' +
                ", password='" + password + '\'' +
                ", url_address='" + url_address + '\'' +
                '}';
    }
}
