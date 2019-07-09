package inventory.service;

import inventory.domain.dto.Computer;

import java.util.Collection;

public interface ComputerService {

    Collection<Computer> getAll();

}
