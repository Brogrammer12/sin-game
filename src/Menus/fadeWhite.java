package Menus;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import Main.gamepanel;

public class fadeWhite {
   gamepanel gp;
   double timer=0;
   float alpha=0f;
   public boolean startFadeDone=false;
   public boolean endFadeDone=false;
public fadeWhite(gamepanel gp) {
this.gp=gp;
}
// 1 means currently fully white, 0 means starting fade and 1 means fade ended
   public int fadeToWhite(double duration, Graphics2D g2) {
    if (startFadeDone==false) {
      startFade(g2);
    }
    if (startFadeDone==true) {
      timer+=gp.delta;
       if (timer>=duration) {
         if (endFadeDone==false) {
            endFade(g2);
            return 2;
         }
         if (endFadeDone==true) {
            return 3;
         }
         else {
            return 0;
         }
    }
    else {
      g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha
        ));
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
      return 1;
    }
    }
    else {
      return 0;
    }
   }
   private void startFade(Graphics2D g2) {
      if (alpha<1) {
         alpha+=gp.delta;
         if (alpha>1) {
            alpha=1;
         }
         g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha
        ));
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
      }
      else {
         alpha=1;
         startFadeDone=true;

      }
   }
   private void endFade(Graphics2D g2) {
      if (alpha>0) {
         alpha-=gp.delta;
         if (alpha<0) {
            alpha=0;
         }
         g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha
        ));
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, gamepanel.GAME_WIDTH, gamepanel.GAME_HEIGHT);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
      }
      else {
         alpha=0;
         endFadeDone=true;
      }
   }
}
