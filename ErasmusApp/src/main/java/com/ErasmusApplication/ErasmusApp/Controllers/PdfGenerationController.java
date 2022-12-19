package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Services.PdfGenerationService;
import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController

public class PdfGenerationController {
    private final PdfGenerationService pdfGenerationService;

    @Autowired
    public PdfGenerationController(PdfGenerationService pdfGenerationService) {
        this.pdfGenerationService = pdfGenerationService;
    }
    @PostMapping("{studentId}/{appTypeInt}/createPdf")
    public void createPdf(@PathVariable Long studentId, @PathVariable int appTypeInt) throws DocumentException, IOException, URISyntaxException {
        String applicationType = "ERASMUS";
        if( appTypeInt == 1){
            applicationType = "EXCHANGE";
        }
        pdfGenerationService.makePdf(studentId,applicationType);
    }







}
