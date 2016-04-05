package userinfo;

public class UserInfo {
	String ErabiltzaileIzena;
	String Izena;
	String Abizena;
	String Email; 
	String Kalea;
	String Herria;
	String PostaKodea;
	String JaiotzeData;
	String Telefonoa;
	String LizentziaMota;
	
	public UserInfo(String ErabiltzaileIzena, String Izena, String Abizena, String Email, 
			String Kalea, String Herria, String PostaKodea, String JaiotzeData, String Telefonoa, String LizentziaMota){
		
		this.ErabiltzaileIzena = ErabiltzaileIzena;
		this.Izena = Izena;
		this.Abizena = Abizena;
		this.Email = Email;
		this.Kalea = Kalea;
		this.Herria = Herria;
		this.PostaKodea = PostaKodea;
		this.JaiotzeData = JaiotzeData;
		this.Telefonoa = Telefonoa;
		this.LizentziaMota = LizentziaMota;
	}

	public String getIzena() {
		return Izena;
	}

	public String getAbizena() {
		return Abizena;
	}
	
	public String getLizentziaMota() {
		return LizentziaMota;
	}

	public String getKalea() {
		return Kalea;
	}

	public String getHerria() {
		return Herria;
	}

	public String getPostaKodea() {
		return PostaKodea;
	}

	public String getJaiotzeData() {
		return JaiotzeData;
	}
	
	public String getEmail() {
		return Email;
	}

	public String getTelefonoa() {
		return Telefonoa;
	}
	
}
