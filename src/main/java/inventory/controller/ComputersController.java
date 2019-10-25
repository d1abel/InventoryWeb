package inventory.controller;

import inventory.domain.dto.Computer;
import inventory.service.ComputerService;
import inventory.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/computers")
public class ComputersController {

    private final ComputerService computerService;
    private final ParserService parserService;
    private Collection<Computer> comps;

    @Autowired
    public ComputersController(ComputerService computerService, ParserService parserService) {
        this.computerService = computerService;
        this.parserService = parserService;
    }

    @GetMapping
    public String computersPage(Model model) {
        this.comps = computerService.getAll();
        model.addAttribute("computers", comps);
        return "computers";
    }

    @PostMapping("/update")
    public String update() {
        parserService.updateDB();

        return "redirect:/computers";
    }

    @GetMapping("/{computerID}")
    public String computerInfoPage(@PathVariable final int computerID, Model model) {
        model.addAttribute("computer", computerService.getById(computerID));
        return "computer_info";
    }

}
