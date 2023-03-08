package mk.ukim.finki.wp.magacin.service;

import mk.ukim.finki.wp.magacin.models.Report;

import java.util.List;

public interface ReportService {
  Report findById(Long id);

  void generateReport(String itemName);

  void deleteReport(Long id);

  List<Report> getAllReports();
}
