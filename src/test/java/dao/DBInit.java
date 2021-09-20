package dao;

import dao.util.DirectConnectionManager;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DBInit {


    public static void startUp() throws Exception{
        URL url1 = VocabularyDaoTest.class.getClassLoader().getResource("tables.sql");
        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        String sql1 = str1.stream().collect(Collectors.joining());

        URL url2 = VocabularyDaoTest.class.getClassLoader().getResource("filling.sql");
        List<String> str2 = Files.readAllLines(Paths.get(url2.toURI()));
        String sql2 = str2.stream().collect(Collectors.joining());

        try (Connection con = new DirectConnectionManager().getConnection();
             Statement stmt = con.createStatement())
        {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
        }
    }
}
