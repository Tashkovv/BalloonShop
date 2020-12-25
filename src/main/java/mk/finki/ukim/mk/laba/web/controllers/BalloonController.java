package mk.finki.ukim.mk.laba.web.controllers;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import mk.finki.ukim.mk.laba.model.exceptions.BalloonAlreadyExistsException;
import mk.finki.ukim.mk.laba.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.laba.service.BalloonService;
import mk.finki.ukim.mk.laba.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/balloons"})
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
        model.addAttribute("bodyContent", "listBalloons");
        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);

        model.addAttribute("type1", TYPE.HEART);
        model.addAttribute("type2", TYPE.OVAL);
        model.addAttribute("type3", TYPE.SQUARE);
        model.addAttribute("type4", TYPE.TRIANGLE);
        model.addAttribute("bodyContent", "add-balloon");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {
        try {
            if (this.balloonService.findById(id).isPresent()) {
                Balloon balloon = this.balloonService.findById(id).get();
                List<Manufacturer> manufacturers = this.manufacturerService.findAll();
                model.addAttribute("manufacturers", manufacturers);
                model.addAttribute("balloon", balloon);

                model.addAttribute("type1", TYPE.HEART);
                model.addAttribute("type2", TYPE.OVAL);
                model.addAttribute("type3", TYPE.SQUARE);
                model.addAttribute("type4", TYPE.TRIANGLE);
                model.addAttribute("bodyContent", "add-balloon");
            }
        } catch (RuntimeException exception) {
            return "redirect:/balloons?error=" + exception.getMessage();
        }
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("manufacturer") Long manufacturer,
                              @RequestParam("type") TYPE type,
                              @RequestParam(required = false) Long id) {
        try {
            if (id != null) {
                balloonService.edit(id, name, description, manufacturer, type);
            } else {
                balloonService.SaveOrUpdate(name, description, manufacturer, type);
            }
            return "redirect:/balloons";
        } catch (ManufacturerNotFoundException | BalloonAlreadyExistsException exception) {
            return "redirect:/balloons?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBalloon(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }
}

