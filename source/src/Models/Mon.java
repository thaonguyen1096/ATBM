package Models;

import java.util.Vector;

public class Mon {

    private String maLop;
    private String tenLop;

    public Mon(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }
    
    public Mon(Vector<String> info){
    	maLop = info.get(0);
    	tenLop = info.get(1);
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    
    
}