package project.kolearn.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.Random;

@RestController
public class rdgram{
    @GetMapping("/randgram")
    public static String randgram(String[] args) throws Exception {
        Random rd = new Random();
        Connection con = null;

        String DB_URL = "jdbc:mysql://localhost:3306/randomgram?useUnicode=true&characterEncoding=utf8";
        String DB_USER = "root";
        String DB_PASSWORD = "carmel1028!";

//        String[] gram = new String[8];

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // 데이터베이스의 연결을 얻는다.
        Statement stmt = conn.createStatement(); // Statement를 생성한다.

        int n = 0;
        int randomnum;
        String query = "SELECT * FROM randomgram.grammar";
        String gram = "";
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
                    for (int i = 2; i < 8; i++) {
                        if (i == 6) {
                            stringBuilder.append("<br>examples<br>");
                        }
                        stringBuilder.append(rs.getString(i) + "<br>");
                    }
                    gram = stringBuilder.toString();
                }
            }
        } catch (SQLException e) {
            System.out.println("쿼리 가져오기 실패");
            e.printStackTrace();
        }
        return gram;
    }
}
