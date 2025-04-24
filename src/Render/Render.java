package Render;

import Effects.Dust;
import Player.Player;

public class Render {

    public void render(Player player, Dust dust) {
        // Atualização do frame do player
        {
            int novoFramePlayer;
            int quantFrame = 4;
            switch (player.getAnimacao()){
                case "idle":
                    quantFrame = 4;
                    break;
                case "run":
                    quantFrame = 6;
                    break;
                case "death":
                    quantFrame = 2;
                    break;
            }
            novoFramePlayer = (player.getFrameAtual() + 1) % quantFrame;
            player.setFrameAtual(novoFramePlayer);


            // Atualização do frame do dust
            int atual = dust.getFrameAtual();
            int novoFrameDust = (atual + 1) % 4;

            dust.setFrameAtual(novoFrameDust);

            // Faz com que o movimento do Dust seja redefinido depois de todos os frames
            if (novoFrameDust == 0 && atual == 3) {
                dust.x = dust.posInicio;
            }
        }
        // Fim da atualização do frame do player

    }
}
