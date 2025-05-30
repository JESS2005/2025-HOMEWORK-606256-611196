package it.uniroma3.diadia.comandi;

public abstract class AbstractComando implements Comando {
	protected String nome;
	
	@Override
	public void setParametro(String parametro) {
		this.nome = parametro;
	}
	
	@Override
	public String getParametro() {
		return this.nome;
	}
	
	@Override
    public String getNome() {
		return this.getClass().getName().substring(getClass().getName().indexOf("Comando")+7);
	}

}
