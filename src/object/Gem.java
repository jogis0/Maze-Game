package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Gem extends SuperObject{
    public Gem(){
        name = "Gem";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gem.png."));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
