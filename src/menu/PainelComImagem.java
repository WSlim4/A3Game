package menu;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PainelComImagem extends JPanel {

    private Image imagemFundo;

    public PainelComImagem(String caminhoResource) {
        carregarImagem(caminhoResource);
        setOpaque(false); // Garante que o fundo seja transparente para sobreposições
    }

    private void carregarImagem(String caminho) {
        URL url = getClass().getResource(caminho);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            imagemFundo = icon.getImage();
        } else {
            System.err.println("❌ Imagem de fundo não encontrada: " + caminho);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}