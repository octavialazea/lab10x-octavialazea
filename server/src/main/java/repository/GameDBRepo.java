package repository;

import domain.entities.Game;
import domain.entities.Player;
import exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import storage.Repository;

import java.util.Optional;

public class GameDBRepo implements Repository<Integer, Game> {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Game> findOne(Integer integer) {
        String sql = "select * from game where id=" + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String company = rs.getString("company");
            Integer price = rs.getInt("price");
            Double rating = rs.getDouble("rating");
            Integer bestPlayerId = rs.getInt("bestplayerid");
            Game g = new Game(name, company, price, rating, bestPlayerId);
            g.setId(id);
            return g;
        }).stream().findFirst();
    }

    @Override
    public Iterable<Game> findAll() {
        String sql = "select * from Game";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String company = rs.getString("company");
            Integer price = rs.getInt("price");
            Double rating = rs.getDouble("rating");
            Integer bestPlayerId = rs.getInt("bestplayerid");
            Game g = new Game(name, company, price, rating, bestPlayerId);
            g.setId(id);
            return g;
        });
    }

    @Override
    public void save(Game entity) throws ValidatorException {
        String sql = "insert into game (id, name, company, price, rating, bestplayerid) values (?, ?, ?, ? , ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getName(), entity.getCompany(), entity.getPrice(), entity.getRating(), entity.getPlayerID());
        // return Optional.of(entity);
    }

    @Override
    public void delete(Integer integer) {
        String sql = "delete from game where id=?";
        jdbcOperations.update(sql, integer);
        //return findOne(integer);
    }

    @Override
    public void update(Game entity) throws ValidatorException {
        String sql = "update game set name=?, company=?, price=?, rating=?, bestplayerid=? where id=?";
        jdbcOperations.update(sql, entity.getName(), entity.getCompany(), entity.getPrice(), entity.getRating(), entity.getPlayerID(), entity.getId());
        //return Optional.of(entity);
    }
}