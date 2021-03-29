package mk.ukim.finki.wp.magacin.web;

import mk.ukim.finki.wp.magacin.service.ItemService;
import mk.ukim.finki.wp.magacin.service.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ReportController {
    private final ReportService reportService;
    private final ItemService itemService;

    public ReportController(ReportService reportService, ItemService itemService) {
        this.reportService = reportService;
        this.itemService = itemService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getReports(Model model){
        model.addAttribute("reports", this.reportService.getAllReports());
        model.addAttribute("bodyContent", "reports");
        return "master-template";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteReport(@PathVariable Long id){
        this.reportService.deleteReport(id);
        return "redirect:/reports";
    }
}
