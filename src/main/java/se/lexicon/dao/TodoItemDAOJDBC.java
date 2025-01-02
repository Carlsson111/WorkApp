package se.lexicon.dao;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static se.lexicon.db.MySQLConnection.getConnection;

public class TodoItemDAOJDBC implements TodoItemDAO{
    private PeopleDao peopleDao;
    private Connection connection;

    public TodoItemDAOJDBC(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    public TodoItem create(TodoItem todo) {
        String query = "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        )
        {
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getDescription());
            statement.setDate(3, Date.valueOf(todo.getDeadLine()));
            statement.setBoolean(4, todo.isDone());
            if (todo.getAssignee() != null) {
                statement.setInt(5, todo.getAssignee().getId());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    todo.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return todo;
    }

    @Override
    public Collection<TodoItem> findAll() {
        String query = "SELECT * FROM todo_item";
        Collection<TodoItem> todos = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet rs = statement.executeQuery())
                 {
            while (rs.next()) {
                Person assignee = null;
                int assigneeId = rs.getInt("assignee_id");
                if (assigneeId != 0) {
                    assignee = peopleDao.findById(assigneeId); //Test
                }
                todos.add(new TodoItem( rs.getInt("todo_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline").toLocalDate(),
                        rs.getBoolean("done"),
                        assignee
        ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return todos;
    }



    @Override
    public TodoItem findById(int id) {
        String query = "SELECT * FROM todo_item WHERE id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Person assignee = null;
                    int assigneeId = resultSet.getInt("assignee_id");
                    if (assigneeId != 0) {
                        assignee = peopleDao.findById(assigneeId);
                    }
                    return new TodoItem(
                            resultSet.getInt("Id"),
                            resultSet.getString("Title"),
                            resultSet.getString("Description"),
                            resultSet.getDate("deadline").toLocalDate(),
                            resultSet.getBoolean("done"),
                            assignee
                    );
                }
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean done) {
        String query = "SELECT * FROM todo_item WHERE done = ?";
        Collection<TodoItem> items = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setBoolean(1, done);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Person assignee = null;
                    int assigneeId = resultSet.getInt("assignee_id");
                    if (assigneeId != 0) {
                        assignee = peopleDao.findById(assigneeId);
                    }
                    items.add(new TodoItem(
                            resultSet.getInt("id"),
                            resultSet.getString("Title"),
                            resultSet.getString("Description"),
                            resultSet.getDate("deadline").toLocalDate(),
                            resultSet.getBoolean("done"),
                            assignee
                    ));
                }
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int personId) {
        String query = "SELECT * FROM todo_item WHERE assignee_id = ?";
        Collection<TodoItem> items = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, personId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Person assignee = peopleDao.findById(personId); //peopledao
                    items.add(new TodoItem(
                            resultSet.getInt("id"),
                            resultSet.getString("Title"),
                            resultSet.getString("Description"),
                            resultSet.getDate("deadline").toLocalDate(),
                            resultSet.getBoolean("done"),
                            assignee
                    ));

                }
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return items;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person assignee) {
        return findByAssignee(assignee.getId());
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoItems() {
        String query = "SELECT * FROM todo_item WHERE assignee_id IS NULL";
        Collection<TodoItem> items = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next()) {
                items.add(new TodoItem(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        null
                ));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return items;
    }

    @Override
    public TodoItem update(TodoItem todo) {
        String query = "UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getDescription());
            statement.setDate(3, Date.valueOf(todo.getDeadLine()));
            statement.setBoolean(4, todo.isDone());
            if (todo.getAssignee() != null) {
                statement.setInt(5, todo.getAssignee().getId());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.setInt(6, todo.getId());
            statement.executeUpdate();
            }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return todo;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM todo_item WHERE id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1,id);
            return statement.executeUpdate() >0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
