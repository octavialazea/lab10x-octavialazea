package service;

import domain.entities.Computer;
import domain.entities.Game;
import domain.entities.Player;
import domain.entities.Zone;
import exceptions.ServiceException;
import storage.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServiceZoneImpl implements ServiceZone{

    private Repository<Integer, Zone> zones;
    private Repository<Integer, Computer> computers;
    private ExecutorService executorService;

    public ServiceZoneImpl(Repository<Integer, Zone> zones, Repository<Integer, Computer> computers, ExecutorService executorService){
        this.zones = zones;
        this.computers = computers;
        this.executorService = executorService;
    }


    @Override
    public Future<Boolean> addZone(String name, String theme, Integer capacity) {
       Zone zone = new Zone(name, theme, capacity);
       zone.generateID();
       while(zones.findOne(zone.getId()).isPresent())
           zone.generateID();
        return executorService.submit(() -> {
            zones.save(zone); return true;
        });
    }

    @Override
    public Future<Boolean> removeZone(Integer ID) {
        return executorService.submit(() -> {
            try{
                zones.findOne(ID).orElseThrow(() -> new ServiceException("Zone is not in the database"));
                zones.delete(ID);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Boolean> updateZone(Integer ID, String name, String theme, Integer capacity) {
        return executorService.submit( () -> {
            try{
                Zone maybe = zones.findOne(ID).orElseThrow(() -> new ServiceException("Player is not in the database"));
                maybe.setName(name);
                maybe.setTheme(theme);
                maybe.setCapacity(capacity);
                zones.update(maybe);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Iterable<Zone>> getAllZones() {
        return executorService.submit(
                () -> {
                    try {
                        return this.zones.findAll();
                    } catch (Exception e) {
                        throw new ServiceException(e.getMessage());
                    }
                });
    }

    @Override
    public Future<Boolean> addComputer(Integer zoneID, String os, String date) {
        zones.findOne(zoneID).orElseThrow(()-> new ServiceException("Given zone is not in the database"));
        Computer attempt = new Computer(zoneID, os, date);
        computers.findAll().forEach(game -> {
            if (game.equals(attempt))
                throw new ServiceException("Computer already stored");
        });
        attempt.generateID();
        while(computers.findOne(attempt.getId()).isPresent())
            attempt.generateID();
        return executorService.submit( () -> {
            try{
                computers.save(attempt);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Boolean> removeComputer(Integer ID) {
        computers.findOne(ID).orElseThrow(()-> new ServiceException("Computer is not in the database"));
        return executorService.submit( () -> {
            try{
                computers.delete(ID);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Boolean> updateComputer(Integer ID, Integer zoneID, String os, String date) {
        return executorService.submit(() -> {
            try{
                Computer maybe = computers.findOne(ID).orElseThrow(()-> new ServiceException("Game is not in the database"));
                zones.findOne(zoneID).orElseThrow(()-> new ServiceException("Given zone is not in the database"));
                maybe.setZoneID(zoneID);
                maybe.setOperatingSystem(os);
                maybe.setPurchaseDate(date);
                computers.update(maybe);
                return true;
            }catch (Exception e){
                return false;
            }
        });
    }

    @Override
    public Future<Iterable<Computer>> getAllComputers() {
        return executorService.submit(
                () -> {
                    try {
                        return this.computers.findAll();
                    } catch (Exception e) {
                        throw new ServiceException(e.getMessage());
                    }
                });
    }

    @Override
    public Future<Zone> getZoneById(Integer ID) {
        return executorService.submit(() -> {
            try {
                return zones.findOne(ID).orElse(null);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    @Override
    public Future<Computer> getComputerById(Integer ID) {
        return executorService.submit(() -> {
            try {
                return computers.findOne(ID).orElse(null);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}
