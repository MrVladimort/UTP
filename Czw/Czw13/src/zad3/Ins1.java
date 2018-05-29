package zad3;

import java.sql.*;

public class Ins1 {

    static public void main(String[] args) throws SQLException {
        new Ins1();
    }

    Statement stmt;

    Ins1() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby:E:/Workspace/IntelliJIDEA/UTP/Czw/Czw13/DerbyDbs/ksidb");
            con.setAutoCommit(false);
            stmt = con.createStatement();
        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawc�w do wpisywania do tabeli
        String[] wyd = {"PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM"};

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;
        String[] ins = {"INSERT INTO WYDAWCA  VALUES(" + beginKey + "," + "'" + wyd[0] + "'" + ")",
                "INSERT INTO WYDAWCA  VALUES(" + ++beginKey + "," + "'" + wyd[1] + "'" + ")",
                "INSERT INTO WYDAWCA  VALUES(" + ++beginKey + "," + "'" + wyd[2] + "'" + ")",
                "INSERT INTO WYDAWCA  VALUES(" + ++beginKey + "," + "'" + wyd[3] + "'" + ")",
                "INSERT INTO WYDAWCA  VALUES(" + ++beginKey + "," + "'" + wyd[4] + "'" + ")",
                "INSERT INTO WYDAWCA  VALUES(" + ++beginKey + "," + "'" + wyd[5] + "'" + ")"
        }; // ? ... tablica instrukcji SQL do wpisywania rekord�w do tabeli: INSERT ...

        int insCount = 0;
        for (String in : ins) insCount += stmt.executeUpdate(in);

        System.out.println("Insertion count: " + insCount);

        ResultSet rs = stmt.executeQuery("SELECT * FROM WYDAWCA");
        String arr;
        while (rs.next()) {
            String em = rs.getString("WYDID") + "   " + rs.getString("NAME");
            arr = em.replace("\n", ",");
            System.out.println(arr);
        }

        beginKey = 15;
        int delCount = 0;
        PreparedStatement stm;
        // przygotowanie instrukcji prekompilowanej
        stm = con.prepareStatement("DELETE FROM WYDAWCA WHERE NAME = ? OR WYDID = ?");    // usunięcie z tabeli WYDAWCA rekordu o podanej nazwie wydawcy z tablicy wyd lub o podanym numerze wydawcy zaczynającym się od beginKey
        for (String aWyd : wyd) {
            stm.setString(1, aWyd);
            stm.setInt(2, beginKey++);
            delCount += stm.executeUpdate();
        }

        System.out.println("Deletion count: " + delCount);

        rs = stmt.executeQuery("SELECT * FROM WYDAWCA");
        while (rs.next()) {
            String em = rs.getString("WYDID") + "   " + rs.getString("NAME");
            arr = em.replace("\n", ",");
            System.out.println(arr);
        }
    }
}