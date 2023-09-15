import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class random_test{

    public static void main (String[] args) throws Exception {
        //System.out.println(randgram());
        System.out.println(examples());

    }
    private static String RandomGrammar = "";
    private static String examplestr="";
    private static String examplenow="";
    private static java.util.Date lastFetchedDate = null;
    private static java.util.Date lastFetchedDate2 = null;
    static int randomnum=1;
    @GetMapping("/randgram")
    public static String randgram() throws Exception {
        java.util.Date currentDate = new java.util.Date();

        if (lastFetchedDate == null || !isSameDay(lastFetchedDate, currentDate)) {
            // Fetch new random grammar if it's a new day
            RandomGrammar = fetchrandgram();
            lastFetchedDate = currentDate;
        }

        return RandomGrammar;
    }
    @GetMapping("/examples")
    public static String examples() throws Exception {
        java.util.Date currentDate = new java.util.Date();
        Date currentDate1 = new Date();

        if (lastFetchedDate2 == null || !isSameDay(lastFetchedDate2, currentDate1)) {
            // Fetch new random grammar if it's a new day
            fetchrandgram();
            examplenow = examplestr;
            lastFetchedDate2 = currentDate1;
        }
        return examplenow;
    }
    private static String fetchrandgram() throws Exception {
        Random rd = new Random();

        String DB_URL = "jdbc:mysql://localhost:3306/randomgram?useUnicode=true&characterEncoding=utf8";
        String DB_USER = "root";
        String DB_PASSWORD = "carmel1028!";

        int n = 0;
        String query = "SELECT * FROM randomgram.grammar";
        String gram = "";
        examplestr="";
        try {
            //assert con != null;
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // 데이터베이스의 연결을 얻는다.
            Statement stmt = conn.createStatement(); // Statement를 생성한다.
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                n++;
            }
            randomnum = rd.nextInt(n) + 1;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder1.append("<br>");
            while (rs.next()) {
                if (rs.getInt(1) == randomnum) {
                    for (int i = 2; i < 8; i++) {
                        if(i==6){
                            stringBuilder2.append("<br>examples<br>");
                            stringBuilder2.append(rs.getString(i) + "<br>");
                        } else if (i>6) {
                            stringBuilder2.append(rs.getString(i) + "<br>");
                        } else{
                            stringBuilder1.append(rs.getString(i) + "<br>");
                        }
                    }
                    gram = stringBuilder1.toString();
                    examplestr = stringBuilder2.toString();
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("쿼리 가져오기 실패");
            e.printStackTrace();
        }
        return gram;
    }

    private static boolean isSameDay(java.util.Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}


