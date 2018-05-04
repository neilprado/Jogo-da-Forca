/* IFPB - Sistemas para Internet
 * Projeto de POO - Jogo da Forca Mario
 * Alunos: Neil John Ávila Prado Júnior - 20171370045
 * 		   Pedro Henrique de Sales Xavier - 20161370021 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JogoDaForca {

	private String[] words;
	private String[] tips;
	private int n_words;
	private int index = -1;
	private int hits;
	private int mistakes;
	private String word_restart;

	public JogoDaForca(String namefile) {
		try{
			Scanner file_text;
			String line;
			String[] lines;
			String word_file;
			String tip_file;
			int i = 0;
			file_text = new Scanner(new File(namefile));
			
			this.n_words = Integer.parseInt(file_text.nextLine());
			this.words = new String[this.n_words];
			this.tips = new String[this.n_words];
			
			while (file_text.hasNextLine()) {
				
				line = file_text.nextLine();
				lines = line.split(";");
				word_file = lines[0];
				tip_file = lines[1];
				this.words[i] = word_file;
				this.tips[i] = tip_file;
				
				i++;
			}
			
			file_text.close();
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
	}
	
	public String[] getWords() {
		return words;
	}

	public int getIndex() {
		return index;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}

	public void initialize() {
		this.index = (int) (Math.random() * this.n_words);
		this.hits = 0;
		this.mistakes = 0;
		this.word_restart = new String(this.words[this.index]);
	}
	
	public int[] play(String letter) {
		int[] positions;
		int j = 0;
		
		int occurrences = 0;
		String letter_word;
		String word = this.word_restart;
		
		if(word.contains(letter)) {
			
			for(int i=0; i<word.length(); i++) {
				letter_word = word.substring(i,i+1);
				if(letter.equals(letter_word)) {
					occurrences++;
				}
			}
			
			positions = new int[occurrences];
			
			for(int i=0; i<word.length(); i++) {
				letter_word = word.substring(i,i+1);
				if(letter.equals(letter_word)) {
					positions[j++] = i;
				}
			}
			this.hits += occurrences;
			word = this.word_restart.replaceAll(letter, "#");

		}else {
			this.mistakes++;
			return null;
		}
		
		return positions;
	}
	
	public boolean guess(String word) {
		if(word.equalsIgnoreCase(this.words[this.index])) {
			//this.hits = this.words[this.index].length();
			this.hits = getSize();
			return true;
		}else {
			this.mistakes = 6;
			return false;
		}
	}
	
	public int getSize() {
		return this.words[this.index].length();
	}
	
	public int getHits() {
		return this.hits;
	}
	
	public int getMistakes() {
		return this.mistakes;
	}
	
	public String getTip() {
		return this.tips[this.index];
	}
	
	public String getWord_restart() {
		return this.word_restart;
	}
	
}