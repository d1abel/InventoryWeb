package inventory.controller;

import inventory.component.DatabaseParser;
import inventory.component.FileParser;
import inventory.domain.entity.ComputerEntity;
import inventory.service.ComputerService;
import inventory.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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

    @ResponseBody
    @PostMapping("/update-by-file")
    public void updateByFile(@RequestBody MultipartFile file) {
        ComputerEntity computerEntity = parserService.readReport((File) file);
        parserService.updateComputer(computerEntity);
    }


}
