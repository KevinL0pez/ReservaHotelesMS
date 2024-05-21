package org.reservahoteles.service;

import org.reservahoteles.dto.ImageRoomResponseDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.RoomPhotoResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRoomPhotoService {
    List<RoomPhotoResponseDto> getPhotosByRoom(Long idRoom);
    ResponseDto<ImageRoomResponseDto> createRoomPhoto(Long idRoom, MultipartFile file);
}
