package VIEW;

import DAO.jovens_dao;
import DTO.jovens_dto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class jovens_view extends JFrame {

    JLabel lb_nome, lb_idade, lb_cpf, lb_sexo, lb_supervisor;
    JTextField txt_nome, txt_cpf, txt_supervisor;
    JSpinner sp_idade;
    JComboBox cb_sexo;
    JButton bt_cadastro;

    public jovens_view(){
        setTitle("Jovens");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        lb_nome = new JLabel("Nome");
        lb_nome.setBounds(15, 40, 250, 50);
        lb_nome.setFont(new Font("Arial Black",20,14));
        add(lb_nome);

        txt_nome = new JTextField();
        txt_nome.setBounds(65, 55, 200, 20);
        add(txt_nome);

        lb_idade = new JLabel("Idade");
        lb_idade.setBounds(15, 70, 250, 50);
        lb_idade.setFont(new Font("Arial Black",20,14));
        add(lb_idade);

        SpinnerNumberModel spinner = new SpinnerNumberModel(0, 0, 100, 1);
        sp_idade = new JSpinner(spinner);
        sp_idade.setBounds(65, 85, 50,25);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) sp_idade.getEditor()).getTextField();
        tf.setEditable(false);
        add(sp_idade);

        lb_cpf = new JLabel("CPF");
        lb_cpf.setBounds(130, 70, 250, 50);
        lb_cpf.setFont(new Font("Arial Black",20,14));
        add(lb_cpf);

        txt_cpf = new JTextField();
        txt_cpf.setBounds(165, 87, 100, 20);
        add(txt_cpf);

        //txt_cpf aceitar apenas numero
        txt_cpf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Bloqueia a tecla
                }
            }
        });

        lb_sexo = new JLabel("Sexo");
        lb_sexo.setBounds(15, 105, 250, 50);
        lb_sexo.setFont(new Font("Arial Black",20,14));
        add(lb_sexo);

        String[] sexos = {"Masculino", "Feminino", "Outros"};
        cb_sexo = new JComboBox(sexos);
        cb_sexo.setBounds(65, 120, 200, 20);
        add(cb_sexo);

        lb_supervisor = new JLabel("Supervisor");
        lb_supervisor.setBounds(15, 140, 250, 50);
        lb_supervisor.setFont(new Font("Arial Black",20,14));
        add(lb_supervisor);

        txt_supervisor = new JTextField();
        txt_supervisor.setBounds(15, 180, 200, 20);
        add(txt_supervisor);

        bt_cadastro = new JButton("Cadastro");
        bt_cadastro.setBounds(15, 300, 100, 30);
        add(bt_cadastro);

        bt_cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, cpf, sexo, supervisor;
                int idade;

                nome = txt_nome.getText();
                idade = (int) sp_idade.getValue();
                cpf = txt_cpf.getText();
                sexo = (String) cb_sexo.getSelectedItem();
                supervisor = txt_supervisor.getText();

                jovens_dto objjovensdto = new jovens_dto();
                objjovensdto.setNome_jovem(nome);
                objjovensdto.setIdade_jovem(idade);
                objjovensdto.setCpf_jovem(cpf);
                objjovensdto.setSexo_jovem(sexo);
                objjovensdto.setSupervisor_jovem(supervisor);

                jovens_dao objjovensdao = new jovens_dao();
                objjovensdao.cadastrajovem(objjovensdto);

                JOptionPane.showMessageDialog(null,"Cadastro conluído");
            }
        });
    }
}
