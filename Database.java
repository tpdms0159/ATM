import java.util.HashMap;

public class Database {
    private HashMap <String, Integer> Login = new HashMap<String, Integer>(); // ID & Password 저장
    private HashMap <String, Integer> Balance = new HashMap<String, Integer>(); // ID & Balance 저장


    private String userID; // 사용자 아이디
    private int userPassword; // 사용자 비밀번호
    private int userBalance; // 사용자 잔고


    public Database() { // 생성자 - Database 선언과 동시에 임의의 사용자 계정 저장

        Login.put("0123456789123", 1234); // 관리자 계좌 저장
        Balance.put("0123456789123",1000000);


        Login.put("1123456789123", 1234); // 입츨금 계좌 저장
        Balance.put("1123456789123",1000000);


        Login.put("2123456789123", 1234); // 정기예금 계좌 저장
        Balance.put("2123456789123",1000000);
    }

    public boolean checkID (String L, int P) { // 아이디와 패스워드 일치 확인 //일치시 : true 불일치 시 : false
        boolean result; // 로그인 성공/실패
        this.userID = L; // 사용자 계좌번호
        this.userPassword = P; // 사용자 비밀번호

        if (Login.get(L) == null ) { return false; } // 잘못된 계좌번호 입력 시 false return
        if (Login.get(L) == P) { result = true; } // 로그인 성공
        else { result = false; } // 비밀번호 불일치
        return result;
    }

    public int checkBalance () { // 사용자의 잔고 return method
        this.userBalance = Balance.get(userID);
        return userBalance;
    }
    public String getUserID() { return userID;} // 사용자 아이디 return
    public int getUserPassword() { return userPassword; } // 사용자 비밀번호 return


}