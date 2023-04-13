package com.ait.pdf;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.swing.text.Document;
import javax.swing.text.Element;

import in.ashokit.PlayerEntity;
	public class DBtoPDF {
	
		public static ByteArrayInputStream playerPDFReport(List<PlayerEntity> players) {
			Document document = new Document();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, out);
			document.open();

			Font fontheader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
			Paragraph para = new Paragraph("Player structure", fontheader);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			PdfPTable table = new PdfPTable(3);
			Stream.of("player_id", "player_name", "player_role").forEach(headerTitle -> {
				PdfPCell header = new PdfPCell();
				Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
				header.setBackgroundColor(Color.CYAN);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(headerTitle, headFont));
				table.addCell(header);

			});

			for (PlayerEntity player : players) {
				PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(player.getPlayer_id())));
				idCell.setPaddingLeft(4);
				idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(idCell);

				PdfPCell firstNameCell = new PdfPCell(new Phrase(player.getPlayer_name()));
				firstNameCell.setPaddingLeft(4);
				firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(firstNameCell);

				PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(player.getPlayer_role())));
				lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				lastNameCell.setPaddingRight(4);
				table.addCell(lastNameCell);
			}
			document.add(table);
			document.close();
			return new ByteArrayInputStream(out.toByteArray());

		}

	}

