package model;

public enum TipoMSG {
    PRIMARY(1, "primary"), 
    SECONDARY(2, "secondary"),
	SUCCESS(3, "sucess"),
	DANGER(4, "danger"),
	WARNING(5, "warning"),
	INFO(6, "info"),
	LIGHT(7, "light"),
	DARK(8, "dark");
	
	private int tipo;
	private String nome;
	
	//CONSTRUTOR DA ENUMERAÇÃO
	TipoMSG(int tipo, String nome) {
	    this.tipo = tipo;
	    this.nome = nome;
	}
	
	public String getNome() {
	    return nome;
	}
	
	public int getTipo() {
	    return tipo;
	}
}
