package connections.localNoSQL;

import connections.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class LocalConnect {
    private static final Logger logger = LogManager.getLogger(LocalConnect.class);

    public static Response connect(LocalConnTypes connTypes) {
        String url = "jdbc:sqlite:RedGeneralLocal.db";
        logger.debug("'connect' method called with "+ connTypes.toString() + " type.");
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()){
            switch (connTypes){
                case SET_DATA:
                    String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                            + "	id integer PRIMARY KEY,\n"
                            + "	name text NOT NULL,\n"
                            + "	capacity real\n"
                            + ");";
                    stmt.execute(sql);
                    break;
                case GET_DATA:
                    DatabaseMetaData md = conn.getMetaData();
                    ResultSet rs = md.getTables(null, null, "%", null);
                    while (rs.next()) {
                        System.out.println(rs.getString(3));
                    }

            }




            return new Response(Response.ResponseType.OK);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            return new Response(Response.ResponseType.FAIL, e);

        }
    }
}
