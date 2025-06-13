package update;

import effects.Music;
import player.Player;
import obstaculo.*;
import ponto.Pontos;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;
import javax.sound.sampled.*;


public class Update {
    // Import de instancia de objetos
    private final Player player;
    private final Obstaculo obstaculo;
    private final Pontos pontos;
    private final Runnable onGameOver;
    private Music music;

    private final Random random = new Random(); // Variável para sorteio de Obstáculos
    private final List<Obstaculo> obstaculos = new ArrayList<>(); // Array para armazenar os obstáculos e renderizar posteriormente

    private double chanceObstaculo = 5; // Frequencia inicial em que os obstáculos são renderizados, diminui o valor gradualmente
    private double tempoAtual = 0; // Armazena o momento

    private long ultimoFrame = 0; // Armazena o momento em que o ultimo frame foi renderizado


    private boolean colidiu = false; // Define colisão para execução de método de gameOver

    private double velocidadeY; //
    private int posY = 500;

    private final Timer DELAY = new Timer(); // Instancía o para definir tempo de execução dos métodos de GameOver

    private Clip clip;

    // Variáveis de armazenamento de sons
    private AudioInputStream soundHit;
    private AudioInputStream soundQueda;

    // Método em loop
    public void update() {
        // Sensor de Colisão
        if (!colidiu){
            for (Obstaculo o : obstaculos) {
                if (player.getHITBOX().intersects(o.getHitbox()) || player.getHITBOX().intersects(obstaculo.getHitbox())) {
                    // Instruções executadas exatamente no momento da morte
                    pontos.createFile(); // Cria o arquivo de save
                    music.stop(); // para a musica do jogo
                    colidiu = true; // Define a colisão
                    System.out.println("Colidiu!!"); // Mensagem para debug
                    player.gameOver(true); // Método para definir todas as funções de gameover do jogador, como a troca de sprite
                    pontos.setGameOver(false); // Método para definir todas as funções de gameover da classe Pontos, como congelamento da tela
                    try {
                        clip.open(soundHit); // Carrega arquivo de audio do hit
                        clip.start(); // Inicia som carregado
                    } catch (Exception e){
                        System.out.println("Erro no momento de colisão do GameOver: " + e);
                    }


                    DELAY.schedule(new TimerTask() {
                        // Instruções executadas com delay de dois segundos após a morte
                        @Override
                        public void run() {
                            try {
                                player.setAnimacao("death"); // Troca sprite do jogador para morte
                                clip.stop(); // Para o som anterior
                                clip.close(); // Fecha o som anterior
                                clip.open(soundQueda); // Abre o novo som de queda
                                clip.start(); // Inicia o som
                            } catch (Exception e){
                                System.out.println("Erro após a colisão do GameOver: " + e);
                            }
                        }
                    }, 2000);
                    DELAY.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // Instruções executadas com delay de 3 segundos após a morte

                            System.out.println("GameOver finalizado");
                            if (onGameOver != null) {
                                onGameOver.run(); // Inicia tela de GameOver
                            }
                        }
                    }, 3500);
                }
            }
        }

        // Sorteio de obstáculos por tempo
        long agora = System.currentTimeMillis(); // armazena tempo atual do sistema em milissegundos
        if (agora - ultimoFrame >= 100 && !colidiu){ // Compara se passaram 100 milissegundos em relação ao tempo anterior
            if (tempoAtual >= chanceObstaculo){ // Verifica se o tempo para spawn de obstaculo foi atingido
                if(chanceObstaculo >= 1.5){
                    chanceObstaculo -= 0.3; // Diminui gradualmente o tempo de spawn de obstaculo com um limite em 1.5
                }
                tempoAtual = 0; // Reseta o tempo de spawn
                sortearObstaculo(); // Sorteia um obstaculo novo
            }
            tempoAtual += 0.1; // Aumenta o tempo do cronometro de spawn de um novo obstaculio
            ultimoFrame = agora; // Troca o tempo anterior para o atual de spawn para nova verificação
        }

        if (!player.getNoChao()){
            velocidadeY += player.getGRAVIDADE();
            posY += velocidadeY;
            player.setPosY(posY);


            int ALTURA_CHAO = 500;
            if (player.getPosY() >= ALTURA_CHAO && !player.getIsGameOver()){
                player.setPosY(ALTURA_CHAO);
                player.setNoChao(true);
                velocidadeY = player.getFORCA_PULO();
                player.setAnimacao("run");
            }
        }
    }

    // Construtor
    public Update(Player player, Obstaculo obstaculo, Pontos pontos, Runnable onGameOver, Music music) {
        // Import de instancias criadas
        this.player = player;
        this.obstaculo = obstaculo;
        this.pontos = pontos;
        this.onGameOver = onGameOver;
        this.music = music;

        // Armazena sons
        try {
            soundHit = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/resource/audio/effects/hit.wav")));
            soundQueda = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/resource/audio/effects/Queda.wav")));
            clip = AudioSystem.getClip();
        } catch (Exception e){
            System.out.println("Erro ao executar audio hit: " + e);
        }
    }

    // Método de sorteio de obstáculos
    public void sortearObstaculo() {
        int tipo = random.nextInt(0,4); // Sorteia um número de

        Obstaculo o = null; // Inicializa o obstáculo

        // Verifica qual tipo de Obstáculo é e aciona o seu método
        switch (tipo) {
            case 0:
                // Esse obstáculo só aparece depois de 50 pontos, devido sua impossibilidade de pular no inicio;
                if (pontos.getPontos() > 50){
                    o = new MoitaGrande();
                    System.out.println("Obstáculo lançado: Moita Grande");
                    break;
                } else {
                    o = new Moita();
                    System.out.println("Obstáculo lançado: Moita (Substituindo Moita Grande)");
                    break;
                }
            case 1:
                o = new Moita();
                System.out.println("Obstáculo lançado: Moita");
                break;
            case 2:
                o = new Pedra();
                System.out.println("Obstáculo lançado: Pedra");
                break;
            case 3:
                // Esse obstáculo só aparece depois de 30 pontos, devido sua impossibilidade de pular no inicio;
                if (pontos.getPontos() > 30){
                    o = new PedraGrande();
                    System.out.println("Obstáculo lançado: Pedra Grande");
                    break;
                } else {
                    o = new Pedra();
                    System.out.println("Obstáculo lançado: Pedra (Substituindo Pedra Grande)");
                    break;
                }
        }

        obstaculos.add(o);
    }

    // Método render chamado no GamePanel
    public void Renderizar(Graphics g) {
        // Itera sobre os obstáculos e renderiza cada um no GamePanel
        for (Obstaculo o : obstaculos) {
            o.Renderizar(g);
        }

        // Remover oque já saiu da tela
        obstaculos.removeIf(o -> o.getX() + o.getWidth() < 0);
    }

    // método get
    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }
}