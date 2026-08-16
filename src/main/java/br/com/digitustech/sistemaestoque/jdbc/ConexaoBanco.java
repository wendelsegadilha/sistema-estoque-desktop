package br.com.digitustech.sistemaestoque.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author wendel
 */
public class ConexaoBanco {
    
    private final String url = "jdbc:mysql://localhost:3306/sistemaestoque?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private final String usuario = "root";
    private final String senha = "root";
    
    public Connection obterConexao(){
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return null;
    }
    
}
