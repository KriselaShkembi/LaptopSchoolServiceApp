package com.example.laptopschoolserviceapp.services;

import com.example.laptopschoolserviceapp.enumerations.Status;
import com.example.laptopschoolserviceapp.models.LaptopPart;
import com.example.laptopschoolserviceapp.models.Ticket;
import com.example.laptopschoolserviceapp.models.Ticket;
import com.example.laptopschoolserviceapp.repositories.LaptopPartRepository;
import com.example.laptopschoolserviceapp.repositories.TicketRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    
    private TicketRepository ticketRepository;
    private final LaptopPartRepository laptopPartRepository;

    public TicketService(TicketRepository ticketRepository,
                         LaptopPartRepository laptopPartRepository) {
        this.ticketRepository = ticketRepository;
        this.laptopPartRepository = laptopPartRepository;
    }

    // Methods

    private void validateTicketCreation(Ticket ticket) {
        if (ticket.getLaptopPart().getStock() == 0) {
            throw new IllegalStateException("Cannot create a ticket for a laptop part with no stock.");
        }
    }
    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }
    public Ticket create(Ticket ticket){
        validateTicketCreation(ticket);
        LaptopPart laptopPart = ticket.getLaptopPart();
        laptopPart.setStock(laptopPart.getStock()-1);
        laptopPartRepository.save(laptopPart);
        ticket.setStatus(Status.OPEN);
        return ticketRepository.save(ticket);
    };


    public Ticket update(Ticket updatedTicket) {
        Ticket existingTicket = ticketRepository.findById(updatedTicket.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        LaptopPart originalPart = existingTicket.getLaptopPart();
        LaptopPart newPart = updatedTicket.getLaptopPart();

        existingTicket.setLaptopPart(newPart);

        adjustPartStock(originalPart, +1); // Increase the stock of the original part
        adjustPartStock(newPart, -1); // Decrease the stock of the new part

        return ticketRepository.save(existingTicket);
    }

    private void adjustPartStock(LaptopPart part, int adjustment) {
        if (part != null) {
            part.setStock(part.getStock() + adjustment);
            laptopPartRepository.save(part);
        }
    }

    public void delete(Long id){
        ticketRepository.deleteById(id);
    };

    public Ticket findById(Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(optionalTicket.isPresent()) {
            return optionalTicket.get();
        }else {
            return null;
        }
    }

    // New feature - create a pdf file

    public void generateTicketPdf(Ticket ticket) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            PDFont font = PDType1Font.HELVETICA_BOLD;
            int fontSize = 14;

            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.setNonStrokingColor(58, 186, 169);;
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Ticket Information:");
            contentStream.setNonStrokingColor(0, 0, 0);

            PDFont textFont = PDType1Font.HELVETICA;
            int textFontSize = 12;
            contentStream.setFont(textFont, textFontSize);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Ticket ID: " + ticket.getId());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Description: " + ticket.getDescription());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Status: " + ticket.getStatus());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Laptop part used: " + ticket.getLaptopPart().getName());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Laptop part used: " + ticket.getLaptopPart().getDescription());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Cost for the repairment: " + ticket.getLaptopPart().getPrice() + " $");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Laptop owner: " + ticket.getLaptop().getUser().getUsername());

            contentStream.endText();
            contentStream.close();
            document.save("ticket" + ticket.getId()+".pdf");
            document.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
