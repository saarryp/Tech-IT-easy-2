package nl.novi.techiteasyopnieuw.dto.television;

import nl.novi.techiteasyopnieuw.dto.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasyopnieuw.models.RemoteController;
import nl.novi.techiteasyopnieuw.models.Television;
import nl.novi.techiteasyopnieuw.models.enums.AvailableSize;
import nl.novi.techiteasyopnieuw.models.enums.RefreshRate;
import nl.novi.techiteasyopnieuw.models.enums.ScreenQuality;
import nl.novi.techiteasyopnieuw.models.enums.ScreenType;
import nl.novi.techiteasyopnieuw.services.TelevisionService;

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
    public RemoteController remoteController;

//    private RemoteControllerDto remoteControllerDto;
//
//    public RemoteControllerDto getRemoteControllerDto() {
//        return remoteControllerDto;
//    }
//
//    public void setRemoteControllerDto(RemoteControllerDto remoteControllerDto) {
//        this.remoteControllerDto = remoteControllerDto;
    }



    // Getter en setter voor remoteControllerDto

//    }

