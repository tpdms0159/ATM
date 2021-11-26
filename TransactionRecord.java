import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TransactionRecord {

    private List<List<String>> list = new ArrayList<List<String>>(); // 로그 기록 리스트

    void logRecord(String log) { // 거래내역 저장 및 출력 - 거래 진행과 동시에 출력

        SimpleDateFormat timeFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String time1 = timeFormat.format(time); // 로그가 기록되는 시간
        List<String> newLog = new ArrayList<String>(); // 거래 내용 저장 리스트
        newLog.add(time1 + "," + log); // 시간 + 거래 내용
        list.add(newLog);

        System.out.println("<" + time1 + "> " + log); // 거래내역 로그 출력

    }
}