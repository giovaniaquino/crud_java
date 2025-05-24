package DAO;

import DTO.jovens_dto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class jovens_dao {

    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<jovens_dto> lista = new ArrayList<>();

    public void cadastrajovem(jovens_dto objjovensdto){
        String sql = "insert into cadastro (nome, idade, cpf, sexo, supervisor) values (?,?,?,?,?)";

        conexao = new conexao_dao().conecta_bd();
        try{
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objjovensdto.getNome_jovem());
            pstm.setInt(2, objjovensdto.getIdade_jovem());
            pstm.setString(3, objjovensdto.getCpf_jovem());
            pstm.setString(4, objjovensdto.getSexo_jovem());
            pstm.setString(5, objjovensdto.getSupervisor_jovem());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Jovens_dao casdastrar"+e);
        }

    }

    public ArrayList<jovens_dto> pesquisa_jovem(){
        String sql = "select * from cadastro";

        conexao = new conexao_dao().conecta_bd();
        try {
            pstm = conexao.prepareStatement(sql);
            rs = pstm.executeQuery();

            while(rs.next()){
                jovens_dto objjovemdto = new jovens_dto();
                objjovemdto.setId_jovem(rs.getInt("id"));
                objjovemdto.setNome_jovem(rs.getString("nome"));
                objjovemdto.setIdade_jovem(rs.getInt("idade"));
                objjovemdto.setCpf_jovem(rs.getString("cpf"));
                objjovemdto.setSexo_jovem(rs.getString("sexo"));
                objjovemdto.setSupervisor_jovem(rs.getString("supervisor"));

                lista.add(objjovemdto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Jovens_dao pesquisa"+e);
        }
        return lista;
    }
    public void atualiza_jovens(jovens_dto objjovensdto){
        String sql = "update cadastro set nome = ?, idade = ?, cpf = ?, sexo = ?, supervisor =? where id = ?";

        conexao = new conexao_dao().conecta_bd();
        try{
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objjovensdto.getNome_jovem());
            pstm.setInt(2, objjovensdto.getIdade_jovem());
            pstm.setString(3, objjovensdto.getCpf_jovem());
            pstm.setString(4, objjovensdto.getSexo_jovem());
            pstm.setString(5, objjovensdto.getSupervisor_jovem());
            pstm.setInt(6, objjovensdto.getId_jovem());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Jovens_dao Atualiza"+e);
        }
    }

    public void apaga_jovens(jovens_dto objjovensdto){
        String sql = "delete from cadastro where id = ?";

        conexao = new conexao_dao().conecta_bd();
        try{
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, objjovensdto.getId_jovem());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Jovens_dao Apaga"+e);
        }
    }

}
