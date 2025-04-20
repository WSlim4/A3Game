import Update.Update;
import Render.Render;
import ProcessInput.ProcessInput;

public class Game {
    private Update UpdateState;
    private Render Renderer;
    private ProcessInput UserInput;

    {
        this.UpdateState = new Update();
        this.Renderer = new Render();
        this.UserInput = new ProcessInput();

    }

    public void run() {
        while(true) {
            this.UserInput.read();
            this.UpdateState.update();
            this.Renderer.render();
        }
    }
}
