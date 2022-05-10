package repository;


import domain.entities.Computer;
import domain.entities.Player;
import exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import storage.Repository;

import java.util.Optional;

public class ComputerDBRepo implements Repository<Integer, Computer> {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Computer> findOne(Integer integer) {
        String sql = "select * from player where id=" + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            Integer zoneId = rs.getInt("zoneId");
            String os = rs.getString("operatingSystem");
            String date = rs.getString("purchaseDate");
            Computer c = new Computer(zoneId, os, date);
            c.setId(id);
            return c;
        }).stream().findFirst();
    }

    @Override
    public Iterable<Computer> findAll() {
        String sql = "select * from Computer";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            Integer zoneId = rs.getInt("zoneId");
            String os = rs.getString("operatingSystem");
            String date = rs.getString("purchaseDate");
            Computer c = new Computer(zoneId, os, date);
            c.setId(id);
            return c;
        });
    }

    @Override
    public void save(Computer entity) throws ValidatorException {
        String sql = "insert into computer (id, zoneId, operatingSystem, purchaseDate) values (?, ?, ?, ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getZoneID(), entity.getOperatingSystem(), entity.getPurchaseDate());
        //return Optional.of(entity);
    }

    @Override
    public void delete(Integer integer) {
        String sql = "delete from computer where id=?";
        jdbcOperations.update(sql, integer);
        //return findOne(integer);
    }

    @Override
    public void update(Computer entity) throws ValidatorException {
        String sql = "update computer set zoneId=?, operatingSystem=?, purchaseDate=? where id=?";
        jdbcOperations.update(sql, entity.getZoneID(), entity.getOperatingSystem(), entity.getPurchaseDate(), entity.getId());
        //return Optional.of(entity);
    }

}
