package com.example.meihanred.quickresume;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class FileOperations {
    public String path= "/mnt/sdcard/QuickResume/";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    public FileOperations() {
    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public Boolean write(Resume resume) {
        try {
            Log.e("","experience name is " + resume.getExperiencetitle().get(0));

            String name = resume.getName();
            int age = resume.getAge();
            String career = resume.getCareer();
            String degree = resume.getDegree();
            String email = resume.getEmail();
            String phone = resume.getPhone();
            int style = resume.getStyle();

            String fpath = path + resume.getFilename() + ".pdf";
            File file = new File(fpath);
            // If file does not exists, then create it

            if (!file.exists()) {

                file.createNewFile();
                Log.d("File created", "File created");
            }
            Rectangle rect = new Rectangle(PageSize.A4);   // size of page
            //rect.setBackgroundColor(BaseColor.ORANGE);      // background color
            Document document = new Document();
            PdfWriter writer;


//            document.addTitle("myResume");
//            document.addAuthor("Han Mei");
//            document.addSubject("myRusume");
//            document.addKeywords("myResume");
//            document.addCreator("Han Mei");




            if(style==0) {

                //  document.setMargins(10, 10, 10, 10);    // set Margins
//            writer.setEncryption("android".getBytes(), "android".getBytes(),    //set password
//                    PdfWriter.ALLOW_SCREENREADERS,
//                    PdfWriter.STANDARD_ENCRYPTION_128);


                // Lets write a big header
                Rectangle rect1 = new Rectangle(PageSize.A4);   // size of page

                rect.setBackgroundColor(BaseColor.CYAN);      // background color

                document = new Document(rect);
                writer = PdfWriter.getInstance(document, new FileOutputStream(fpath));
                writer.setPdfVersion(PdfWriter.PDF_VERSION_1_4);  //default is 1.4
                document.open();
                Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 23, Font.BOLD);
                Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD);
                Paragraph presumetitle = new Paragraph("Resume",f1);
                presumetitle.setAlignment(Element.ALIGN_CENTER);
                document.add(presumetitle);
                Paragraph empty = new Paragraph();
                addEmptyLine(empty, 1);
                document.add(empty);
                Chunk pnamecontent = new Chunk(name);
                Chunk pnametitle = new Chunk("Name:   ",f2);
                Phrase combnamephrase = new Phrase();
                combnamephrase.add(pnametitle);
                combnamephrase.add(pnamecontent);
                document.add(new Paragraph(combnamephrase));
                empty = new Paragraph();
                addEmptyLine(empty, 1);
                document.add(empty);

                Chunk pagecontent = new Chunk(""+age);
                Chunk pagetitle = new Chunk("Age:   ",f2);
                Phrase combagephrase = new Phrase();
                combagephrase.add(pagetitle);
                combagephrase.add(pagecontent);
                document.add(new Paragraph(combagephrase));
                empty = new Paragraph();
                addEmptyLine(empty, 1);
                document.add(empty);

                Chunk pcareercontent = new Chunk(career);
                Chunk pcareertitle = new Chunk("Job Objective:   ",f2);
                Phrase combcareerphrase = new Phrase();
                combcareerphrase.add(pcareertitle);
                combcareerphrase.add(pcareercontent);
                document.add(new Paragraph(combcareerphrase));
                empty = new Paragraph();
                addEmptyLine(empty, 1);
                document.add(empty);


                Chunk pdegreecontent = new Chunk(degree);
                Chunk pdegreetitle = new Chunk("Degree:   ",f2);
                Phrase combdegreephrase = new Phrase();
                combdegreephrase.add(pdegreetitle);
                combdegreephrase.add(pdegreecontent);
                document.add(new Paragraph(combdegreephrase));
                empty = new Paragraph();
                addEmptyLine(empty, 1);
                document.add(empty);


                Chunk pemailcontent = new Chunk(degree);
                Chunk pemailtitle = new Chunk("Email:   ",f2);
                Phrase combemailphrase = new Phrase();
                combemailphrase.add(pemailtitle);
                combemailphrase.add(pemailcontent);
                document.add(new Paragraph(combemailphrase));
                empty = new Paragraph();
                addEmptyLine(empty, 1);
                document.add(empty);


                Chunk pphonecontent = new Chunk(phone);
                Chunk pphonetitle = new Chunk("Phone:   ",f2);
                Phrase combphonephrase = new Phrase();
                combphonephrase.add(pphonetitle);
                combphonephrase.add(pphonecontent);
                document.add(new Paragraph(combphonephrase));
                empty = new Paragraph();
                addEmptyLine(empty, 2);
                document.add(empty);




                int elength = resume.getExperiencetitle().size();
                int edulength = resume.getEducationtitle().size();
                int i;
                document.add(new Paragraph("Education:   ", f2));
                for(i=0;i<edulength;i++){
                    document.add(new Paragraph(resume.getEducationtitle().get(i)));
                    document.add(new Paragraph(resume.getEducationdetail().get(i)));
                    empty = new Paragraph();
                    addEmptyLine(empty, 1);
                    document.add(empty);

                }

                empty = new Paragraph();
                addEmptyLine(empty, 2);
                document.add(empty);

                document.add(new Paragraph("Experience:   ", f2));

                for(i=0;i<elength;i++){
                    document.add(new Paragraph(resume.getExperiencetitle().get(i)));
                    document.add(new Paragraph(resume.getExperiencedetail().get(i)));
                    empty = new Paragraph();
                    addEmptyLine(empty, 1);
                    document.add(empty);
                }



            }

            if(style==1) {

                //  document.setMargins(10, 10, 10, 10);    // set Margins
//            writer.setEncryption("android".getBytes(), "android".getBytes(),    //set password
//                    PdfWriter.ALLOW_SCREENREADERS,
//                    PdfWriter.STANDARD_ENCRYPTION_128);


                // Lets write a big header
                Rectangle rect1 = new Rectangle(PageSize.A4);   // size of page

                rect.setBackgroundColor(BaseColor.ORANGE);      // background color

                document = new Document(rect);
                writer = PdfWriter.getInstance(document, new FileOutputStream(fpath));
                writer.setPdfVersion(PdfWriter.PDF_VERSION_1_4);  //default is 1.4
                document.open();
                Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD);
                Paragraph pname = new Paragraph(name, f1);
                pname.setAlignment(Element.ALIGN_CENTER);
                document.add(pname);
                addEmptyLine(new Paragraph(), 2);
                document.add(new Paragraph("age:   " + age));
                addEmptyLine(new Paragraph(), 2);
                document.add(new Paragraph("Career Objective:   " + career));
                document.add(new Paragraph("Degree:   " + degree));
                addEmptyLine(new Paragraph(), 2);
                document.add(new Paragraph("Email:   " + email));
                addEmptyLine(new Paragraph(), 2);
                document.add(new Paragraph("Phone:   " + phone));
                addEmptyLine(new Paragraph(), 2);

                int elength = resume.getExperiencedetail().size();
                int edulength = resume.getEducationtitle().size();
                int i;
                document.add(new Paragraph("Education:   "));
                for(i=0;i<edulength;i++){
                    document.add(new Paragraph(resume.getEducationtitle().get(i)));
                    document.add(new Paragraph(resume.getEducationdetail().get(i)));
                    addEmptyLine(new Paragraph(), 2);
                }
                document.add(new Paragraph("Experience:   "));

                for(i=0;i<elength;i++){
                    document.add(new Paragraph(resume.getExperiencetitle().get(i)));
                    document.add(new Paragraph(resume.getExperiencedetail().get(i)));
                    addEmptyLine(new Paragraph(), 2);
                }
            }





//
//            document.add(new VerticalPositionMark() {
//
//                public void draw(PdfContentByte canvas, float llx, float lly,
//                                 float urx, float ury, float y) {
//                    canvas.beginText();
//                    BaseFont bf = null;
//                    try {
//                        bf = BaseFont.createFont(BaseFont.ZAPFDINGBATS, "", BaseFont.EMBEDDED);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    canvas.setFontAndSize(bf, 12);
//
//                    // LEFT
//                    canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), llx - 10, y, 0);
//                    // RIGHT
//                    canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), urx + 10, y + 8, 180);
//
//                    canvas.endText();
//                }
//            });
//            //Underline
//            String para2 = "This is my second paragraph";
//            Paragraph p2 = new Paragraph(para2);
//
//            LineSeparator UNDERLINE = new LineSeparator(1, para2.length()+3, null, Element.ALIGN_LEFT, -2);
//
//            p2.add(UNDERLINE);
//            document.add(p2);
//
//            document.add(new Paragraph("Blow here is an image which is rotated PI/3 radians "));
//            //add pic
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            //Bitmap bitmap = BitmapFactory.decodeResource(ITextActivity.getActivity().getResources(), R.drawable.otsoe);
//            Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/Download/nyu-icon.jpeg");
//            bitmap.compress(Bitmap.CompressFormat.JPEG /* FileType */,100 /* Ratio */, stream);
//            Image jpg = Image.getInstance(stream.toByteArray());
//            jpg.setAlignment(Image.MIDDLE);
//            jpg.setRotation((float)Math.PI/3);
//            document.add(jpg);
//            document.add(new Paragraph("New page to be followed", redFont));
//
//            // add new page
//            document.newPage();
//            document.add(new Paragraph("Hello, welcome to page 2"));
//            Anchor anchor = new Anchor("First Chapter", catFont);
//            anchor.setName("First Chapter");
//            //chapter 1
//            Chapter chapter = new Chapter(new Paragraph(anchor), 1);
//            Paragraph subPara = new Paragraph("Subcategory 1", subFont);
//            //subcat 1
//            Section subcat = chapter.addSection(subPara);
//            subcat.add(new Paragraph("Hello"));
//            document.add(subcat);
//            // subcat 2
//            subPara = new Paragraph("Subcategory 2", subFont);
//            subcat = chapter.addSection(subPara);
//            subcat.add(new Paragraph("Paragraph 1"));
//            subcat.add(new Paragraph("Paragraph 2"));
//            subcat.add(new Paragraph("Paragraph 3"));
//            Paragraph paragraph = new Paragraph();
//            addEmptyLine(paragraph, 3);
//            subcat.add(paragraph);
//
//            //add a list
//            List list = new List(true,false, 10);
//            list.add(new ListItem("I am the first"));
//            list.add(new ListItem("I am the second"));
//            list.add(new ListItem("I am the third"));
//            subcat.add(list);
//            addEmptyLine(paragraph, 4);
//            subcat.add(paragraph);
//
//            //add a table
//            PdfPTable table = new PdfPTable(4);
//            PdfPCell pc = new PdfPCell(new Phrase("Table Header 1"));
//            pc.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(pc);
//
//            pc = new PdfPCell(new Phrase("Table Header 2"));
//            pc.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(pc);
//
//            pc = new PdfPCell(new Phrase("Table Header 3"));
//            pc.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(pc);
//
//            pc = new PdfPCell(new Phrase("Table Header 4"));
//            pc.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(pc);
//
//            table.setHeaderRows(1);
//            table.addCell("1.1");
//            table.addCell("1.2");
//            table.addCell("1.3");
//            table.addCell("1.4");
//            table.addCell("2.1");
//            table.addCell("2.2");
//            table.addCell("2.3");
//            table.addCell("2.4");
//            subcat.add(new Paragraph());
//            subcat.add(table);
//            document.add(subcat);
//            addEmptyLine(paragraph, 3);
//            document.add(paragraph);
//
//            //chapter2
//            Chapter chapter2 = new Chapter(new Paragraph(anchor), 2);
//            Paragraph subPara21 = new Paragraph("Subcategory 1", subFont);
//            Section subcat21 = chapter2.addSection(subPara21);
//            subcat21.add(new Paragraph("Hello, here is Chapter 2"));
//            document.add(subcat21);
//            //Barcode
//            String myString = "Hey, I am Han";
//            Barcode128 code128 = new Barcode128();
//            code128.setCode(myString.trim());
//            code128.setCodeType(Barcode128.CODE128);
//            PdfContentByte cb = writer.getDirectContent();
//            Image code128Image = code128.createImageWithBarcode(cb,null,null );
//            code128Image.setAbsolutePosition(10,200);
//            code128Image.scalePercent(125);
//            document.add(code128Image);
//            BarcodeQRCode qrcode = new BarcodeQRCode(myString.trim(), 1, 1, null);
//            Image qrcodeImage = qrcode.getImage();
//            qrcodeImage.setAbsolutePosition(10,300);
//            qrcodeImage.scalePercent(200);
//            document.add(qrcodeImage);
//
//
//
//
//
//
//            //Annotation
//            document.add(new Annotation("Title", "I am a annotation!"));








            Log.v("BBBBBBBBB","BBBBBBBBB");
            document.close();

            Log.d("Suceess", "Sucess");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }


}