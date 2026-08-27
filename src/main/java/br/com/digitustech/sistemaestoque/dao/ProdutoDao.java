package br.com.digitustech.sistemaestoque.dao;

import br.com.digitustech.sistemaestoque.jdbc.ConexaoBanco;
import br.com.digitustech.sistemaestoque.model.Cliente;
import br.com.digitustech.sistemaestoque.model.Fornecedor;
import br.com.digitustech.sistemaestoque.model.Produto;
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
public class ProdutoDao {

    private final Connection conexao;

    public ProdutoDao() {
        this.conexao = new ConexaoBanco().obterConexao();
    }

    public void cadastrar(Produto produto) {
        try (conexao) {
            // montar sql
            String sql = "INSERT INTO tb_produtos " +
                "(descricao, preco, qtd_estoque, for_id) " +
                "VALUES(?, ?, ?, ?);";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setInt(4, produto.getFornecedor().getId());
            // executar o sql
            stmt.execute();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Produto cadatrado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto. Erro: " + ex);
        }

    }
    
    public void alterar(Produto produto) {
        try (conexao) {
            // montar sql
            String sql = "UPDATE tb_produtos " +
                "SET descricao = ?, preco = ?, qtd_estoque = ?, for_id = ? " +
                "WHERE id = ?;";
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setInt(4, produto.getFornecedor().getId());
            stmt.setInt(5, produto.getId());
            // executar o sql
            stmt.execute();
            // notifica sucesso
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
        } catch (SQLException ex) {
            // notifica falha
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar atualizar. Erro: " + ex);
        }
    }
    
    public Produto buscarPorNome(String nome) {
        try {
            String sql = "SELECT id, descricao, preco, qtd_estoque, for_id " +
                "FROM tb_produtos WHERE descricao = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Produto produto = null;
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidadeEstoque(rs.getInt("qtd_estoque"));
                
                var dao = new FornecedorDao();
                Fornecedor fornecedor = dao.buscarPorId(rs.getInt("for_id"));
                produto.setFornecedor(fornecedor);
            }
            return produto;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bucar cliente. Erro: " + ex);
        }
        return null;
    }
    
    public List<Produto> listar() {
        List<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT id, descricao, preco, qtd_estoque, for_id " +
                "FROM tb_produtos;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidadeEstoque(rs.getInt("qtd_estoque"));
                
                var dao = new FornecedorDao();
                Fornecedor fornecedor = dao.buscarPorId(rs.getInt("for_id"));
                produto.setFornecedor(fornecedor);
                produtos.add(produto);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos. Erro: " + ex);
        }
        return produtos;
    }
    
     public List<Produto> filtarPorNome(String nome) {
        List<Produto> produtos = listar();
        List<Produto> produtosFiltrados = produtos.stream().filter(c -> c.getDescricao().contains(nome)).toList();
        return produtosFiltrados;
     }
     
     public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM tb_produtos " +
                "WHERE id = ?;";
            
            // preparar o inserção do objeto
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto. Erro: " + ex);
        }
    }

}
