package DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao_dao {

    public Connection conecta_bd(){
        Connection conexao = null;

        try {
            String url = "jdbc:mysql://localhost:3306/cons_futuro";
            String user = "root";
            String senha = "root";
            conexao = DriverManager.getConnection(url, user, senha);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return conexao;
    }
}
