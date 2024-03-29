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
public class PersonDAO
{
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate)
    { this.jdbcTemplate = jdbcTemplate; }

    public List<Person> index()
    { return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)); }

    public Person show(int id)
    { return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
            new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null); }

    public void save(Person person)
    { jdbcTemplate.update("INSERT INTO Person(full_name, age) VALUES (?, ?)",
            person.getFullName(), person.getAge()); }

    public void update(int id, Person updatedPerson)
    { jdbcTemplate.update("UPDATE Person SET full_name=?, age=? WHERE id=?",
            updatedPerson.getFullName(), updatedPerson.getAge(), id); }

    public void delete(int id)
    { jdbcTemplate.update("DELETE FROM Person WHERE id=?", id); }

    public Optional<Person> getPersonByFullName(String fullName)
    { return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[] {fullName},
            new BeanPropertyRowMapper<>(Person.class)).stream().findAny(); }

    public List<Book> getBooksByPersonId(int id)
    { return jdbcTemplate.query("SELECT * FROM Book Where person_id=?", new Object[]{id},
            new BeanPropertyRowMapper<>(Book.class)); }
}
