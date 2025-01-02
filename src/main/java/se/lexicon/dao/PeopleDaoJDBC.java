package se.lexicon.dao;

import se.lexicon.model.Person;
import se.lexicon.sequencers.PersonIdSequencer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static se.lexicon.db.MySQLConnection.getConnection;


public class PeopleDaoJDBC implements PeopleDao {
    private Connection connection;

    @Override
    public Person create(Person person) {
        String sql = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        try (
                Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getInt(1));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;

    }

    @Override
    public Collection<Person> findAll() {
        String sql = "SELECT * FROM person";
        Collection<Person> people = new ArrayList<>();
        try (
                Connection connection = getConnection();
                Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
            people.add(new Person(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name")));
            }
        } catch (SQLException e) { e.printStackTrace();
        }
        return people;
    }

    @Override
    public Person findById(int id) {
        String sql = "SELECT * FROM person WHERE person_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Person(
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"));
                }
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {
        String sql = "SELECT * FROM person WHERE first_name LIKE ? OR last_name LIKE ?";
        Collection<Person> people = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    people.add(new Person(
                            rs.getInt("person_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Person update(Person person) {
        String sql = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setInt(3, person.getId());
            stmt.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM person WHERE person_id = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
