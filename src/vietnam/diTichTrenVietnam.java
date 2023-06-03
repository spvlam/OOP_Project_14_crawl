package vietnam;

public class diTichTrenVietnam {
    private String name;
    private String district;
    private String province;
    private String content;
    private String category;
    // year of certification
    private String yearEs;
    private String src;
    public diTichTrenVietnam(String nam,String provi, String distric, String contents, String classify, String srcStr, String year){
        this.name = nam;
        this.province = provi;
        this.district = distric;
        this.category = classify;
        this.content = contents;
        this.src=srcStr;
        this.yearEs = year;
    }
    public String getProvince(){
        return this.province;
    }
    public String getYear(){
        return this.yearEs;
    }
    public void setYear(String year){
        this.yearEs = year;

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
}
