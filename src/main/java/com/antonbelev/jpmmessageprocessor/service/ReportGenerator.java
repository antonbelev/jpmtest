package com.antonbelev.jpmmessageprocessor.service;

import com.antonbelev.jpmmessageprocessor.model.Adjustment;
import com.antonbelev.jpmmessageprocessor.model.Sale;

import java.util.List;

public interface ReportGenerator {
    String generateSalesReport(List<Sale> sales);
    String generateAdjustmentsReport(List<Adjustment> adjustments);
}
