package service;

import domain.utils.Factory;
import domain.entities.Computer;
import domain.entities.Message;
import domain.entities.Zone;
import exceptions.ServiceException;
import tcp.TcpClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ServiceZoneClient implements ServiceZone{

    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ServiceZoneClient(ExecutorService executorService, TcpClient tcpClient){
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<Boolean> addZone(String name, String theme, Integer capacity) {
        Zone zone =  new Zone(name, theme, capacity);
        Message response = tcpClient.sendAndReceive(new Message("add zone", Factory.zoneToLine(zone)));

        if (response.getHeader().equals("error")){
            throw new ServiceException("error adding zone");
        }

        return CompletableFuture.supplyAsync(
                () -> true,
                executorService
        );
    }

    @Override
    public Future<Boolean> removeZone(Integer ID) {
        Message response = tcpClient.sendAndReceive(new Message("remove zone", Integer.toString(ID)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error removing zone");
        return CompletableFuture.supplyAsync(
                () -> true,
                executorService
        );    }

    @Override
    public Future<Boolean> updateZone(Integer ID, String name, String theme, Integer capacity) {
        Zone zone = new Zone(name, theme, capacity);
        zone.setId(ID);
        Message response = tcpClient.sendAndReceive(new Message("update zone", Factory.zoneToString(zone)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error updating zone");
        return CompletableFuture.supplyAsync(
                ()->true,
                executorService
        );
    }

    @Override
    public Future<Iterable<Zone>> getAllZones() {
        Message response;
        response = tcpClient.sendAndReceive(new Message("list zones", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Zone> zones = Arrays.stream(tokens)
                .map(Factory::stringToZone)
                .collect(Collectors.toList());
        return CompletableFuture.supplyAsync(
                () -> zones,
                executorService
        );
    }

    @Override
    public Future<Boolean> addComputer(Integer zoneID, String os, String date) {
        Computer computer =  new Computer(zoneID, os, date);
        Message response = tcpClient.sendAndReceive(new Message("add computer", Factory.computerToLine(computer)));

        if (response.getHeader().equals("error")){
            throw new ServiceException("error adding computer");
        }

        return CompletableFuture.supplyAsync(
                () -> true,
                executorService
        );
    }

    @Override
    public Future<Boolean> removeComputer(Integer ID) {
        Message response = tcpClient.sendAndReceive(new Message("remove computer", Integer.toString(ID)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error removing computer");
        return CompletableFuture.supplyAsync(
                () -> true,
                executorService
        );
    }

    @Override
    public Future<Boolean> updateComputer(Integer ID, Integer zoneID, String os, String date) {
        Computer computer = new Computer(zoneID, os, date);
        computer.setId(ID);
        Message response = tcpClient.sendAndReceive(new Message("update computer", Factory.computerToString(computer)));
        if (response.getHeader().equals("error"))
            throw new ServiceException("error updating computer");
        return CompletableFuture.supplyAsync(
                ()->true,
                executorService
        );
    }

    @Override
    public Future<Iterable<Computer>> getAllComputers() {
        Message response;
        response = tcpClient.sendAndReceive(new Message("list computers", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Computer> computers = Arrays.stream(tokens)
                .map(Factory::stringToComputer)
                .collect(Collectors.toList());
        return CompletableFuture.supplyAsync(
                () -> computers,
                executorService
        );
    }

    @Override
    public Future<Zone> getZoneById(Integer ID) {
        Message response;
        response = tcpClient.sendAndReceive(new Message("get zone", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Zone> zones = Arrays.stream(tokens)
                .map(Factory::messageToZone)
                .collect(Collectors.toList());
        Optional<Zone> zone = zones.stream()
                .filter(p -> p.getId().equals(ID))
                .findAny();
        return CompletableFuture.supplyAsync(
                zone::get,
                executorService
        );
    }

    @Override
    public Future<Computer> getComputerById(Integer ID) {
        Message response;
        response = tcpClient.sendAndReceive(new Message("get computer", ""));
        String[] tokens = response.getBody().split(System.lineSeparator());
        List<Computer> computers = Arrays.stream(tokens)
                .map(Factory::messageToComputer)
                .collect(Collectors.toList());
        Optional<Computer> computer = computers.stream()
                .filter(p -> p.getId().equals(ID))
                .findAny();
        return CompletableFuture.supplyAsync(
                computer::get,
                executorService
        );
    }
}
