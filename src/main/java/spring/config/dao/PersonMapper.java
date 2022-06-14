package spring.config.dao;

import org.springframework.jdbc.core.RowMapper;
import spring.config.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("person_id"));
        person.setFullName(resultSet.getString("full_name"));
        person.setYearOfBirth(resultSet.getInt("year_of_birth"));
        return person;
    }
}
