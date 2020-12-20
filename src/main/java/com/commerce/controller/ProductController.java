package com.commerce.controller;

import com.commerce.model.Product;
import com.commerce.service.ProductService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class ProductController {

    private ProductService productService = new ProductService();

    @GetMapping("/product")
    public String getList(Model model, HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        model.addAttribute("products", productService.findAll());
        try {
            model.addAttribute("success", inputFlashMap.get("success"));
        } catch (Exception e) {
            model.addAttribute("success", "");
        }
        try {
            model.addAttribute("filename", inputFlashMap.get("filename"));
        } catch (Exception e) {
            model.addAttribute("filename", "");
        }

        return "products";
    }

//    @GetMapping(
//            value = "/product/{name}",
//            produces = "application/pdf"
//    )
//    public @ResponseBody
//    byte[] getImageWithMediaType(@PathVariable String name) throws IOException {
//        InputStream in = getClass()
//                .getResourceAsStream("/static/documents/" + name);
//        return IOUtils.toByteArray(in);
//    }

    @RequestMapping("/product/{name}")
    public void downloadPDFResource(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("name") String fileName) {
        String dataDirectory = request.getServletContext().getRealPath("/static/documents/");
        Path file = Paths.get(dataDirectory, fileName);
        System.out.println(file.getParent().toAbsolutePath().toString());
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Error :(((((((((((((((((((((((((((");
        }
    }

    @GetMapping("/product/add")
    public String getEntryForm(Model model) {
        Product entry = new Product();
        model.addAttribute("entry", entry);
        return "add_product";
    }


    private void addTableHeader(PdfPTable table) {
        Stream.of("Nume", "Stoc", "UM")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addChangeHeader(PdfPTable table) {
        Stream.of("Nume", "Stoc", "Data")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    @PostMapping("/product/add/submit")
    public String addEntry(@ModelAttribute Product form, Model model, RedirectAttributes redirectAttributes) {

        if (productService.save(form) != null) {
            redirectAttributes.addFlashAttribute("success", "Ai adaugat cu succes produsul: " + form.getName());
        } else {
            redirectAttributes.addFlashAttribute("success", "Eroare la verificarea datelor!");
        }

        return "redirect:/product";
    }

    @GetMapping("/product/delete/{id}")
    public String delEntry(Model model, @PathVariable String id, RedirectAttributes redirectAttributes) {
        Product entity = productService.findById((long) Integer.parseInt(id));
        if (productService.delete(entity)) {
            redirectAttributes.addFlashAttribute("success", "Ai sters cu succes produsul: " + entity.getName());
        } else {
            redirectAttributes.addFlashAttribute("success", "Atentie! Produsul pe care incerci sa il stergi nu exista");
        }

        return "redirect:/product";
    }
}