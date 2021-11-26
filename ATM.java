
public class ATM {
    private Database db = new Database(); // 데이터베이스의 로그인 정보 확인
    private Account account; // 사용자 계좌 - 계좌번호, 비밀번호, 잔고 저장
    private TransactionRecord record = new TransactionRecord(); // 거래 기록저장 - 시간 + 로그 정보 출력


    private static int atm_one; // ATM 만 원 개수
    private static int atm_five; // ATM 오 만 원 개수
    private String log;

    ATM(){ // ATM의 1만원권, 5만원 개수 초기 설정
        this.atm_five = 200;
        this.atm_one = 1000;
    }


    boolean Login(String ID, int Password) { // 사용자의 로그인 정보 확인

        if(db.checkID(ID, Password)) { //로그인 성공
            account = new Account(ID, Password,db.checkBalance() ); // 사용자의 계정 생성
            log = "[Log] Account : " + account.getAccountNumber() + " Login Successful";
            record.logRecord(log);
            return true;
        }
        else { //로그인 실패
            log = "[Log] Login Failed";
            record.logRecord(log);
            return false;
        }
    }

    void Logout() { // 로그아웃 했을 때 로그 저장
        //로그
        log = "[Log] Account : " + account.getAccountNumber() + " Logout";
        record.logRecord(log);
    }

    void Deposit(int Five, int One ) { // 입금하기 메소드
        int amount = totalMoney(One, Five);// 입금하는 금액계산

        atm_five += Five; // ATM 지폐수 업데이트
        atm_one += One; // ATM 지폐수 업데이트

        account.deposit(amount); // 계좌에 잔액 입금

        // 로그
        log = "[Log] Account : " + account.getAccountNumber() + " Deposit : " + amount + " Won";
        record.logRecord(log);
    }

    boolean Withdraw(int amount) { // 출금하기 메소드 - 출금 가능 여부 return
        if(amount > account.getBalance()) { //출금하려는 금액이 잔고보다 더 클 경우
            log = "[Log] Account : " +  account.getAccountNumber() + " Withdraw Fail";
            return false;
        }
        else {
            //로그
            return true;
        }
    }
    boolean CheckWithdrawOne(int one) // ATM기에 만원권의 수가 충분한지 확인
    {
        if(one>atm_one)
        {
            log = "[Log] Account : " +  account.getAccountNumber() + " Withdraw Fail : Not Enough ATM Cash";
            record.logRecord(log);
            return false;
        }
        else return true;

    }

    boolean CheckWithdrawFive(int five) // ATM기에 오만원권의 수가 충분한지 확인
    {
        if(five>atm_five)
        {
            log = "[Log] Account : " +  account.getAccountNumber() + " Withdraw Fail : Not Enough ATM Cash";
            record.logRecord(log);
            return false;
        }
        else return true;
    }

    void Calculation(int one, int Five) // 사용자가 출금하려는 총금액 계산
    {
        atm_one -= one;
        atm_five -= Five;
        int amount = totalMoney(one, Five);
        account.withdraw(amount); // 출금진행

        log = "[Log] Account : " +  account.getAccountNumber() + " Withdraw Success"; // 거래 계좌 번호 얻기
        record.logRecord(log); // 로그 내용 출력
        System.out.println(account.getBalance());
    }

    int totalMoney (int one, int five) { return (10000 * one) + (50000 * five); } // 입금 및 출금하는 총 금액 계산

    int CheckBalance() { // 잔고확인 메소드
        int balance = account.getBalance();

        // 로그
        log = "[Log] Account : " +  account.getAccountNumber() + " Check Balance";
        record.logRecord(log);

        return balance;
    }
    public void UpdateBalance (int balance) { // 거래이후 잔고 update;
        account.setBalance(balance);
    } // 거래 진행 후 잔고 갱신

    void Reset() { // ATM의 지폐수 초기화
        atm_one = 1000;
        atm_five = 200;
        //로그
        log = "[Log] ATM Cash Reset";
        record.logRecord(log);
    }

    void Nothing () { // 초기화 - 아니오 선택시
        log = "[Log] ATM Cash Nothing";
        record.logRecord(log);
    }
}