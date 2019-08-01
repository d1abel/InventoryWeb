package inventory.component.fileToObject;

import inventory.domain.entity.ComputerEntity;
import inventory.domain.entity.ProcessorEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class Processor implements ComputerParametersService {

    private Collection<ProcessorEntity> processors = new ArrayList<>();

    @Override
    public void readRow(ComputerEntity pc, String row) {
        ProcessorEntity processorEntity = null;
        for (ProcessorEntity processor : processors) {
            if (row.equalsIgnoreCase(processor.getProcname())) {
                processorEntity = processor;
                pc.setProcessor(processor);
            }
        }
        if (processorEntity == null) {
            processorEntity = new ProcessorEntity(row);
            processors.add(processorEntity);
            pc.setProcessor(processorEntity);
        }
        processorEntity.getPcs().add(pc);
    }
}
