package controllayer;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modellayer.*;

public class CompendiumCtr {
	
	//Forskellige fonts. F�rst importere man fonten (Times New Roman), s� indtastes skriftst�rrelsen og til sidst om det skal v�re fed, kusrivt eller normalt.
		private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			    Font.BOLD);
		private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			    Font.NORMAL, BaseColor.RED);		
		private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
				Font.BOLD);			  
		private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				Font.BOLD);
		private BreederCtr bCtr;
		private ChartCtr cCtr;

	public CompendiumCtr() {
		bCtr = new BreederCtr();
		cCtr = new ChartCtr();
	}
	
	public void createPDF() {
		Document document = new Document();
		Date date = new Date();
		
		//Navngivning af PDF fil
		@SuppressWarnings("deprecation")
		int year = date.getYear();		
		String pdfName = "Kompendium " + Integer.toString(year) + ".pdf";
		try {
			
			PdfWriter.getInstance(document, new FileOutputStream(pdfName)); //Henter en instance af PdfWriter og laver en fil med ovenn�vnte navn
            document.open();
            
            //meta data
            document.addTitle("Kompendium");
    	    document.addSubject("");
    	    document.addKeywords("");
    	    document.addAuthor("Ligustica");
    	    document.addCreator("Ligustica");
    	    
    	    //titel
    	    Paragraph preface = new Paragraph();
    		addEmptyLine(preface, 1);
    		preface.add(new Paragraph("Test af PDF", catFont));
    		addEmptyLine(preface, 1);	
    		preface.add(new Paragraph("Et dokument med en test tabel generet med iText",
    		        smallBold));
    		addEmptyLine(preface, 8);
    		document.add(preface); 
			
			
			for(Breeder breeder : bCtr.getAllBreeders()) { // Hver breeder
				Paragraph breederName = new Paragraph();
	    		addEmptyLine(breederName , 1);
				breederName .add(new Paragraph(breeder.getFname() + " " + breeder.getLname(), catFont));
	    		addEmptyLine(breederName , 2);
	    		document.add(breederName ); 
				
				
				for(Chart chart : cCtr.getAllCharts()) { // hver chart
					
					if(chart.getBreeder().equals(breeder)) {
						
						PdfPTable tableHead = new PdfPTable(2); // 2 kolonner.
			            //linie 1
						PdfPCell nameCell = new PdfPCell(new Paragraph("Navn: " + breeder.getFname() + " " + breeder.getLname()));
			            PdfPCell emptyCell = new PdfPCell(new Paragraph(""));
			            //linie 2:
			            PdfPCell addressCell = new PdfPCell(new Paragraph("Adresse: " + breeder.getCity().getCity()));
			            PdfPCell initialsCell = new PdfPCell(new Paragraph("Avlerinitial: "));
			            //linie 3:
			            PdfPCell yearCell = new PdfPCell(new Paragraph("Avlsår: " + chart.getYear()));
			            PdfPCell pedigreeCell = new PdfPCell(new Paragraph("Stamtavle: " + chart.getPedigree()));
			            //indsæt de to linier i tabellen
			            tableHead.addCell(nameCell);
			            tableHead.addCell(emptyCell);
			            tableHead.addCell(addressCell);	            
			            tableHead.addCell(initialsCell);
			            tableHead.addCell(yearCell);
			            tableHead.addCell(pedigreeCell);
			            float[] widths = {25f, 25f, 50f}; //Hvor meget hver kolonno m� fylde i bredden, relativt til hinanden
			            tableHead.setWidths(widths);
			            tableHead.setWidthPercentage(50); //Hvor meget hele tabellen m� fylde p� hele siden
			            tableHead.setHorizontalAlignment(Element.ALIGN_LEFT); //Aligner tabellen mod venstre
			            document.add(tableHead); //Tilf�jer tabellen
						
			            
			            PdfPTable tableChart = new PdfPTable(9); // 9 kolonner.
			            //linie 1
						PdfPCell queenCell = new PdfPCell(new Paragraph("Dronning"));
			            PdfPCell year2Cell = new PdfPCell(new Paragraph("År"));
			            PdfPCell swarmCell = new PdfPCell(new Paragraph("Årskarakter sværmetendens"));
			            PdfPCell tempCell = new PdfPCell(new Paragraph("Årskarakter temperament"));
			            PdfPCell honeyCombFirmCell = new PdfPCell(new Paragraph("Årskarakter tavlefasthed"));
			            PdfPCell honeyYeildYearCell = new PdfPCell(new Paragraph("Årskarakter Honningudbytte"));
			            PdfPCell honeyYieldCell = new PdfPCell(new Paragraph("Honningudbytte"));
			            PdfPCell nosemaCell = new PdfPCell(new Paragraph("Nosema"));
			            PdfPCell cleansingCell = new PdfPCell(new Paragraph("Udrensningsevne"));
			            
			            tableChart.addCell(queenCell);
			            tableChart.addCell(year2Cell);
			            tableChart.addCell(swarmCell);
			            tableChart.addCell(tempCell);
			            tableChart.addCell(honeyCombFirmCell);
			            tableChart.addCell(honeyYeildYearCell);
			            tableChart.addCell(honeyYieldCell);
			            tableChart.addCell(nosemaCell);
			            tableChart.addCell(cleansingCell);
			            
						for(PartChart pC : chart.getAllPartCharts()) { //hver partchart
							PdfPCell queenCell2 = new PdfPCell(new Paragraph(pC.getQueen().getName()));
				            PdfPCell year2Cell2 = new PdfPCell(new Paragraph(pC.getYear()));
				            PdfPCell swarmCell2 = new PdfPCell(new Paragraph(pC.getSwarmTendency()));
				            PdfPCell tempCell2 = new PdfPCell(new Paragraph(pC.getSwarmTendency()));
				            PdfPCell honeyCombFirmCell2 = new PdfPCell(new Paragraph(pC.getHoneycomFirmness()));
				            PdfPCell honeyYeildYearCell2 = new PdfPCell(new Paragraph(pC.getHoneyYieldYear()));
				            PdfPCell honeyYieldCell2 = new PdfPCell(new Paragraph(pC.getHoneyYield()));
				            PdfPCell nosemaCell2 = new PdfPCell(new Paragraph(pC.getNosema()));
				            PdfPCell cleansingCell2 = new PdfPCell(new Paragraph(pC.getClensingAbility()));
				            
				            tableChart.addCell(queenCell2);
				            tableChart.addCell(year2Cell2);
				            tableChart.addCell(swarmCell2);
				            tableChart.addCell(tempCell2);
				            tableChart.addCell(honeyCombFirmCell2);
				            tableChart.addCell(honeyYeildYearCell2);
				            tableChart.addCell(honeyYieldCell2);
				            tableChart.addCell(nosemaCell2);
				            tableChart.addCell(cleansingCell2);
						}//end for partchart
						tableChart.setWidths(widths);
			            tableChart.setWidthPercentage(50); //Hvor meget hele tabellen m� fylde p� hele siden
			            tableChart.setHorizontalAlignment(Element.ALIGN_LEFT); //Aligner tabellen mod venstre
						document.add(tableChart);
						Paragraph emptyLines = new Paragraph();
			    		addEmptyLine(emptyLines, 3);
			    		
					} //end if
					
				}//end for Chart
				
			}//end for breeder
			document.close();
		}catch(Exception e) {
			//
		}
	}
	
	/**
	 * Laver et antal nye, tomme linjer
	 * @param paragraph Paragrafen der skal have linjer
	 * @param number Linjer der skal tilf�jes
	 */
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }

}
