package animacje;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            try{
                AnimFrame animFrame = new AnimFrame("Animation Frame");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}