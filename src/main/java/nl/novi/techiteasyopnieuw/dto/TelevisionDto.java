package nl.novi.techiteasyopnieuw.dto;

import nl.novi.techiteasyopnieuw.models.enums.AvailableSize;
import nl.novi.techiteasyopnieuw.models.enums.RefreshRate;
import nl.novi.techiteasyopnieuw.models.enums.ScreenQuality;
import nl.novi.techiteasyopnieuw.models.enums.ScreenType;

public class TelevisionDto {

    public Long id;
    public String type;
    public String brand;

    public String name;
    public Double price;
    public AvailableSize availableSize;
    public RefreshRate refreshRate;
    public ScreenType screenType;
    public ScreenQuality screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
}
