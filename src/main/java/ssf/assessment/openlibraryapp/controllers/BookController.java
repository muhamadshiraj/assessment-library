package ssf.assessment.openlibraryapp.controllers;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ssf.assessment.openlibraryapp.model.Book;
import ssf.assessment.openlibraryapp.services.DetailService;

@Controller
@RequestMapping(path = "/book", produces=MediaType.TEXT_HTML_VALUE)
public class BookController {

    
    private final Logger logger = Logger.getLogger(BookController.class.getName());
    @Autowired
    DetailService detailService;


    @GetMapping(path ="{worksId}" )
    public String getDetails(@PathVariable("worksId")String worksId, Model model){
        
        Book book = detailService.getBook(worksId);
        model.addAttribute("worksId", worksId);
        model.addAttribute("book", book);

        return "bookview";

    }
}
