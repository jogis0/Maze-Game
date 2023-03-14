package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends SuperObject {
    public Key(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png."));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
