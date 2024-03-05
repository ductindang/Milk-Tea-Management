package DEV;

public class ChiTietPhieuNhap {

    String ma;
    String maSP;
    Integer soLuong;
    Float donGia;

    public ChiTietPhieuNhap() {

    }

    public ChiTietPhieuNhap(String ma, String maSP, Integer soLuong, Float donGia) {
        this.ma = ma;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMa() {
        return ma;
    }

    public String getMaSP() {
        return maSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

}
