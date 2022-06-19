package spring.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring.models.Person;
import spring.services.PeopleService;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;
    private String oldFullName;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(peopleService.checkByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName", "", "A user with this full name already exists");
        }
    }

    public String getOldFullName() {
        return oldFullName;
    }

    public void setOldFullName(String oldFullName) {
        this.oldFullName = oldFullName;
    }
}
