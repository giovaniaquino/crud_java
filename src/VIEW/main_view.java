package VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class main_view extends JFrame {
    JButton bt_cadastro, bt_estoque, bt_cad_user;
    JLabel logoLabel;

    public main_view(){
        setTitle("Menu Princical");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/img/logo.png"));

        // Redimensiona se necessário (opcional)
        Image img = logoIcon.getImage();
        Image resizedImg = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedImg);

        logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(100, 50, 400, 400); // Ajuste as coordenadas conforme necessário
        add(logoLabel);

        bt_cadastro = new JButton("Jovens");
        bt_cadastro.setBounds(10, 10, 100, 30);
        add(bt_cadastro);

        bt_cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jovens_view obj_jovensview = new jovens_view();
                obj_jovensview.setVisible(true);
                dispose();
            }
        });
        bt_estoque = new JButton("Estoque");
        bt_estoque.setBounds(115, 10, 100, 30);
        add(bt_estoque);

        bt_estoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estoque_view objestoqueview = new estoque_view();
                objestoqueview.setVisible(true);
                dispose();
            }
        });

        bt_cad_user = new JButton("Usuarios");
        bt_cad_user.setBounds(220, 10, 100, 30);
        add(bt_cad_user);

        bt_cad_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cad_user_view objcaduserview = new cad_user_view();
                objcaduserview.setVisible(true);
                dispose();
            }
        });
    }
}
