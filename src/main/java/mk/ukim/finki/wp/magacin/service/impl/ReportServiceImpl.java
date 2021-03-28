package mk.ukim.finki.wp.magacin.service.impl;

import mk.ukim.finki.wp.magacin.models.Report;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.repository.ItemRepository;
import mk.ukim.finki.wp.magacin.repository.ReportRepository;
import mk.ukim.finki.wp.magacin.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final ItemRepository itemRepository;

    public ReportServiceImpl(ReportRepository reportRepository, ItemRepository itemRepository) {
        this.reportRepository = reportRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Report findById(Long id) {
        return this.reportRepository.findById(id).orElseThrow(InvalidItemIdException::new);
    }

    @Override
    public Report generateReport(String itemName) {
        return this.reportRepository.save(new Report(itemName));
    }

    @Override
    public Report deleteReport(Long id) {
        Report report = this.findById(id);
        this.reportRepository.delete(report);
        return report;
    }

    @Override
    public List<Report> getAllReports() {
        return this.reportRepository.findAll();
    }
}
