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
    private final BufferedImage[] SPRITE_SHEET;
    private int frameAtual = 0;


    // Se botar lambda, ele estranhamente da erro ou aparecem mais erros, então o aviso tem que ser ignorado
    private final Timer TEMPO = new Timer(120, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            frameAtual++;
            int MAX_FRAME = 4;
            if (frameAtual >= MAX_FRAME -1) {
                frameAtual = 0;
            }
            x -= 2;
        }
    });

    public Ave(int altura) {
        super(1280, 548-(15 * altura * 2), 48, 38, 2, 30, 15);
        SPRITE_SHEET = new BufferedImage[4];

        Random RANDOM = new Random();
        int tipo = RANDOM.nextInt(0,4);


        try {
            SPRITE_SHEET[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_1.png")));
            SPRITE_SHEET[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_2.png")));
            SPRITE_SHEET[2] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_3.png")));
            SPRITE_SHEET[3] = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resource/objects/Enemies/pterosaur_4.png")));
            System.out.println("Ave carregada!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar Ave > " + e);
        }
        TEMPO.start();
    }



    @Override
    public void move() {
        super.move();  // chama o move() do Obstaculo, que atualiza a posição do x
    }

    @Override
    public void Renderizar(Graphics g) {
        g.drawImage(SPRITE_SHEET[frameAtual], x, y, width * upscaling, height * upscaling, null);

        //Permite renderizar a hitbox do obstáculo
        if (verHitbox){
            g.setColor(Color.RED);
            g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }
}