package repository;

import domain.entities.Player;
import exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import storage.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

//import java.sql.*;


public class PlayerDBRepo implements Repository<Integer, Player> {

    @Autowired
    private JdbcTemplate jdbcOperations;

    public class CustomerRowMapper implements RowMapper<Player>{
        @Override
        public Player mapRow(ResultSet rs, int i) throws SQLException{
            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setName(rs.getString("name"));
            player.setDateOfBirth(rs.getString("dateofbirth"));
            player.setEmail(rs.getString("email"));
            return player;
        }
    }

    @Override
    public Optional<Player> findOne(Integer integer) {

        String sql = "select * from player where id=" + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String dob = rs.getString("dateofbirth");
            String email = rs.getString("email");
            Player p =  new Player(name, dob, email);
            p.setId(id);
            return p;
        }).stream().findFirst();
    }


    @Override
    public Iterable<Player> findAll() {
        String sql = "select * from player";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String dob = rs.getString("dateofbirth");
            String email = rs.getString("email");
            Player p =  new Player(name, dob, email);
            p.setId(id);
            return p;
        });
//        return jdbcOperations.query(sql, (rs, i) -> new Player(
//            rs.getString("name"),
//            rs.getString("dateofbirth"),
//            rs.getString("email")
//        ));
    }

    @Override
    public void save(Player entity) throws ValidatorException {
        String sql = "insert into player (id, name, dateofbirth, email) values (?, ?, ?, ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getName(), entity.getDate(), entity.getEmail());
//        return Optional.of(entity);
    }

    @Override
    public void delete(Integer integer) {
        String sql = "delete from player where id = ?";
        jdbcOperations.update(sql, integer);
//        return findOne(integer);
    }

    @Override
    public void update(Player entity) throws ValidatorException {
        String sql = "update player set name=?, dateofbirth=?, email=? where id=?";
        jdbcOperations.update(sql, entity.getName(), entity.getDate(), entity.getEmail(), entity.getId());
//        return Optional.of(entity);
    }

}
