public class RegularAccount {
    String userID; // 사용자 계좌번호
    int userPassword; // 사용자 비밀번호
    int userBalance; // 사용자 잔고

    public void setBalance(int balance) {
        this.userBalance = balance;
    }

    public String getAccountNumber() {
        return userID;
    } // 사용자 계좌번호 return

    public int getPassword () { return userPassword; } // 사용자의 비밀번호 return

    public int getBalance() { // 잔고 확인
        return userBalance;
    } // 거래 후의 사용자 잔고 return
}