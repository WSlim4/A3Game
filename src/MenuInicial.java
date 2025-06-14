import javax.swing.*;
import java.awt.*;
import java.net.URL;
import menu.*;

public class MenuInicial extends JFrame {

    private final BotaoImagem btn_iniciar;

    public MenuInicial() {
        setTitle("menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // Painel com imagem de fundo
        PainelComImagem fundo = new PainelComImagem(Recursos.fundoMadeira);
        fundo.setLayout(new BorderLayout());

        // LayeredPane para sobrepor imagem de fundo e painel de botões
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280, 720));
        fundo.setBounds(0, 0, 1280, 720);

        // Painel transparente para conter os botões
        JPanel botoesPanel = new JPanel(new GridBagLayout());
        botoesPanel.setOpaque(false); // Transparente para mostrar fundo
        botoesPanel.setBounds(0, 0, 1280, 720);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Coluna 0 para todos os componentes
        gbc.anchor = GridBagConstraints.CENTER;

        // Logo do menu
        URL imageUrl = getClass().getResource(Recursos.menu);
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(350, 300, Image.SCALE_SMOOTH);
            JLabel menuLabel = new JLabel(new ImageIcon(scaledImage));
            gbc.gridy = 0; // Linha 0
            gbc.insets = new Insets(-150, 0, 30, 0); // Espaço abaixo da imagem
            botoesPanel.add(menuLabel, gbc);
        } else {
            System.err.println("Imagem 'menu' não encontrada!");
        }
        SomUtils.tocarMusicaMenu("/resource/audio/menu/musica_menu.wav");

        // Criação do botão Iniciar com imagem
        btn_iniciar = new BotaoImagem("/resource/menu/btn_iniciar.png", 350, 120);

        // Adiciona o botão Iniciar ao painel com espaçamento
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 35, 0);
        botoesPanel.add(btn_iniciar, gbc);

        // Adiciona fundo e painel de botões ao layeredPane em camadas diferentes
        layeredPane.add(fundo, Integer.valueOf(0));     // Fundo atrás
        layeredPane.add(botoesPanel, Integer.valueOf(1)); // Botões na frente

        // Adiciona o layeredPane ao JFrame
        add(layeredPane);

        // Torna o frame visível
        setVisible(true);

        // Ação do botão Iniciar
        btn_iniciar.addActionListener(e -> {
            SomUtils.tocarSom(Recursos.somClique);
            SomUtils.pararMusicaFundo();
            this.dispose(); // Fecha o menu

            SwingUtilities.invokeLater(() -> {
                Game jogo = new Game();
                jogo.start(); // Abre a janela do jogo
                jogo.started = true;
                new Thread(jogo::run).start(); // Inicia o loop do jogo
            });
        });
    }
}