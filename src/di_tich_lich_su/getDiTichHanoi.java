package di_tich_lich_su;

// import di_tich_lich_su.ditichHaNoi ;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Entities;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class getDiTichHanoi {

    public ditichHaNoi getInformationDetail(String urlArticle) {
        // get in field for each object like, name, district, contents, img-link
        try {
            Document document = Jsoup.connect(urlArticle).get();
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
            document.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
            document.outputSettings().indentAmount(4);
            // System.out.print(document.html());
            Element title = document.selectFirst("h1.entry-title");
            Elements para = document.select("p");
            String imgD;
            try {
                Element img = document.selectFirst("img.alignnone");
                imgD = img.attr("src");
            } catch (Exception E) {
                imgD = "null";
            }
            String titleDetail = title.text();
            String classify = title.text().split(" ", 2)[0];
            String diaDiem;
            try {
                int str = titleDetail.indexOf("(");
                int fi = titleDetail.indexOf(")");
                diaDiem = titleDetail.substring(str + 1, fi);
            } catch (Exception E) {
                diaDiem = "Hà Nội";
            }
            ditichHaNoi dt = new ditichHaNoi(titleDetail, diaDiem, para.text(), imgD, classify, urlArticle);
            return dt;
        } catch (Exception e) {
            e.printStackTrace();
            ditichHaNoi dt = new ditichHaNoi(null, null, null, null, null, null);
            return dt;
        }
    }

    public void getArticle() {
        String base_url = "http://ditichlichsu-vanhoahanoi.com/";
        String filePath = "data2.json";
        List<ditichHaNoi> array_of_ditich = new ArrayList<>();
        for (int i = 1; i < 52; i++) {
            String url = base_url + "page/" + i + '/';
            try {
                Document document = Jsoup.connect(url).get();
                Elements links = document.select("a:contains(Continue reading)");
                for (Element link : links) {
                    String href = link.attr("href");
                    array_of_ditich.add(getInformationDetail(href));
                    // List<ditichHaNoi> arrayOf = new ArrayList<>();
                    System.out.println(href);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // save to json 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(array_of_ditich);
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write JSON string to file
            writer.write(jsonArray);
            System.out.println("Data saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// running for crawl
    public static void main(String[] args) {
        getDiTichHanoi a = new getDiTichHanoi();
        a.getArticle();
    }

}
