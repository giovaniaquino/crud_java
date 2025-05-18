package VIEW;

import DAO.user_dao;
import DTO.user_dto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login_view extends JFrame {
    private JTextField txt_senha_user;
    private JButton bt_entra;
    private JTextField txt_nome_user;
    private JPanel painel_login;

    public login_view() {
       setContentPane(painel_login);
       setTitle("Login");
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setSize(500,400);
       setVisible(true);
       bt_entra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome_usuario, senha_usuario;

                    nome_usuario = txt_nome_user.getText();
                    senha_usuario = txt_senha_user.getText();

                    user_dto obj_usuario_dto = new user_dto();
                    obj_usuario_dto.setNome_usuario(nome_usuario);
                    obj_usuario_dto.setSenha_usuario(senha_usuario);

                    user_dao obj_userdao = new user_dao();
                    ResultSet rs_userdao = obj_userdao.autenticacao_user(obj_usuario_dto);

                    if (rs_userdao.next()){
                        //chamar tela
                        main_view obj_mainview = new main_view();
                        obj_mainview.setVisible(true);
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "Usuario ou senha invalida");
                    }
                }catch (SQLException erro){
                    JOptionPane.showMessageDialog(null, "loginView "+erro);
                }
            }
        });
    }
    public static void main(String[] args){
        new login_view();
    }
}
