package br.com.digitustech.sistemaestoque.utils;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author wendel
 */
public class Formularios {
    
    public static void limparTela(JPanel container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField jTextField) {
                jTextField.setText(null);
            }
        }
    }
    
}
