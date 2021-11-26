public class Account extends RegularAccount {//정기예금 계좌 상속받고 입금,출금 메소드 추가
    private boolean isDeposit; // 출금 가능 불가능

    public Account(String userID, int userPassword, int initialBalance) { // 생성자
        this.userID = userID; // ID 저장
        this.userPassword = userPassword; // Password 저장

        if(initialBalance > 0) { // 초기 잔고 확인
            this.userBalance = initialBalance;
        }

    }

    public void deposit(int amount) { // 입금
        userBalance = userBalance + amount;
    } // 입금하기

    public void withdraw(int amount) { // 출금 진행

        if(userBalance >= amount) {
            setBalance(this.userBalance - amount);
        }
        else
            System.out.println("출금 가능 금액을 초과했습니다.");

    }

    public boolean getIsDepositWithdraw(String account) { // 입출금계좌인지, 정기예금인지 확인
        account.charAt(0);

        if(account.charAt(0)=='1') // 입출금 계좌인경우
            isDeposit = true;
        else if(account.charAt(0)=='2') // 정기예금 계좌인경우
            isDeposit = false;
        return isDeposit;

    }
}
