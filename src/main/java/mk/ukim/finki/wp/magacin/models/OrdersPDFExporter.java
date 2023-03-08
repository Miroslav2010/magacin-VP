package mk.ukim.finki.wp.magacin.models;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class OrdersPDFExporter {
  private final List<Order> orders;
  private double total = 0.0;

  public OrdersPDFExporter(List<Order> orders) {
    this.orders = orders;
  }

  private void writeTableHeader(PdfPTable table) {
    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(Color.BLUE);
    cell.setPadding(5);

    Font font = FontFactory.getFont(FontFactory.HELVETICA);
    font.setColor(Color.WHITE);

    cell.setPhrase(new Phrase("Username", font));

    table.addCell(cell);

    cell.setPhrase(new Phrase("E-mail", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("Full Name", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("Order status", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("Date", font));
    table.addCell(cell);

    cell.setPhrase(new Phrase("Price", font));
    table.addCell(cell);
  }

  private void writeTableData(PdfPTable table) {
    for (Order order : orders) {
      table.addCell(order.getUser().getUsername());
      table.addCell(order.getEmail());
      table.addCell(order.getFirstName() + " " + order.getLastName());
      table.addCell(order.getStatus().toString());
      table.addCell(order.exactDate());
      table.addCell(order.getTotalPrice().toString());
      this.total += order.getTotalPrice();
    }
  }

  public void export(HttpServletResponse response) throws DocumentException, IOException {
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, response.getOutputStream());

    document.open();
    Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    font.setSize(18);
    font.setColor(Color.BLUE);

    Paragraph p = new Paragraph("List of Orders", font);
    p.setAlignment(Paragraph.ALIGN_CENTER);

    document.add(p);

    PdfPTable table = new PdfPTable(6);
    table.setWidthPercentage(100f);
    table.setWidths(new float[]{1.5f, 3.0f, 3.0f, 2.0f, 2.0f, 1.5f});
    table.setSpacingBefore(10);

    writeTableHeader(table);
    writeTableData(table);

    document.add(table);

    p = new Paragraph(String.format("Total: %f", total));
    p.setAlignment(Paragraph.ALIGN_RIGHT);
    document.add(p);

    document.close();
  }
}
