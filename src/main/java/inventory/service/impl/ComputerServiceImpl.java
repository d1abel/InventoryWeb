package inventory.service.impl;

import inventory.domain.dto.Computer;
import inventory.domain.entity.ComputerEntity;
import inventory.repository.ComputerRepository;
import inventory.service.ComputerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ComputerServiceImpl implements ComputerService {

    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public Collection<Computer> getAll() {
        Iterable<ComputerEntity> values = computerRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();

        return StreamSupport.stream(values.spliterator(), false)
                .map(computerEntity -> modelMapper.map(computerEntity, Computer.class))
                .collect(Collectors.toList());
    }

    @Override
    public Computer getById(int id) {
        ModelMapper modelMapper = new ModelMapper();
        ComputerEntity byId = computerRepository.findById(id);
        return modelMapper.map(byId, Computer.class);
    }
}
