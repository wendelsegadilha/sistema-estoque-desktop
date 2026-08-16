package br.com.digitustech.sistemaestoque.dao;

import br.com.digitustech.sistemaestoque.jdbc.ConexaoBanco;
import br.com.digitustech.sistemaestoque.model.Funcionario;
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
public class FuncionarioDao {

    private final Connection conexao;

    public FuncionarioDao() {
        this.conexao = new ConexaoBanco().obterConexao();
    }

    public void cadastrar(Funcionario funcionario) {
        try (conexao) {
            // montar sql
            String sql = "INSERT INTO tb_funcionarios "
                    + "(nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, "
                    + "numero, complemento, bairro, cidade, estado) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setString(6, funcionario.getCargo());
            stmt.setString(7, funcionario.getNivelAcesso());
            stmt.setString(8, funcionario.getTelefone());
            stmt.setString(9, funcionario.getCelular());
            stmt.setString(10, funcionario.getCep());
            stmt.setString(11, funcionario.getEndereco());
            stmt.setInt(12, funcionario.getNumero());
            stmt.setString(13, funcionario.getComplemento());
            stmt.setString(14, funcionario.getBairro());
            stmt.setString(15, funcionario.getCidade());
            stmt.setString(16, funcionario.getEstado());
            // executar o sql
            stmt.execute();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Funcionario cadatrado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionario. Erro: " + ex);
        }

    }
    
    public void alterar(Funcionario funcionario) {
        try (conexao) {
            // montar sql
            String sql = "UPDATE tb_funcionarios " +
            "SET nome = ?, rg = ?, cpf = ?, email = ?, senha = ?, cargo = ?, nivel_acesso = ?, telefone = ?, celular = ?, cep = ?, "
            + "endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? " 
            + "WHERE id = ?;";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getRg());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setString(6, funcionario.getCargo());
            stmt.setString(7, funcionario.getNivelAcesso());
            stmt.setString(8, funcionario.getTelefone());
            stmt.setString(9, funcionario.getCelular());
            stmt.setString(10, funcionario.getCep());
            stmt.setString(11, funcionario.getEndereco());
            stmt.setInt(12, funcionario.getNumero());
            stmt.setString(13, funcionario.getComplemento());
            stmt.setString(14, funcionario.getBairro());
            stmt.setString(15, funcionario.getCidade());
            stmt.setString(16, funcionario.getEstado());
            stmt.setInt(17, funcionario.getId());
            // executar o sql
            stmt.executeUpdate();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Funcionario atualizado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionario. Erro: " + ex);
        }

    }
    
    public Funcionario buscarPorNome(String nome) {
        try {
            String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado "
            + "FROM tb_funcionarios WHERE nome = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionario funcionario = null;
            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setNivelAcesso(rs.getString("nivel_acesso"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setComplemento(rs.getString("complemento"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));
            }
            return funcionario;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bucar funcionario. Erro: " + ex);
        }
        return null;
    }
    
    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            String sql = "SELECT id, nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado "
            + "FROM tb_funcionarios;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setNivelAcesso(rs.getString("nivel_acesso"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNumero(rs.getInt("numero"));
                funcionario.setComplemento(rs.getString("complemento"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));
                funcionarios.add(funcionario);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionarios. Erro: " + ex);
        }
        return funcionarios;
    }
    
     public List<Funcionario> filtarPorNome(String nome) {
        List<Funcionario> funcionarios = listar();
        List<Funcionario> funcionariosFiltrados = funcionarios.stream().filter(c -> c.getNome().contains(nome)).toList();
        return funcionariosFiltrados;
     }
     
     public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM tb_funcionarios " 
            + "WHERE id = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionario excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario. Erro: " + ex);
        }
    }

}
