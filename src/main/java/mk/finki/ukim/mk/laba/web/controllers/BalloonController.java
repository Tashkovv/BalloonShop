package mk.finki.ukim.mk.laba.web.controllers;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import mk.finki.ukim.mk.laba.service.BalloonService;
import mk.finki.ukim.mk.laba.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }


    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Balloon> balloons = this.balloonService.listAll();
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);

        model.addAttribute("edit", false);

        model.addAttribute("type1", TYPE.HEART);
        model.addAttribute("type2", TYPE.OVAL);
        model.addAttribute("type3", TYPE.SQUARE);
        model.addAttribute("type4", TYPE.TRIANGLE);
        return "add-balloon";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        if (this.balloonService.findById(id).isPresent()) {
            Balloon balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);

            model.addAttribute("edit", true);
            model.addAttribute("oldName", balloon.getName());

            model.addAttribute("type1", TYPE.HEART);
            model.addAttribute("type2", TYPE.OVAL);
            model.addAttribute("type3", TYPE.SQUARE);
            model.addAttribute("type4", TYPE.TRIANGLE);
            return "add-balloon";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam(name = "oldName", required = false) String oldName,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("manufacturer") Long manufacturer,
                              @RequestParam("type") TYPE type) {
        this.balloonService.SaveOrUpdate(name, description, manufacturer, type, oldName);
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }
}

