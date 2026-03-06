package Main;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) {
        //Rememeber what brought you this far. Your DREAM, ever since you were a kid. You fought through learning code in order to get here. Don't throw that effort away. Remember that.
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(true);
        window.setTitle("SIN");
        window.pack();
        gamepanel gp=new gamepanel();
        window.add(gp);
        window.pack();
        gp.startGame();
    }
}
