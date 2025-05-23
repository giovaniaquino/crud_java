package VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class main_view extends JFrame {
    private JButton bt_cadastro;

    public main_view(){
    setTitle("Menu Princical");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(600,500);
    setLocationRelativeTo(null);
    setResizable(false);
    getContentPane().setBackground(Color.lightGray);
    setLayout(null);

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



    }
}
