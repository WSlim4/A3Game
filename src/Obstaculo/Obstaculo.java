package Obstaculo;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Obstaculo {
    private int x;
    private int y = 521;
    private int width;
    private int height;
    private Image rockImage;

    public Obstaculo() {
        this.width = 27;
        this.height = 14;

        // Carrega a imagem da pedra
        try {
            rockImage = ImageIO.read(new File("/src/resource/background/Sliced/moita.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void move(int speed) {
        x -= speed;
        if (x < -width) {
            x = 800; // Reinicia do lado direito (ajuste conforme sua lógica)
        }
    }

    public void draw(Graphics g) {
        g.drawImage(rockImage, x, y, width, height, null);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    // Simples verificação de colisão com retângulo do "dino"
    public boolean collidesWith(int dinoX, int dinoY, int dinoWidth, int dinoHeight) {
        return (x < dinoX + dinoWidth &&
                x + width > dinoX &&
                y < dinoY + dinoHeight &&
                y + height > dinoY);
    }
}