package com.ErasmusApplication.ErasmusApp.Models;

import java.util.List;
import com.ErasmusApplication.ErasmusApp.Services.StudentService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
@RequiredArgsConstructor
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
        // Get the required information from the student
        long id = 1; // parametre olarak mı alıcaz?? ahmet
        long applicationId = 2;
        Student student = studentService.getStudent(id);

        String studentName = student.getFirstName();
        String studentSurName = student.getLastName();
        String studentID = student.getSchoolId();
        String department = student.getDepartment();

        Application application = student.getApplicationById(applicationId);
        String hostName = application.getPlacedHostUniversity().getNameOfInstitution();
        // appliedAcademicSemester nasıl alıcaz
        String academicYearAndSemester = application.getAppliedAcademicSemester();

        int slashIndex = academicYearAndSemester.indexOf("/");
        String academicYear = academicYearAndSemester.substring(0,slashIndex);
        String semester = academicYearAndSemester.substring(slashIndex + 1);








        // Create the PDF
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
        Font font7  = FontFactory.getFont(FontFactory.TIMES_ROMAN,7  );
        Font font8  = FontFactory.getFont(FontFactory.TIMES_ROMAN,8  );

        // Create elements
        Paragraph paragraph1 = new Paragraph(
                "Bilkent University            Course Exemption Pre-Approval Form for Outgoing Exchange Students", font16);
        Paragraph paragraph2 = new Paragraph( "* ECTS credits for Erasmus exchange students.", font7);
        Paragraph paragraph3 = new Paragraph( "** Applicable only if there is a directly equivalent course in the " +
                "elective group that the student is exempted from. The student will be considered to have taken this " +
                "course by the STARS system.", font7);
        Paragraph paragraph4 = new Paragraph("† A transferred course may provide exemption from a requirement in " +
                "the curriculum if deemed to be equivalent by the Faculty/School Executive Board." +
                "It is possible for one transferred course to provide exemption from one or more curriculum " +
                "courses or vice versa.",font7);

        // Create information about student table
        PdfPTable table1 = new PdfPTable(4);
        table1.setSpacingBefore(30);
        table1.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Create cells and add them to the table
        PdfPCell emptyCell = new PdfPCell();
        PdfPCell cell1 = new PdfPCell();
        PdfPCell cell2 = new PdfPCell();
        PdfPCell cell3 = new PdfPCell();
        PdfPCell cell4 = new PdfPCell();
        PdfPCell cell5 = new PdfPCell();
        PdfPCell cell6 = new PdfPCell();
        PdfPCell cell7 = new PdfPCell();
        PdfPCell cell8 = new PdfPCell();

        emptyCell.addElement(new Chunk("",font12));
        emptyCell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT );


        cell1.addElement(new Chunk("Name",font12));
        cell2.addElement(new Chunk(studentName,font12));
        cell3.addElement(new Chunk("ID Number",font12));
        cell4.addElement(new Chunk(studentID,font12));
        cell5.addElement(new Chunk("Surname",font12));
        cell6.addElement(new Chunk(studentSurName,font12));
        cell7.addElement(new Chunk("Department",font12));
        cell8.addElement(new Chunk(department,font12));

        table1.addCell(cell1);
        table1.addCell(cell2);
        table1.addCell(cell3);
        table1.addCell(cell4);
        table1.addCell(cell5);
        table1.addCell(cell6);
        table1.addCell(cell7);
        table1.addCell(cell8);

        // Create information about university table
        PdfPTable table2 = new PdfPTable(4);
        table2.setSpacingBefore(15);
        table2.setHorizontalAlignment(Element.ALIGN_LEFT);

        // Create cells and add them to the table
        PdfPCell ucell1 = new PdfPCell();
        PdfPCell ucell2 = new PdfPCell();



        ucell1.addElement(new Chunk("Name of the host institution",font12));
        ucell2.addElement(new Chunk(hostName, font12));

        // Create cells and add them to the table
        PdfPCell icell1 = new PdfPCell();
        PdfPCell icell2 = new PdfPCell();
        PdfPCell icell3 = new PdfPCell();
        PdfPCell icell4 = new PdfPCell();

        icell1.addElement(new Chunk("Academic Year",font12));
        icell2.addElement(new Chunk(academicYear, font12));
        icell3.addElement(new Chunk("Semester", font12));
        icell4.addElement(new Chunk(semester, font12));

        ucell1.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT ); // Arrange borders
        ucell2.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.LEFT );

        table2.addCell(ucell1);
        table2.addCell(ucell2);
        table2.addCell(icell1);
        table2.addCell(icell2);
        table2.addCell(emptyCell);
        table2.addCell(emptyCell);
        table2.addCell(icell3);
        table2.addCell(icell4);




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
        PdfPTable courses = new PdfPTable(7);
        courses.setHorizontalAlignment(Element.ALIGN_LEFT);

        courses.setWidths(new float [] {3, 10, 39, 7, 39, 6, 15});
        PdfPCell courses1 = new PdfPCell();
        PdfPCell courses2 = new PdfPCell();
        PdfPCell courses3 = new PdfPCell();
        PdfPCell courses4 = new PdfPCell();
        PdfPCell courses5 = new PdfPCell();
        PdfPCell courses6 = new PdfPCell();
        PdfPCell courses7 = new PdfPCell();

        courses1.addElement(new Chunk("", font8));
        courses2.addElement(new Chunk("Course Code", font8));
        courses3.addElement(new Chunk("Course Name",font8));
        courses4.addElement(new Chunk("Credits*",font8));
        courses5.addElement(new Chunk("Course Code and Name for a Required Course," +
                "Elective Group Name for an Elective Requirement", font8));
        courses6.addElement(new Chunk("Credits", font8));
        courses7.addElement(new Chunk("Elective Requirement Exemptions only: Course code(s) of " +
                "directly equivalent course(s), if any **",font8));
        courses.addCell(courses1);
        courses.addCell(courses2);
        courses.addCell(courses3);
        courses.addCell(courses4);
        courses.addCell(courses5);
        courses.addCell(courses6);
        courses.addCell(courses7);


        // Create courses that will be transferred
        int courseNumber  = 1; // will be replaced
        for(int i = 1; i <= courseNumber; i++){
            // Get information about the new course by using the


            String rowNumber = i + "";

            // New Courses
            PdfPCell newCourses1 = new PdfPCell();
            PdfPCell newCourses2 = new PdfPCell();
            PdfPCell newCourses3 = new PdfPCell();
            PdfPCell newCourses4 = new PdfPCell();
            PdfPCell newCourses5 = new PdfPCell();
            PdfPCell newCourses6 = new PdfPCell();
            PdfPCell newCourses7 = new PdfPCell();

            newCourses1.addElement(new Chunk(rowNumber, font12));
            newCourses2.addElement(new Chunk("INF4032", font12));
            newCourses3.addElement(new Chunk("Computer Networks",font12));
            newCourses4.addElement(new Chunk("3.0",font12));
            newCourses5.addElement(new Chunk("Technical Elective", font12));
            newCourses6.addElement(new Chunk("3", font12));
            newCourses7.addElement(new Chunk("CS421",font12));

            courses.addCell(newCourses1);
            courses.addCell(newCourses2);
            courses.addCell(newCourses3);
            courses.addCell(newCourses4);
            courses.addCell(newCourses5);
            courses.addCell(newCourses6);
            courses.addCell(newCourses7);
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
        document.add(table4);
        document.add(courses);
        document.add(tableApp);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);


        document.close(); // document is closes
    }
}
