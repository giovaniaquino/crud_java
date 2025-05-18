package DAO;

import DTO.user_dto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_dao {

    Connection conexao;

    public ResultSet autenticacao_user(user_dto obj_userdto) {
        conexao = new conexao_dao().conecta_bd();

        try{
            String sql = "Select * from usuario where nome_user = ? and senha_user = ?;";

            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, obj_userdto.getNome_usuario());
            pstm.setString(2, obj_userdto.getSenha_usuario());

            ResultSet rs = pstm.executeQuery();
            return rs;
        }catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " +erro);
            return null;
        }

    }

}
