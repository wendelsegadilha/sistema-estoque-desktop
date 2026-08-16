/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.digitustech.sistemaestoque.jdbc;

import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author wendel
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        
        var conexaoBanco = new ConexaoBanco();
        
        Connection conexao = conexaoBanco.obterConexao();
        
        if (conexao != null) {
            JOptionPane.showMessageDialog(null, "Conexão com MySQL estabelecida com sucesso");
        }
    }
    
}
