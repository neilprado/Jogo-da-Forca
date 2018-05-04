/* IFPB - Sistemas para Internet
 * Projeto de POO - Jogo da Forca Mario
 * Alunos: Neil John Ávila Prado Júnior - 20171370045
 * 		   Pedro Henrique de Sales Xavier - 20161370021 */

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Song {
   private Player player;
 
	public void player(String path) {
		 try {
			 player = new Player(JogoDaForcaSwing.class.getResourceAsStream("songs/"+path+".mp3"));
		      
		 }catch(JavaLayerException e) {
		     e.printStackTrace();
		    }
		 
		 	new Thread() {
	            public void run() {
	                try { player.play(); }
	                catch (Exception e) { System.out.println(e); }
	            }
	        }.start();
		   
	}
	public void stop () {
		if(player != null) 
			player.close();
	}

}