package DAO;

import DTO.estoque_dto;
import DTO.user_dto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_dao {

    Connection conexao;
    PreparedStatement pstm;

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
    public void cadastrausuario(user_dto objuserdto){
        String sql = "insert into usuario (nome_user, senha_user) values (?,?)";

        conexao = new conexao_dao().conecta_bd();
        try{
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objuserdto.getNome_usuario());
            pstm.setString(2, objuserdto.getSenha_usuario());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Usuario casdastrar"+e);
        }

    }

}
