package ru.alishov.springproject.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishov.springproject.model.Book;
import ru.alishov.springproject.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO
{
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate)
    { this.jdbcTemplate = jdbcTemplate; }

    public List<Book> index()
    { return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class)); }

    public Book show(int id)
    { return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
            new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null); }

    public void save(Book book)
    { jdbcTemplate.update("INSERT INTO Book(book_name, author_name, year_of_issue) VALUES (?, ?, ?)",
            book.getBookName(), book.getAuthorName(), book.getYearOfIssue()); }

    public void update(int id, Book updatedBook)
    { jdbcTemplate.update("UPDATE Book SET full_name=?, author_name=?, year_of_issue=? WHERE id=?",
            updatedBook.getBookName(), updatedBook.getAuthorName(), updatedBook.getYearOfIssue(), id); }

    public void delete(int id)
    { jdbcTemplate.update("DELETE FROM Book WHERE id=?", id); }

    public Optional<Person> getBookOwner(int id)
    { return jdbcTemplate.query("SELECT Person.* FROM Book Join Person On Book.person_id = Person.id " +
            "WHERE Book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny(); }

    public void release(int id)
    { jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id); }

    public void assign(int id, Person selectedPerson)
    { jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id); }
}
