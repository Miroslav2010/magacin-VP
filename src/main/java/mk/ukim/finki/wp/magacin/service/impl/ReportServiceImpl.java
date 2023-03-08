package mk.ukim.finki.wp.magacin.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.magacin.models.Report;
import mk.ukim.finki.wp.magacin.models.exceptions.InvalidItemIdException;
import mk.ukim.finki.wp.magacin.repository.ReportRepository;
import mk.ukim.finki.wp.magacin.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
  private final ReportRepository reportRepository;

  @Override
  public Report findById(Long id) {
    return this.reportRepository.findById(id).orElseThrow(InvalidItemIdException::new);
  }

  @Override
  public void generateReport(String itemName) {
    this.reportRepository.save(new Report(itemName));
  }

  @Override
  public void deleteReport(Long id) {
    Report report = this.findById(id);
    this.reportRepository.delete(report);
  }

  @Override
  public List<Report> getAllReports() {
    return this.reportRepository.findAll();
  }
}
