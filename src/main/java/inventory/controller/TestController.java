package inventory.controller;

import inventory.component.DatabaseParser;
import inventory.repository.ComputerRepository;
import inventory.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {

    private final DatabaseParser databaseParser;
    private final ComputerService computerService;

    @Autowired
    public TestController(DatabaseParser databaseParser, ComputerService computerService) {
        this.databaseParser = databaseParser;
        this.computerService = computerService;
    }

    @GetMapping("/test")
    public String testPage(Model model) {
        model.addAttribute("computers", computerService.getAll());
        return "test";
    }

    @PostMapping("/update")
    public String update() {
        databaseParser.updateDB();
        return "redirect:/test";
    }


}
