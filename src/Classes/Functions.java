
package Classes;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Functions {

    public void BackgroundCamposObrigatorios(JTextField textField) {

        textField.setBackground(new Color(255, 250, 220)); // Cor quando selecionado

    }
      
    public void BackgroundCamposBrancos(JTextField textField) {

        textField.setBackground(new Color(242, 242, 242)); // Cor quando 

    }
      
      // Método que adiciona o efeito de mudança de cor ao foco
    public void configurarFocus(JTextField textField) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setBackground(new Color(255, 250, 220)); // Cor quando selecionado
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBackground(Color.white); // Volta para a cor padrão
            }
        });
    }
     
    public static class LimitadorDeCaracteres extends PlainDocument {
    private final int limite;

    
    public LimitadorDeCaracteres(int limite) {
        this.limite = limite;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        if ((getLength() + str.length()) <= limite) {
            super.insertString(offset, str, attr);
        }
    }

}
      
            
}
