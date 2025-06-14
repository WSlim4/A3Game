import javax.swing.*;
import java.awt.*;
import java.net.URL;
import menu.*;
import ponto.Pontos;

public class GameOver extends JFrame {
    private final BotaoImagem btnReiniciar;

    public GameOver(Pontos pontos) {

        setTitle("Game Over");
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
        URL imageUrl = getClass().getResource(Recursos.gameover);
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(650, 475, Image.SCALE_SMOOTH);
            JLabel menuLabel = new JLabel(new ImageIcon(scaledImage));
            gbc.gridy = 0; // Linha 0
            gbc.insets = new Insets(-150, 0, 30, 0); // Espaço abaixo da imagem
            botoesPanel.add(menuLabel, gbc);
        } else {
            System.err.println("Imagem 'menu' não encontrada!");
        }
        SomUtils.tocarMusicaUmaVez("/resource/audio/menu/gameoversong.wav");

        // Criação do botão reiniciar com imagem
        btnReiniciar = new BotaoImagem("/resource/gameover/btn_reiniciar.png", 325, 230);

        // Adiciona o botão Iniciar ao painel com espaçamento
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 35, 0);
        botoesPanel.add(btnReiniciar, gbc);

        // Adiciona fundo e painel de botões ao layeredPane em camadas diferentes
        layeredPane.add(fundo, Integer.valueOf(0));     // Fundo atrás
        layeredPane.add(botoesPanel, Integer.valueOf(1)); // Botões na frente

        // Adiciona o layeredPane ao JFrame
        add(layeredPane);

        // Torna o frame visível
        setVisible(true);

        // Ação do botão Iniciar
        btnReiniciar.addActionListener(e -> {
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

        int x = 510;
        int yRecord = 350;
        int yScore = 400;

        long recorde = pontos.lerRecorde();
        long atual = pontos.getPontos();

        if (atual > recorde) {
            String record = "Novo Recorde!";
            System.out.println("Recorde!");

            JLabel labelRecord = new JLabel(record);
            labelRecord.setFont(pontos.fonte);
            labelRecord.setForeground(Color.PINK);
            labelRecord.setBounds(510, yRecord, 600, 50);
            layeredPane.add(labelRecord, Integer.valueOf(2));
        } else {
            String record = "Record: " + recorde;

            JLabel labelRecord = new JLabel(record);
            labelRecord.setFont(pontos.fonte);
            labelRecord.setForeground(Color.WHITE);
            labelRecord.setBounds(520, yRecord, 600, 50);
            layeredPane.add(labelRecord, Integer.valueOf(2));
        }

        String score = "Score: " + atual;
        JLabel labelScore = new JLabel(score);
        labelScore.setFont(pontos.fonte);
        labelScore.setForeground(Color.WHITE);
        labelScore.setBounds(535, yScore, 600, 50);
        layeredPane.add(labelScore, Integer.valueOf(2));
        pontos.saveScore();
    }
}