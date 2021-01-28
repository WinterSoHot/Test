package cn.dx.pp;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongxian
 * @version 0.1
 * @date 2020/12/25
 **/
@RestController
@RequestMapping("/")
public class PDFController {
    @Value("${DEST}")
    private String dest;
    @Value("${HTML}")
    private String html;
    @Value("${FONT}")
    private String font;
    private static Configuration freemarkerCfg = null;


    @RequestMapping(value = "helloPdf")
    public void showPdf(HttpServletResponse response) throws IOException, DocumentException {
        //需要填充的数据
        Map<String, Object> data = new HashMap<>(16);
        data.put("name", "kevin");

        String content = freeMarkerRender(data, html);
        //创建pdf
        createPdf(content, dest);

        // 读取pdf并预览
        readPdf(response);

    }

    public void createPdf(String content, String dest) throws IOException, DocumentException {

        File pdfFile = new File(dest);
        System.out.println(pdfFile.getAbsoluteFile());
        if (!pdfFile.exists()) {
            pdfFile.getParentFile().mkdirs();
            pdfFile.createNewFile();
        }
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(font);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), null, StandardCharsets.UTF_8, fontImp);

        // step 5
        document.close();

    }

    /**
     * freemarker渲染html
     */
    public String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();

        try {
            // 获取模板,并设置编码方式
            setFreemarkerCfg();
            Template template = freemarkerCfg.getTemplate(htmlTmp, "UTF-8");
            //将合并后的数据和模板写入到流中，这里使用的字符流
            template.process(data, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 设置freemarkerCfg
     */
    private void setFreemarkerCfg() {
        Version incompatibleImprovements;
        freemarkerCfg = new Configuration(Configuration.VERSION_2_3_0);
        //freemarker的模板目录
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new ClassPathResource("templates").getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取本地pdf,这里设置的是预览
     */
    private void readPdf(HttpServletResponse response) {
        response.reset();
        response.setContentType("application/pdf");
        try {
            File file = new File(dest);
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
            response.setHeader("Content-Disposition",
                    "inline; filename= file");
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
