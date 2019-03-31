package pl.coderslab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    @Autowired
    private BookDao bookDao;

    @RequestMapping(path = "/addbook/{title}/{author}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String addBook(@PathVariable String title, @PathVariable String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookDao.saveBook(book);
        return "Id dodanej książki to:" + book.getId();
    }

    @RequestMapping(path = "/findbook/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String findBookById(@PathVariable long id){
        Book findedBook = bookDao.findById(id);
        return String.format("Książka o id %s to %S %s", id, findedBook.getTitle(), findedBook.getAuthor());
    }


    @RequestMapping(path = "/delbook/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String delBook(@PathVariable long id){
        bookDao.delete(id);
        return String.format("Rekord o id %s usunięty", id);
    }

    @RequestMapping(path = "/updateTitle/{id}/{newTitle}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String changeTitle(@PathVariable long id, @PathVariable String newTitle){

    Book book = bookDao.findById(id);
    book.setTitle(newTitle);
    bookDao.updateTitle(book);
    return String.format("Zmiana tytułu książki o id %s", id);
    }

    @RequestMapping(path = "/updateAuthor/{id}/{newAuthor}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String changeAuthor(@PathVariable long id, @PathVariable String newAuthor){

        Book book = bookDao.findById(id);
        book.setAuthor(newAuthor);
        bookDao.updateAuthor(book);
        return String.format("Zmiana autora książki o id %s", id);
    }
}


