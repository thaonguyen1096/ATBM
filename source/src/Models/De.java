package Models;

import java.util.Vector;

public class De{
	protected String maDe;
	protected String moTa;
	
	public String getMaDe() {
		return maDe;
	}
	public void setMaDe(String maDe) {
		this.maDe = maDe;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	public De(){
		
	}
	
	public De(Vector<String> info){
		maDe = info.get(0);
		moTa = info.get(1);
	}
	
	public De(String maDe, String moTa) {
        this.maDe = maDe;
        this.moTa = moTa;
    }
	
	
	@Override
    public String toString()  {
        return this.maDe;
    }
}
