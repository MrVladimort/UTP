package zad4;

import java.sql.*;

public class Exec {
    static public void main(String[] args) throws SQLException {
        new Exec();
        new Exec("123");
    }

    private Statement stmt;

    private Exec() throws SQLException {
        System.out.println("Exec 1");

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby:E:/Workspace/IntelliJIDEA/UTP/Czw/Czw13/DerbyDbs/ksidb");
            con.setAutoCommit(false);
            stmt = con.createStatement();
        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }

        String sel = "SELECT a.NAME, p.TYTUL, p.ROK, p.CENA FROM pozycje p" +
                " INNER JOIN autor a ON p.AUTID = a.AUTID " +
                "WHERE p.CENA > 30.0 AND p.ROK > 2000";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sel);

        while (rs.next()) {
            String nazwisko = rs.getString("NAME").split(" ")[0];// ... ?
            System.out.println("Autor: " + nazwisko);
            String em = "Tytul: " + rs.getString("TYTUL") +
                    "   Rok: " + rs.getString("ROK") +
                    "   Cena: " + rs.getString("CENA");
            System.out.println(em + '\n' + "======================================================================");
        }
//        stmt.close();
//            con.close();
    }

    private Exec(String str) throws SQLException {
        System.out.println("Exec 2");

        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby:E:/Workspace/IntelliJIDEA/UTP/Czw/Czw13/DerbyDbs/ksidb");
            con.setAutoCommit(false);
            stmt = con.createStatement();
        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }

        String sel = "SELECT * FROM pozycje";// ... ?
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sel);
        ResultSetMetaData rsmd = rs.getMetaData();
        int cc = rsmd.getColumnCount();
        for (int i = 1; i <= cc; i++)
            System.out.print(rsmd.getColumnLabel(i) + "     ");

        System.out.println("\n------------------------------ przewijanie do góry");

        while (rs.next()) {
            for (int i = 1; i <= cc; i++)
                System.out.print(rs.getString(i) + "     ");
            System.out.println();
        }

        System.out.println("\n----------------------------- pozycjonowanie abs.");
        int[] poz = {3, 7, 9};
        for (int aPoz : poz) {
            System.out.print("[ " + aPoz + " ]: ");
            System.out.print(rs.absolute(aPoz) + " ");
            for (int i = 1; i <= cc; i++) System.out.print(rs.getString(i) + ", ");
            System.out.println("");
        }
//        stmt.close();
//        con.close();
    }
}
