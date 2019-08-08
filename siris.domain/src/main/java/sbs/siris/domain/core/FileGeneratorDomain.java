package sbs.siris.domain.core;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sbs.siris.domain.entity.Informe;
import sbs.siris.domain.entity.Validacion;
import sbs.siris.domain.entity.ValidacionDomain;
import sbs.siris.domain.entity.dto.EventoReporteDTO;
import sbs.siris.domain.entity.dto.ReporteDTO;

@Service
public class FileGeneratorDomain {
	
	@Autowired
	private ValidacionDomain validacionDomain;

	@Transactional(propagation = Propagation.REQUIRED)
	public byte[] generarPDFConstanciaEnvio(EventoReporteDTO parametros) throws Exception {
        List<ReporteDTO> reporteList = new ArrayList<ReporteDTO>();
        
        ReporteDTO reporte = new ReporteDTO();
        
        List<Informe> informeLista = new ArrayList<Informe>();
        parametros.getInforme().setContactoCorreo(parametros.getCorreoInforme().getDesCorreo());//paso temporal del correo del informe
        informeLista.add(parametros.getInforme());
        
        reporte.setTipEvento(parametros.getInforme().getTipEvento());
        reporte.setInforme(informeLista);
        
        reporte.setImpacto(parametros.getImpacto());
        
        reporte.setCanales(parametros.getCanales());
        reporte.setPlanAccion(parametros.getPlanAccion());
        
        List<Validacion> listaValidacion = validacionDomain.listarValidacionByEvent(parametros.getIdEvento());
        reporte.setValidaciones(listaValidacion);
        
        reporteList.add(reporte);

        JRBeanCollectionDataSource itemsJRBean2 = new JRBeanCollectionDataSource(reporteList);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();

        /* Using compiled version(.jasper) of Jasper report to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("informe_constancia.jasper"), parameters, itemsJRBean2);

        /* outputStream to create PDF */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        /* Write content to PDF file */
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
       
		return outputStream.toByteArray();
	}
	
}
