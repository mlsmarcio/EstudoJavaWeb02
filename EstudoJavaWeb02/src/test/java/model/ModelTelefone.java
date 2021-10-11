package model;

import java.io.Serializable;

public class ModelTelefone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private ModelLogin usuario_id;
	private ModelLogin usuario_cad;
	
	public ModelTelefone() {
		super();
	}
	
	public ModelTelefone(Long id, String numero, ModelLogin usuario_id, ModelLogin usuario_cad) {
		super();
		this.id = id;
		this.numero = numero;
		this.usuario_id = usuario_id;
		this.usuario_cad = usuario_cad;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public ModelLogin getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(ModelLogin usuario_id) {
		this.usuario_id = usuario_id;
	}
	public ModelLogin getUsuario_cad() {
		return usuario_cad;
	}
	public void setUsuario_cad(ModelLogin usuario_cad) {
		this.usuario_cad = usuario_cad;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefone other = (ModelTelefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
