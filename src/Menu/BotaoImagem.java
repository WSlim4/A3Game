package Menu;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BotaoImagem extends JButton {

    public BotaoImagem(String caminho, int largura, int altura) {
        ImageIcon icone = carregarIconeRedimensionado(caminho, largura, altura);
        if (icone != null) {
            setIcon(icone);
        } else {
            setText("Imagem não encontrada"); // fallback simples visível
        }

        setBorderPainted(false); // Remove a borda do botão
        setContentAreaFilled(false); // Remove o fundo padrão
        setFocusPainted(false); // Remove o contorno ao focar
        setOpaque(false); // Garante transparência
        setPreferredSize(new Dimension(largura, altura)); // Tamanho com base na imagem
    }

    private ImageIcon carregarIconeRedimensionado(String caminho, int largura, int altura) {
        URL url = getClass().getResource(caminho);
        if (url == null) {
            System.err.println("❌ Imagem não encontrada: " + caminho);
            return null;
        }

        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}