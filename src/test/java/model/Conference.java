package model;

public class Conference {
    private Integer id;
    private String name;
    private Location location;
    private Sponsor[] sponsors;
    private String[] reports;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Sponsor[] getSponsors() {
        return sponsors;
    }

    public void setSponsors(Sponsor[] sponsors) {
        this.sponsors = sponsors;
    }

    public String[] getReports() {
        return reports;
    }

    public void setReports(String[] reports) {
        this.reports = reports;
    }
}

