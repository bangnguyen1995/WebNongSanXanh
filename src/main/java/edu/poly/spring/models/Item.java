package edu.poly.spring.models;

public class Item {
private int quantity;
private SanPham sanpham;
public Item(int quantity, SanPham sanpham) {
	super();
	this.quantity = quantity;
	this.sanpham = sanpham;
}
public Item() {
	super();
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public SanPham getSanpham() {
	return sanpham;
}
public void setSanpham(SanPham sanpham) {
	this.sanpham = sanpham;
}


}
