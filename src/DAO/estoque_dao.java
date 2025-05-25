package DAO;

import DTO.estoque_dto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class estoque_dao {

    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<estoque_dto> lista = new ArrayList<>();


    public void cadastraestoque(estoque_dto objestoquedto){
        String sql = "insert into estoque (codigo, descricao, quantidade, valor) values (?,?,?,?)";

        conexao = new conexao_dao().conecta_bd();
        try{
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objestoquedto.getCodigo_estoque());
            pstm.setString(2, objestoquedto.getDescricao_estoque());
            pstm.setInt(3, objestoquedto.getQuantidade_estoque());
            pstm.setFloat(4, objestoquedto.getValor_estoque());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Jovens_dao casdastrar"+e);
        }

    }

    public ArrayList<estoque_dto> pesquisa_estoque(){
        String sql = "select * from estoque";

        conexao = new conexao_dao().conecta_bd();
        try {
            pstm = conexao.prepareStatement(sql);
            rs = pstm.executeQuery();

            while(rs.next()){
                estoque_dto objestoqueto = new estoque_dto();
                objestoqueto.setCodigo_estoque(rs.getString("codigo"));
                objestoqueto.setDescricao_estoque(rs.getString("descricao"));
                objestoqueto.setQuantidade_estoque(rs.getInt("quantidade"));
                objestoqueto.setValor_estoque(rs.getFloat("valor"));

                lista.add(objestoqueto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "estoque_dao pesquisa"+e);
        }
        return lista;
    }
    public void atualiza_estoque(estoque_dto objestoquedto){
        String sql = "update estoque set descricao = ?, quantidade = ?, valor = ? where codigo = ?";

        conexao = new conexao_dao().conecta_bd();
        try{
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objestoquedto.getDescricao_estoque());
            pstm.setInt(2, objestoquedto.getQuantidade_estoque());
            pstm.setFloat(3, objestoquedto.getValor_estoque());
            pstm.setString(4, objestoquedto.getCodigo_estoque());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Jovens_dao Atualiza"+e);
        }
    }

    public void apaga_estoque(estoque_dto objestoquedto){
        String sql = "delete from estoque where codigo = ?";

        conexao = new conexao_dao().conecta_bd();
        try{
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objestoquedto.getCodigo_estoque());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "estoque_dao Apaga"+e);
        }
    }
}
