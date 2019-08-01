package inventory.controller;

import inventory.component.config.ReadConfigurationFile;
import inventory.domain.dto.Computer;
import inventory.service.ComputerService;
import inventory.service.ParserService;
import inventory.service.PcUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

@Controller
public class TestController {

    private final ComputerService computerService;
    private final ParserService parserService;
    private final PcUserService pcUserService;
    private final ReadConfigurationFile configurationFile;
    //для сортировок, чисто по фану
    //хотя, я думаю, адекватные люди пишут это во фронте
    private Collection<Computer> comps;

    @Autowired
    public TestController(ComputerService computerService, ParserService parserService, PcUserService pcUserService, ReadConfigurationFile configurationFile) {
        this.parserService = parserService;
        this.computerService = computerService;
        this.pcUserService = pcUserService;
        this.configurationFile = configurationFile;
    }

    @GetMapping("/")
    public String testPage(Model model) {
        this.comps = computerService.getAll();
        model.addAttribute("computers", comps);
        return "index";
    }

    @PostMapping("/update")
    public String update() {
        parserService.updateDB();

        return "redirect:/";
    }

    @SneakyThrows
    @PostMapping("/update-by-file")
    public String updateByFile(@RequestBody MultipartFile file) {
        Path filepath = Paths.get(configurationFile.getSettings().get("reports.dir"), file.getOriginalFilename());
        file.transferTo(filepath);
        parserService.updateByFile(filepath.toFile());

        return "redirect:/";
    }

    @PostMapping("sort-by-username")
    public String sortByUsername() {


        return "redirect:/";
    }
}
