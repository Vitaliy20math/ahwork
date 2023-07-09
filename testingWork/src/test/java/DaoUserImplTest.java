import org.example.exceptions.UserIsNotException;
import org.example.model.Car;
import org.example.model.User;
import org.example.service.ServiceUser;
import org.example.service.ServiceUserImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DaoUserImplTest {

    ServiceUser serviceUser = new ServiceUserImpl();
    @Test
    public void get_All() {
        List<User> list = serviceUser.getUsers();

        assertEquals(4, list.size());
    }

    @Test
    public void get_ValidId() {
        int id = 1;

        User user = serviceUser.findUserById(id);

        assertEquals("Иванов", user.getUsername());
    }
    @Test
    public void valid_Create() {
        String name = "A";
        String surname = "B";
        int salary = 100000;
        String stuff ="manager";
        int login = 5;

        Car car = new Car("Lada");

        List<Car> list = new ArrayList<>();
        list.add(car);

        User user = new User(name, surname, salary, stuff, list, login);

        assertEquals("A", user.getUsername());
        assertEquals("B", user.getSurname());
        assertEquals(salary, user.getSalary());
        assertEquals(login, user.getLogin());


    }


    @Test
    public void get_ValidLogin() {
        int login = 1;

        User user = serviceUser.findUserById(1);

        assertEquals(1, user.getLogin());
    }
    @Test
    public void delete_ValidId() {
        serviceUser.deleteUser(serviceUser.findUserById(3));

        assertEquals(3, serviceUser.getUsers().size());
    }
    @Test
    public void findUserById_NotValidId() {
        User user = serviceUser.findUserById(5);
        assertNull(user);
    }


}
