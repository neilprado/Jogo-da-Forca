import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JogoDaForcaSwing {
	private JFrame frame;
	private JogoDaForca Game;
	private JButton[] keyboard = new JButton[26];
	private String[] buttons = {"A","B","C","D","E","F","G","H","I","J","K","L","M",
								"N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private JLabel lblHits;
	private JLabel lblMistakes;
	private JLabel lblResult;
	private JLabel mar1, mar2, mar3, mar4, mar5, mar6;
	private JLabel lblTip;
	private String tip_game;
	private JButton btnWord[];
	private Song audio = new Song();
	private Thread thread;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JogoDaForcaSwing window = new JogoDaForcaSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}


	public JogoDaForcaSwing() {
		initialize();
		tocar();
	}

	public void tocar() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
			audio.player("back");
			}
		};
		thread = new Thread(runnable);// cria uma nova Thread
		thread.start();

	}


	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Mario's Hangman - Pedro Xavier & Neil Prado");
		frame.setBounds(350, 200, 920, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnRestart = new JButton("RESET");
		btnRestart.setFocusable(false);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				audio.stop();
				frame.dispose();
				JogoDaForcaSwing novo = new JogoDaForcaSwing();
				novo.frame.setVisible(true);

			}
		});
		btnRestart.setBounds(684, 158, 89, 23);
		frame.getContentPane().add(btnRestart);

		JButton btnKick = new JButton("CHUTE");
		btnKick.setFocusable(false);
		btnKick.setEnabled(true);
		btnKick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String kick;
					kick = JOptionPane.showInputDialog("Digite a palavra:");

					String word_game = Game.getWord_restart();
					String [] word_split;
					word_split = word_game.split("");

					if(Game.guess(kick)) {
						
						audio.stop();
						audio.player("win");
						for(int i=0; i<26; i++) {
							keyboard[i].setEnabled(false);
						} 
						Game.setHits(Game.getWord_restart().length());
						lblHits.setText("Acertos: " + Game.getHits());
						btnKick.setEnabled(false);

						for (int i=0; i<word_split.length; i++) {
							for(int j = 0; j<word_split.length; j++)
								btnWord[j].setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/tp2.jpg")));
							btnWord[i].setText(word_split[i]);
						}

						lblResult.setText("Você venceu!");

					}else {
						audio.stop();
						audio.player("lose");
						for(int i=0; i<26; i++) {
							keyboard[i].setEnabled(false);
						}
						Game.setMistakes(6);
						lblMistakes.setText("Erros: " + Game.getMistakes());
						mar6.setVisible(true);
						btnKick.setEnabled(false);

						for (int i=0; i<word_split.length; i++) {
							for(int j = 0; j<word_split.length; j++)
								btnWord[j].setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/tp2.jpg")));
							btnWord[i].setText(word_split[i]);
						}

						lblResult.setText("Você perdeu!");

					}
				}catch(NullPointerException e) {
					System.out.println("Chute abortado");
				}


			}

		});
		btnKick.setBounds(779, 158, 89, 23);
		frame.getContentPane().add(btnKick);

		lblResult = new JLabel("");
		lblResult.setForeground(new Color(255, 255, 255));
		lblResult.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));
		lblResult.setBounds(697, 115, 171, 20);
		frame.getContentPane().add(lblResult);

		lblMistakes = new JLabel("Erros: ");
		lblMistakes.setForeground(new Color(255, 255, 255));
		lblMistakes.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		lblMistakes.setBounds(697, 84, 171, 20);
		frame.getContentPane().add(lblMistakes);


		lblHits = new JLabel("Acertos: ");
		lblHits.setForeground(new Color(255, 255, 255));
		lblHits.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 15));
		lblHits.setBounds(697, 53, 171, 20);
		frame.getContentPane().add(lblHits);

		JPanel panel_word = new JPanel();
		panel_word.setBounds(156, 268, 577, 37);
		panel_word.setOpaque(false);
		frame.getContentPane().add(panel_word);

		JPanel panel_keyboard = new JPanel();
		panel_keyboard.setBounds(113, 341, 693, 98);
		frame.getContentPane().add(panel_keyboard);
		panel_keyboard.setOpaque(false);

		lblTip = new JLabel("");
		lblTip.setOpaque(true);
		lblTip.setForeground(new Color(255, 255, 255));
		lblTip.setBackground(new Color(173, 170, 181, 200));
		lblTip.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblTip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTip.setBounds(10, 11, 879, 28);
		frame.getContentPane().add(lblTip);

		mar6 = new JLabel("");
		mar6.setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/mariio6.png")));
		mar6.setBounds(168, 102, 123, 149);
		mar6.setVisible(false);
		frame.getContentPane().add(mar6);

		mar5 = new JLabel("");
		mar5.setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/mariio5.png")));
		mar5.setBounds(168, 105, 108, 142);
		mar5.setVisible(false);
		frame.getContentPane().add(mar5);

		mar4 = new JLabel("");
		mar4.setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/mariio4.png")));
		mar4.setBounds(168, 105, 123, 107);
		mar4.setVisible(false);
		frame.getContentPane().add(mar4);

		mar3 = new JLabel("");
		mar3.setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/mariio3.png")));
		mar3.setBounds(168, 107, 123, 107);
		mar3.setVisible(false);
		frame.getContentPane().add(mar3);

		mar2 = new JLabel("");
		mar2.setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/mark2.png")));
		mar2.setBounds(168, 105, 90, 142);
		mar2.setVisible(false);
		frame.getContentPane().add(mar2);

		mar1 = new JLabel("");
		mar1.setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/mariio1.png")));
		mar1.setBounds(168, 85, 85, 107);
		mar1.setVisible(false);
		frame.getContentPane().add(mar1);


		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/fore6.jpg")));
		lblNewLabel.setBounds(10, 0, 884, 426);
		frame.getContentPane().add(lblNewLabel);


		//Game Initialization

		Game = new JogoDaForca("carros.txt");
		Game.initialize();

		String word_game = Game.getWord_restart();
		tip_game = Game.getTip();
		lblTip.setText(tip_game);

		int word_size = Game.getWords()[Game.getIndex()].length();
		String [] word_split;
		word_split = word_game.split("");

		btnWord = new JButton[word_size];
		for(int i=0; i<word_size; i++) {
			btnWord[i] = new JButton(" ");
			btnWord[i].setBounds(i*20, 0, 29, 24);
			btnWord[i].setFont(new Font("Tahoma", Font.BOLD, 18));
			btnWord[i].setForeground(Color.WHITE);
			btnWord[i].setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/tr1.jpg")));
			btnWord[i].setContentAreaFilled(false);
			btnWord[i].setBorder(null);
			btnWord[i].setVerticalTextPosition(SwingConstants.CENTER);
			btnWord[i].setHorizontalTextPosition(SwingConstants.CENTER);
			btnWord[i].setFocusable(false);
			panel_word.add(btnWord[i]);
		};

		for(int i=0; i<26; i++) {
			keyboard[i] = new JButton(buttons[i]);
			keyboard[i].setBounds(98, 128, 29, 24);
			keyboard[i].setFont(new Font("Tahoma", Font.BOLD, 18));
			keyboard[i].setForeground(Color.WHITE);
			keyboard[i].setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/tc.jpg")));
			keyboard[i].setContentAreaFilled(false);
			keyboard[i].setBorder(null);
			keyboard[i].setVerticalTextPosition(SwingConstants.CENTER);
			keyboard[i].setHorizontalTextPosition(SwingConstants.CENTER);
			panel_keyboard.add(keyboard[i]);
			keyboard[i].setFocusable(false);
			keyboard[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton btnActual = (JButton) e.getSource();
					String letter = btnActual.getText(); 
					int[] indexes = Game.play(letter);

					if(indexes!=null) {
						audio.player("letter");
						for(int i=0; i<indexes.length; i++) {
							btnWord[indexes[i]].setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/tp2.jpg")));
							btnWord[indexes[i]].setText(letter);
							btnActual.setEnabled(false);
							lblHits.setText("Acertos: " + Game.getHits());
						}
					}else {
						audio.player("wrong");
						btnActual.setEnabled(false);
						lblMistakes.setText("Erros: " + Game.getMistakes());
					}
					if(Game.getMistakes() == 1) {
						mar1.setVisible(true);
					}else if(Game.getMistakes() == 2) {
						mar1.setVisible(false);
						mar2.setVisible(true);
					}else if(Game.getMistakes() == 3) {
						mar2.setVisible(false);
						mar3.setVisible(true);
					}else if(Game.getMistakes() == 4) {
						mar3.setVisible(false);
						mar4.setVisible(true);
					}else if(Game.getMistakes() == 5) {
						mar4.setVisible(false);
						mar5.setVisible(true);
					}
					if (Game.getMistakes() == 6 || Game.getHits() == word_game.length()) {
						for(int i=0; i<26; i++) {
							keyboard[i].setEnabled(false);
						} 

						for (int i=0; i<word_split.length; i++) {
							btnWord[i].setText(word_split[i]);
						}

						if(Game.getMistakes() == 6) {
							mar5.setVisible(false);
							mar6.setVisible(true);
							audio.stop();
							audio.player("lose");
							lblResult.setText("Você perdeu!");
							btnRestart.setEnabled(true);

							for (int i=0; i<word_split.length; i++) {
								for(int j = 0; j<word_split.length; j++)
									btnWord[j].setIcon(new ImageIcon(JogoDaForcaSwing.class.getResource("/imagem/tp2.jpg")));
								btnWord[i].setText(word_split[i]);
							}
						}else if(Game.getHits() == word_game.length()) {
							audio.stop();
							audio.player("win");
							lblResult.setText("Você venceu!");
							btnRestart.setEnabled(true);
						}
						btnKick.setEnabled(false);
					}

				}


			});



		}

	}
}
