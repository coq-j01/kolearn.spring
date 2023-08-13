import java.sql.*;
import java.util.Random;

public class randomg {
    public static void main(String[] args) {
        Random rd = new Random();
        Connection con = null;

        String server = "localhost";
        String user_name = "root";
        String password = "carmel1028!";

        try{ //jdbc 드라이버 로드
            Class.forName("com.mysql.jdbc.Driver");

        } catch(ClassNotFoundException e){
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생"+e.getMessage());
            e.printStackTrace();
        }
        try{ //접속
            con = DriverManager.getConnection("jdbc:mysql://"+server+"/"+"?useSSL=false",user_name,password);
            System.out.println("연결 완료!");
        } catch(SQLException e){
            System.err.println("연결 오류"+e.getMessage());
            e.printStackTrace();
        }
        int n=0;
        int randomnum;
        String query = "SELECT * FROM randomgram.grammar";
        try{
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                n++;
            }
            randomnum = rd.nextInt(n)+1;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getInt(1)==randomnum){
                    for(int i=2; i<8; i++){
                        if(i==6) System.out.println("\nexample");
                        System.out.println(rs.getString(i));
                    }
                }
            }
        } catch(SQLException e){
            System.out.println("쿼리 가져오기 실패");
            e.printStackTrace();
        }

        //접속 종료
        try{
            if(con!=null)
                con.close();
        } catch(SQLException e){}
    }
}
