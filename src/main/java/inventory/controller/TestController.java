package inventory.controller;

import inventory.component.config.InvConfig;
import inventory.domain.entity.ComputerEntity;
import inventory.service.ComputerService;
import inventory.service.ParserService;
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

@Controller
public class TestController {

    private final ComputerService computerService;
    private final ParserService parserService;

    @Autowired
    public TestController(ComputerService computerService, ParserService parserService) {
        this.parserService = parserService;
        this.computerService = computerService;
    }

    @GetMapping("/")
    public String testPage(Model model) {
        model.addAttribute("computers", computerService.getAll());
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
        Path filepath = Paths.get(InvConfig.getInstance().getConfig().getSettings().get("reports.dir"), file.getOriginalFilename());
        file.transferTo(filepath);
        ComputerEntity computerEntity = parserService.readReport(filepath.toFile());
        parserService.updateComputer(computerEntity);

        return "redirect:/";
    }
}
