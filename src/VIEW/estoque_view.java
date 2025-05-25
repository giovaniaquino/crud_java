package VIEW;

import DAO.estoque_dao;
import DTO.estoque_dto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class estoque_view extends JFrame{

    JLabel lb_codigo, lb_descricao, lb_quantidade, lb_valor, lb_titulo;
    JTextField txt_codigo, txt_descricao, txt_quantidade, txt_valor;
    JButton bt_cadastro, bt_apaga, bt_atualiza, bt_volta, bt_limpa;
    JTable tb_estoque;
    JScrollPane sp_tabela;

    public estoque_view(){
        setSize(600,500);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        setLayout(null);

        String[] colunas = {"Codigo", "Descrição", "Quantidade", "Valor"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tb_estoque = new JTable(modelo);
        tb_estoque.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        sp_tabela = new JScrollPane(tb_estoque);
        add(sp_tabela);
        lista_estoque();

        lb_titulo = new JLabel("Estoque");
        lb_titulo.setBounds(100,20,250,50);
        lb_titulo.setFont(new Font("Arial Black",20,22));
        add(lb_titulo);

        lb_codigo = new JLabel("Código");
        lb_codigo.setBounds(15, 60, 250, 50);
        lb_codigo.setFont(new Font("Arial Black",20,14));
        add(lb_codigo);

        txt_codigo = new JTextField();
        txt_codigo.setBounds(75, 75, 40, 20);
        add(txt_codigo);

        lb_descricao = new JLabel("Descrição");
        lb_descricao.setBounds(15, 90, 250, 50);
        lb_descricao.setFont(new Font("Arial Black",20,14));
        add(lb_descricao);

        txt_descricao = new JTextField();
        txt_descricao.setBounds(100, 105, 200, 20);
        add(txt_descricao);

        lb_quantidade = new JLabel("Quantidade");
        lb_quantidade.setBounds(15, 120, 250, 50);
        lb_quantidade.setFont(new Font("Arial Black",20,14));
        add(lb_quantidade);

        txt_quantidade = new JTextField();
        txt_quantidade.setBounds(110, 135, 40, 20);
        add(txt_quantidade);

        lb_valor = new JLabel("Valor");
        lb_valor.setBounds(160, 120, 250, 50);
        lb_valor.setFont(new Font("Arial Black",20,14));
        add(lb_valor);

        txt_valor = new JTextField();
        txt_valor.setBounds(210, 135, 50, 20);
        add(txt_valor);

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

        bt_cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo, descricao;
                int quantidade;
                float valor;

                codigo = txt_codigo.getText();
                descricao = txt_descricao.getText();
                quantidade = Integer.parseInt(txt_quantidade.getText());
                valor = Float.parseFloat(txt_valor.getText());


                estoque_dto objestoquedto = new estoque_dto();
                objestoquedto.setCodigo_estoque(codigo);
                objestoquedto.setDescricao_estoque(descricao);
                objestoquedto.setQuantidade_estoque(quantidade);
                objestoquedto.setValor_estoque(valor);

                estoque_dao objestoquedao = new estoque_dao();
                objestoquedao.cadastraestoque(objestoquedto);

                limpar();
                lista_estoque();
            }
        });


        bt_atualiza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo, descricao;
                int quantidade;
                float valor;

                txt_codigo.setEditable(true);
                codigo = txt_codigo.getText();
                descricao = txt_descricao.getText();
                quantidade = Integer.parseInt(txt_quantidade.getText());
                valor = Float.parseFloat(txt_valor.getText());


                estoque_dto objestoquedto = new estoque_dto();
                objestoquedto.setCodigo_estoque(codigo);
                objestoquedto.setDescricao_estoque(descricao);
                objestoquedto.setQuantidade_estoque(quantidade);
                objestoquedto.setValor_estoque(valor);

                estoque_dao objestoquedao = new estoque_dao();
                objestoquedao.atualiza_estoque(objestoquedto);

                limpar();
                lista_estoque();
            }
        });

        bt_apaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo;

                txt_codigo.setEditable(true);
                codigo = txt_codigo.getText();

                estoque_dto objsetoqueto = new estoque_dto();
                objsetoqueto.setCodigo_estoque(codigo);

                estoque_dao objestoquedao = new estoque_dao();
                objestoquedao.apaga_estoque(objsetoqueto);

                limpar();
                lista_estoque();
            }
        });

        tb_estoque.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                txt_codigo.setEditable(false);
                int linha = tb_estoque.getSelectedRow(); // ← Certifique-se de declarar isso

                if (linha != -1) {
                    // Pegando os valores da tabela e setando nos campos
                    txt_codigo.setText(tb_estoque.getValueAt(linha,0).toString());
                    txt_descricao.setText(tb_estoque.getValueAt(linha, 1).toString());
                    txt_quantidade.setText(tb_estoque.getValueAt(linha, 2).toString());
                    txt_valor.setText(tb_estoque.getValueAt(linha, 3).toString());
                }
            }
        });
    }
    private void lista_estoque() {
        try {
            estoque_dao objestoquedao = new estoque_dao();

            DefaultTableModel modelo = (DefaultTableModel) tb_estoque.getModel();
            modelo.setRowCount(0);

            ArrayList<estoque_dto> lista = objestoquedao.pesquisa_estoque();

            for (int num = 0; num < lista.size(); num++) {
                modelo.addRow(new Object[]{
                        lista.get(num).getCodigo_estoque(),
                        lista.get(num).getDescricao_estoque(),
                        lista.get(num).getQuantidade_estoque(),
                        lista.get(num).getValor_estoque()
                });
            }
            ajustarLarguraColunas(tb_estoque);
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
        for (int i = 0; i < tb_estoque.getColumnCount(); i++) {
            larguraTotal += tb_estoque.getColumnModel().getColumn(i).getPreferredWidth();
        }

        int altura = sp_tabela.getPreferredSize().height;

        sp_tabela.setPreferredSize(new Dimension(larguraTotal, altura));
        sp_tabela.setBounds(140, 200, larguraTotal + 5, 200); // +5 de margem
    }
    private void limpar(){
        txt_codigo.setEditable(true);
        txt_codigo.setText("");
        txt_descricao.setText("");
        txt_quantidade.setText("");
        txt_valor.setText("");
    }

}
