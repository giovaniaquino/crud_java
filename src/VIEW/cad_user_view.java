package VIEW;

import DAO.user_dao;
import DTO.user_dto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cad_user_view extends JFrame {
    JLabel lb_titulo, lb_nome, lb_senha, lb_con_senha;
    JTextField txt_nome;
    JPasswordField pf_senha, pf_con_senha;
    JButton bt_volta, bt_cadastro;

    public cad_user_view(){
        setSize(600,500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        lb_titulo = new JLabel("Cadastro Usuário");
        lb_titulo.setBounds(100,20,250,50);
        lb_titulo.setFont(new Font("Arial Black",20,22));
        add(lb_titulo);

        lb_nome = new JLabel("Nome");
        lb_nome.setBounds(15, 160, 250, 50);
        lb_nome.setFont(new Font("Arial Black",20,14));
        add(lb_nome);

        txt_nome = new JTextField();
        txt_nome.setBounds(75, 175, 200, 20);
        add(txt_nome);

        lb_senha = new JLabel("Senha");
        lb_senha.setBounds(15, 190, 250, 50);
        lb_senha.setFont(new Font("Arial Black",20,14));
        add(lb_senha);

        pf_senha = new JPasswordField();
        pf_senha.setBounds(75, 205, 200, 20);
        add(pf_senha);

        lb_con_senha = new JLabel("Confirme a senha");
        lb_con_senha.setBounds(15, 220, 250, 50);
        lb_con_senha.setFont(new Font("Arial Black",20,14));
        add(lb_con_senha);

        pf_con_senha = new JPasswordField();
        pf_con_senha.setBounds(160, 235, 200, 20);
        add(pf_con_senha);

        bt_volta = new JButton("Voltar");
        bt_volta.setBounds(500, 30, 90, 30);
        add(bt_volta);

        bt_volta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_view objmainview = new main_view();
                objmainview.setVisible(true);
                dispose();
            }
        });

        bt_cadastro = new JButton("Cadastro");
        bt_cadastro.setBounds(250, 350, 90, 30);
        add(bt_cadastro);

        bt_cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, senha;

                if (pf_con_senha.getText().equals(pf_senha.getText())) {
                    nome = txt_nome.getText();
                    senha = pf_senha.getText();


                    user_dto objuserdto = new user_dto();
                    objuserdto.setNome_usuario(nome);
                    objuserdto.setSenha_usuario(senha);

                    user_dao objuserdao = new user_dao();
                    objuserdao.cadastrausuario(objuserdto);

                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                    limpar();
                }else {
                    JOptionPane.showMessageDialog(null,"Os campos de senha não são iguais");
                }
            }
        });
    }

    private void limpar(){
        txt_nome.setText("");
        pf_senha.setText("");
        pf_con_senha.setText("");
    }
}
