package com.ErasmusApplication.ErasmusApp.Controllers;

import com.ErasmusApplication.ErasmusApp.Models.Application;
import com.ErasmusApplication.ErasmusApp.Security.JwtUtils;
import com.ErasmusApplication.ErasmusApp.Services.PdfGenerationService;
import com.itextpdf.text.DocumentException;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;


@RestController
@AllArgsConstructor
public class PdfGenerationController {
    private final JwtUtils jwtUtils;
    private final PdfGenerationService pdfGenerationService;


    @PostMapping("/{appTypeInt}/createPdf")
    public void createPdf(@RequestHeader(name="Authorization") String token, @PathVariable int appTypeInt) throws DocumentException, IOException, URISyntaxException {
        String applicationType = "ERASMUS";
        if( appTypeInt == 1){
            applicationType = "EXCHANGE";
        }
        String stringToken = token.substring(7);
        Claims a = jwtUtils.decrypt(stringToken);
        String sId = a.getSubject();
        pdfGenerationService.makePdf(sId,applicationType);
    }







}
