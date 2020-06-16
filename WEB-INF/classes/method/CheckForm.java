package method;

public class CheckForm {

  public CheckForm() {}

  public boolean checkForm(String name, String mailaddress, String password) {
    if(name.length() >= 2 && name.length() <= 20 &&
       password.length() >= 8 && password.length() <= 64 && isHanStr(password) == "SUCCESS" &&
       mailaddress.length() <= 50 && isEmpty(mailaddress) == false && isHanStr(mailaddress) == "SUCCESS"){
      return true;
    }else{
      return false;
    }
  }

  public boolean isEmpty(String value) {
    if ( value == null || value.length() == 0 ){
      return true;
    }else{
      return false;
    }
  }

  public String isHanStr(String s){
    if (!s.matches("^[0-9a-zA-Z]+$")){
      return "FAILURE";
    }else{
      return "SUCCESS";
    }
  }

  public String checkPassword(String str){
    if (str.length() >= 8 && str.length() <= 64){
      return "SUCCESS";
    }else{
      return "FAILURE";
    }
  }

  public String checkName(String str){
    if (str.length() >= 2 && str.length() <= 20){
      return "SUCCESS";
    }else{
      return "FAILURE";
    }
  }

  public String checkMailaddress(String str){
    if (str.length() <= 50){
      return "SUCCESS";
    }else{
      return "FAILURE";
    }
  }

}
