package ltd.ontsol.web.controller;

import ltd.ontsol.core.dto.AddressDTO;
import ltd.ontsol.core.dto.AgrrNodeDTO;
import ltd.ontsol.core.service.AgrrNodeService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/agrr")
public class ExcelController {
    @Inject
    AgrrNodeService agrrNodeService;
        @GetMapping(value = "/export")
        @ResponseBody
        public void export(HttpServletResponse response) throws IOException {
          //  List<User> users = userService.selectUsers();

            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFSheet sheet = wb.createSheet("获取excel测试表格");

            HSSFRow row = null;

            row = sheet.createRow(0);//创建第一个单元格
            row.setHeight((short) (26.25 * 20));
            row.createCell(0).setCellValue("用户信息列表");//为第一行单元格设值

            /*为标题设计空间
             * firstRow从第1行开始
             * lastRow从第0行结束
             *
             *从第1个单元格开始
             * 从第3个单元格结束
             */
            CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
            sheet.addMergedRegion(rowRegion);

      /*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
      sheet.addMergedRegion(columnRegion);*/


            /*
             * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
             * 第一个table_name 表名字
             * 第二个table_name 数据库名称
             * */
            List<AgrrNodeDTO> dto = agrrNodeService.findAll();
            row = sheet.createRow(1);
            row.setHeight((short) (22.50 * 20));
            row.createCell(0).setCellValue("公司名称");
            row.createCell(1).setCellValue("联系人姓名");
            row.createCell(2).setCellValue("联系人电话");
            row.createCell(3).setCellValue("电子邮箱");
            row.createCell(4).setCellValue("微信");
            row.createCell(5).setCellValue("服务商类型");
            row.createCell(6).setCellValue("所在省市");
            row.createCell(7).setCellValue("详细地址");
            row.createCell(8).setCellValue("门店面积");
            row.createCell(9).setCellValue("门店仓库面积");
            row.createCell(10).setCellValue("主营业务");
            row.createCell(11).setCellValue("安装工位");
            row.createCell(12).setCellValue("技师数量");
            row.createCell(13).setCellValue("工具车数量");
            row.createCell(14).setCellValue("向终端车主销售加价率");
            row.createCell(15).setCellValue("采购金额");
            row.createCell(16).setCellValue("采购片数");
            row.createCell(17).setCellValue("库存片数");
            row.createCell(18).setCellValue("库存占比");
            row.createCell(19).setCellValue("销售金额");
            row.createCell(20).setCellValue("上级经销商");
            row.createCell(21).setCellValue("上级经销商系统编码");
            row.createCell(22).setCellValue("法人代表");
            row.createCell(23).setCellValue("经营年限");
            row.createCell(24).setCellValue("采购负责人");
            row.createCell(25).setCellValue("采购负责人电话");
            row.createCell(26).setCellValue("门店性质(自有/租赁）");
            row.createCell(27).setCellValue("房租/年/元");
            row.createCell(28).setCellValue("目前主营业务");
            row.createCell(29).setCellValue("销售品牌");
            row.createCell(30).setCellValue("销售辐射区域");


            for (int i = 0; i < dto.size(); i++) {
                row = sheet.createRow(i + 2);
                AgrrNodeDTO agrrNode = dto.get(i);
                Long agrrId = agrrNode.getId();
               // List<AddressDTO> addressDTOList = Address
                row.createCell(0).setCellValue(agrrNode.getCompanyName());
                row.createCell(1).setCellValue(agrrNode.getName());
                row.createCell(2).setCellValue(agrrNode.getTel());
                row.createCell(3).setCellValue(agrrNode.getEmail());
                row.createCell(4).setCellValue(agrrNode.getWx());
                row.createCell(5).setCellValue(agrrNode.getVerifyType());

                List<AddressDTO> list = new ArrayList(agrrNode.getAddrs());
                row.createCell(6).setCellValue(list.get(0).getProvince()+list.get(0).getCity());
                row.createCell(7).setCellValue(list.get(0).getStreet());
                row.createCell(8).setCellValue(agrrNode.getShopSize());
                row.createCell(9).setCellValue(agrrNode.getDepotSize());
                row.createCell(10).setCellValue(agrrNode.getVerifyType());
                row.createCell(11).setCellValue(agrrNode.getSlotCount());
                row.createCell(12).setCellValue(agrrNode.getTechCount());
                row.createCell(13).setCellValue(agrrNode.getTranCount());
                row.createCell(14).setCellValue(agrrNode.getMarkupRate());
                row.createCell(15).setCellValue(agrrNode.getPurchase());
                row.createCell(16).setCellValue(agrrNode.getPurchaseNumber());
                row.createCell(17).setCellValue(agrrNode.getStock());
                row.createCell(18).setCellValue(agrrNode.getStockRate());
                row.createCell(19).setCellValue(agrrNode.getSales());
                row.createCell(20).setCellValue(agrrNode.getDistributor());
                row.createCell(21).setCellValue(agrrNode.getDistributorCode());
                row.createCell(22).setCellValue(agrrNode.getRepresentative());
                row.createCell(23).setCellValue(agrrNode.getYears());
                row.createCell(24).setCellValue(agrrNode.getPurchaser());
                row.createCell(25).setCellValue(agrrNode.getPurchaserNumber());
                row.createCell(26).setCellValue(agrrNode.getStoreproperty());
                row.createCell(27).setCellValue(agrrNode.getRent());
                row.createCell(28).setCellValue(agrrNode.getServiceType());
                row.createCell(29).setCellValue(agrrNode.getSalesbrand());
                row.createCell(30).setCellValue(agrrNode.getSalesarea());

                System.out.println("-------->"+list.get(0).getProvince());
                //row.createCell(0).setCellValue(agrrNode.getEmail());

            }

            sheet.setDefaultRowHeight((short) (16.5 * 20));
            //列宽自适应
            for (int i = 0; i <= 13; i++) {
                sheet.autoSizeColumn(i);
            }

            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            OutputStream os = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment;filename=Data.xls");
            wb.write(os);
            os.flush();
            os.close();
        }

}
