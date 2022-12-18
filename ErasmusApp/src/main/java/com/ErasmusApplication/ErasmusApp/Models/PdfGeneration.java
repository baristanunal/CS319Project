package com.ErasmusApplication.ErasmusApp.Models;

import java.util.List;
import com.ErasmusApplication.ErasmusApp.Services.StudentService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
// ahmete: all args hata veriyor silmek zorunda kaldım
public class PdfGeneration {
    private StudentService studentService;

    private void  aa(){
        long x = 1;
        Student student = studentService.getStudent(x);
        Application application= studentService.getApplicationByApplicationType(x,"ERASMUS");
        CourseWishList courseWishList = application.getCourseWishlist();
//        if( courseWishList.isCompleted()){
//
//        }

        List<Wish> wishes = courseWishList.getWishes();
        Course bilk = wishes.get(0).getBilkentCourse();
        Course host = wishes.get(0).getCourseToCountAsBilkentCourse();



    }
    public void createPdf() throws IOException, DocumentException, URISyntaxException {
        Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream("D://myFile.pdf"));
        document.open(); // document is opened

        // Create Bilkent image
        Path path = Paths.get(ClassLoader.
                getSystemResource("images//bilkentlogo.png").toURI());
        Image image = Image.getInstance(path.toAbsolutePath().toString());
        image.scaleAbsolute(30,30);

        // Create fonts
        //Font font12 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font font12 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font font14 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
        Font font16 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);

        // Create chunks
        Paragraph paragraph1 = new Paragraph(
                "Bilkent University            Course Exemption Pre-Approval Form for Outgoing Exchange Students", font16);

        // Create information about student table
        PdfPTable table1 = new PdfPTable(4);
        table1.setSpacingBefore(30);
        table1.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Create cells and add them to the table
        PdfPCell cell1 = new PdfPCell();
        PdfPCell cell2 = new PdfPCell();
        PdfPCell cell3 = new PdfPCell();
        PdfPCell cell4 = new PdfPCell();
        PdfPCell cell5 = new PdfPCell();
        PdfPCell cell6 = new PdfPCell();
        PdfPCell cell7 = new PdfPCell();
        PdfPCell cell8 = new PdfPCell();

        cell1.addElement(new Chunk("Name",font12));
        cell2.addElement(new Chunk("Ahmet",font12));
        cell3.addElement(new Chunk("ID Number",font12));
        cell4.addElement(new Chunk("21903105",font12));
        cell5.addElement(new Chunk("Surname",font12));
        cell6.addElement(new Chunk("Altun",font12));
        cell7.addElement(new Chunk("Department",font12));
        cell8.addElement(new Chunk("Computer Science",font12));

        table1.addCell(cell1);
        table1.addCell(cell2);
        table1.addCell(cell3);
        table1.addCell(cell4);
        table1.addCell(cell5);
        table1.addCell(cell6);
        table1.addCell(cell7);
        table1.addCell(cell8);

        // Create information about university table
        PdfPTable table2 = new PdfPTable(3);
        table2.setSpacingBefore(15);
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Create cells and add them to the table
        PdfPCell ucell1 = new PdfPCell();
        PdfPCell ucell2 = new PdfPCell();



        ucell1.addElement(new Chunk("Name of the host institution",font12));
        ucell2.addElement(new Chunk("Ecole Superieure d'Informatique", font12));
        // Create inner table
        PdfPTable table3 = new PdfPTable(2);
        // Create cells and add them to the table
        PdfPCell icell1 = new PdfPCell();
        PdfPCell icell2 = new PdfPCell();
        PdfPCell icell3 = new PdfPCell();
        PdfPCell icell4 = new PdfPCell();

        icell1.addElement(new Chunk("Academic Year",font12));
        icell2.addElement(new Chunk("2022-2023", font12));
        icell3.addElement(new Chunk("Semester", font12));
        icell4.addElement(new Chunk("Fall", font12));
        table3.addCell(icell1);
        table3.addCell(icell2);
        table3.addCell(icell3);
        table3.addCell(icell4);

        table2.addCell(ucell1);
        table2.addCell(ucell2);
        table2.addCell(table3);

        // Create header
        PdfPTable table4 = new PdfPTable(2);
        table4.setSpacingBefore(15);
        table4.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Create cells and add them to the table
        PdfPCell courseCell1 = new PdfPCell();
        PdfPCell courseCell2 = new PdfPCell();
        courseCell1.addElement(new Chunk("Host institution courses to be transferred upon approval",font12));
        courseCell2.addElement(new Chunk(
                "Course or requirement to be exempted if transferred course is completed with a passing grade †"
                ,font12
        ));
        table4.addCell(courseCell1);
        table4.addCell(courseCell2);

        // Host table
        PdfPTable hostHeader = new PdfPTable(4);
        PdfPCell hostCell1 = new PdfPCell();
        PdfPCell hostCell2 = new PdfPCell();
        PdfPCell hostCell3 = new PdfPCell();
        PdfPCell hostCell4 = new PdfPCell();
        hostCell1.addElement(new Chunk("", font12));
        hostCell2.addElement(new Chunk("Course Code", font12));
        hostCell3.addElement(new Chunk("Course Name",font12));
        hostCell4.addElement(new Chunk("Credits*",font12));
        hostHeader.addCell(hostCell1);
        hostHeader.addCell(hostCell2);
        hostHeader.addCell(hostCell3);
        hostHeader.addCell(hostCell4);

        // Bilkent Table
        PdfPTable bilkentHeader = new PdfPTable(3);
        PdfPCell bilkentCell1 = new PdfPCell();
        PdfPCell bilkentCell2 = new PdfPCell();
        PdfPCell bilkentCell3 = new PdfPCell();
        bilkentCell1.addElement(new Chunk("Course Code and Name for a Required Course," +
                "Elective Group Name for an Elective Requirement", font12));
        bilkentCell2.addElement(new Chunk("Credits", font12));
        bilkentCell3.addElement(new Chunk("Elective Requirement Exemptions only: Course code(s) of " +
                "directly equivalent course(s), if any **",font12));
        bilkentHeader.addCell(bilkentCell1);
        bilkentHeader.addCell(bilkentCell2);
        bilkentHeader.addCell(bilkentCell3);


        // Create courses that will be transferred
        int courseNumber  = 1; // will be replaced
        for(int i = 1; i <= courseNumber; i++){
            String rowNumber = i + "";

            // Host Courses
            PdfPCell newHostCell1 = new PdfPCell();
            PdfPCell newHostCell2 = new PdfPCell();
            PdfPCell newHostCell3 = new PdfPCell();
            PdfPCell newHostCell4 = new PdfPCell();
            newHostCell1.addElement(new Chunk(rowNumber, font12));
            newHostCell2.addElement(new Chunk("INF4032", font12));
            newHostCell3.addElement(new Chunk("Computer Networks",font12));
            newHostCell4.addElement(new Chunk("3.0",font12));
            hostHeader.addCell(newHostCell1);
            hostHeader.addCell(newHostCell2);
            hostHeader.addCell(newHostCell3);
            hostHeader.addCell(newHostCell4);

            // Bilkent Courses
            PdfPCell newBilkentCell1 = new PdfPCell();
            PdfPCell newBilkentCell2 = new PdfPCell();
            PdfPCell newBilkentCell3 = new PdfPCell();
            newBilkentCell1.addElement(new Chunk("Technical Elective", font12));
            newBilkentCell2.addElement(new Chunk("3", font12));
            newBilkentCell3.addElement(new Chunk("CS421",font12));
            bilkentHeader.addCell(newBilkentCell1);
            bilkentHeader.addCell(newBilkentCell2);
            bilkentHeader.addCell(newBilkentCell3);
        }

        // Create the approved by table
        PdfPTable tableApp = new PdfPTable(4);
        tableApp.setSpacingBefore(15);
        tableApp.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell appCell1 = new PdfPCell();
        PdfPCell appCell2 = new PdfPCell();
        PdfPCell appCell3 = new PdfPCell();
        PdfPCell appCell4 = new PdfPCell();
        PdfPCell appCell5 = new PdfPCell();
        PdfPCell appCell6 = new PdfPCell();
        PdfPCell appCell7 = new PdfPCell();
        PdfPCell appCell8 = new PdfPCell();

        appCell1.addElement(new Chunk("Approved by",font12));
        appCell2.addElement(new Chunk("Name",font12));
        appCell3.addElement(new Chunk("Signature",font12));
        appCell4.addElement(new Chunk("Date",font12));
        appCell5.addElement(new Chunk("Exchange Coordinator",font12));
        appCell6.addElement(new Chunk("Can Alkan",font12));
        appCell7.addElement(new Chunk("imzaa",font12));
        appCell8.addElement(new Chunk("18.12.2022",font12));

        tableApp.addCell(appCell1);
        tableApp.addCell(appCell2);
        tableApp.addCell(appCell3);
        tableApp.addCell(appCell4);
        tableApp.addCell(appCell5);
        tableApp.addCell(appCell6);
        tableApp.addCell(appCell7);
        tableApp.addCell(appCell8);








        // Add elements
        document.add(image);
        document.add(paragraph1);
        document.add(table1);
        document.add(table2);
        table4.addCell(hostHeader);
        table4.addCell(bilkentHeader);
        document.add(table4);
        document.add(tableApp);


        document.close(); // document is closes
    }
}
