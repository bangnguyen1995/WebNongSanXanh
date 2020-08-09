package edu.poly.spring.Dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PhuongThucThanhToanDto implements Serializable{
 private Integer maPT;
 @NotNull
 @NotEmpty
 private String tenPT;
 @NotEmpty
 @NotNull
 private String mota;
 
public Integer getMaPT() {
	return maPT;
}
public void setMaPT(Integer maPT) {
	this.maPT = maPT;
}
public String getTenPT() {
	return tenPT;
}
public void setTenPT(String tenPT) {
	this.tenPT = tenPT;
}
public String getMota() {
	return mota;
}
public void setMota(String mota) {
	this.mota = mota;
}
 
 
}
