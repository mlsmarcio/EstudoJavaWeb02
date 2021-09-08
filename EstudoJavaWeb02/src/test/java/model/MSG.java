package model;

public class MSG {
    public static String criar(TipoMSG tipo, String tituloMensagem, String mensagem) {
    	String titulo = "";
    	
    	switch (tipo.getTipo()) {
		case 1:
			titulo = "Primario";
			break;

		case 2:
			titulo = "Secundário";
			break;

		case 3:
			titulo = "Sucesso";
			break;
			
		case 4:
			titulo = "Perigo";
			break;

		case 5:
			titulo = "Aviso";
			break;

		case 6:
			titulo = "Informações";
			break;

		case 7:
			titulo = "Luz";
			break;
			
		case 8:
			titulo = "Sombrio";
			break;

		default:
			break;
		}
    	
		titulo = (tituloMensagem.isEmpty() || tituloMensagem == null ?titulo :tituloMensagem);
		
		return "<div class='alert alert-" + tipo.getNome() + " alert-dismissible fade show'> " + 
				"<button type='button' class='close' data-dismiss='alert'>&times;</button>" + 
				"<strong>" + titulo + "! </strong> " + mensagem +
				"</div>";
    	
    }

}
