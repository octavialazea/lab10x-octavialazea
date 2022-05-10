package repository;


import domain.entities.Player;
import domain.entities.Zone;
import exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import storage.Repository;

import java.util.Optional;

public class ZoneDBRepo implements Repository<Integer, Zone> {


    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Zone> findOne(Integer integer) {
        String sql = "select * from zone where id=" + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String theme = rs.getString("theme");
            Integer capacity = rs.getInt("capacity");
            Zone z = new Zone(name, theme, capacity);
            z.setId(id);
            return z;
        }).stream().findFirst();
    }

    @Override
    public Iterable<Zone> findAll() {
        String sql = "select * from zone";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            String theme = rs.getString("theme");
            Integer capacity = rs.getInt("capacity");
            Zone z = new Zone(name, theme, capacity);
            z.setId(id);
            return z;
        });
    }

    @Override
    public void save(Zone entity) throws ValidatorException {
        String sql = "insert into zone (id, name, theme, capacity) values (?, ?, ?, ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getName(), entity.getTheme(), entity.getCapacity());
        //return Optional.of(entity);
    }

    @Override
    public void delete(Integer integer) {
        String sql = "delete from zone where id=?";
        jdbcOperations.update(sql, integer);
        // return findOne(integer);
    }

    @Override
    public void update(Zone entity) throws ValidatorException {
        String sql = "update zone set name=?, theme=?, capacity=? where id=?";
        jdbcOperations.update(sql, entity.getName(), entity.getTheme(), entity.getCapacity(), entity.getId());
        //return Optional.of(entity);
    }
}
