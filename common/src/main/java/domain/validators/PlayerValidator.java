package domain.validators;

import domain.entities.Player;
import exceptions.ValidatorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PlayerValidator implements Validator<Player> {

    @Override
    public void validate(Player entity) throws ValidatorException {
        if (entity.getName().isEmpty() || entity.getDate().isEmpty() || entity.getEmail().isEmpty())
            throw new ValidatorException("Empty string");
        if (entity.getName().contains("1234567890!@#$%^&*("))
            throw new ValidatorException("That's not your real name");
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date.parse(entity.getDate());
        } catch (ParseException e) {
            throw new ValidatorException("Not a valid date format");
        }
    }
}
