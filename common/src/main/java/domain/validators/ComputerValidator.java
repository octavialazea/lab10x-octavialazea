package domain.validators;

import domain.entities.Computer;
import exceptions.ValidatorException;

public class ComputerValidator implements Validator<Computer>{
    @Override
    public void validate(Computer entity) throws ValidatorException {
        if (entity.getZoneID() <= 0)
            throw new ValidatorException("Zone id must be greater than 0");

        if (entity.getOperatingSystem().isEmpty())
            throw new ValidatorException("Operating system must be defined");

        if (entity.getPurchaseDate().isEmpty())
            throw new ValidatorException("Purchase date must be defined");
    }
}