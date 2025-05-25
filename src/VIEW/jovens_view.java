package VIEW;

import DAO.jovens_dao;
import DTO.jovens_dto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class jovens_view extends JFrame {

    JLabel lb_nome, lb_idade, lb_cpf, lb_sexo, lb_supervisor, lb_id, lb_titulo;
    JTextField txt_nome, txt_cpf, txt_supervisor, txt_id;
    JSpinner sp_idade;
    JComboBox cb_sexo;
    JButton bt_cadastro, bt_apaga, bt_atualiza, bt_volta, bt_limpa;
    JTable tb_jovem;
    JScrollPane sp_tabela;

    public jovens_view(){
        setSize(600,500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);


        String[] colunas = {"ID", "Nome", "Idade", "CPF", "Sexo", "Supervisor"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tb_jovem = new JTable(modelo);
        tb_jovem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        sp_tabela = new JScrollPane(tb_jovem);
        sp_tabela.setBounds(300, 40, 290, 400);
        add(sp_tabela);
        lista_jovem();

        lb_titulo = new JLabel("Jovens");
        lb_titulo.setBounds(100,20,250,50);
        lb_titulo.setFont(new Font("Arial Black",20,22));
        add(lb_titulo);

        lb_id = new JLabel("ID");
        lb_id.setBounds(15, 60, 250, 50);
        lb_id.setFont(new Font("Arial Black",20,14));
        add(lb_id);

        txt_id = new JTextField();
        txt_id.setBounds(65, 75, 40, 20);
        txt_id.setEditable(false);
        add(txt_id);

        lb_nome = new JLabel("Nome");
        lb_nome.setBounds(15, 90, 250, 50);
        lb_nome.setFont(new Font("Arial Black",20,14));
        add(lb_nome);

        txt_nome = new JTextField();
        txt_nome.setBounds(65, 105, 200, 20);
        add(txt_nome);

        lb_idade = new JLabel("Idade");
        lb_idade.setBounds(15, 120, 250, 50);
        lb_idade.setFont(new Font("Arial Black",20,14));
        add(lb_idade);

        SpinnerNumberModel spinner = new SpinnerNumberModel(0, 0, 100, 1);
        sp_idade = new JSpinner(spinner);
        sp_idade.setBounds(65, 135, 50,25);
        JFormattedTextField tf = ((JSpinner.DefaultEditor) sp_idade.getEditor()).getTextField();
        tf.setEditable(false);
        add(sp_idade);

        lb_cpf = new JLabel("CPF");
        lb_cpf.setBounds(130, 120, 250, 50);
        lb_cpf.setFont(new Font("Arial Black",20,14));
        add(lb_cpf);

        txt_cpf = new JTextField();
        txt_cpf.setBounds(165, 137, 100, 20);
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
        lb_sexo.setBounds(15, 155, 250, 50);
        lb_sexo.setFont(new Font("Arial Black",20,14));
        add(lb_sexo);

        String[] sexos = {"Masculino", "Feminino", "Outros"};
        cb_sexo = new JComboBox(sexos);
        cb_sexo.setBounds(65, 170, 200, 20);
        add(cb_sexo);

        lb_supervisor = new JLabel("Supervisor");
        lb_supervisor.setBounds(15, 190, 250, 50);
        lb_supervisor.setFont(new Font("Arial Black",20,14));
        add(lb_supervisor);

        txt_supervisor = new JTextField();
        txt_supervisor.setBounds(15, 230, 200, 20);
        add(txt_supervisor);

        bt_cadastro = new JButton("Cadastro");
        bt_cadastro.setBounds(350, 80, 90, 30);
        add(bt_cadastro);

        bt_apaga = new JButton("Apagar");
        bt_apaga.setBounds(445, 80, 90, 30);
        add(bt_apaga);

        bt_atualiza = new JButton("Atualizar");
        bt_atualiza.setBounds(350, 130, 90, 30);
        add(bt_atualiza);

        bt_limpa = new JButton("Limpa");
        bt_limpa.setBounds(445, 130, 90, 30);
        add(bt_limpa);

        bt_volta = new JButton("Voltar");
        bt_volta.setBounds(500, 30, 90, 30);
        add(bt_volta);

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

                if (cpf.length() != 11) {
                    JOptionPane.showMessageDialog(null, "O CPF deve conter exatamente 11 dígitos.");
                    return;
                }

                jovens_dto objjovensdto = new jovens_dto();
                objjovensdto.setNome_jovem(nome);
                objjovensdto.setIdade_jovem(idade);
                objjovensdto.setCpf_jovem(cpf);
                objjovensdto.setSexo_jovem(sexo);
                objjovensdto.setSupervisor_jovem(supervisor);

                jovens_dao objjovensdao = new jovens_dao();
                objjovensdao.cadastrajovem(objjovensdto);

                limpar();
                lista_jovem();
            }
        });

        bt_atualiza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id, idade;
                String nome, cpf, sexo, supervisor;

                id = Integer.parseInt(txt_id.getText());
                nome = txt_nome.getText();
                idade = (int) sp_idade.getValue();
                cpf = txt_cpf.getText();
                sexo = (String) cb_sexo.getSelectedItem();
                supervisor = txt_supervisor.getText();

                jovens_dto objjovensdto = new jovens_dto();
                objjovensdto.setId_jovem(id);
                objjovensdto.setNome_jovem(nome);
                objjovensdto.setIdade_jovem(idade);
                objjovensdto.setCpf_jovem(cpf);
                objjovensdto.setSexo_jovem(sexo);
                objjovensdto.setSupervisor_jovem(supervisor);

                jovens_dao objjovensdao = new jovens_dao();
                objjovensdao.atualiza_jovens(objjovensdto);

                limpar();
                lista_jovem();
            }
        });

        bt_apaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;

                id = Integer.parseInt(txt_id.getText());

                jovens_dto objjovensdto = new jovens_dto();
                objjovensdto.setId_jovem(id);

                jovens_dao objjovensdao = new jovens_dao();
                objjovensdao.apaga_jovens(objjovensdto);

                limpar();
                lista_jovem();
            }
        });

        bt_limpa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        bt_volta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_view objmainview = new main_view();
                objmainview.setVisible(true);
                dispose();
            }
        });

        tb_jovem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tb_jovem.getSelectedRow(); // ← Certifique-se de declarar isso

                if (linha != -1) {
                    // Pegando os valores da tabela e setando nos campos
                    txt_id.setText(tb_jovem.getValueAt(linha,0).toString());
                    txt_nome.setText(tb_jovem.getValueAt(linha, 1).toString());
                    sp_idade.setValue(Integer.parseInt(tb_jovem.getValueAt(linha, 2).toString()));
                    txt_cpf.setText(tb_jovem.getValueAt(linha, 3).toString());
                    cb_sexo.setSelectedItem(tb_jovem.getValueAt(linha, 4).toString());
                    txt_supervisor.setText(tb_jovem.getValueAt(linha, 5).toString());
                }
            }
        });
    }
    private void lista_jovem() {
        try {
            jovens_dao objjovemdao = new jovens_dao();

            DefaultTableModel modelo = (DefaultTableModel) tb_jovem.getModel();
            modelo.setRowCount(0);

            ArrayList<jovens_dto> lista = objjovemdao.pesquisa_jovem();

            for (int num = 0; num < lista.size(); num++) {
                modelo.addRow(new Object[]{
                        lista.get(num).getId_jovem(),
                        lista.get(num).getNome_jovem(),
                        lista.get(num).getIdade_jovem(),
                        lista.get(num).getCpf_jovem(),
                        lista.get(num).getSexo_jovem(),
                        lista.get(num).getSupervisor_jovem()
                });
            }
            ajustarLarguraColunas(tb_jovem);
            ajustarTamanhoScrollPane();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lista Jovem " + e);
        }
    }
    private void ajustarLarguraColunas(JTable tabela) {
        for (int coluna = 0; coluna < tabela.getColumnCount(); coluna++) {
            int larguraMaxima = 50; // largura mínima

            for (int linha = 0; linha < tabela.getRowCount(); linha++) {
                TableCellRenderer renderizador = tabela.getCellRenderer(linha, coluna);
                Component componente = tabela.prepareRenderer(renderizador, linha, coluna);
                larguraMaxima = Math.max(componente.getPreferredSize().width + 10, larguraMaxima);
            }

            // Considera também o tamanho do cabeçalho
            TableCellRenderer cabeçalhoRenderer = tabela.getTableHeader().getDefaultRenderer();
            Component cabecalhoComponente = cabeçalhoRenderer.getTableCellRendererComponent(
                    tabela, tabela.getColumnName(coluna), false, false, 0, coluna);
            larguraMaxima = Math.max(cabecalhoComponente.getPreferredSize().width + 10, larguraMaxima);

            tabela.getColumnModel().getColumn(coluna).setPreferredWidth(larguraMaxima);
        }
    }
    private void ajustarTamanhoScrollPane() {
        int larguraTotal = 0;
        for (int i = 0; i < tb_jovem.getColumnCount(); i++) {
            larguraTotal += tb_jovem.getColumnModel().getColumn(i).getPreferredWidth();
        }

        int altura = sp_tabela.getPreferredSize().height;

        sp_tabela.setPreferredSize(new Dimension(larguraTotal, altura));
        sp_tabela.setBounds(50, 280, larguraTotal + 20, 200);
    }
    private void limpar(){
        txt_id.setText("");
        txt_nome.setText("");
        sp_idade.setValue(0);
        txt_cpf.setText("");
        txt_supervisor.setText("");
    }
}
