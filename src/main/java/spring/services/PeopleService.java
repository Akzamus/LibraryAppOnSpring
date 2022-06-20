package spring.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.models.Person;
import spring.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        Person person = peopleRepository.findById(id).orElse(null);
        if(person != null)
            Hibernate.initialize(person.getBooks());
        return person;
    }

    @Transactional
    public void save(Person newPerson) {
        peopleRepository.save(newPerson);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public Optional<Person> checkByFullName(String fullName){
        return peopleRepository.findPersonByFullName(fullName);
    }
}
