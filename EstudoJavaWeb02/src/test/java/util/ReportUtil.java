package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] geraRelatorioPDF(List listaDadoss, String nomeRelatorio, ServletContext servletContext) throws Exception{
		
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDadoss);
		
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		//ESPECIFICA O PARÂMETRO QUE CONTÉM O CAMINHO PATH DO SUBRELATÓRIO
		parametros.put("PARAM_SUB_REPORT", servletContext.getRealPath("relatorio") + File.separator);
		
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper,parametros,jrbcds);

		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}

}
