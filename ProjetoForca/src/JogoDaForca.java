public class JogoDaForca {
	private String[] palavras;
	private String[] dicas;
	private int n;
	private int sorteio = -1;
	private int acertos;
	private int erros;
	
	public JogoDaForca(String arquivo){
		
	}
	
	public void inicializar(){
		this.sorteio = (int)(Math.random()*10);
		this.acertos = 0;
		this.erros = 0;
	}
	
	public int [] jogar(String letra){
		/*Verificação da existência da letra dentro da palavra sorteada, caso exista
		retorna um array com os indices encontrados */
		return ;
	}
	
	public boolean advinhar (String palavra){
		return true;
	}
	
	public int getTamanho(){
		return 
	}
	
	public int getAcertos(){
		return this.acertos;
	}
	
	public int getErros(){
		return this.erros;
	}
	
	public String getDica(){
		return this.dicas;
	}
}
