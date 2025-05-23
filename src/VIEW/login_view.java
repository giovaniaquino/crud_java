package VIEW;

import DAO.user_dao;
import DTO.user_dto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login_view extends JFrame {
    JPasswordField txt_senha_user;
    JLabel lb_titulo, lb_nome, lb_senha;
    JButton bt_entra;
    JTextField txt_nome_user;

    public login_view() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        lb_titulo = new JLabel("Construir Futuro");
        lb_titulo.setBounds(100, 50, 400, 50);
        lb_titulo.setFont(new Font("Arial Black",20,20));
        add(lb_titulo);

        lb_nome = new JLabel("Nome de Usuario");
        lb_nome.setBounds(50, 120, 200, 50);
        lb_nome.setFont(new Font("Arial Black",20,14));
        add(lb_nome);

        txt_nome_user = new JTextField();
        txt_nome_user.setBounds(50, 160, 250, 20);
        add(txt_nome_user);

        lb_senha = new JLabel("Senha");
        lb_senha.setBounds(50, 190, 200, 50);
        lb_senha.setFont(new Font("Arial Black",20,14));
        add(lb_senha);

        txt_senha_user = new JPasswordField();
        txt_senha_user.setBounds(50, 230, 250, 20);
        add(txt_senha_user);

        bt_entra = new JButton("Entrar");
        bt_entra.setBounds(90, 300, 200, 40);
        add(bt_entra);

        setVisible(true);
        bt_entra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logar();
            }
        });
    }
    public static void main(String[] args){
        new login_view();
    }
    private void logar(){
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
}
