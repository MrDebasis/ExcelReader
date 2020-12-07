package com.reader.serviceImpl;

import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.reader.model.ArnatiSteelLimited;
import com.reader.repository.ExcelRepository;
import com.reader.service.ExcelReaderService;

@Service
@Transactional
public class ExcelReaderServiceImpl implements ExcelReaderService {

	@Autowired
	private ExcelRepository excelRepository;

	@Override
	public List<ArnatiSteelLimited> findAll() {
		return (List<ArnatiSteelLimited>) excelRepository.findAll();
	}

	public boolean saveDataFromUploadFile(MultipartFile file) {
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			isFlag = readDataFromExcel(file);

		}
		return isFlag;
	}

	private boolean readDataFromExcel(MultipartFile file) {
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		while (rows.hasNext()) {
			Row row = rows.next();
			ArnatiSteelLimited arnatiSteelLimited = new ArnatiSteelLimited();
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
				arnatiSteelLimited.setDescriptionOfItem(row.getCell(0).getStringCellValue());
			}
			if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
				arnatiSteelLimited.setQnty(row.getCell(1).getStringCellValue());
			}
			excelRepository.save(arnatiSteelLimited);
		}
		return true;
	}

	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			if (extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else if (extension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workbook;
	}

}
