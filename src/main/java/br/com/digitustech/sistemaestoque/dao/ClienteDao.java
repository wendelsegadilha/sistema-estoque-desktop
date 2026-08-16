package br.com.digitustech.sistemaestoque.dao;

import br.com.digitustech.sistemaestoque.jdbc.ConexaoBanco;
import br.com.digitustech.sistemaestoque.model.Cliente;
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
public class ClienteDao {

    private final Connection conexao;

    public ClienteDao() {
        this.conexao = new ConexaoBanco().obterConexao();
    }

    public void cadastrar(Cliente cliente) {
        try (conexao) {
            // montar sql
            String sql = "INSERT INTO tb_clientes "
                    + "(nome, rg, cpf, email, telefone, celular, cep, endereco, "
                    + "numero, complemento, bairro, cidade, estado) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getEndereco());
            stmt.setInt(9, cliente.getNumero());
            stmt.setString(10, cliente.getComplemento());
            stmt.setString(11, cliente.getBairro());
            stmt.setString(12, cliente.getCidade());
            stmt.setString(13, cliente.getEstado());
            // executar o sql
            stmt.execute();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Cliente cadatrado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente. Erro: " + ex);
        }

    }
    
    public void alterar(Cliente cliente) {
        try (conexao) {
            // montar sql
            String sql = "UPDATE tb_clientes " +
            "SET nome = ?, rg = ?, cpf = ?, email = ?, telefone = ?, celular = ?, cep = ?, "
            + "endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? " 
            + "WHERE id = ?;";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getRg());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setString(7, cliente.getCep());
            stmt.setString(8, cliente.getEndereco());
            stmt.setInt(9, cliente.getNumero());
            stmt.setString(10, cliente.getComplemento());
            stmt.setString(11, cliente.getBairro());
            stmt.setString(12, cliente.getCidade());
            stmt.setString(13, cliente.getEstado());
            stmt.setInt(14, cliente.getId());
            // executar o sql
            stmt.executeUpdate();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente. Erro: " + ex);
        }

    }
    
    public Cliente buscarPorNome(String nome) {
        try {
            String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado "
            + "FROM tb_clientes WHERE nome = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Cliente cliente = null;
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setRg(rs.getString(3));
                cliente.setCpf(rs.getString(4));
                cliente.setEmail(rs.getString(5));
                cliente.setTelefone(rs.getString(6));
                cliente.setCelular(rs.getString(7));
                cliente.setCep(rs.getString(8));
                cliente.setEndereco(rs.getString(9));
                cliente.setNumero(rs.getInt(10));
                cliente.setComplemento(rs.getString(11));
                cliente.setBairro(rs.getString(12));
                cliente.setCidade(rs.getString(13));
                cliente.setEstado(rs.getString(14));
                
            }
            return cliente;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bucar cliente. Erro: " + ex);
        }
        return null;
    }
    
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT id, nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado "
            + "FROM tb_clientes;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setRg(rs.getString(3));
                cliente.setCpf(rs.getString(4));
                cliente.setEmail(rs.getString(5));
                cliente.setTelefone(rs.getString(6));
                cliente.setCelular(rs.getString(7));
                cliente.setCep(rs.getString(8));
                cliente.setEndereco(rs.getString(9));
                cliente.setNumero(rs.getInt(10));
                cliente.setComplemento(rs.getString(11));
                cliente.setBairro(rs.getString(12));
                cliente.setCidade(rs.getString(13));
                cliente.setEstado(rs.getString(14));
                clientes.add(cliente);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes. Erro: " + ex);
        }
        return clientes;
    }
    
     public List<Cliente> filtarPorNome(String nome) {
        List<Cliente> clientes = listar();
        List<Cliente> clientesFiltrados = clientes.stream().filter(c -> c.getNome().contains(nome)).toList();
        return clientesFiltrados;
     }
     
     public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM tb_clientes " 
            + "WHERE id = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente. Erro: " + ex);
        }
    }

}
