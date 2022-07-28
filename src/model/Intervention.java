package model;

public class Intervention {
    private final int id;
    private final String name;
    private final String email;
    private final String address;
    private final String phone;
    private final String image;
    private final double longitude;
    private final double latitude;

    private String date = "";
    private String status = "";

    public Intervention(int id, String name, String email, String address, String phone, String image, double longitude, double latitude) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getImage() {
        return image;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public String getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setStatus(String status) {
        this.status = status;
    }





}
