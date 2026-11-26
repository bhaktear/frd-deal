package abl.frd.repository;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import abl.frd.service.CommonService;

@Repository 
public class SecondaryQueryRepository {
    private final DataSource dataSource;
    private String userTbl = "user";
    public SecondaryQueryRepository(@Qualifier("secondaryDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> getData(String sql, List<Object> params) {
        Map<String, Object> resp = new HashMap<>();
        List<Map<String, Object>> rows = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));
                }
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                if (!rs.next()) {
                    return CommonService.getResp(1, "No data found", rows);
                }

                do {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnsNumber; i++) {
                        String columnName = rsmd.getColumnLabel(i); // respects SQL aliases (AS ...)
                        Object columnValue = rs.getObject(i);
                        row.put(columnName, columnValue);
                    }
                    rows.add(row);
                } while (rs.next());

                return CommonService.getResp(0, "Data Found", rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp = CommonService.getResp(1, "An error occurred: " + e.getMessage(), rows);
        }

        return resp;
    }

    public Map<String, Object> getUser(String userId){
        List<Object> params = new ArrayList<>();
        String queryStr = "SELECT * FROM " + this.userTbl + " a JOIN user_role b on a.user_id = b.user_id JOIN role c on b.role_id= c.role_id WHERE login_id=?";
        params.add(userId);
        return getData(queryStr,params);
    }


}
