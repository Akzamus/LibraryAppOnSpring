package spring.config.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.config.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES( ?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET full_name = ?,  year_of_birth = ? WHERE person_id = ?",
                updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);
    }

    public Optional<Person> show(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name = ?", new Object[] {fullName},
                        new PersonMapper()).stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?", new Object[]{id},
                        new PersonMapper()).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", id);
    }
}
