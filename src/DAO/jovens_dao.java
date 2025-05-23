package DAO;

import DTO.jovens_dto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class jovens_dao {

    Connection conexao;
    PreparedStatement pstm;
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Jovens_dao"+e);
        }

    }

}
