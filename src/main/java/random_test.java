import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.sql.*;
import java.util.Random;

public class random_test{
    public static void main (String[] args) throws Exception {
        Random rd = new Random();
        Connection con = null;

        String DB_URL = "jdbc:mysql://localhost:3306/randomgram?useUnicode=true&characterEncoding=utf8";
        String DB_USER = "root";
        String DB_PASSWORD = "carmel1028!";

        String[] gram = new String[8];

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // 데이터베이스의 연결을 얻는다.
        Statement stmt = conn.createStatement(); // Statement를 생성한다.

        int n = 0;
        int randomnum;
        String query = "SELECT * FROM randomgram.grammar";
        try {
            //assert conn != null;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                n++;
            }
            randomnum = rd.nextInt(n) + 1;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            StringBuilder stringBuilder = new StringBuilder();
            while (rs.next()) {
                if (rs.getInt(1) == randomnum) {
                    int g=0;
                    for (int i = 2; i < 8; i++) {
//                        if (i == 6) {
//                            gram[g]="<br>";
//                            System.out.println(gram[g]);
//                            g++;
//                            gram[g]="example<br>";
//                            System.out.println(gram[g]);
//                            g++;
//                        }
//                        gram[g]=rs.getString(i)+"<br>";
//                        System.out.println(gram[g]);
//                        g++;
                        stringBuilder.append(rs.getString(i)+"<br>");
                        g++;

                    }
                    String strArrayToString = stringBuilder.toString();
                    System.out.println(strArrayToString);
                }
            }
        } catch (SQLException e) {
            System.out.println("쿼리 가져오기 실패");
            e.printStackTrace();
        }
        //return gram;
    }
}


