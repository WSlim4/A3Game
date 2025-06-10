package Obstaculo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.swing.Timer;
import java.util.Random;

public class Ave extends Obstaculo {
    private BufferedImage[] spriteSheet;
    private int frameAtual = 0;
    private int maxFrame = 4;
    private Random random = new Random();


    private Timer tempo = new Timer(120, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            frameAtual++;
            if (frameAtual >= maxFrame-1) {
                frameAtual = 0;
            }
            x -= 2;
        }
    });

    public Ave(int altura) {
        super(1280, 548-(15 * altura * 2), 48, 38, 2, 30, 15);
        spriteSheet = new BufferedImage[4];

        int tipo = random.nextInt(0,4);


        try {
            spriteSheet[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_1.png")));
            spriteSheet[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_2.png")));
            spriteSheet[2] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_3.png")));
            spriteSheet[3] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_4.png")));
            System.out.println("Ave carregada!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar Ave > " + e);
        }
        tempo.start();
    }



    @Override
    public void move() {
        super.move();  // chama o move() do Obstaculo, que atualiza a posição do x
    }

    @Override
    public void Renderizar(Graphics g) {
        g.drawImage(spriteSheet[frameAtual], x, y, width * upscaling, height * upscaling, null);

        //Permite renderizar a hitbox do obstáculo
        if (verHitbox){
            g.setColor(Color.RED);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }
}