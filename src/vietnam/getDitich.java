package vietnam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;



public class getDitich {
    private List<diTichTrenVietnam> array_of_ditich = new ArrayList<>();
    public List<diTichTrenVietnam> get_di_tich_array(){
        return this.array_of_ditich;
    }
    public void add_ditich_to_array(diTichTrenVietnam ditich){
         this.array_of_ditich.add(ditich);
    }
    public void getArticle(String fileName){
        String baseUrl = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam"; 
         try {
            Document document = Jsoup.connect(baseUrl).get();
            Elements h3_Provinces = document.select("h3");
            for (Element h3_provin:h3_Provinces){
                Element nextSibling = h3_provin.nextElementSibling();
                // use province for all below task
                Element province = h3_provin.selectFirst("span.mw-headline");
                String provin = province.text();
                String year;
                // get by table
                Elements nextSibling_body = nextSibling.select("tr");
                boolean isFirstElement = true;
                for (Element row :nextSibling_body ){
                   if(isFirstElement){
                    isFirstElement = false;
                   }else{
                    Element FirstTD = row.selectFirst("td");
                    Element name = row.selectFirst("a");
                    String nameDT;
                    String content = "";
                    if(name!=null){
                        String href_Content = name.attr( "href");
                        nameDT = name.text();
                        try {
                            String Real_url = "https://vi.wikipedia.org"+href_Content;
                            System.out.println(Real_url);
                            Document doc_Content = Jsoup.connect(Real_url).get();
                            Elements para = doc_Content.select("p");
                            // join content of tab p
                            int count= 0;
                            for (Element par: para){
                                content+=par.text()+".";
                                count++;
                                if(count==5){
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            content = "null";
                        }
                    }else{
                        content = "null";
                        nameDT="unknown";
                    }
                    Element vitri = FirstTD.nextElementSibling();
                    String vitriE = vitri.text();
                    Element type_Di = vitri.nextElementSibling();
                    String type_di = type_Di.text();
                    Element yea = type_Di.nextElementSibling();
                    if(yea!=null){
                        year =yea.text();
                    }else{
                        year ="unknown";
                    }
                    
                    diTichTrenVietnam ditich_Object = new diTichTrenVietnam(nameDT, provin, vitriE, content, type_di,baseUrl,year);
                    add_ditich_to_array(ditich_Object);
                }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // save to json file 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(array_of_ditich);
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write JSON string to file
            writer.write(jsonArray);
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        getDitich abc = new getDitich();
        abc.getArticle("dataDitichVietName.json");
    }
    
}
