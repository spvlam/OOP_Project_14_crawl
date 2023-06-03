package di_tich_lich_su;
// crawl from http://ditichlichsu-vanhoahanoi.com/
// only in hanoi
public class ditichHaNoi {
    private String name;
    private String district;
    private String content;
    private String imagine;
    private String category;
    private String src;
    public ditichHaNoi(String nam, String distric , String contents, String img, String classify, String srcStr){
        this.name = nam;
        this.district = distric;
        this.category = classify;
        this.imagine = img;
        this.content = contents;
        this.src=srcStr;
    }
    public String getSrc(){
        return this.src;
    }
    public String getCategory(){
          return this.category;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String nameDetail){
        this.name = nameDetail;
    }

    public String getDistrict(){
        return this.district;
    }
    public void setDistrict(String locationDetail){
        this.district = locationDetail;
    }
    public String getContent(){
        return this.content ;
    }
    public void setContent(String ContentDetail){
       this.content = ContentDetail;
    }
    public String getImagine(){
        return this.imagine;
    }
    public void setImagine(String urlImagine){
        this.imagine = urlImagine;
    }
    
    public static void main(String[] args) {
      
    }
}
