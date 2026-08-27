package br.com.digitustech.sistemaestoque.dao;

import br.com.digitustech.sistemaestoque.jdbc.ConexaoBanco;
import br.com.digitustech.sistemaestoque.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author wendel
 */
public class FornecedorDao {

    private final Connection conexao;

    public FornecedorDao() {
        this.conexao = new ConexaoBanco().obterConexao();
    }

    public void cadastrar(Fornecedor fornecedor) {
        try (conexao) {
            // montar sql
            String sql = "INSERT INTO tb_fornecedores "
                    + "(nome, cnpj, email, telefone, celular, cep, endereco, "
                    + "numero, complemento, bairro, cidade, estado) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCelular());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setInt(8, fornecedor.getNumero());
            stmt.setString(9, fornecedor.getComplemento());
            stmt.setString(10, fornecedor.getBairro());
            stmt.setString(11, fornecedor.getCidade());
            stmt.setString(12, fornecedor.getEstado());
            // executar o sql
            stmt.execute();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Fornecedor cadatrado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedo. Erro: " + ex);
        }

    }
    
    public void alterar(Fornecedor fornecedor) {
        try (conexao) {
            // montar sql
            String sql = "UPDATE tb_fornecedores " +
            "SET nome = ?, cnpj = ?, email = ?, telefone = ?, celular = ?, cep = ?, "
            + "endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? " 
            + "WHERE id = ?;";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getCelular());
            stmt.setString(6, fornecedor.getCep());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setInt(8, fornecedor.getNumero());
            stmt.setString(9, fornecedor.getComplemento());
            stmt.setString(10, fornecedor.getBairro());
            stmt.setString(11, fornecedor.getCidade());
            stmt.setString(12, fornecedor.getEstado());
            stmt.setInt(13, fornecedor.getId());
            // executar o sql
            stmt.executeUpdate();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao atualizar fornecedor. Erro: " + ex);
        }

    }
    
    public Fornecedor buscarPorNome(String nome) {
        try {
            String sql = "SELECT id, nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado "
            + "FROM tb_fornecedores WHERE nome = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Fornecedor fornecedor = null;
            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setNumero(rs.getInt("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setEstado(rs.getString("estado"));
                
            }
            return fornecedor;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bucar fornecedor. Erro: " + ex);
        }
        return null;
    }
    
    public List<Fornecedor> listar() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            String sql = "SELECT id, nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado "
            + "FROM tb_fornecedores;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                var fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setNumero(rs.getInt("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setEstado(rs.getString("estado"));
                fornecedores.add(fornecedor);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores. Erro: " + ex);
        }
        return fornecedores;
    }
    
     public List<Fornecedor> filtarPorNome(String nome) {
        List<Fornecedor> fornecedores = listar();
        List<Fornecedor> fornecedoresFiltrados = fornecedores.stream().filter(c -> c.getNome().contains(nome)).toList();
        return fornecedoresFiltrados;
     }
     
     public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM tb_fornecedores " 
            + "WHERE id = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor. Erro: " + ex);
        }
    }

    public Fornecedor buscarPorId(int codigoFornecedor) {
        try {
            String sql = "SELECT id, nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado "
            + "FROM tb_fornecedores WHERE id = ?";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoFornecedor);
            ResultSet rs = stmt.executeQuery();
            Fornecedor fornecedor = null;
            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCelular(rs.getString("celular"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setNumero(rs.getInt("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setEstado(rs.getString("estado"));
            }
            return fornecedor;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bucar fornecedor. Erro: " + ex);
        }
        return null;
    }

}
