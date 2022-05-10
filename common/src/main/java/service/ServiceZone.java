package service;

import domain.entities.Computer;
import domain.entities.Game;
import domain.entities.Player;
import domain.entities.Zone;
import storage.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface ServiceZone {

    Future<Boolean> addZone(String name, String theme, Integer capacity);
    Future<Boolean> removeZone(Integer ID);
    Future<Boolean> updateZone(Integer ID, String name, String theme, Integer capacity);
    Future<Iterable<Zone>> getAllZones();
    Future<Boolean> addComputer(Integer zoneID, String os, String date);
    Future<Boolean> removeComputer(Integer ID);
    Future<Boolean> updateComputer(Integer ID, Integer zoneID, String os, String date);
    Future<Iterable<Computer>> getAllComputers();
    Future<Zone> getZoneById(Integer ID);
    Future<Computer> getComputerById(Integer ID);
}
