package Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.gameState;
import Main.gamepanel;

public class titleScreen {
    gamepanel gp;
    public String titleState="menu";
    public String playerName="";
    public boolean newGameConfirmation=false;
    public int classSelected=0;
    public BufferedImage Continue, back, play, exit;
    dialogueOptions dOptions, dOptions2;
    public fadeWhite fWhite;
    timer timer1;
    timer timer2;
    timer timer3;
    public titleScreen(gamepanel gp) {
        this.gp=gp;
        loadImages();
        dOptions=new dialogueOptions(new String[] {"Yes", "No"});
        dOptions2=new dialogueOptions(new String[] {"Yes","No"});
        timer1=new timer();
        timer2=new timer();
        timer3=new timer();
        fWhite=new fadeWhite(gp);
    }
    public void loadImages() {
        try {
            Continue=ImageIO.read(getClass().getResourceAsStream("/resources/titleScreen/continueButton.png"));
            back=ImageIO.read(getClass().getResourceAsStream("/resources/titleScreen/backButton.png"));
            exit=ImageIO.read(getClass().getResourceAsStream("/resources/titleScreen/exitButton.png"));
            play=ImageIO.read(getClass().getResourceAsStream("/resources/titleScreen/playButton.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void drawTitleScreen(Graphics2D g2, double scaleX, double scaleY) {
        gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 48f, g2);
        if (titleState=="menu") {
            g2.drawImage(Continue, gamepanel.GAME_WIDTH/2-gamepanel.resTileSize*2, gamepanel.GAME_HEIGHT/2-300, gamepanel.resTileSize*4, gamepanel.resTileSize*4, null);
             g2.drawImage(play, gamepanel.GAME_WIDTH/2-gamepanel.resTileSize*2, gamepanel.GAME_HEIGHT/2-150, gamepanel.resTileSize*4, gamepanel.resTileSize*4, null);
              g2.drawImage(exit, gamepanel.GAME_WIDTH/2-gamepanel.resTileSize*2, gamepanel.GAME_HEIGHT/2, gamepanel.resTileSize*4, gamepanel.resTileSize*4, null);
              Rectangle playRect=new Rectangle(gp.tDrawer.scalex(scaleX, gamepanel.GAME_WIDTH/2-gamepanel.resTileSize*2), gp.tDrawer.scaley(scaleY, gamepanel.GAME_HEIGHT/2-150), gp.tDrawer.scalewidth(scaleX, gamepanel.resTileSize*4), gp.tDrawer.scaleheight(scaleY, gamepanel.resTileSize*4));
              Rectangle continueRect=new Rectangle(gp.tDrawer.scalex(scaleX, gamepanel.GAME_WIDTH/2-gamepanel.resTileSize*2), gp.tDrawer.scaley(scaleY, gamepanel.GAME_HEIGHT/2-300), gp.tDrawer.scalewidth(scaleX, gamepanel.resTileSize*4), gp.tDrawer.scaleheight(scaleY, gamepanel.resTileSize*4));
              Rectangle exitRect=new Rectangle(gp.tDrawer.scalex(scaleX, gamepanel.GAME_WIDTH/2-gamepanel.resTileSize*2), gp.tDrawer.scaley(scaleY, gamepanel.GAME_HEIGHT/2), gp.tDrawer.scalewidth(scaleX, gamepanel.resTileSize*4), gp.tDrawer.scaleheight(scaleY, gamepanel.resTileSize*4));
              if (gp.listener.mouseRect.intersects(playRect) && gp.listener.mouseClicked==true && gp.listener.hasPressed==false) {
                titleState="newGame";
                gp.listener.hasPressed=true;
              }
              if (gp.listener.mouseRect.intersects(exitRect) && gp.listener.mouseClicked==true && gp.listener.hasPressed==false) {
                System.exit(0);
              }
        }
        else if(titleState=="newGame") {
            Font currentFont=g2.getFont();
            int textWidth=gp.tDrawer.getTextWidth(currentFont, "Are you sure you want to start a new game?", g2);
            int textHeight=gp.tDrawer.getTextHeight(currentFont, g2);
            g2.setColor(Color.WHITE);
            gp.wordBoi.drawAll(gamepanel.GAME_WIDTH/2-textWidth/2, gamepanel.GAME_HEIGHT/2+textHeight/2, "Are you sure you want to start a new game?", g2);
//g2.drawString("Are you sure you want to start a new game?", gamepanel.GAME_WIDTH/2-textWidth/2, gamepanel.GAME_HEIGHT/2+textHeight/2);
g2.drawString("Yes.", 190, 450);
g2.drawString("No.", gamepanel.GAME_WIDTH-300, 450);
int noWidth=gp.tDrawer.getTextWidth(currentFont, "No.", g2);
int yesWidth=gp.tDrawer.getTextWidth(currentFont, "Yes.", g2);
Rectangle noRect=new Rectangle(gp.tDrawer.scalex(scaleX, gamepanel.GAME_WIDTH-300), gp.tDrawer.scaley(scaleY, 450-textHeight), gp.tDrawer.scalewidth(scaleX, noWidth), gp.tDrawer.scaleheight(scaleY, textHeight));
Rectangle yesRect=new Rectangle(gp.tDrawer.scalex(scaleX, 190), gp.tDrawer.scaley(scaleY, 450-textHeight), gp.tDrawer.scalewidth(scaleX, yesWidth), gp.tDrawer.scaleheight(scaleY, textHeight));
if (gp.listener.mouseRect.intersects(noRect) && gp.listener.mouseClicked==true && gp.listener.hasPressed==false) {
    titleState="menu";
    gp.listener.hasPressed=true;
}
if (gp.listener.keyCodes[KeyEvent.VK_ESCAPE]==true && gp.listener.hasPressed==false) {
    titleState="menu";
    gp.listener.hasPressed=true;
}
if (gp.listener.mouseRect.intersects(yesRect) && gp.listener.mouseClicked==true && gp.listener.hasPressed==false) {
    titleState="playerName";
    gp.listener.hasPressed=true;
}
        }
        else if(titleState=="continue") {

        }
         else if(titleState=="playerName") {
            Font currentFont=g2.getFont();
            int textWidth=gp.tDrawer.getTextWidth(currentFont, "What is your name?", g2);
            int textHeight=gp.tDrawer.getTextHeight(currentFont, g2);
            g2.setColor(Color.WHITE);
            gp.wordBoi.drawAll(gamepanel.GAME_WIDTH/2-textWidth/2, gamepanel.GAME_HEIGHT/2+textHeight/2-50, "What is your name?", g2);
            //g2.drawString("What is your name?", gamepanel.GAME_WIDTH/2-textWidth/2, gamepanel.GAME_HEIGHT/2+textHeight/2-50);
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(gamepanel.GAME_WIDTH/2-textWidth/2, gamepanel.GAME_HEIGHT/2+textHeight/2, textWidth, textHeight-10);
            if (gp.listener.lastTyped != 0) {
    playerName += gp.listener.lastTyped;  // add the character
    gp.listener.lastTyped = 0;          // consume it
}
if (gp.listener.keyCodes[KeyEvent.VK_BACK_SPACE]==true && playerName.length()>0 && gp.listener.hasPressed==false) {
    playerName=playerName.substring(0, playerName.length()-1);
    gp.listener.hasPressed=true;
}
g2.setColor(Color.WHITE);
g2.drawString(playerName, gamepanel.GAME_WIDTH/2-textWidth/2, gamepanel.GAME_HEIGHT/2+textHeight+7);
if (gp.listener.keyCodes[KeyEvent.VK_ENTER]==true && gp.listener.hasPressed==false) {
    titleState="nameConfirmation";
    gp.listener.hasPressed=true;
}
        }
        else if(titleState=="nameConfirmation") {
            String dialogue;
            switch (playerName) {
                case "SIN":
                    dialogue="I see through my strength and understand my weakness.";
                    break;
                    case "CORRUPT":
                    dialogue="...there's nothing left to eliminate.";
                    break;
                    case "IAN":
                    dialogue="The name belonging to the Maker of this World.";
                    break;
                    case "PERFECTION":
                    dialogue="I will do it right this time.\n... \nI have to.";
                    break;
                    case "WHOAMI":
                    dialogue="an identity unknown to the world.";
                    break;
                    case "JOHN":
                    dialogue="He tried to save you from corruption.\nREMEMBER THAT.";
                    break;
                    case "NIGGER":
                        dialogue="...Really?";
                        break;
                    default:
                    dialogue="If you're sure you've entered your name correctly, hit \"Continue\".";
                    break;
            }
            Font currentFont=g2.getFont();
            int textWidth=gp.tDrawer.getTextWidth(currentFont, dialogue, g2);
            int textHeight=gp.tDrawer.getTextHeight(currentFont, g2);
            int x=gamepanel.GAME_WIDTH/2-textWidth/2;
            if (x<0) {
                x=0;
            }
            g2.setColor(Color.WHITE);
            gp.wordBoi.drawAll(x, gamepanel.GAME_HEIGHT/2+textHeight-50, dialogue, g2);
            //g2.drawString(dialogue, gamepanel.GAME_WIDTH/2-textWidth/2, gamepanel.GAME_HEIGHT/2+textHeight-50);
            int nameWidth=gp.tDrawer.getTextWidth(currentFont, playerName, g2);
            g2.drawString(playerName, gamepanel.GAME_WIDTH/2-nameWidth/2, gamepanel.GAME_HEIGHT/2+textHeight+50);
            g2.drawString("Continue", gamepanel.GAME_WIDTH-300, 450);
            g2.drawString("Back", 190, 450);
            int contWidth=gp.tDrawer.getTextWidth(currentFont, "Continue", g2);
            int backWidth=gp.tDrawer.getTextWidth(currentFont, "Back", g2);
            Rectangle contRect=new Rectangle(gp.tDrawer.scalex(scaleX, gamepanel.GAME_WIDTH-300), gp.tDrawer.scaley(scaleY, 450-textHeight), gp.tDrawer.scalewidth(scaleX, contWidth), gp.tDrawer.scaleheight(scaleY, textHeight));
            Rectangle backRect=new Rectangle(gp.tDrawer.scalex(scaleX, 190), gp.tDrawer.scaley(scaleY, 450-textHeight), gp.tDrawer.scalewidth(scaleX, backWidth), gp.tDrawer.scaleheight(scaleY, textHeight));
            if (gp.listener.mouseRect.intersects(contRect) && gp.listener.mouseClicked==true && gp.listener.hasPressed==false) {
                titleState="classSelection";
                gp.p1.Name=playerName;
                gp.listener.hasPressed=true;
            }
            if (gp.listener.mouseRect.intersects(backRect) && gp.listener.mouseClicked==true && gp.listener.hasPressed==false) {
                titleState="playerName";
                gp.listener.hasPressed=true;
            }
        }
        else if(titleState=="classSelection") {
            String Class=null;
            String classDialogue=null;
            switch (classSelected) {
                case 0:
                    Class="Brute";
                    classDialogue="You are strong and mighty. Your willpower alone moves boulders, and people respect your strength. You wield a big mace; Heavy and hard to move, but one hit is enough anyways, right?";
                    break;
            
                case 1:
                    Class="Dark Mage";
                    classDialogue="Time and space itself bend to your will. With forbidden spells and hidden techniques long ago lost, you conquer your enemies by tapping into deadly and dangerous magic combined with white hot emotion. You wield a basic mage staff made of wood; not good for melee, but helps to cast some spells. Feels good to wipe out someone annoying, doesn't it?";
                    break;
                    case 2:
                Class="Bounty Hunter";
                classDialogue=" Since you could think you have been trained to hunt and kill other people. You are very mobile and can dodge better than others, but you only have proficiency in stealthy weapons. You spent most of your money made on gambling and won it all back, meaning you have better luck in combat. I mean, the job doesn't matter as long as it pays big, right?";
                    break;
                    case 3:
                Class="Cyborg";
                classDialogue="A human who has been technologically advanced to have enhanced adaptability. Weaker than the other classes at first glance, the cyborg can modify their own body parts depending on the current situation. After all, adapting to the powers of others and making them your own is easier than enhancing your power.";
                    break;
                    case 4:
                    Class="Mystic One";
                    classDialogue="You are the son of a very rich family that rules over the kingdom. When you were born, they gave you a blessing of magic that would protect and guide you through your whole life, meaning that all encounters in general become easier to deal with. You lived your life casting magic as if it were picking up a glass of water, meaning you have massively enhanced proficiency in magic, but you didn't learn many spells at the academy. With this powerful blessing of magic, it looks like life has got your back.";
                    break;
            }
            g2.setColor(Color.WHITE);
            Font currentfont=g2.getFont();
            int totalWidth=gp.tDrawer.getTextWidth(currentfont, "<- "+Class+" ->", g2);
            int totalHeight=gp.tDrawer.getTextHeight(currentfont, g2);
            g2.drawString("<- "+Class+" ->", gamepanel.GAME_WIDTH/2-totalWidth/2, gamepanel.GAME_HEIGHT/2+totalHeight/2);
            gp.tDrawer.setCustomFont("/resources/customFonts/PixelOperatorSC-Bold.ttf", 24, g2);
            currentfont=g2.getFont();
            int dialogueWidth=gp.tDrawer.getTextWidth(currentfont, classDialogue, g2);
            int height=gp.tDrawer.getTextHeight(currentfont, g2);
            int dialogueX=gamepanel.GAME_WIDTH/2-dialogueWidth/2;
            if (dialogueX<0) {
                dialogueX=0;
            }
            gp.wordBoi.drawAll(dialogueX, gamepanel.GAME_HEIGHT/2+height+50, classDialogue, g2);
            int leftWidth=gp.tDrawer.getTextWidth(currentfont, "<- ", g2);
            int rightWidth=gp.tDrawer.getTextWidth(currentfont, " ->", g2);
            Rectangle leftRect=new Rectangle(gp.tDrawer.scalex(scaleX, gamepanel.GAME_WIDTH/2-totalWidth/2), gp.tDrawer.scaley(scaleY, gamepanel.GAME_HEIGHT/2+totalHeight/2-height), gp.tDrawer.scalewidth(scaleX, leftWidth), gp.tDrawer.scaleheight(scaleY, height));
            Rectangle rightRect=new Rectangle(gp.tDrawer.scalex(scaleX, (gamepanel.GAME_WIDTH/2-totalWidth/2)+totalWidth-rightWidth), gp.tDrawer.scaley(scaleY, gamepanel.GAME_HEIGHT/2+totalHeight/2-height), gp.tDrawer.scalewidth(scaleX, rightWidth), gp.tDrawer.scaleheight(scaleY, height));
            if (((gp.listener.mouseRect.intersects(leftRect) && gp.listener.mouseClicked==true) || gp.listener.keyCodes[KeyEvent.VK_LEFT]==true) && gp.listener.hasPressed==false) {
                if (classSelected==0) {
                    classSelected=4;
                }
                else {
                    classSelected--;
                }
                gp.listener.hasPressed=true;
            }
            if (((gp.listener.mouseRect.intersects(rightRect) && gp.listener.mouseClicked==true) || gp.listener.keyCodes[KeyEvent.VK_RIGHT]==true) && gp.listener.hasPressed==false) {
                if (classSelected==4) {
                    classSelected=0;
                }
                else {
                    classSelected++;
                }
                gp.listener.hasPressed=true;
            }
            if (gp.listener.keyCodes[KeyEvent.VK_ENTER]==true && gp.listener.hasPressed==false) {
                titleState="Ready";
                gp.p1.Class=Class;
                gp.listener.hasPressed=true;
            }
        }
        else if(titleState=="Ready") {
            dOptions.dialogueWithOption(g2, gp.wordBoi, "Are you ready to fight?", gamepanel.GAME_WIDTH/2, gamepanel.GAME_HEIGHT/2, scaleX, scaleY);
            if (gp.listener.mouseClicked==true && gp.listener.mouseRect.intersects(dOptions.getRectangle(0)) && gp.listener.hasPressed==false) {
                titleState="uSure";
                gp.listener.hasPressed=true;
            }
            if (gp.listener.mouseClicked==true && gp.listener.mouseRect.intersects(dOptions.getRectangle(1)) && gp.listener.hasPressed==false) {
                titleState="hellnaw";
                gp.listener.hasPressed=true;
            }
        }
        else if(titleState=="uSure") {
            dOptions2.dialogueWithOption(g2, gp.wordBoi, "Are you prepared for what lies ahead?", gamepanel.GAME_WIDTH/2, gamepanel.GAME_HEIGHT/2, scaleX, scaleY);
            if (gp.listener.mouseClicked==true && gp.listener.mouseRect.intersects(dOptions2.getRectangle(0)) && gp.listener.hasPressed==false) {
                titleState="willingTo";
                gp.listener.hasPressed=true;
            }
            if (gp.listener.mouseClicked==true && gp.listener.mouseRect.intersects(dOptions2.getRectangle(1)) && gp.listener.hasPressed==false) {
                titleState="hellnaw";
                gp.listener.hasPressed=true;
            }
        }
        else if(titleState=="hellnaw") {
            FontMetrics fm=g2.getFontMetrics();
            int width=fm.stringWidth("THEN GET OUT.");
            gp.wordBoi.drawAll((gamepanel.GAME_WIDTH/2)-width/2, gamepanel.GAME_HEIGHT/2, "THEN GET OUT.", g2);
            boolean timerDone=timer1.wait(3, gp.delta);
            if (timerDone==true) {
                titleState="menu";
            }
        }
        else if(titleState=="willingTo") {
            FontMetrics fm=g2.getFontMetrics();
            int width=fm.stringWidth("Are you willing to- #$^*@1");
            g2.setColor(Color.WHITE);
            gp.wordBoi.drawAll((gamepanel.GAME_WIDTH/2)-width/2, gamepanel.GAME_HEIGHT/2, "Are you willing to- #$^*@1", g2);
            boolean timerDone=timer2.wait(1.5, gp.delta);
            if (timerDone==true) {
                titleState="gate";
            }
        }
        else if(titleState=="gate") {
             FontMetrics fm=g2.getFontMetrics();
            int width=fm.stringWidth("In that case, the way is open.");
            g2.setColor(Color.WHITE);
            gp.wordBoi.drawAll((gamepanel.GAME_WIDTH/2)-width/2, gamepanel.GAME_HEIGHT/2, "In that case, the way is open.", g2);
            boolean time=timer3.wait(2, gp.delta);
            if (time==true) {
                int fadeCode=fWhite.fadeToWhite(2, g2);
                if (fadeCode==0) {
                    System.out.println("starting fade");
                }
                else if(fadeCode==1) {
                    System.out.println("fade in progress");
                    gp.gameProcess=true;
                    gp.state=gameState.CUTSCENE;
                }
                else if(fadeCode==2) {
                    System.out.println("ending fade");
                }
                else if(fadeCode==3) {
                    System.out.println("fade finished");
                    titleState="runningGame";
                }
            }
        }
    }
}
