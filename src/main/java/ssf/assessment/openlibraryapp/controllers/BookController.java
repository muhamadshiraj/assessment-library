package ssf.assessment.openlibraryapp.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ssf.assessment.openlibraryapp.services.BookService;

@Controller
@RequestMapping(path = "/book", produces=MediaType.TEXT_HTML_VALUE)
public class BookController {
    
    private final Logger logger = Logger.getLogger(BookController.class.getName());
    @Autowired
    BookService bookService;

    @GetMapping("/{worksId}")
    public String showBookView(@PathVariable String worksId, Model model){
        List<String> bookJson
}
