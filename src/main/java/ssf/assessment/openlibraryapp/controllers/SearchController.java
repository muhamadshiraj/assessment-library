package ssf.assessment.openlibraryapp.controllers;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssf.assessment.openlibraryapp.model.Book;
import ssf.assessment.openlibraryapp.services.BookService;

@Controller
@RequestMapping(path = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    private final Logger logger = Logger.getLogger(SearchController.class.getName());

    @Autowired
    private BookService bookService;

    @PostMapping
    public String getBookResult(@RequestBody MultiValueMap<String, String> form, Model model) {
       String title = form.getFirst("title");

        List<Book> bookList = Collections.emptyList();
        

        try {
            bookList = bookService.search(title);
            logger.log(Level.INFO, ">>> booklist: %s".formatted(bookList));

        } catch (Exception ex) {
            logger.log(Level.WARNING, "Warning: %s".formatted(ex.getMessage()));
        }

        model.addAttribute("book", bookList);
        model.addAttribute("title", title);

        return "result";
    }
}
