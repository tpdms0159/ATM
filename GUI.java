import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GUI {
    private int amount; // 거래 진행 총금액
    private int atm_one; // atm기의 만원권 지폐수
    private int atm_five; // atm기의 오만원권 지폐수
    private int fiveAmount;
    private int oneAmount;
    private ATM transaction = new ATM();
    boolean loginBool = false;
    int deposit_withdraw = 0;


    private String accountNumber = ""; // 사용자가 입력하는 계좌번호 저장
    private Integer password; // 사용자가 입력하는 비밀번호 저장
    private boolean end = false; // 로그아웃 여부 확인
    private int select_work; // 사용자가 선택한 업무 번호

    public String getaccNumber() {
        return accountNumber;
    }

    public int getPassword() {
        return password;
    }


    Font font_all = new Font("궁서 굵게", Font.BOLD, 30);

    JFrame atm = new JFrame("ATM SYSTEM");
    //모든 frame에 들어가는 panel
    JPanel atm_label = new JPanel();
    JLabel label = new JLabel("K 은행 ATM");


    // 계좌 별 메뉴를 구성하는 panel
    JPanel login = new JPanel(); // 로그인 패널
    JPanel menu_withdraw = new JPanel(); // 입출금 패널
    JPanel menu_regular = new JPanel(); // 정기 계좌 패널
    JPanel menu_manager = new JPanel(); // 관리자 패널
    JPanel menu_error_page = new JPanel(); // 로그인 에러 패널
    JPanel money_cnt_error = new JPanel(); // 지폐 수 입력 에러 패널



    // 입금 & 출금 입력받기
    // 입금 & 출금 입력확인
    JPanel user_input  = new JPanel();
    JPanel user_input_show = new JPanel();

    //입금 과정에서 나타나는 panel
    JPanel deposit_user_show_finish = new JPanel(); // 입금 완료 후 잔고 & 입금금액 출력

    //출금 과정에서 나타나는 panel

    JPanel withdraw_user_show_finish = new JPanel(); // 출금 완료 후 잔고 & 출금금액 출력
    JPanel withdraw_user_moneyOneFive_error = new JPanel(); // atm 지폐 수 1만원권 & 5만원권 부족 error
    JPanel withdraw_user_moneyone_error = new JPanel(); //atm 지폐 수 1만원권 부족 error
    JPanel withdraw_user_moneyfive_error = new JPanel(); // atm 지폐 수 5만원권 부족 error
    JPanel withdraw_user_balance_error = new JPanel(); // 사용자 잔고 부족 error

    //잔고조회 과정에서 나타나는 panel
    JPanel balance_user_show = new JPanel(); // 계좌 번호 & 잔고 출력

    //관리자 atm 초기화 과정
    JPanel manager_user_input = new JPanel(); // 사용자 입력 받기
    JPanel manager_user_show_finish = new JPanel(); // 초기화 완료 후 출력


    //JButton & JLabel & JTextfield 정의

    //deposit_user_input
    JTextField one_text = new JTextField(20);
    JTextField five_text = new JTextField(20);
    //입력 불가능하게 수정 필요 -- not clear

    //deposit_user_show_input
    JTextField text_user_input_one_show = new JTextField(10);
    JTextField text_user_input_five_show = new JTextField(10);
    JTextField text_user_input_total_show = new JTextField(20);

    //deposit_user_show_finish
    JTextField text_deposit_id_finish = new JTextField(20);
    JTextField text_deposit_money_finish = new JTextField(20);
    JTextField text_deposit_balance_finish = new JTextField(20);
    //withdraw_user_input
    JTextField text_withdraw_user_one = new JTextField(20);
    JTextField text_withdraw_user_five = new JTextField(20);
    //입력 불가능하게 수정 필요 -- not clear
    //withdraw_user_show_input
    JTextField text_withdraw_one_show = new JTextField(10);
    JTextField text_withdraw_five_show = new JTextField(10);
    JTextField text_withdraw_total_show = new JTextField(20);
    //withdraw_user_show_finish
    JTextField text_withdraw_id_finish = new JTextField(20);
    JTextField text_withdraw_money_finish = new JTextField(20);
    JTextField text_withdraw_balance_finish = new JTextField(20);
    //balance_user_show
    JTextField text_balance_id = new JTextField(20);
    JTextField text_balance_balance = new JTextField(20);

    void LoginWindow() { // GUI 시작 메소드 - 로그아웃 이전까지 무한 반복 & 로그인 정부 불일치 시에도 무한 반복

        //frame 설정
        atm.setLayout(new BoxLayout(atm.getContentPane(), BoxLayout.Y_AXIS));
        atm.setSize(400, 250);
        atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        atm.getContentPane().setBackground(Color.cyan);
        //모든 frame에 들어가는 panel
        atm_label.add(label);
        atm.add(atm_label);


        //login panel 설정
        login.setSize(500, 400);

        //모든 panel 수직 정렬 설정 // 이게 맞나..?
        login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        menu_withdraw.setLayout(new BoxLayout(menu_withdraw, BoxLayout.Y_AXIS));
        menu_manager.setLayout(new BoxLayout(menu_manager, BoxLayout.Y_AXIS));
        menu_regular.setLayout(new BoxLayout(menu_regular, BoxLayout.Y_AXIS));
        menu_error_page.setLayout(new BoxLayout(menu_error_page, BoxLayout.Y_AXIS));
        money_cnt_error.setLayout(new BoxLayout(money_cnt_error, BoxLayout.Y_AXIS));

        user_input.setLayout(new BoxLayout(user_input, BoxLayout.Y_AXIS));
        user_input_show.setLayout(new BoxLayout(user_input_show, BoxLayout.Y_AXIS));

        deposit_user_show_finish.setLayout(new BoxLayout(deposit_user_show_finish, BoxLayout.Y_AXIS));

        withdraw_user_balance_error.setLayout(new BoxLayout(withdraw_user_balance_error, BoxLayout.Y_AXIS));
        withdraw_user_moneyone_error.setLayout(new BoxLayout(withdraw_user_moneyone_error, BoxLayout.Y_AXIS));
        withdraw_user_moneyfive_error.setLayout(new BoxLayout(withdraw_user_moneyfive_error, BoxLayout.Y_AXIS));
        withdraw_user_show_finish.setLayout(new BoxLayout(withdraw_user_show_finish, BoxLayout.Y_AXIS));

        balance_user_show.setLayout(new BoxLayout(balance_user_show, BoxLayout.Y_AXIS));

        manager_user_input.setLayout(new BoxLayout(manager_user_input, BoxLayout.Y_AXIS));
        manager_user_show_finish.setLayout(new BoxLayout(manager_user_show_finish, BoxLayout.Y_AXIS));

        //고정 textField 설정
        text_user_input_five_show.setEditable(false);
        text_user_input_one_show.setEditable(false);
        text_user_input_total_show.setEditable(false);

        text_deposit_balance_finish.setEditable(false);
        text_deposit_id_finish.setEditable(false);
        text_deposit_money_finish.setEditable(false);

        text_withdraw_id_finish.setEditable(false);
        text_withdraw_balance_finish.setEditable(false);
        text_withdraw_money_finish.setEditable(false);

        text_balance_balance.setEditable(false);
        text_balance_id.setEditable(false);


//로그인 panel
        //id panel 설정
        JPanel id = new JPanel();
        JPanel pass = new JPanel();
        JPanel panel_login_btn = new JPanel();

        JTextField id_text = new JTextField(20);
        JPasswordField pass_text = new JPasswordField(20); // 비밀번호 입력 시 '*'로 나타냄

        id.add(new JLabel("ID"));
        id.add(id_text);

        //pass panel 설정
        pass.add(new JLabel("Password"));
        pass.add(pass_text);
        pass_text.setEchoChar('*');


        //확인 버튼 panel 생성
        JButton menu_ok_btn = new JButton("확인");
        JButton menu_exit_btn = new JButton("종료");

        panel_login_btn.add(menu_exit_btn);
        panel_login_btn.add(menu_ok_btn);

        login.add(id);
        login.add(pass);
        login.add(panel_login_btn);
        login.setVisible(true);

        atm.add(login);
        atm.setVisible(true);

        menu_exit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu_ok_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(false); //  다른 panel로 전환

                //ID or Password 제약 조건 / id 길이 13자리 이상, password 길이 4자리 이상, password 문자 들어올시
                if (id_text.getText().length() > 13 || pass_text.getText().length() > 4) {
                    menu_error_page.setVisible(true);
                }
                //textField에서 값 받아오기
                else {
                    try {
                        accountNumber = id_text.getText();
                        password = Integer.parseInt(pass_text.getText());

                    } catch (NumberFormatException event) {
                        password = Integer.parseInt("0");
                    }
                }
                id_text.setText("");
                pass_text.setText("");

                //계좌번호 & 비밀번호 값 받아오기
                if (!accountNumber.equals("-1") && accountNumber.length() == 13) {
                    loginBool = transaction.Login(accountNumber, password);

                    //입출금 계좌 메뉴 선택
                    System.out.println(loginBool);
                    text_deposit_id_finish.setText(accountNumber);
                    text_withdraw_id_finish.setText(accountNumber);
                    text_balance_id.setText(accountNumber);

                    if (loginBool) { // 로그인 성공시 진행
                        System.out.println("go");
                        if (accountNumber.charAt(0) == '1') { // 입출금 계좌 = 사용자 계좌
                            menu_withdraw.setVisible(true);
                        } else if (accountNumber.charAt(0) == '2') { // 정기예금계좌 = 사용자 계좌
                            //정기예금 계좌 panel
                            menu_regular.setVisible(true);
                        } else if (accountNumber.charAt(0) == '0') { // 관리자 계좌 = 사용자 계좌
                            menu_manager.setVisible(true);
                        }
                    } else { // 로그인 정보가 일치하지 않을 때
                        //에러 panel
                        System.out.println("errrrr");
                        menu_error_page.setVisible(true);
                    }
                }

            }

        });

//입출금 패널
        JButton btn_deposit = new JButton("입금");
        JButton btn_withdraw = new JButton("출금");
        JButton btn_balance = new JButton("잔고조회");
        JButton btn_exit = new JButton("종료");


        JPanel first = new JPanel();
        first.add(btn_deposit);
        first.add(btn_withdraw);

        JPanel second = new JPanel();
        second.add(btn_balance);
        second.add(btn_exit);

        menu_withdraw.add(first);
        menu_withdraw.add(second);

        btn_deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_withdraw.setVisible(false);
                user_input.setVisible(true);
                deposit_withdraw = 1;

            }
        });
        btn_withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_withdraw.setVisible(false);
                user_input.setVisible(true);
                deposit_withdraw = 2;

            }

        });
        btn_balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_withdraw.setVisible(false);
                balance_user_show.setVisible(true);
                text_balance_balance.setText(Integer.toString(transaction.CheckBalance()));
            }
        });
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_withdraw.setVisible(false);
                login.setVisible(true);
                System.out.println(4);
            }
        });
        atm.add(menu_withdraw);
        menu_withdraw.setVisible(false);

//정기예금 panel
        JPanel panel_regural_btn = new JPanel();
        JButton btn_regular_balance = new JButton("잔고조회");
        JButton btn_regular_exit = new JButton("종료");
        panel_regural_btn.add(btn_regular_balance);
        panel_regural_btn.add(btn_regular_exit);

        menu_regular.add(panel_regural_btn);

        btn_regular_balance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_regular.setVisible(false);
                balance_user_show.setVisible(true);
                text_balance_balance.setText(Integer.toString(transaction.CheckBalance()));
            }
        });
        btn_regular_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_regular.setVisible(false);
                login.setVisible(true);
            }
        });
        atm.add(menu_regular);
        menu_regular.setVisible(false);

// 관리자 panel // manager_user_input panel 손보기
        JPanel panel_manager = new JPanel();
        JButton btn_maneger_reset = new JButton("초기화");
        JButton btn_manager_exit = new JButton("종료");

        btn_maneger_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_manager.setVisible(false);
                manager_user_input.setVisible(true);
            }
        });
        btn_manager_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_manager.setVisible(false);
                login.setVisible(true);
            }
        });
        panel_manager.add(btn_maneger_reset);
        panel_manager.add(btn_manager_exit);
        menu_manager.add(panel_manager);

        atm.add(menu_manager);
        menu_manager.setVisible(false);

// 로그인 정보 오류 panel
        JPanel error_btn = new JPanel();
        JPanel error_label = new JPanel();
        JPanel error_label2 = new JPanel();

        error_label.add(new JLabel("로그인 정보가 일치하지 않습니다."));
        error_label2.add(new JLabel("다시 입력해주세요!"));


        JButton btn_error_back = new JButton("확인");
        error_btn.add(btn_error_back);
        menu_error_page.add(error_label);
        menu_error_page.add(error_label2);
        menu_error_page.add(error_btn);

        btn_error_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_error_page.setVisible(false);
                login.setVisible(true);
            }
        });
        atm.add(menu_error_page);
        menu_error_page.setVisible(false);
// 지폐 수 입력 오류 panel
        JPanel panel_money_range_label = new JPanel();
        JPanel panel_money_range_label2 = new JPanel();
        JPanel panel_monety_range_label3 = new JPanel();
        JPanel panel_monuy_range_btn = new JPanel();

        panel_money_range_label.add(new JLabel("범위에 맞는 지폐 수를 입력하세요"));
        panel_money_range_label2.add(new JLabel("1만원권 : 1000 이하 & 0 < 입력 길이 < 9"));
        panel_monety_range_label3.add(new JLabel("5만원권 : 200 이하 & 0 < 입력 길이  < 9"));

        JButton btn_money_range_back = new JButton("확인");
        btn_money_range_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                money_cnt_error.setVisible(false);
                user_input.setVisible(true);
            }
        });

        panel_monuy_range_btn.add(btn_money_range_back);
        money_cnt_error.add(panel_money_range_label);
        money_cnt_error.add(panel_money_range_label2);
        money_cnt_error.add(panel_monety_range_label3);
        money_cnt_error.add(panel_monuy_range_btn);

        atm.add(money_cnt_error);
        money_cnt_error.setVisible(false);

// 지폐 수 입력 panel 설정
        JPanel one_panel = new JPanel();
        JPanel five_panel = new JPanel();
        JPanel panel_user_input_btn = new JPanel();

        one_panel.add(new JLabel("1만원\t\t\t"));
        one_panel.add(one_text);
        five_panel.add(new JLabel("5만원\t\t\t"));
        five_panel.add(five_text);

        JButton btn_user_input_back = new JButton("취소");
        JButton btn_user_input_front = new JButton("확인");

        panel_user_input_btn.add(btn_user_input_back);
        panel_user_input_btn.add(btn_user_input_front);

        user_input.add(one_panel);
        user_input.add(five_panel);
        user_input.add(panel_user_input_btn);

        btn_user_input_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_input.setVisible(false);
                menu_withdraw.setVisible(true);
            }
        });
        btn_user_input_front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_input.setVisible(false);
                //제약 조건 / 지폐 수 입력 오류 확인

                if (one_text.getText().length() > 0 && one_text.getText().length() < 9 && five_text.getText().length() > 0 && five_text.getText().length() < 9) {
                    fiveAmount = Integer.parseInt(five_text.getText());
                    oneAmount = Integer.parseInt(one_text.getText());
                    amount = fiveAmount * 50000 + oneAmount * 10000;
                    // deposit 입려 시 / 입금 최종

                    text_user_input_one_show.setText(Integer.toString(oneAmount));
                    text_user_input_five_show.setText(Integer.toString(fiveAmount));
                    text_deposit_money_finish.setText(Integer.toString(amount));
                    text_withdraw_money_finish.setText(Integer.toString(amount));
                    text_user_input_total_show.setText(Integer.toString(amount));
                    user_input_show.setVisible(true);
                }
                else {
                    money_cnt_error.setVisible(true);
                }
                one_text.setText("");
                five_text.setText("");
            }

        });

        atm.add(user_input);
        user_input.setVisible(false);

// 입금 및 출금 총 금액 보여주기 화면 / user_input_show
        JPanel one_five = new JPanel();
        JPanel total = new JPanel();
        JPanel btn_panel = new JPanel();

        JButton btn_user_input_show_back = new JButton("취소");
        JButton btn_user_input_show_front = new JButton("확인");

        btn_user_input_show_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_input_show.setVisible(false);
                user_input.setVisible(true);
            }
        });
        btn_user_input_show_front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_input_show.setVisible(false);
                int balance = transaction.CheckBalance();

                // ATMTransaction에서 입금 부분 call
                if (deposit_withdraw == 1) {
                    transaction.Deposit(fiveAmount, oneAmount);
                    // 입금 완료 후의 금액 출력
                    balance = transaction.CheckBalance();
                    transaction.UpdateBalance(balance); // 본계좌 잔고 업데이트
                    text_deposit_balance_finish.setText(Integer.toString(transaction.CheckBalance()));
                    deposit_user_show_finish.setVisible(true);
                } else if (deposit_withdraw == 2) {
                    if (transaction.CheckWithdrawFive(fiveAmount) || transaction.CheckWithdrawOne(oneAmount)) // ATM기에 오만원권의 수가 충분한지 확인
                    {
                        if (transaction.CheckWithdrawOne(oneAmount) && transaction.CheckWithdrawFive(fiveAmount)) // ATM기에 만원권의 수가 충분한지 확인
                        {
                            if (transaction.Withdraw(amount)) // 출금하려는 금액이 잔고보다 큰지 확인
                            {
                                text_withdraw_balance_finish.setText(Integer.toString(balance - amount));
                                transaction.Calculation(oneAmount, fiveAmount);
                                transaction.UpdateBalance(balance-amount); // 본계좌 잔고 업데이트
                                withdraw_user_show_finish.setVisible(true);
                            } else {
                                withdraw_user_balance_error.setVisible(true);
                                user_input_show.setVisible(false);
                                withdraw_user_show_finish.setVisible(false);
                            }
//                    System.out.println("계좌의 잔액이 부족합니다.");
                        } else {
                            user_input.setVisible(false);
                            user_input_show.setVisible(false);

                            if (transaction.CheckWithdrawFive(fiveAmount)){withdraw_user_moneyone_error.setVisible(true);}
                            else{withdraw_user_moneyfive_error.setVisible(true);}

                        }
                    }
                    else{user_input.setVisible(false);
                        user_input_show.setVisible(false);
                        withdraw_user_moneyOneFive_error.setVisible(true);
                    }
                }

            }
        });

        one_five.add(new JLabel("1만원\t\t"));
        one_five.add(text_user_input_one_show);
        one_five.add(new JLabel("5만원\t\t"));
        one_five.add(text_user_input_five_show);

        total.add(new JLabel("총 금액 \t\t"));
        total.add(text_user_input_total_show);

        btn_panel.add(btn_user_input_show_back);
        btn_panel.add(btn_user_input_show_front);

        user_input_show.add(one_five);
        user_input_show.add(total);
        user_input_show.add(btn_panel);

        atm.add(user_input_show);
        user_input_show.setVisible(false);

// 입금 panel / deposit_user_input

//입금 deposit_user_show_input

//입금 finish panel / deposit_user_show_finish

        JPanel panel_deposit_id_finish = new JPanel();
        JPanel panel_deposit_money_finish = new JPanel();
        JPanel panel_deposit_balance_finish = new JPanel();
        JPanel panel_deposit_finish_btn = new JPanel();

        panel_deposit_id_finish.add(new JLabel("계좌번호\t\t\t"));
        panel_deposit_money_finish.add(new JLabel("입금금액\t\t\t "));
        panel_deposit_balance_finish.add(new JLabel("잔고\t\t\t"));


        panel_deposit_id_finish.add(text_deposit_id_finish);
        panel_deposit_money_finish.add(text_deposit_money_finish);
        panel_deposit_balance_finish.add(text_deposit_balance_finish);

        JButton btn_deposit_finish_front = new JButton("확인");
        JButton btn_deposit_finish_back = new JButton("재입금");
        panel_deposit_finish_btn.add(btn_deposit_finish_back);
        panel_deposit_finish_btn.add(btn_deposit_finish_front);

        btn_deposit_finish_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit_user_show_finish.setVisible(false);
                user_input.setVisible(true);
            }
        });
        btn_deposit_finish_front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit_user_show_finish.setVisible(false);
                menu_withdraw.setVisible(true);
            }
        });

        deposit_user_show_finish.add(panel_deposit_id_finish);
        deposit_user_show_finish.add(panel_deposit_money_finish);
        deposit_user_show_finish.add(panel_deposit_balance_finish);
        deposit_user_show_finish.add(panel_deposit_finish_btn);

        atm.add(deposit_user_show_finish);
        deposit_user_show_finish.setVisible(false);


//출금 panel withdraw_user_input

//출금 input panel / withdraw_user_show_input

//출금 withdraw_user_moneyOneFive_error
        JPanel panel_withdraw_user_money_OneFive_error_label = new JPanel();
        JPanel panel_withdraw_user_money_OneFive_error_btn = new JPanel();
        panel_withdraw_user_money_OneFive_error_label.add(new JLabel("1만원권과 5만원권의 지폐 수가 부족합니다."));

        JButton btn_withdraw_user_money_OneFive_error = new JButton("확인");
        btn_withdraw_user_money_OneFive_error.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw_user_moneyOneFive_error.setVisible(false);
                user_input.setVisible(true);
            }
        });

        withdraw_user_moneyOneFive_error.add(panel_withdraw_user_money_OneFive_error_label);
        withdraw_user_moneyOneFive_error.add(panel_withdraw_user_money_OneFive_error_btn);

        atm.add(withdraw_user_moneyOneFive_error);
        withdraw_user_moneyOneFive_error.setVisible(false);
//출금 withdraw_user_moneyone_error
        JPanel panel_withdraw_money_error = new JPanel();
        //  JPanel panel_withdraw_moneyone_btn = new JPanel();
        panel_withdraw_money_error.add(new JLabel("ATM에 1만원권이 부족합니다."));

        JButton btn_withdraw_money_error = new JButton("확인");
        //  panel_withdraw_moneyone_btn.add(btn_withdraw_money_error);
        btn_withdraw_money_error.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw_user_moneyone_error.setVisible(false);
                user_input.setVisible(true);
            }
        });
        withdraw_user_moneyone_error.add(new JLabel("ERROR"));
        withdraw_user_moneyone_error.add(panel_withdraw_money_error);
        withdraw_user_moneyone_error.add(btn_withdraw_money_error);

        atm.add(withdraw_user_moneyone_error);
        withdraw_user_moneyone_error.setVisible(false);


//출금 지폐 수 5만원권 부족 error / withdraw_user_moneyfive_error
        JPanel panel_withdraw_moneyfive_error = new JPanel();
        // JPanel panel_withdraw_moneyfive_btn = new JPanel();
        //panel_withdraw_moneyfive_error.add(new JLabel("ERROR"));

        panel_withdraw_moneyfive_error.add(new JLabel("ATM에 5만원권이 부족합니다."));

        JButton btn_withdraw_moneyfive_error = new JButton("확인");
        // panel_withdraw_moneyfive_btn.add(btn_withdraw_money_error);

        btn_withdraw_moneyfive_error.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw_user_moneyfive_error.setVisible(false);
                user_input.setVisible(true);
            }
        });
        withdraw_user_moneyfive_error.add(new JLabel("ERROR"));
        withdraw_user_moneyfive_error.add(panel_withdraw_moneyfive_error);
        withdraw_user_moneyfive_error.add(btn_withdraw_moneyfive_error);

        atm.add(withdraw_user_moneyfive_error);
        withdraw_user_moneyfive_error.setVisible(false);

//출금 잔고 부족 / withdraw_user_balance_error
        JPanel panel_withdraw_balance_error = new JPanel();
        panel_withdraw_balance_error.add(new JLabel("계좌의 잔고가 부족합니다."));

        JButton btn_withdraw_balance_error = new JButton("확인");
        btn_withdraw_balance_error.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw_user_balance_error.setVisible(false);
                user_input.setVisible(true);
            }
        });

        withdraw_user_balance_error.add(new JLabel("ERROR"));
        withdraw_user_balance_error.add(panel_withdraw_balance_error);
        withdraw_user_balance_error.add(btn_withdraw_balance_error);

        atm.add(withdraw_user_balance_error);
        withdraw_user_balance_error.setVisible(false);

//출금 절차 완료 panel / withdraw_user_show_finish
        JPanel panel_withdraw_id_finish = new JPanel();
        JPanel panel_withdraw_money_finish = new JPanel();
        JPanel panel_withdraw_balance_finish = new JPanel();
        JPanel panel_withdraw_finish_btn = new JPanel();

        panel_withdraw_id_finish.add(new JLabel("계좌번호\t\t\t"));
        panel_withdraw_money_finish.add(new JLabel("출금금액\t\t\t "));
        panel_withdraw_balance_finish.add(new JLabel("잔고\t\t\t"));


        panel_withdraw_id_finish.add(text_withdraw_id_finish);
        panel_withdraw_money_finish.add(text_withdraw_money_finish);
        panel_withdraw_balance_finish.add(text_withdraw_balance_finish);

        JButton btn_withdraw_finish_front = new JButton("확인");
        JButton btn_withdraw_finish_back = new JButton("재출금");

        panel_withdraw_finish_btn.add(btn_withdraw_finish_back);
        panel_withdraw_finish_btn.add(btn_withdraw_finish_front);

        btn_withdraw_finish_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw_user_show_finish.setVisible(false);
                user_input.setVisible(true);
            }
        });
        btn_withdraw_finish_front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw_user_show_finish.setVisible(false);
                menu_withdraw.setVisible(true);
            }
        });

        withdraw_user_show_finish.add(panel_withdraw_id_finish);
        withdraw_user_show_finish.add(panel_withdraw_money_finish);
        withdraw_user_show_finish.add(panel_withdraw_balance_finish);
        withdraw_user_show_finish.add(panel_withdraw_finish_btn);

        atm.add(withdraw_user_show_finish);
        withdraw_user_show_finish.setVisible(false);

//잔고조회 panel / balance_user_show
        JPanel panel_balance_id = new JPanel();
        JPanel panel_balance_balance = new JPanel();
        JPanel panel_balance_btn = new JPanel();

        JButton btn_balance_back = new JButton("확인");
        panel_balance_btn.add(btn_balance_back);

        btn_balance_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                balance_user_show.setVisible(false);

                if (accountNumber.charAt(0) == '1') menu_withdraw.setVisible(true);
                else if (accountNumber.charAt(0) == '2') menu_regular.setVisible(true);
            }
        });
        panel_balance_id.add(new JLabel("계좌번호\t\t\t"));
        panel_balance_id.add(text_balance_id);
        panel_balance_balance.add(new JLabel("잔고"));
        panel_balance_balance.add(text_balance_balance);;


        balance_user_show.add(panel_balance_id);
        balance_user_show.add(panel_balance_balance);
        balance_user_show.add(panel_balance_btn);

        atm.add(balance_user_show);
        balance_user_show.setVisible(false);

//관리자 panel / manager_user_input
        JPanel panel_manager_input_label = new JPanel();
        panel_manager_input_label.add(new JLabel("ATM의 돈을 초기화 하시겠습니까?"));
        JPanel panel_manager_input_btn = new JPanel();

        manager_user_input.add(panel_manager_input_label);
        JButton btn_manager_back = new JButton("취소");
        JButton btn_manager_front = new JButton("확인");

        btn_manager_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_user_input.setVisible(false);
                menu_manager.setVisible(true);
            }

        });
        btn_manager_front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_user_input.setVisible(false);
                manager_user_show_finish.setVisible(true);
                transaction.Reset();
//                System.out.println("\nATM 기기 금액을 초기화하겠습니까? (0: 예, 1: 아니오)");
//                String selection = scan.nextLine();
//
//                if (selection.equals("0")) {
//                    transaction.Reset();
//                    // reset panel 설정
//                    System.out.println("ATM 기기 금액을 초기화했습니다.");
//                } else {
//                    transaction.Nothing();
//                }
//
            }
        });
        panel_manager_input_btn.add(btn_manager_back);
        panel_manager_input_btn.add(btn_manager_front);

        manager_user_input.add(panel_manager_input_btn);
        atm.add(manager_user_input);
        manager_user_input.setVisible(false);

//관리자 최종 panel / manager_user_show_finish
        JPanel panel_manager_show_finish_label = new JPanel();
        JPanel panel_manager_show_finish_label2 = new JPanel();

        JPanel panel_manager_finish_btn = new JPanel();

        panel_manager_show_finish_label.add(new JLabel("ATM의 돈을 초기화했습니다."));
        panel_manager_show_finish_label2.add(new JLabel("(1만원 : 1000장, 5만원 : 200장)"));

        JButton btn_manager_finish = new JButton("확인");
        panel_manager_finish_btn.add(btn_manager_finish);
        btn_manager_finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_user_show_finish.setVisible(false);
                menu_manager.setVisible(true);
            }
        });

        manager_user_show_finish.add(panel_manager_show_finish_label);
        manager_user_show_finish.add(panel_manager_show_finish_label2);
        manager_user_show_finish.add(panel_manager_finish_btn);
        atm.add(manager_user_show_finish);
        manager_user_show_finish.setVisible(false);

// 모든 panel 정의 완료


        System.out.println("panel");
    }
// 로그인 정보 확인한 후 페이지 변경 & 버튼 입력 후 부터 정보 확인 start


//    void MenuSelectwindow() { // 입출금계좌 로그인 성공 했을 시

//        while (true) {// 로그아웃 이전까지 무한 반복
//            Scanner scan = new Scanner(System.in);
//
//            System.out.println("\n업무를 선택해주세요. (0: 입금, 1: 출금, 2: 잔액확인, 3: 로그아웃)");
//
//
//            if(select_work == 3) { // 로그아웃
//                transaction.Logout(); // DB에 데이터 저장
//                end = true; // 로그아웃 정보 확인
//                break;
//            }
//            // 입금
//            else if(select_work == 0) { Deposit(); }
//            // 출금
//            else if(select_work == 1) { Withdraw(); }
//            // 잔고조회
//            else if(select_work == 2) { System.out.println("잔액 : " + transaction.CheckBalance()); }
//            //로그인 정보 불일치
//            else { System.out.println("잘못된 입력입니다. 다시 입력해주세요."); }
//        }
//    }
//



}