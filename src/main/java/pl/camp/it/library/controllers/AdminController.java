package pl.camp.it.library.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.library.model.Author;
import pl.camp.it.library.model.Book;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IBookService;
import pl.camp.it.library.services.IUserService;

@Controller
@RequestMapping(value = "/admin/utils")
public class AdminController {

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser () {
        User user = new User();
        user.setLogin("admin");
        String hashedPassword = DigestUtils.md5Hex("admin");
        user.setPassword(hashedPassword);

        userService.addUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/addBooks", method = RequestMethod.GET)
    public String addBooks() {
        Book book1 = new Book();
        book1.setIsbn("1987398G");
        book1.setTitle("Jadłonomia po polsku");
        book1.setCategory(Book.Category.COOKING);

        Author author1 = new Author();
        author1.setName("Marta");
        author1.setSurname("Dymek");

        book1.setAuthor(author1);

        Book book2 = new Book();
        book2.setIsbn("23498429GG");
        book2.setTitle("Smak wiecznej młodości. Jak zachować młodość i witalność w każdym wieku");
        book2.setCategory(Book.Category.COOKING);

        Author author2 = new Author();
        author2.setName("Agnieszka");
        author2.setSurname("Maciąg");

        book2.setAuthor(author2);

        Book book3 = new Book();
        book3.setIsbn("345345DD");
        book3.setTitle("Smak zdrowia");
        book3.setCategory(Book.Category.COOKING);

        book3.setAuthor(author2);

        Book book4 = new Book();
        book4.setIsbn("2346768SS");
        book4.setTitle("Chleb. Techniki wypieku, przepisy, wskazówki");
        book4.setCategory(Book.Category.BAKING);

        Author author4 = new Author();
        author4.setName("Jeffrey");
        author4.setSurname("Hamelman");

        book4.setAuthor(author4);

        Book book5 = new Book();
        book5.setIsbn("35645CC");
        book5.setTitle("Ciasta torty i inne desery");
        book5.setCategory(Book.Category.BAKING);

        Author author5 = new Author();
        author5.setName("Opracowanie");
        author5.setSurname("Zbiorowe");

        book5.setAuthor(author5);

        this.bookService.addBook(book1);
        this.bookService.addBook(book2);
        this.bookService.addBook(book3);
        this.bookService.addBook(book4);
        this.bookService.addBook(book5);

        return "redirect:/main";
    }
}
