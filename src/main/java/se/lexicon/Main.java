package se.lexicon;

import se.lexicon.dao.PeopleDao;
import se.lexicon.dao.PeopleDaoJDBC;
import se.lexicon.dao.TodoItemDAO;
import se.lexicon.dao.TodoItemDAOJDBC;
import se.lexicon.db.MySQLConnection;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Connection connection = MySQLConnection.getConnection();
        PeopleDao peopleDao = new PeopleDaoJDBC();
        TodoItemDAO todoItemDAO = new TodoItemDAOJDBC(peopleDao);

        // Test creating a Person
        Person person1 = new Person(0, "Fredrick", "Doe");
        person1 = peopleDao.create(person1);
        System.out.println("Created Person: " + person1);

        //Test finding all Persons
        Collection<Person> allPersons = peopleDao.findAll();
        System.out.println("All Persons: " + allPersons);

        // Test finding a Person by ID
        Person foundPerson = peopleDao.findById(person1.getId());
        System.out.println("Found Person by ID: " + foundPerson);

        // Test creating a TodoItem
        TodoItem todo1 = new TodoItem(0, "Buying Kebab", "Eaten Kebab", LocalDate.of(2025, 1, 15), false, person1);
        todo1 = todoItemDAO.create(todo1);
        System.out.println("Created TodoItem: " + todo1);

        // Test finding all TodoItems
        Collection<TodoItem> allTodos = todoItemDAO.findAll();
        System.out.println("All TodoItems: " + allTodos);

        // Test finding a TodoItem by ID
        TodoItem foundTodo = todoItemDAO.findById(todo1.getId());
        System.out.println("Found TodoItem by ID: " + foundTodo);

        // Test finding TodoItems by done status
        Collection<TodoItem> doneTodos = todoItemDAO.findByDoneStatus(false);
        System.out.println("TodoItems by Done Status: " + doneTodos);

        // Test finding TodoItems by assignee ID
        Collection<TodoItem> assignedTodos = todoItemDAO.findByAssignee(person1.getId());
        System.out.println("TodoItems by Assignee ID: " + assignedTodos);

        // Test finding TodoItems by assignee
        Collection<TodoItem> assignedTodosByPerson = todoItemDAO.findByAssignee(person1);
        System.out.println("TodoItems by Assignee: " + assignedTodosByPerson);

        // Test finding unassigned TodoItems
        Collection<TodoItem> unassignedTodos = todoItemDAO.findByUnassignedTodoItems();
        System.out.println("Unassigned TodoItems: " + unassignedTodos);

        // Test updating a TodoItem
        todo1.setTitle("Finish Report (Updated)");
        todo1 = todoItemDAO.update(todo1);
        System.out.println("Updated TodoItem: " + todo1);

        // Test deleting a TodoItem by ID
        boolean deleteResult = todoItemDAO.deleteById(todo1.getId());
        System.out.println("Deleted TodoItem: " + deleteResult);

    }
}