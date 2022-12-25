package com.example.demo.controller;

import com.example.demo.model.dto.ReportDTO;
import com.example.demo.model.dto.WarehouseDTO;
import com.example.demo.model.entity.DateDim;
import com.example.demo.model.entity.Warehouse;
import com.example.demo.model.mapper.WarehouseMapper;
import com.example.demo.service.DateDimService;
import com.example.demo.service.WarehouseService;
import com.example.demo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private DateDimService dateDimService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private DateUtils dateUtils;

    @GetMapping(value = {"/", "", "/trang-chu.html"})
    public String homePage(Model model, HttpServletRequest request, HttpServletResponse response) {

        Date date = new Date();
        String dateStr = dateUtils.convertDateToString(date, "yyyy-MM-dd");
        DateDim dateDim = dateDimService.getDateDimByFullDate(dateStr);
        DateDim dateDimBefore = dateDimService.findById(dateDim.getDateSk() - 1);

        List<WarehouseDTO> listNewMienBac = warehouseMapper.toListDTO(warehouseService.listNew(dateDim.getDateSk(), "Mien Bac"));
        List<WarehouseDTO> listNewMienTrung = warehouseMapper.toListDTO(warehouseService.listNew(dateDim.getDateSk(), "Mien Trung"));
        List<WarehouseDTO> listNewMienNam = warehouseMapper.toListDTO(warehouseService.listNew(dateDim.getDateSk(), "Mien Nam"));

        List<ReportDTO> list = new ArrayList<>();
        list.add(new ReportDTO(listNewMienTrung, dateDim.getDayOfWeek(), dateStr, "Mien Trung", 100.0 / listNewMienTrung.size() + "%"));
        list.add(new ReportDTO(listNewMienNam, dateDim.getDayOfWeek(), dateStr, "Mien Nam", 100.0 / listNewMienNam.size() + "%"));

        List<ReportDTO> listMienBac = new ArrayList<>();
        listMienBac.add(new ReportDTO(listNewMienBac, dateDim.getDayOfWeek(), dateStr, "Mien Bac", 100.0 / listNewMienBac.size() + "%"));

        List<WarehouseDTO> listBeforeMienBac = warehouseMapper.toListDTO(warehouseService.listNew(dateDimBefore.getDateSk() , "Mien Bac"));
        List<WarehouseDTO> listBeforeMienTrung = warehouseMapper.toListDTO(warehouseService.listNew(dateDimBefore.getDateSk() , "Mien Trung"));
        List<WarehouseDTO> listBeforeMienNam = warehouseMapper.toListDTO(warehouseService.listNew(dateDimBefore.getDateSk(), "Mien Nam"));

        List<ReportDTO> listBefore = new ArrayList<>();
        listBefore.add(new ReportDTO(listBeforeMienTrung, dateDimBefore.getDayOfWeek(), dateDimBefore.getFullDate(), "Mien Trung", 100.0 / listNewMienTrung.size() + "%"));
        listBefore.add(new ReportDTO(listBeforeMienNam, dateDimBefore.getDayOfWeek(), dateDimBefore.getFullDate(), "Mien Nam", 100.0 / listNewMienNam.size() + "%"));

        List<ReportDTO> listBeforeMB = new ArrayList<>();
        listBeforeMB.add(new ReportDTO(listBeforeMienBac, dateDimBefore.getDayOfWeek(), dateDimBefore.getFullDate(), "Mien Bac", 100.0 / listNewMienBac.size() + "%"));

        model.addAttribute("list", list);
        model.addAttribute("listMienBac", listMienBac);

        model.addAttribute("listBefore", listBefore);
        model.addAttribute("listBeforeMB", listBeforeMB);

        model.addAttribute("date", dateDim.getFullDate());
        model.addAttribute("dateBf", dateDimBefore.getFullDate());
        return "home";
    }


}
