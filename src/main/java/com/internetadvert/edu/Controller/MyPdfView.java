package com.internetadvert.edu.Controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.internetadvert.edu.Pojo.Invoice;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class MyPdfView extends AbstractPdfView{


	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document document,
			PdfWriter arg2, HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		ArrayList<Invoice> invoiceList = (ArrayList<Invoice>) request.getSession().getAttribute("inv");

		
		
		for(Invoice invoice : invoiceList){
			Paragraph space = new Paragraph("    ");
			Random random = new Random();
			int i = random.nextInt(5);
			Paragraph head = new Paragraph("Payment Receipt");
			Paragraph heading = new Paragraph("Receipt No:" + i);
			Paragraph p1 = new Paragraph("Invoice ID:" + invoice.getInvoiceId());
			Paragraph p2 = new Paragraph("Payment Time: " + map.get("time"));

			Paragraph p3 = new Paragraph("Amount: "+invoice.getAmount());
			

			PdfPTable tbl = new PdfPTable(5);
			tbl.setHorizontalAlignment(20);
			tbl.setWidthPercentage(100);


			PdfPCell cell = new PdfPCell(heading);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			tbl.addCell(cell);

			PdfPTable tb2 = new PdfPTable(1);
			tb2.setHorizontalAlignment(20);
			tb2.setWidthPercentage(100);

			cell = new PdfPCell(p1);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			tb2.addCell(cell);

			PdfPTable tb3 = new PdfPTable(1);
			tb3.setHorizontalAlignment(20);
			tb3.setWidthPercentage(100);

			cell = new PdfPCell(p3);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			tb3.addCell(cell);

			PdfPTable tb4 = new PdfPTable(1);
			tb4.setHorizontalAlignment(20);
			tb4.setWidthPercentage(100);

			cell = new PdfPCell(p2);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			tb4.addCell(cell);
			
			document.add(head);
			document.add(space);
			document.add(tbl);
			document.add(tb2);
			document.add(tb3);
			document.add(tb4);
			document.add(space);
			document.add(space);
			document.add(space);
			document.add(space);
		}
		document.close();
	}
}
